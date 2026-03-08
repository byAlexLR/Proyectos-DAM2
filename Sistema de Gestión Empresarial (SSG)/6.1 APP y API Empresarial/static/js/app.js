// byAlexLR

// Aquí gestiono toda la lógica del cliente (Frontend).
// Controlo la navegación, las llamadas a la API (fetch) y la actualización del DOM.

document.addEventListener("DOMContentLoaded", () => {
    // Al cargar la página, muestro la sección de login por defecto
    showSection('login-section');
});

function showSection(sectionId) {
    // Oculto todas las secciones de <main> y muestro solo la solicitada
    document.querySelectorAll('main > section').forEach(s => s.style.display = 'none');
    document.getElementById(sectionId).style.display = 'block';
}

function logout() {
    // Cierro sesión limpiando el almacenamiento local y redirigiendo al login
    localStorage.removeItem('user');
    showSection('login-section');
    updateNav(false);
}

function updateNav(isLoggedIn) {
    // Actualizo la barra de navegación según si el usuario está logueado o no
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    document.getElementById('nav-login').style.display = isLoggedIn ? 'none' : 'inline-block';
    document.getElementById('nav-register').style.display = isLoggedIn ? 'none' : 'inline-block';
    document.getElementById('nav-events').style.display = isLoggedIn ? 'inline-block' : 'none';
    document.getElementById('nav-bookings').style.display = isLoggedIn ? 'inline-block' : 'none';
    document.getElementById('nav-stats').style.display = isLoggedIn ? 'inline-block' : 'none';
    document.getElementById('nav-logout').style.display = isLoggedIn ? 'inline-block' : 'none';
}

// --- GESTIÓN DE USUARIOS ---

// Login
document.getElementById('login-form').addEventListener('submit', async (e) => {
    // Manejo el envío del formulario de inicio de sesión
    e.preventDefault();
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    const res = await fetch('/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });

    if (res.ok) {
        // Si el login es correcto, guardo el usuario y cargo el catálogo
        const data = await res.json();
        localStorage.setItem('user', JSON.stringify({ username: data.username }));
        updateNav(true);
        showSection('events-section');
        loadEvents();
    } else {
        document.getElementById('login-message').innerText = "Error: Credenciales inválidas";
    }
});

// Register
document.getElementById('register-form').addEventListener('submit', async (e) => {
    // Manejo el registro de nuevos usuarios
    e.preventDefault();
    const username = document.getElementById('reg-username').value;
    const email = document.getElementById('reg-email').value;
    const password = document.getElementById('reg-password').value;

    const res = await fetch('/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, email, password })
    });

    if (res.ok) {
        document.getElementById('reg-message').innerText = "Registro exitoso. Ahora inicia sesión.";
        setTimeout(() => showSection('login-section'), 2000);
    } else {
        document.getElementById('reg-message').innerText = "Error al registrarse.";
    }
});

// --- GESTIÓN DE EVENTOS Y RESERVAS ---
// Load Events
async function loadEvents() {
    // Obtengo el catálogo de eventos desde la API, aplicando filtros si existen
    const category = document.getElementById('filter-category').value;
    const min = document.getElementById('filter-min').value;
    const max = document.getElementById('filter-max').value;
    
    // Construyo la URL con los parámetros de filtrado si existen
    let url = '/events?';
    if (category) url += `category=${category}&`;
    if (min) url += `min_price=${min}&`;
    if (max) url += `max_price=${max}`;

    const res = await fetch(url);
    const events = await res.json();
    
    // Renderizo las tarjetas de eventos en el grid
    const container = document.getElementById('events-list');
    container.innerHTML = events.map(e => `
        <div class="card">
            <h3>${e.title}</h3>
            <p><i class="fa-solid fa-tag"></i> ${e.category}</p>
            <p><i class="fa-solid fa-euro-sign"></i> ${e.price}</p>
            <div class="input-group" style="margin-bottom: 10px;">
                <input type="number" id="qty-${e.id}" value="1" min="1">
            </div>
            <button onclick="bookEvent(${e.id})" class="btn-primary">Reservar</button>
        </div>
    `).join('');
}

// Book Event
async function bookEvent(eventId) {
    // Envío una solicitud de reserva para el evento seleccionado
    // Obtengo el usuario actual y la cantidad seleccionada del DOM
    const user = JSON.parse(localStorage.getItem('user'));
    const qty = document.getElementById(`qty-${eventId}`).value;
    
    const res = await fetch('/bookings', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            username: user.username,
            lines: [{ event_id: eventId, quantity: parseInt(qty) }]
        })
    });

    if (res.ok) {
        alert("Reserva creada con éxito!");
    } else {
        alert("Error al reservar.");
    }
}

// Load Bookings
async function loadBookings() {
    // Consulto y muestro el historial de reservas del usuario actual
    const user = JSON.parse(localStorage.getItem('user'));
    const res = await fetch(`/bookings?username=${user.username}`);
    const bookings = await res.json();
    
    const container = document.getElementById('bookings-list');
    if (bookings.length === 0) {
        container.innerHTML = "<p>No tienes reservas.</p>";
        return;
    }
    
    // Renderizo cada reserva con opciones para cancelar o eliminar
    container.innerHTML = bookings.map(b => `
        <div class="booking-item">
            <div class="booking-info">
                <strong><i class="fa-solid fa-bookmark"></i> Reserva #${b.id}</strong>
                <div style="font-size: 0.9em; color: var(--text-muted); margin: 5px 0 10px 0;">
                    <i class="fa-solid fa-list-check"></i> ${b.items || 'Sin detalles'}
                </div>
                <div>
                    <span class="status ${b.status.toLowerCase()}">${b.status}</span> - ${b.total} €
                </div>
            </div>
            <div class="booking-actions">
                <button onclick="updateStatus(${b.id}, 'CANCELADA')" class="btn-cancel"><i class="fa-solid fa-ban"></i></button>
                <button onclick="deleteBooking(${b.id})" class="btn-delete"><i class="fa-solid fa-trash"></i></button>
            </div>
        </div>
    `).join('');
}

async function updateStatus(id, status) {
    // Actualizo el estado de una reserva (ej. CANCELADA)
    await fetch(`/bookings/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status })
    });
    loadBookings();
}

async function deleteBooking(id) {
    // Elimino permanentemente una reserva
    if(!confirm("¿Seguro que quieres eliminar esta reserva?")) return;
    await fetch(`/bookings/${id}`, { method: 'DELETE' });
    loadBookings();
}

// --- ESTADÍSTICAS ---
// Load Stats
async function loadStats() {
    // Obtengo y muestro los KPIs del negocio
    const res = await fetch('/stats');
    const stats = await res.json();
    const container = document.getElementById('stats-content');
    // Itero sobre las claves y valores del objeto de estadísticas para generar el HTML dinámicamente
    container.innerHTML = Object.entries(stats).map(([k, v]) => `
        <div class="stat-card">
            <span class="stat-number">${v}</span>
            <span class="stat-label">${k}</span>
        </div>
    `).join('');
}
