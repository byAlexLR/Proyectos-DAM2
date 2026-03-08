# byAlexLR

# Aquí defino la lógica HTTP de la API, las rutas y la validación de datos

from fastapi import APIRouter, HTTPException, Query
from pydantic import BaseModel, Field
from typing import List, Optional
from db import (
    db_create_user, db_check_user, db_list_events, 
    db_create_booking, db_list_bookings, db_stats_by_status,
    db_update_booking_status, db_delete_booking
)

# Agrupo todos los endpoints en un router
router = APIRouter()

# --- MODELOS DE DATOS ---
class UserLogin(BaseModel):
    username: str
    password: str

class UserRegister(BaseModel):
    username: str
    email: str
    password: str

class EventOut(BaseModel):
    id: int
    title: str
    category: str
    price: float

class BookingLine(BaseModel):
    event_id: int
    quantity: int = Field(ge=1) # Obligo a que al menos reserven 1 entrada

class BookingCreate(BaseModel):
    username: str
    lines: List[BookingLine]

class BookingStatus(BaseModel):
    status: str

# --- ENDPOINTS ---
@router.post("/auth/register", status_code=201)
def register(payload: UserRegister):
    # Recibo la petición de registro desde el cliente
    success = db_create_user(payload.username, payload.email, payload.password)
    if not success:
        raise HTTPException(status_code=400, detail="El usuario ya existe")
    return {"message": "Usuario registrado"}

@router.post("/auth/login")
def login(payload: UserLogin):
    # Recibo la petición de login desde el cliente
    user = db_check_user(payload.username, payload.password)
    if not user:
        raise HTTPException(status_code=401, detail="Credenciales incorrectas")
    return {"message": "Login exitoso", "username": user["username"]}

@router.get("/events", response_model=List[EventOut])
def get_events(
    category: Optional[str] = None, 
    min_price: Optional[float] = None, 
    max_price: Optional[float] = None
):
    # Permito consultar el catálogo de eventos con filtros opcionales
    return db_list_events(category=category, min_price=min_price, max_price=max_price)

@router.post("/bookings", status_code=201)
def create_booking(payload: BookingCreate):
    # Permito crear una operación principal de reserva
    if len(payload.lines) == 0:
        raise HTTPException(status_code=400, detail="La reserva debe tener al menos una entrada")
    try:
        # Llamo a la lógica de negocio en BD
        op = db_create_booking(payload.username, payload.lines)
        return op
    except ValueError as e:
        # Capturo errores controlados (ej. evento no existe)
        raise HTTPException(status_code=400, detail=str(e))

@router.get("/bookings")
def get_bookings(username: Optional[str] = None):
    # Permito consultar el estado de las operaciones
    return db_list_bookings(username=username)

@router.get("/stats")
def get_stats():
    # Devuelvo datos resumidos de la actividad de la empresa
    return db_stats_by_status()

@router.patch("/bookings/{booking_id}")
def update_booking_status(booking_id: int, payload: BookingStatus):
    # Modifico el estado de una operación
    success = db_update_booking_status(booking_id, payload.status)
    if not success:
        raise HTTPException(status_code=404, detail="Reserva no encontrada")
    return {"message": "Estado actualizado"}

@router.delete("/bookings/{booking_id}")
def delete_booking(booking_id: int):
    # Elimino una operación
    success = db_delete_booking(booking_id)
    if not success:
        raise HTTPException(status_code=404, detail="Reserva no encontrada")
    return {"message": "Reserva eliminada"}
