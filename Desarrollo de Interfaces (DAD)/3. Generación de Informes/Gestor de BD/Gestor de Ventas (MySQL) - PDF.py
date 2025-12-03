# byAlexLR

import tkinter as tk
from tkinter import ttk, messagebox
import mysql.connector  # Necesario para comunicar Python con el servidor MySQL
from fpdf import FPDF  # Biblioteca para generar PDFs

# Configuración de MySQL
DB_CONFIG = {
    "host": "localhost",
    "user": "root",
    "password": "root",
    "database": "tienda_db",
}

class AppVentasPDF:
    def __init__(self, root):
        self.root = root
        self.root.title("Gestor de Ventas (MySQL) - PDF")
        self.root.geometry("900x600")

        # Almacenamiento de datos en memoria
        self.datos_memoria = []

        # Interfaz de usuario
        frame_top = tk.Frame(self.root, pady=10)
        frame_top.pack(fill="x")

        # Etiqueta y entrada para filtrar
        tk.Label(frame_top, text="Buscar por Producto:").pack(side="left", padx=10)
        self.entrada_filtro = tk.Entry(frame_top)
        self.entrada_filtro.pack(side="left", padx=5)
        self.entrada_filtro.bind(
            "<KeyRelease>", self.filtrar_datos
        )  # Filtrar al escribir

        # Botón de exportar a PDF
        btn_pdf = tk.Button(
            frame_top,
            text="Exportar a PDF",
            command=self.generar_pdf,
            bg="#FF5722",
            fg="white",
            font=("Arial", 10, "bold"),
        )
        btn_pdf.pack(side="right", padx=10)

        # Tabla
        frame_tabla = tk.Frame(self.root)
        frame_tabla.pack(fill="both", expand=True, padx=10, pady=10)

        # Columnas de la tabla
        cols = ("Producto", "Unidades Vendidas", "Ingreso Total")
        self.tree = ttk.Treeview(frame_tabla, columns=cols, show="headings")

        self.tree.heading("Producto", text="Producto")
        self.tree.heading("Unidades Vendidas", text="Unidades Vendidas")
        self.tree.heading("Ingreso Total", text="Ingreso Total ($)")

        self.tree.column("Producto", width=300)
        self.tree.column("Unidades Vendidas", width=150, anchor="center")
        self.tree.column("Ingreso Total", width=150, anchor="center")

        # Para desplazarse por la tabla
        scrollbar = ttk.Scrollbar(
            frame_tabla, orient="vertical", command=self.tree.yview
        )
        self.tree.configure(yscroll=scrollbar.set)
        scrollbar.pack(side="right", fill="y")
        self.tree.pack(fill="both", expand=True)

        # Carga los datos iniciales
        self.cargar_datos_mysql()

    # Lógica para cargar datos desde MySQL
    def cargar_datos_mysql(self):
        try:
            # Establece la conexión
            conn = mysql.connector.connect(**DB_CONFIG)
            cursor = conn.cursor(dictionary=True)

            # Consulta para obtener ventas agrupadas por producto
            query = """
                SELECT 
                    p.nombre, 
                    SUM(v.cantidad) as total_unidades, 
                    SUM(v.cantidad * p.precio) as importe_total
                FROM ventas v
                JOIN productos p ON v.producto_id = p.id
                GROUP BY p.nombre
                ORDER BY importe_total DESC
            """
            cursor.execute(query)
            # Guardamos como lista de tuplas
            self.datos_memoria = cursor.fetchall()
            self.actualizar_tabla(self.datos_memoria)
            # Cierra la conexión
            cursor.close()
            conn.close()
        # Captura errores de conexión o consulta
        except mysql.connector.Error as e:
            messagebox.showerror("Error MySQL", f"No se pudo conectar a la BD: {e}")
    
    # Actualiza la tabla con una lista
    def actualizar_tabla(self, lista):
        # Limpia la tabla
        for item in self.tree.get_children():
            self.tree.delete(item)
        
        # Inserta los nuevos datos
        for fila in lista:
            # Soporta tanto diccionarios como tuplas
            if isinstance(fila, dict):
                nombre = fila['nombre']
                unidades = fila['total_unidades']
                ingreso = fila['importe_total']
            else:
                nombre = fila[0]
                unidades = fila[1]
                ingreso = fila[2]

            precio_fmt = f"${float(ingreso):,.2f}"
            self.tree.insert("", "end", values=(nombre, unidades, precio_fmt))
    
    
    # Lógica para filtrar datos localmente
    def filtrar_datos(self, event=None):
        texto = self.entrada_filtro.get().lower()
        if not texto:
            # Si el campo está vacío, muestra todos
            self.actualizar_tabla(self.datos_memoria)
        else:
            # Filtrar productos por nombre
            filtrados = [p for p in self.datos_memoria if texto in p['nombre'].lower()]
            # Actualiza la tabla con los datos filtrados
            self.actualizar_tabla(filtrados)

    # Exporta a PDF usando FPDF
    def generar_pdf(self):
        # Obtiene todos los datos visibles en la tabla
        items = self.tree.get_children()

        # Verifica si hay datos en la tabla
        if not items:
            messagebox.showwarning("Aviso", "No hay datos para exportar")
            return

         # Crea el PDF
        pdf = FPDF()
        pdf.add_page()
        pdf.set_font("Arial", size=12)

        # Título del PDF
        pdf.set_font("Arial", "B", 16)
        pdf.cell(190, 10, txt="Informe de Ventas", ln=True, align="C")
        pdf.ln(10)

        # Configuración de los estilos del encabezado
        pdf.set_font("Arial", "B", 12)
        pdf.set_fill_color(70, 130, 180)  # Azul acero (Fondo)
        pdf.set_text_color(255, 255, 255)  # Blanco (Texto)
        
        # Encabezados de la tabla en el PDF
        pdf.cell(100, 10, "Producto", border=1, align="C", fill=True)
        pdf.cell(40, 10, "Unidades", border=1, align="C", fill=True)
        pdf.cell(50, 10, "Ingreso Total", border=1, ln=1, align="C", fill=True)
        
        # Restaura el color de las filas
        pdf.set_font("Arial", size=10)
        pdf.set_text_color(0, 0, 0)

        # Filas de datos
        for item in items:
            valores = self.tree.item(item)["values"]

            # Obtiene y convierte los datos
            nombre_prod = str(valores[0])
            unidades = str(valores[1])
            ingreso_fmt = str(valores[2])

            # Si el nombre es muy largo, lo trunca
            if len(nombre_prod) > 35:
                nombre_prod = nombre_prod[:32] + "..."

            # Filas de la tabla en el PDF
            pdf.cell(100, 10, nombre_prod, 1, 0, "C")
            pdf.cell(40, 10, unidades, 1, 0, "C")
            pdf.cell(50, 10, ingreso_fmt, 1, 1, "C") 

        # Guarda la información como un archivo PDF
        nombre_archivo = "informe_ventas.pdf"
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
    app = AppVentasPDF(root)
    root.mainloop()
