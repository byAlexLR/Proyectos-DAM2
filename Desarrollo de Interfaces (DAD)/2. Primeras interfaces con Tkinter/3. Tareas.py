# byAlexLR

# 3. Crear una lista de tareas donde se puedan agregar nuevas tareas y eliminarlas individualmente.

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import messagebox

# Clase principal de la aplicación
class TodoApp:
    # Inicializa la ventana
    def __init__(self, root):
        self.root = root
        root.title("Lista de Tareas")
        # Crea un marco principal
        frame = tk.Frame(root, padx=10, pady=10)
        frame.pack()

        # Elementos para añadir la tarea (Etiqueta, entrada y botón)
        tk.Label(frame, text="Nueva tarea:").grid(row=0, column=0, sticky="w")
        self.entry = tk.Entry(frame, width=40)
        self.entry.grid(row=0, column=1, padx=5)
        tk.Button(frame, text="Añadir", command=self.agregar).grid(row=0, column=2)

        # Lista para mostrar las tareas
        self.listbox = tk.Listbox(frame, width=60, height=10)
        self.listbox.grid(row=1, column=0, columnspan=3, pady=10)

        # Elementos para mostrar los botones de eliminar
        btn_frame = tk.Frame(frame)
        btn_frame.grid(row=2, column=0, columnspan=3)
        # Elimina la tarea seleccionada o todas las tareas
        tk.Button(
            btn_frame, text="Eliminar seleccionada", command=self.eliminar_seleccionada
        ).pack(side="left", padx=5)
        tk.Button(btn_frame, text="Eliminar todo", command=self.eliminar_todo).pack(
            side="left", padx=5
        )

    # Función para añadir una tarea
    def agregar(self):
        texto = self.entry.get().strip() # Obtiene el texto y quita espacios
        # Si tiene texto añade la tarea al final de la lista
        if texto:
            self.listbox.insert("end", texto) 
            self.entry.delete(0, "end") # Limpia la entrada
        else:
            messagebox.showinfo("Info", "Introduce una tarea")

    # Función para eliminar la tarea seleccionada
    def eliminar_seleccionada(self):
        seleccion = self.listbox.curselection() # Obtiene la selección
        # Si no se ha seleccionado nada, muestra un mensaje
        if not seleccion:
            messagebox.showinfo("Info", "Selecciona una tarea para eliminar")
            return
        # Elimina las tareas seleccionadas (de abajo hacia arriba)
        for i in reversed(seleccion):
            self.listbox.delete(i)

    # Función para eliminar todas las tareas
    def eliminar_todo(self):
        # Pide confirmar el borado de todo
        if messagebox.askyesno("Confirmar", "¿Eliminar todas las tareas?"):
            self.listbox.delete(0, "end")

# Inicia la aplicación
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    app = TodoApp(root) # Crea una instancia de la clase TodoApp
    root.mainloop() # Inicia el bucle principal de la ventana
