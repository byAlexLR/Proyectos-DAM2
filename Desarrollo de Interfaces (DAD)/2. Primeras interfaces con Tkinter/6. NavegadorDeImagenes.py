# 6. Crear un visor de imágenes que permita navegar entre diferentes imágenes usando botones anterior/siguiente.

# Importa el módulo os para operaciones del sistema de archivos
import os

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import filedialog, messagebox

# Importa PIL para el manejo de imágenes
from PIL import Image, ImageTk

# Extensiones de imagen soportadas
SUPPORTED = (".png", ".jpg", ".jpeg", ".gif", ".bmp")

# Clase principal del visor de imágenes
class VisorImagen:
    # Inicializa la ventana y configura todos los elementos de la interfaz
    def __init__(self, root):
        self.root = root
        # Establece el título de la ventana
        root.title("Visor de Imágenes")
        # Crea el marco principal con espaciado interno que se expande
        self.frame = tk.Frame(root, padx=20, pady=20)
        self.frame.pack()
        # Crea la parte superior con el botón para abrir carpeta
        top = tk.Frame(self.frame)
        top.pack()
        tk.Button(top, text="Abrir carpeta...", command=self.abrir_carpeta).pack(
            side="left"
        )
        # Crea la etiqueta para mostrar el nombre de la imagen y el canvas para mostrar la imagen
        self.label = tk.Label(self.frame, text="Ninguna imagen cargada")
        self.label.pack(pady=5)
        self.canvas = tk.Label(self.frame)
        self.canvas.pack()
        # Crea la parte inferior con los botones de navegación
        nav = tk.Frame(self.frame)
        nav.pack(pady=5)
        tk.Button(nav, text="<< Anterior", command=self.anterior).pack(side="left", padx=5)
        tk.Button(nav, text="Siguiente >>", command=self.siguiente).pack(side="left", padx=5)

        # Inicializa la lista de imágenes y el índice actual
        self.images = []
        self.index = -1
        self.photo = None

    # Función para abrir una carpeta y cargar las imágenes
    def abrir_carpeta(self):
        # Abre un diálogo para seleccionar una carpeta
        carpeta = filedialog.askdirectory(
            title="Selecciona carpeta de imágenes", initialdir="."
        )
        if not carpeta:
            return
        # Lista todos los archivos de imagen en la carpeta seleccionada
        archivos = [
            os.path.join(carpeta, f)
            for f in os.listdir(carpeta)
            if f.lower().endswith(SUPPORTED)
        ]
        # Ordena los archivos por nombre
        archivos.sort()
        if not archivos:
            messagebox.showinfo("Info", "No se encontraron imágenes en la carpeta")
            return
        # Guarda la lista de imágenes y muestra la primera
        self.images = archivos
        self.index = 0
        self.mostrar_imagen()

    # Función para mostrar la imagen actual
    def mostrar_imagen(self):
        # Carga la imagen actual y la muestra en el canvas
        path = self.images[self.index]
        img = Image.open(path)
        img.thumbnail((800, 600))
        # Guarda la referencia de la imagen para evitar que se vuelva basura
        self.photo = ImageTk.PhotoImage(img)
        # Actualiza el canvas y la etiqueta con la imagen y su nombre
        self.canvas.config(image=self.photo)
        # Actualiza el texto de la etiqueta con el nombre del archivo y el índice
        self.label.config(
            text=f"{os.path.basename(path)} ({self.index+1}/{len(self.images)})"
        )

    # Función para mostrar la imagen anterior
    def anterior(self):
        if not self.images:
            return
        # Resta 1 al índice y usa módulo para ciclar
        self.index = (self.index - 1) % len(self.images)
        self.mostrar_imagen() # Muestra la imagen actual

    # Función para mostrar la imagen siguiente
    def siguiente(self):
        if not self.images:
            return
        # Suma 1 al índice y usa módulo para ciclar
        self.index = (self.index + 1) % len(self.images)
        self.mostrar_imagen() # Muestra la imagen actual

# Inicia la aplicación principal
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    VisorImagen(root) # Crea una instancia de la clase VisorImagen
    root.mainloop() # Inicia el bucle principal de la ventana
