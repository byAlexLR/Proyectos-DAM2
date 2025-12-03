# byAlexLR

import tkinter as tk
from tkinter import ttk, messagebox
import requests # Necesario para comunicar Python con APIs
import pandas as pd # Biblioteca para generar Excel

class AppUsuarios:
    def __init__(self, root):
        self.root = root
        self.root.title("Gestor de Usuarios (API) - Excel")
        self.root.geometry("900x600")

        # Almacenamiento de datos en memoria
        self.datos_usuarios = []

        # Interfaz de usuario
        frame_top = tk.Frame(self.root, pady=15, padx=10)
        frame_top.pack(fill="x")

        # Etiqueta y entrada para filtrar
        tk.Label(frame_top, text="Buscar Usuario:").pack(side="left")

        self.entrada_filtro = tk.Entry(frame_top, width=30)
        self.entrada_filtro.pack(side="left", padx=10)

        # Botón de búsqueda
        btn_buscar = tk.Button(
            frame_top,
            text="Buscar",
            command=self.cargar_usuarios,
            bg="#4CAF50",
            fg="white",
        )
        btn_buscar.pack(side="left")

        # Botón de exportar a Excel
        btn_excel = tk.Button(
            frame_top,
            text="Exportar a Excel",
            command=self.exportar_excel_pandas,
            bg="#FF5722",
            fg="white",
        )
        btn_excel.pack(side="right")

        # Tabla
        frame_tabla = tk.Frame(self.root)
        frame_tabla.pack(fill="both", expand=True, padx=10, pady=10)

        # Columnas de la tabla
        cols = ("ID", "Nombre", "Email", "Ciudad", "Edad")
        self.tree = ttk.Treeview(frame_tabla, columns=cols, show="headings")

        self.tree.heading("ID", text="ID")
        self.tree.heading("Nombre", text="Nombre Completo")
        self.tree.heading("Email", text="Email")
        self.tree.heading("Ciudad", text="Ciudad")
        self.tree.heading("Edad", text="Edad")

        self.tree.column("ID", width=50, anchor="center")
        self.tree.column("Nombre", width=200)
        self.tree.column("Email", width=250)
        self.tree.column("Ciudad", width=150)
        self.tree.column("Edad", width=50, anchor="center")

        # Para desplazarse por la tabla
        scrollbar = ttk.Scrollbar(
            frame_tabla, orient="vertical", command=self.tree.yview
        )
        self.tree.configure(yscroll=scrollbar.set)
        scrollbar.pack(side="right", fill="y")
        self.tree.pack(fill="both", expand=True)

        # Carga los datos iniciales
        self.cargar_usuarios()

    # Carga usuarios desde la API
    def cargar_usuarios(self):
        query = self.entrada_filtro.get()
        # API de búsqueda de productos
        url = f"https://dummyjson.com/users/search?q={query}"

        try:
            # Solicita los datos a la API
            respuesta = requests.get(url)
            respuesta.raise_for_status()
            data = respuesta.json()
            self.datos_usuarios = data["users"]

            # Limpia y carga la tabla
            for item in self.tree.get_children():
                self.tree.delete(item)

            # Inserta los nuevos datos
            for u in self.datos_usuarios:
                nombre_completo = f"{u['firstName']} {u['lastName']}"
                ciudad = u["address"]["city"]  # Dato anidado

                self.tree.insert(
                    "",
                    "end",
                    values=(u["id"], nombre_completo, u["email"], ciudad, u["age"]),
                )
        # Captura errores de conexión
        except requests.exceptions.RequestException as e:
            messagebox.showerror("Error", f"Error conectando a la API: {e}")
            
    # Exporta a Excel usando Pandas
    def exportar_excel_pandas(self):
        # Verifica si hay datos en la tabla
        if not self.datos_usuarios:
            messagebox.showwarning("Aviso", "No hay datos para exportar")
            return

        try:
            # Prepara los datos para el DataFrame
            lista_limpia = []
            # Limpia y estructura los datos
            for u in self.datos_usuarios:
                lista_limpia.append(
                    {
                        "ID": u["id"],
                        "Nombre": u["firstName"],
                        "Apellido": u["lastName"],
                        "Email": u["email"],
                        "Ciudad": u["address"]["city"],
                        "Edad": u["age"],
                        "Teléfono": u["phone"],
                    }
                )

            # Crea el DataFrame
            df = (
                pd.read_json(json.dumps(lista_limpia))
                if False
                else pd.DataFrame(lista_limpia)
            )

            # Guarda el Excel
            nombre_archivo = "informe_usuarios.xlsx"

            # Exporta usando Pandas
            df.to_excel(nombre_archivo, index=False, sheet_name="Usuarios")

            messagebox.showinfo("Exito", f"Excel generado correctamente:\n{nombre_archivo}")
        # Captura errores al generar el Excel
        except Exception as e:
            messagebox.showerror("Error", f"Error al generar el Excel: {e}")

# Ejecuta la aplicación
if __name__ == "__main__":
    root = tk.Tk()
    AppUsuarios(root)
    root.mainloop()
