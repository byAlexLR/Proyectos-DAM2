# 4. Conversor entre kilómetros y millas con actualización en tiempo real.

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk

# Constante de conversión: 1 kilómetro equivale a 0.621371 millas
KM_TO_MILES = 0.621371

# Clase principal de la aplicación conversora
class Convertidor:
    # Inicializa la ventana y configura todos los elementos de la interfaz
    def __init__(self, root):
        # Variable para evitar actualizaciones recursivas entre los campos
        self.updating = False
        # Establece el título de la ventana
        root.title("Conversor Km <-> Millas")
        # Crea el marco principal con espaciado interno
        frame = tk.Frame(root, padx=10, pady=10)
        frame.pack()

        # Crea las etiquetas para identificar cada campo de entrada
        tk.Label(frame, text="Kilómetros:").grid(row=0, column=0, sticky="e")
        tk.Label(frame, text="Millas:").grid(row=1, column=0, sticky="e")

        # Variables de control para los campos de entrada
        self.km_var = tk.StringVar() # Variable para el campo de kilómetros
        self.mi_var = tk.StringVar() # Variable para el campo de millas
        # Configura el seguimiento de cambios en las variables para conversión automática
        self.km_var.trace_add("write", self.km_cambiado) # Se ejecuta al cambiar kilómetros
        self.mi_var.trace_add("write", self.mi_cambiado) # Se ejecuta al cambiar millas

        # Crea los campos de entrada para kilómetros y millas
        tk.Entry(frame, textvariable=self.km_var, width=20).grid(row=0, column=1, padx=5)
        tk.Entry(frame, textvariable=self.mi_var, width=20).grid(row=1, column=1, padx=5)
        
        # Crea el botón para limpiar ambos campos de entrada
        tk.Button(frame, text="Limpiar", command=self.limpiar).grid(row=2, column=0, columnspan=2, pady=5)
    
    # Función que se ejecuta automáticamente cuando cambia el valor de kilómetros
    def km_cambiado(self, *args):
        # Evita las llamadas recursivas infinitas entre los campos
        if self.updating:
            return
        self.updating = True
        # Intenta convertir el valor de kilómetros y calcular las millas equivalentes
        try:
            km = float(self.km_var.get()) # Convierte el texto a número decimal
            self.mi_var.set(f"{km * KM_TO_MILES:.6g}") # Calcula y actualiza millas
        except:
            # Si hay error en la conversión, limpia el campo de millas si kilómetros está vacío
            if self.km_var.get().strip() == "":
                self.mi_var.set("")
        self.updating = False

    # Función que se ejecuta automáticamente cuando cambia el valor de millas
    def mi_cambiado(self, *args):
        # Evita las llamadas recursivas infinitas entre los campos
        if self.updating:
            return
        self.updating = True
        # Intenta convertir el valor de millas y calcular los kilómetros equivalentes
        try:
            mi = float(self.mi_var.get()) # Convierte el texto a número decimal
            self.km_var.set(f"{mi / KM_TO_MILES:.6g}") # Calcula y actualiza kilómetros
        except:
            # Si hay error en la conversión, limpia el campo de kilómetros si millas está vacío
            if self.mi_var.get().strip() == "":
                self.km_var.set("")
        self.updating = False
    
    # Función para limpiar ambos campos de entrada
    def limpiar(self):
        self.km_var.set("") # Limpia el campo de kilómetros
        self.mi_var.set("") # Limpia el campo de millas
        
# Inicia la aplicación principal
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    Convertidor(root) # Crea una instancia de la clase Convertidor
    root.mainloop() # Inicia el bucle principal de la ventana