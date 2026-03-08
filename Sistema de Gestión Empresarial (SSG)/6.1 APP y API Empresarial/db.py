# byAlexLR

# En este archivo gestiono toda la conexión con la base de datos SQLite para Pedritos House S.L.
# Las tablas están diseñadas para almacenar información sobre eventos, usuarios y reservas.

import sqlite3
from contextlib import contextmanager

# Defino la ruta de mi archivo de base de datos.
DB_PATH = "pedritos_house.db"

@contextmanager
def get_conn():
    # Abro la conexión a la base de datos de forma segura
    conn = sqlite3.connect(DB_PATH)
    # Configuro la conexión para acceder a las columnas por su nombre
    conn.row_factory = sqlite3.Row 
    try:
        # Devuelvo la conexión para usarla en mis consultas
        yield conn
        # Guardo los cambios automáticamente
        conn.commit() 
    finally:
        # Me aseguro de cerrar siempre la conexión
        conn.close()

def init_db():
    # Creo las tablas necesarias si no existen al arrancar la app
    with get_conn() as conn:
        # Tabla del catálogo (eventos en mi caso)
        conn.execute("""
            CREATE TABLE IF NOT EXISTS events (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                category TEXT NOT NULL,
                price REAL NOT NULL
            );
        """)
        # Tabla de usuarios (Mi módulo original para el Login/Registro)
        conn.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                email TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL
            );
        """)
        # Tabla de operaciones principales (Reservas)
        conn.execute("""
            CREATE TABLE IF NOT EXISTS bookings (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL,
                status TEXT NOT NULL DEFAULT 'CONFIRMADA',
                total REAL NOT NULL DEFAULT 0
            );
        """)
        # Tabla intermedia para guardar cuántas entradas se compran por evento
        conn.execute("""
            CREATE TABLE IF NOT EXISTS booking_lines (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                booking_id INTEGER NOT NULL,
                event_id INTEGER NOT NULL,
                quantity INTEGER NOT NULL,
                FOREIGN KEY(booking_id) REFERENCES bookings(id),
                FOREIGN KEY(event_id) REFERENCES events(id)
            );
        """)

def seed_data():
    # Añado datos de prueba iniciales para el catálogo de eventos
    with get_conn() as conn:
        # Compruebo si ya hay datos para no duplicarlos
        count = conn.execute("SELECT COUNT(*) as c FROM events").fetchone()["c"]
        if count > 0:
            return 
            
        # Inserto eventos de divulgación si la base de datos está vacía
        conn.executemany(
            "INSERT INTO events(title, category, price) VALUES(?,?,?)",
            [
                ("El origen del Universo", "Astronomía", 15.00),
                ("Mitos de la Nutrición", "Salud", 10.00),
                ("IA para todos", "Tecnología", 0.00)
            ],
        )

# --- MÓDULO DE USUARIOS (Conecta con mi interfaz) ---
def db_create_user(username, email, password):
    # Inserto un nuevo usuario desde la pantalla "Registrarse"
    with get_conn() as conn:
        try:
            conn.execute("INSERT INTO users(username, email, password) VALUES(?,?,?)", (username, email, password))
            return True
        except sqlite3.IntegrityError:
            return False

def db_check_user(username, password):
    # Valido las credenciales desde la pantalla "Iniciar Sesión"
    with get_conn() as conn:
        user = conn.execute("SELECT * FROM users WHERE username = ? AND password = ?", (username, password)).fetchone()
        return dict(user) if user else None

# --- MÓDULO CORE DEL PROYECTO ---
def db_list_events(category=None, min_price=None, max_price=None):
    # Devuelvo todo el catálogo de eventos con filtros opcionales
    sql = "SELECT id, title, category, price FROM events WHERE 1=1"
    params = []
    
    if category:
        sql += " AND category = ?"
        params.append(category)
    if min_price is not None:
        sql += " AND price >= ?"
        params.append(min_price)
    if max_price is not None:
        sql += " AND price <= ?"
        params.append(max_price)
        
    sql += " ORDER BY id DESC"

    with get_conn() as conn:
        rows = conn.execute(sql, params).fetchall()
        return [dict(r) for r in rows]

def db_create_booking(username, lines):
    # Creo una operación de reserva calculando el precio total
    with get_conn() as conn:
        # Creo la reserva vacía
        cur = conn.execute("INSERT INTO bookings(username, status, total) VALUES(?,?,?)", (username, "CONFIRMADA", 0.0))
        booking_id = cur.lastrowid
        total = 0.0
        
        # Inserto cada línea de la reserva y sumo los precios
        for line in lines:
            event = conn.execute("SELECT price FROM events WHERE id=?", (line.event_id,)).fetchone()
            if not event:
                raise ValueError(f"El evento {line.event_id} no existe")
            
            price = float(event["price"])
            total += price * line.quantity
            
            # Guardo la línea en la base de datos
            conn.execute("INSERT INTO booking_lines(booking_id, event_id, quantity) VALUES(?,?,?)", (booking_id, line.event_id, line.quantity))
            
        # Actualizo la reserva con el precio total real
        conn.execute("UPDATE bookings SET total = ? WHERE id = ?", (total, booking_id))
        
        # Devuelvo los datos de la reserva creada
        row = conn.execute("SELECT * FROM bookings WHERE id = ?", (booking_id,)).fetchone()
        return dict(row)

def db_list_bookings(username=None):
    # Consulto las operaciones realizadas con detalles de los eventos
    # Usando subconsultas o GROUP_CONCAT. SQLite soporta GROUP_CONCAT.
    # Nota: GROUP_CONCAT puede devolver NULL si no hay líneas, por eso el LEFT JOIN.
    
    base_sql = """
        SELECT b.id, b.username, b.status, b.total, 
               GROUP_CONCAT(e.title || ' (x' || bl.quantity || ')', ', ') as items
        FROM bookings b
        LEFT JOIN booking_lines bl ON b.id = bl.booking_id
        LEFT JOIN events e ON bl.event_id = e.id
    """
    
    where_clauses = []
    params = []
    
    if username:
        where_clauses.append("b.username = ?")
        params.append(username)
        
    if where_clauses:
        base_sql += " WHERE " + " AND ".join(where_clauses)
        
    base_sql += " GROUP BY b.id ORDER BY b.id DESC"
        
    with get_conn() as conn:
        rows = conn.execute(base_sql, params).fetchall()
        # Convert to list of dicts and handle None items
        results = []
        for r in rows:
            d = dict(r)
            if d['items'] is None:
                d['items'] = "Sin detalles"
            results.append(d)
        return results

def db_stats_by_status():
    # Devuelvo un resumen estadístico de la actividad de la empresa
    with get_conn() as conn:
        rows = conn.execute("SELECT status, COUNT(*) as count FROM bookings GROUP BY status").fetchall()
        return {r["status"]: r["count"] for r in rows}

def db_update_booking_status(booking_id, new_status):
    # Actualizo el estado de una reserva
    with get_conn() as conn:
        cursor = conn.execute("UPDATE bookings SET status = ? WHERE id = ?", (new_status, booking_id))
        return cursor.rowcount > 0

def db_delete_booking(booking_id):
    # Elimino una reserva y sus líneas
    with get_conn() as conn:
        # Primero elimino las líneas asociadas
        conn.execute("DELETE FROM booking_lines WHERE booking_id = ?", (booking_id,))
        # Luego elimino la reserva
        cursor = conn.execute("DELETE FROM bookings WHERE id = ?", (booking_id,))
        return cursor.rowcount > 0
