# Pedritos House - Proyecto Backend (API)
Este proyecto es una aplicación backend desarrollada con **FastAPI** y **SQLite** para la gestión de eventos y reservas de la empresa "Pedritos House". Incluye una interfaz web moderna y una API REST completa.

## 📋 Requisitos Previos
- Python 3.8 o superior
- pip (gestor de paquetes de Python)

## 🚀 Instalación y Puesta en Marcha
1.  **Clonar o descargar el proyecto** en tu equipo.

2.  **Crear y activar un entorno virtual**:
    ```bash
    python -m venv .venv
    .venv\Scripts\activate
    ```

3.  **Instalar dependencias**:
    ```bash
    pip install -r requirements.txt
    ```

4.  **Iniciar el servidor**:
    ```bash
    uvicorn main:app --reload
    ```
    El servidor arrancará en `http://127.0.0.1:8000`.

## 🌐 Uso de la Aplicación
### Interfaz Web (Cliente)
Abre tu navegador y ve a:
👉 **[http://127.0.0.1:8000/](http://127.0.0.1:8000/)**

Desde aquí podrás:
- Registrarte e iniciar sesión.
- Consultar el catálogo de eventos (filtrar por categoría/precio).
- Realizar reservas.
- Ver tus reservas y cancelarlas/eliminarlas.
- Ver estadísticas de la empresa.

### Documentación de la API (Swagger)
Para ver y probar los endpoints de la API directamente:
👉 **[http://127.0.0.1:8000/docs](http://127.0.0.1:8000/docs)**

## ✅ Verificación Automática
Este proyecto incluye un script para verificar que toda la lógica de negocio de la API funciona correctamente

Con el servidor funcionando en segundo plano, ejecuta:

```bash
python verify_api.py
```

Este script realizará automáticamente:
1.  Registro y Login de un usuario de prueba.
2.  Consulta y filtrado de eventos.
3.  Creación de una reserva.
4.  Consulta del historial de reservas.
5.  Actualización de estado y eliminación de la reserva.

## 📂 Estructura del Proyecto
- `main.py`: Punto de entrada de la aplicación.
- `api.py`: Definición de endpoints y lógica HTTP.
- `db.py`: Gestión de base de datos SQLite.
- `templates/`: Plantillas HTML para el frontend.
- `static/`: Archivos CSS y JS estáticos.
- `verify_api.py`: Script de pruebas de integración.
- `pedritos_house.db`: Archivo de base de datos (se crea automáticamente).