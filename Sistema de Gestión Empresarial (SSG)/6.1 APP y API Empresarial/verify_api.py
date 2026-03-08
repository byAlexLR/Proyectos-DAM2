# byAlexLR

# Este script ejecuta una batería de pruebas integrales contra la API de Pedritos House.
# Simulo un flujo completo de usuario: registro, login, consulta, reserva y gestión.

import requests
import uuid

# --- CONFIGURACIÓN ---
BASE_URL = "http://127.0.0.1:8000"
TEST_USER = f"user_{uuid.uuid4().hex[:8]}"
TEST_EMAIL = f"{TEST_USER}@example.com"
TEST_PASS = "password123"

# --- UTILIDADES ---
def log(msg, success=True):
    # Imprimo mensajes de estado con formato simple
    status = "Success:" if success else "Error:"
    print(f"{status} {msg}")

# --- PRUEBAS ---
def test_api():    
    # 1. Registro
    # Intento registrar un nuevo usuario en el sistema
    res = requests.post(f"{BASE_URL}/auth/register", json={
        "username": TEST_USER,
        "email": TEST_EMAIL,
        "password": TEST_PASS
    })
    if res.status_code == 201:
        log("Usuario registrado correctamente")
    else:
        log(f"Fallo al registrar: {res.text}", False)
        return

    # 2. Login
    # Verifico que el usuario recién creado puede iniciar sesión
    res = requests.post(f"{BASE_URL}/auth/login", json={
        "username": TEST_USER,
        "password": TEST_PASS
    })
    if res.status_code == 200:
        log("Login exitoso")
    else:
        log(f"Fallo al loguear: {res.text}", False)
        return

    # 3. Catálogo de Eventos
    # Consulto la lista de eventos disponibles
    res = requests.get(f"{BASE_URL}/events")
    events = res.json()
    if res.status_code == 200 and isinstance(events, list) and len(events) > 0:
        log(f"Se obtuvieron {len(events)} eventos")
        event_id = events[0]['id']
        print(f"Evento seleccionado para prueba: {events[0]['title']} (ID: {event_id})")
    else:
        log("Fallo al obtener eventos o lista vacía", False)
        return

    # 3.1 Filtros
    # Pruebo el filtrado por categoría usando el primer evento como referencia
    category = events[0]['category']
    res = requests.get(f"{BASE_URL}/events", params={"category": category})
    filtered = res.json()
    if res.status_code == 200 and all(e['category'] == category for e in filtered):
        log(f"Filtro por categoría '{category}' funciona correctamente")
    else:
        log("Fallo en filtro de categoría", False)

    # 4. Crear Reserva
    # Simulo la compra de entradas para el evento seleccionado
    res = requests.post(f"{BASE_URL}/bookings", json={
        "username": TEST_USER,
        "lines": [{"event_id": event_id, "quantity": 2}]
    })
    if res.status_code == 201:
        booking = res.json()
        booking_id = booking['id']
        log(f"Reserva creada con ID: {booking_id}. Total: {booking['total']}€")
    else:
        log(f"Fallo al crear reserva: {res.text}", False)
        return

    # 5. Listar Reservas
    # Compruebo que la reserva aparece en el historial del usuario
    res = requests.get(f"{BASE_URL}/bookings", params={"username": TEST_USER})
    my_bookings = res.json()
    if res.status_code == 200 and len(my_bookings) > 0:
        log(f"Se encontraron {len(my_bookings)} reservas para el usuario")
    else:
        log("Fallo al listar reservas", False)

    # 6. Estadísticas
    # Consulto las estadísticas globales de ventas
    res = requests.get(f"{BASE_URL}/stats")
    stats = res.json()
    if res.status_code == 200:
        log(f"Estadísticas obtenidas: {stats}")
    else:
        log("Fallo al obtener estadísticas", False)

    # 7. Actualizar Reserva
    # Cambio el estado de la reserva a PAGADA
    res = requests.patch(f"{BASE_URL}/bookings/{booking_id}", json={"status": "PAGADA"})
    if res.status_code == 200:
        log("Estado actualizado a PAGADA")
    else:
        log(f"Fallo al actualizar: {res.text}", False)

    # 8. Eliminar Reserva
    # Borro la reserva del sistema para limpiar
    res = requests.delete(f"{BASE_URL}/bookings/{booking_id}")
    if res.status_code == 200:
        log("Reserva eliminada correctamente")
    else:
        log(f"Fallo al eliminar: {res.text}", False)

    # Verificación final
    # Me aseguro de que la reserva ya no exista
    res = requests.get(f"{BASE_URL}/bookings", params={"username": TEST_USER})
    if len(res.json()) == 0:
        log("Verificación final: La reserva ya no existe")
    else:
        log("Advertencia: La reserva sigue apareciendo", False)

    print("\n✨ PRUEBAS COMPLETADAS ✨")

if __name__ == "__main__":
    try:
        test_api()
    except Exception as e:
        log(f"Error crítico en el script de prueba: {e}", False)
