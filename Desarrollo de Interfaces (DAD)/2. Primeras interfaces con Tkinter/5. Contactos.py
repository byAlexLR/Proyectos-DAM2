# byAlexLR

# 5. Sistema CRUD simple para gestionar contactos con nombre y teléfono.

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import ttk, messagebox

# Clase principal de la aplicación de gestión de contactos
class ContactosApp:
    # Inicializa la ventana y configura todos los elementos de la interfaz
    def __init__(self, root):
        # Establece el título de la ventana
        root.title("Contactos")
        # Crea el marco principal con espaciado interno que se expande
        frame = tk.Frame(root, padx=10, pady=10)
        frame.pack(fill="both", expand=True)

        # Crea un marco para el formulario de entrada de datos
        form = tk.Frame(frame)
        form.pack(fill="x")
        # Crea las etiquetas para identificar los campos de entrada
        tk.Label(form, text="Nombre:").grid(row=0, column=0, sticky="e")
        tk.Label(form, text="Teléfono:").grid(row=1, column=0, sticky="e")
        # Crea los campos de entrada para nombre y teléfono
        self.name_entry = tk.Entry(form) # Campo para el nombre del contacto
        self.phone_entry = tk.Entry(form) # Campo para el teléfono del contacto
        # Posiciona los campos de entrada en la cuadrícula
        self.name_entry.grid(row=0, column=1, padx=5, pady=2)
        self.phone_entry.grid(row=1, column=1, padx=5, pady=2)

        # Crea un marco para los botones de operaciones CRUD
        btns = tk.Frame(form)
        btns.grid(row=0, column=2, rowspan=2, padx=10)
        # Crea los botones para añadir, actualizar y eliminar contactos
        tk.Button(btns, text="Añadir", command=self.agregar_contacto).pack(fill="x")
        tk.Button(btns, text="Actualizar", command=self.actualizar_contacto).pack(
            fill="x", pady=5
        )
        tk.Button(btns, text="Eliminar", command=self.eliminar_contacto).pack(fill="x")

        # Crea la tabla (Treeview) para mostrar la lista de contactos
        self.tree = ttk.Treeview(
            frame, columns=("name", "phone"), show="headings", height=10
        )
        # Configura las cabeceras de las columnas
        self.tree.heading("name", text="Nombre")
        self.tree.heading("phone", text="Teléfono")
        # Posiciona la tabla en la ventana
        self.tree.pack(fill="both", expand=True, pady=10)
        # Vincula la selección de filas a la función al_seleccionar
        self.tree.bind("<<TreeviewSelect>>", self.al_seleccionar)

        # Lista para almacenar todos los contactos en memoria
        self.contacts = []
        # Variable para almacenar el ID del contacto seleccionado
        self.selected_id = None

    # Función para añadir un nuevo contacto a la lista
    def agregar_contacto(self):
        # Obtiene y limpia los datos de los campos de entrada
        name = self.name_entry.get().strip() # Obtiene el nombre sin espacios extra
        phone = self.phone_entry.get().strip() # Obtiene el teléfono sin espacios extra
        # Valida que ambos campos tengan contenido
        if not name or not phone:
            messagebox.showinfo("Info", "Nombre y teléfono requeridos")
            return
        # Añade el nuevo contacto a la lista y actualiza la tabla
        self.contacts.append({"name": name, "phone": phone})
        self.actualizar_vista() # Actualiza la visualización de la tabla
        # Limpia los campos de entrada para el siguiente contacto
        self.name_entry.delete(0, "end")
        self.phone_entry.delete(0, "end")

    # Función para actualizar un contacto existente
    def actualizar_contacto(self):
        # Obtiene la selección actual de la tabla
        sel = self.tree.selection()
        if not sel:
            messagebox.showinfo("Info", "Selecciona un contacto")
            return
        idx = int(sel[0]) # Convierte el ID de texto a número
        # Obtiene y limpia los datos de los campos de entrada
        name = self.name_entry.get().strip()
        phone = self.phone_entry.get().strip()
        # Valida que ambos campos tengan contenido
        if not name or not phone:
            messagebox.showinfo("Info", "Nombre y teléfono requeridos")
            return
        # Actualiza el contacto en la lista y refresca la tabla
        self.contacts[idx] = {"name": name, "phone": phone}
        self.actualizar_vista() # Actualiza la visualización de la tabla

    # Función para eliminar un contacto de la lista
    def eliminar_contacto(self):
        # Obtiene la selección actual de la tabla
        sel = self.tree.selection()
        if not sel:
            messagebox.showinfo("Info", "Selecciona un contacto")
            return
        idx = int(sel[0]) # Convierte el ID de texto a número
        # Elimina el contacto de la lista y actualiza la tabla
        del self.contacts[idx]
        self.actualizar_vista() # Actualiza la visualización de la tabla
        # Limpia los campos de entrada
        self.name_entry.delete(0, "end")
        self.phone_entry.delete(0, "end")

    # Función para actualizar la visualización de la tabla con los contactos actuales
    def actualizar_vista(self):
        # Limpia todas las filas existentes de la tabla
        for i in self.tree.get_children():
            self.tree.delete(i)
        # Añade cada contacto de la lista a la tabla
        for i, c in enumerate(self.contacts):
            self.tree.insert("", "end", iid=str(i), values=(c["name"], c["phone"]))

    # Función que se ejecuta cuando el usuario selecciona un contacto en la tabla
    def al_seleccionar(self, event):
        sel = self.tree.selection() # Obtiene la selección actual
        if not sel:
            return
        idx = int(sel[0]) # Convierte el ID de texto a número
        c = self.contacts[idx] # Obtiene el contacto seleccionado
        # Limpia los campos de entrada
        self.name_entry.delete(0, "end")
        self.phone_entry.delete(0, "end")
        # Rellena los campos con los datos del contacto seleccionado
        self.name_entry.insert(0, c["name"])
        self.phone_entry.insert(0, c["phone"])

# Inicia la aplicación principal
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    ContactosApp(root) # Crea una instancia de la clase ContactosApp
    root.mainloop() # Inicia el bucle principal de la ventana
