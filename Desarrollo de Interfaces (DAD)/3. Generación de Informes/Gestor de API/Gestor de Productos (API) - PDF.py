# byAlexLR

import tkinter as tk
from tkinter import ttk, messagebox
import requests # Necesario para comunicar Python con APIs
from fpdf import FPDF # Biblioteca para generar PDFs

class AppProductos:
    def __init__(self, root):
        self.root = root
        self.root.title("Gestor de Productos (API) - PDF")
        self.root.geometry("900x600")

        # Interfaz de usuario
        frame_top = tk.Frame(self.root, pady=15, padx=10)
        frame_top.pack(fill="x")

        # Etiqueta y entrada para filtrar
        tk.Label(frame_top, text="Buscar Producto:").pack(side="left")

        self.entrada_busqueda = tk.Entry(frame_top, width=30)
        self.entrada_busqueda.pack(side="left", padx=10)

        # Botón de búsqueda
        btn_buscar = tk.Button(
            frame_top,
            text="Buscar",
            command=self.buscar_productos,
            bg="#4CAF50",
            fg="white",
        )
        btn_buscar.pack(side="left")

        # Botón de exportar a PDF
        btn_pdf = tk.Button(
            frame_top,
            text="Exportar a PDF",
            command=self.generar_pdf,
            bg="#FF5722",
            fg="white",
        )
        btn_pdf.pack(side="right")

        # Tabla
        frame_tabla = tk.Frame(self.root)
        frame_tabla.pack(fill="both", expand=True, padx=10, pady=10)

        # Columnas de la tabla
        cols = ("Nombre", "Categoría", "Precio")
        self.tree = ttk.Treeview(frame_tabla, columns=cols, show="headings")

        self.tree.heading("Nombre", text="Nombre")
        self.tree.heading("Categoría", text="Categoría")
        self.tree.heading("Precio", text="Precio ($)")

        self.tree.column("Nombre", width=300)
        self.tree.column("Categoría", width=150, anchor="center")
        self.tree.column("Precio", width=100, anchor="center")

        # Para desplazarse por la tabla
        scrollbar = ttk.Scrollbar(
            frame_tabla, orient="vertical", command=self.tree.yview
        )
        self.tree.configure(yscroll=scrollbar.set)
        scrollbar.pack(side="right", fill="y")
        self.tree.pack(fill="both", expand=True)

        # Carga los datos iniciales
        self.buscar_productos()

    # Cargar productos desde la API
    def buscar_productos(self):
        query = self.entrada_busqueda.get()
        # API de búsqueda de productos
        url = f"https://dummyjson.com/products/search?q={query}"

        try:
            # Solicita los datos a la API
            respuesta = requests.get(url)
            respuesta.raise_for_status()
            datos = respuesta.json()
            productos = datos["products"]

            # Limpia y carga la tabla
            for item in self.tree.get_children():
                self.tree.delete(item)

            # Inserta los nuevos datos
            for p in productos:
                self.tree.insert(
                    "", "end", values=(p["title"], p["category"], f"${p['price']}")
                )
        # Captura errores de conexión
        except requests.exceptions.RequestException as e:
            messagebox.showerror("Error", f"Error de conexión: {e}")

    # Exportar a PDF usando FPDF
    def generar_pdf(self):
        # Verifica si hay datos en la tabla
        items = self.tree.get_children()
        if not items:
            messagebox.showwarning("Aviso", "No hay datos para exportar")
            return

        # Crea el PDF
        pdf = FPDF()
        pdf.add_page()
        pdf.set_font("Arial", size=12)

        # Título del PDF
        pdf.set_font("Arial", "B", 16)
        pdf.cell(0, 10, "Informe de Productos Filtrados", ln=True, align="C")
        pdf.ln(10)

        # Configuración de los estilos del encabezado
        pdf.set_font("Arial", "B", 12)
        pdf.set_fill_color(70, 130, 180)  # Azul acero (Fondo)
        pdf.set_text_color(255, 255, 255)  # Blanco (Texto)
        
        # Encabezados de la tabla en el PDF
        pdf.cell(110, 10, "Nombre", 1, 0, "C", fill=True)
        pdf.cell(50, 10, "Categoría", 1, 0, "C", fill=True)
        pdf.cell(30, 10, "Precio", 1, 1, "C", fill=True)

        # Restaura el color de las filas
        pdf.set_font("Arial", size=10)
        pdf.set_text_color(0, 0, 0)

        # Filas de datos
        for item in items:
            vals = self.tree.item(item)["values"]
            # Si el nombre es muy largo, lo trunca
            nombre = (
                str(vals[0])[:45] + "..." if len(str(vals[0])) > 45 else str(vals[0])
            )

            # Filas de la tabla en el PDF
            pdf.cell(110, 10, nombre, 1, 0, "L")
            pdf.cell(50, 10, str(vals[1]), 1, 0, "C")
            pdf.cell(30, 10, str(vals[2]), 1, 1, "C")
            
        # Guarda la información como un archivo PDF
        nombre_archivo = "informe_productos.pdf"
        try:
            pdf.output(nombre_archivo)
            messagebox.showinfo(
                "Exito", f"PDF generado correctamente:\n{nombre_archivo}"
            )
        # Captura errores al generar el PDF
        except Exception as e:
            messagebox.showerror("Error", f"Error al generar el PDF: {e}")

# Ejecuta la aplicación
if __name__ == "__main__":
    root = tk.Tk()
    AppProductos(root)
    root.mainloop()
