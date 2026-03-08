# byAlexLR

# Punto de entrada de la aplicación. Aquí se crea la API y se conecta todo.

from fastapi import FastAPI, Request
from fastapi.staticfiles import StaticFiles
from fastapi.templating import Jinja2Templates
from api import router
from db import init_db, seed_data

# Instancio la aplicación FastAPI para Pedritos House S.L.
app = FastAPI(title="Pedritos House API")

# Monto los archivos estáticos (CSS, JS, imágenes)
app.mount("/static", StaticFiles(directory="static"), name="static")

# Configuro el motor de plantillas (HTML)
templates = Jinja2Templates(directory="templates")

# Este evento se ejecuta UNA SOLA VEZ cuando arranca la API
@app.on_event("startup")
def startup():
    # Creo las tablas de la base de datos si no existen
    init_db()
    # Inserto datos de ejemplo solo si la BD está vacía
    seed_data()

# Registro todas las rutas (endpoints) definidas en api.py
app.include_router(router)

# Ruta principal para servir la interfaz web
@app.get("/")
def read_root(request: Request):
    return templates.TemplateResponse("index.html", {"request": request})
