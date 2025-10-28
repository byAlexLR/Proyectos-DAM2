# byAlexLR

# 7. Crear un sistema para gestionar productos que incluya nombre, precio
# y imagen. Mostrar los productos en una lista con sus imágenes.

# Importa el módulo os para operaciones del sistema de archivos
import os

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import ttk, filedialog, messagebox

# Importa PIL para el manejo de imágenes
from PIL import Image, ImageTk

# Extensiones de imagen soportadas
SUPPORTED = (".png", ".jpg", ".jpeg", ".gif", ".bmp")

# Clase principal de la aplicación de gestión de productos
class ProductosApp:
    # Inicializa la ventana y configura todos los elementos de la interfaz
    def __init__(self, root):
        root.title("Gestión de Productos") # Establece el título de la ventana
        # Crea el marco principal con espaciado interno
        frame = tk.Frame(root, padx=10, pady=10)
        frame.pack(fill="both", expand=True)
        root.geometry("600x450") # Establece el tamaño de la ventana
        root.minsize(400, 300)  # Establece el tamaño mínimo de la ventana
        
        # Crea un marco para el formulario de entrada de datos
        form = tk.Frame(frame)
        form.pack(fill="x", padx=20)
        # Grid para centrar las columnas del formulario
        form.grid_columnconfigure(0, weight=1)  # Columna izquierda
        form.grid_columnconfigure(1, weight=2)  # Columna central
        form.grid_columnconfigure(2, weight=1)  # Columna derecha
        # Crea las etiquetas para identificar los campos de entrada
        tk.Label(form, text="Nombre:", anchor="e").grid(row=0, column=0, sticky="e", padx=(50,0))
        tk.Label(form, text="Precio:", anchor="e").grid(row=1, column=0, sticky="e", padx=(50,0))
        tk.Label(form, text="Imagen:", anchor="e").grid(row=2, column=0, sticky="e", padx=(50,0))
        # Crea los campos de entrada para los datos del producto
        self.name_entry = tk.Entry(form) # Campo para el nombre
        self.price_entry = tk.Entry(form) # Campo para el precio
        self.image_entry = tk.Entry(form, width=40) # Campo para la ruta
        # Posiciona los campos introducidos en un grid
        self.name_entry.grid(row=0, column=1, padx=5, pady=5, sticky="ew")
        self.price_entry.grid(row=1, column=1, padx=5, pady=5, sticky="ew")
        self.image_entry.grid(row=2, column=1, padx=5, pady=5, sticky="ew")
        # Crea el botón para seleccionar una imagen
        tk.Button(form, text="Seleccionar...", command=self.seleccionar_imagen).grid(row=2, column=2, padx=(0,50), pady=5)

        # Crea el botón para añadir un nuevo producto
        add_button = tk.Button(form, text="Añadir producto", command=self.agregar_producto)
        add_button.grid(row=3, column=1, pady=15)

        # Crea un frame para contener el canvas y la barra de desplazamiento
        canvas_frame = tk.Frame(frame)
        canvas_frame.pack(fill="both", expand=True, pady=10)
        
        # Crea el scrollbar vertical con estilo
        scrollbar = ttk.Scrollbar(canvas_frame)
        scrollbar.pack(side="right", fill="y")
        
        # Crea el canvas donde se mostrarán los productos con sus imágenes
        self.canvas = tk.Canvas(canvas_frame, 
                              yscrollcommand=scrollbar.set,
                              highlightthickness=0)
        self.canvas.pack(side="left", fill="both", expand=True)
        
        # Configura el scrollbar
        scrollbar.config(command=self.canvas.yview)
        
        # Crea un frame interior para los productos
        self.products_frame = tk.Frame(self.canvas)
        # Centro el frame de productos horizontalmente en el canvas
        self.canvas.create_window((300, 0), window=self.products_frame, anchor="n")
        
        # Configura el evento para actualizar el scroll
        self.products_frame.bind("<Configure>", self.actualizar_scroll)
        
        # Lista para almacenar todos los productos en memoria
        self.products = []
        # Lista para mantener las referencias de las miniaturas de imágenes
        self.thumbs = []

    # Función para abrir un diálogo de selección de archivo de imagen
    def seleccionar_imagen(self):
        # Abre un diálogo para seleccionar un archivo de imagen
        archivo = filedialog.askopenfilename(title="Seleccionar imagen", filetypes=[("Imagenes", "*.png *.jpg *.jpeg *.gif *.bmp")])
        if archivo: # Si se selecciona un archivo
            self.image_entry.delete(0, "end") # Limpia el campo de imagen
            self.image_entry.insert(0, archivo) # Inserta la ruta del archivo seleccionado

    # Función para añadir un nuevo producto a la lista
    def agregar_producto(self):
        # Obtiene y limpia los datos de los campos de entrada
        name = self.name_entry.get().strip() # Obtiene el nombre sin espacios
        price = self.price_entry.get().strip() # Obtiene el precio sin espacios
        img = self.image_entry.get().strip() # Obtiene la ruta de la imagen sin espacios
        # Valida que los campos obligatorios tengan contenido
        if not name or not price:
            messagebox.showinfo("Info", "Nombre y precio requeridos")
            return
        # Convierte el precio a número decimal
        try:
            pricef = float(price)
        except:
            messagebox.showinfo("Info", "Precio debe ser numérico")
            return
        # Añade el nuevo producto a la lista
        self.products.append({"name": name, "price": pricef, "image": img})
        self.actualizar_vista() # Actualiza la visualización del canvas
        # Limpia todos los campos de entrada para el siguiente producto
        self.name_entry.delete(0,'end'); self.price_entry.delete(0,'end'); self.image_entry.delete(0,'end')

    def actualizar_scroll(self, event=None):
        # Actualiza la región de scroll del canvas
        self.canvas.configure(scrollregion=self.canvas.bbox("all"))

    # Función para actualizar la visualización del canvas con todos los productos
    def actualizar_vista(self):
        # Limpia los productos anteriores
        for widget in self.products_frame.winfo_children():
            widget.destroy()
        self.thumbs.clear()

        # Configura el grid del frame de productos para dos columnas centradas
        self.products_frame.grid_columnconfigure(0, weight=1, minsize=200)
        self.products_frame.grid_columnconfigure(1, weight=1, minsize=200)
        
        # Espacio para centrar verticalmente
        self.products_frame.grid_rowconfigure(0, weight=1)

        # Itera todos los productos y los muestra en el grid
        for i, p in enumerate(self.products):
            # Crea un frame para cada producto
            product_frame = tk.Frame(self.products_frame)
            row = i // 2  # Calcula la fila
            col = i % 2   # Calcula la columna (0 o 1)
            product_frame.grid(row=row+1, column=col, padx=20, pady=15)  # row+1 para dejar espacio arriba

            # Verifica si existe una imagen válida para el producto
            if p["image"] and os.path.exists(p["image"]):
                try:
                    # Abre y redimensiona la imagen
                    im = Image.open(p["image"])
                    im.thumbnail((180, 180))  # Imagen más grande
                    photo = ImageTk.PhotoImage(im)
                    self.thumbs.append(photo)
                    # Crea un label para mostrar la imagen
                    img_label = tk.Label(product_frame, image=photo)
                    img_label.pack(pady=(0,10))
                except:
                    pass
            
            # Etiquetas para el nombre y precio con mejor formato
            tk.Label(product_frame, text=p["name"], 
                    font=("Arial", 12, "bold")).pack(pady=(0,5))
            tk.Label(product_frame, text=f"{p['price']:.2f}€", 
                    font=("Arial", 11),
                    fg='#666666').pack()

# Inicia la aplicación principal
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    ProductosApp(root) # Crea una instancia de la clase ProductosApp
    root.mainloop() # Inicia el bucle principal de la ventana