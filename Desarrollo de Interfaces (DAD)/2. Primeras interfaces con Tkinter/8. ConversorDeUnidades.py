# 8. Desarrollar una aplicación de conversión de unidades que implemente un sistema de
# ventanas múltiples. La aplicación constará de:
# 1.Ventana Principal: Permitirá seleccionar entre tres categorías de conversión:
# Unidades Monetarias, Unidades de Longitud y Unidades de Temperatura.
# 2.Ventanas Secundarias: Cada categoría abrirá una ventana específica con:
# •Monetarias: Conversión entre Euros, Dólares y Libras Esterlinas
# •Longitud: Conversión entre Milímetros, Centímetros, Metros, Kilómetros, Pulgadas, Yardas y Millas
# •Temperatura: Conversión entre Celsius, Fahrenheit y Kelvin
# El sistema debe permitir introducir un valor numérico y realizar conversiones
# bidireccionales entre cualquier par de unidades dentro de la misma categoría.

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import ttk

# Diccionario con las tasas de cambio fijas
RATES = {
    ("EUR", "USD"): 1.16, # 1 Euro = 1.16 Dólares
    ("EUR", "GBP"): 0.87, # 1 Euro = 0.87 Libras Esterlinas
}
# Lista de las monedas disponibles para conversión
CURRENCIES = ["EUR", "USD", "GBP"]

# Diccionario con las unidades de longitud y sus equivalencias en metros
LENGTH_UNITS = {
    "milímetro": 0.001,    # 1 milímetro = 0.001 metros
    "centímetro": 0.01,    # 1 centímetro = 0.01 metros
    "metro": 1.0,          # 1 metro = 1 metro
    "kilómetro": 1000.0,   # 1 kilómetro = 1000 metros
    "pulgada": 0.0254,     # 1 pulgada = 0.0254 metros
    "yarda": 0.9144,       # 1 yarda = 0.9144 metros
    "milla": 1609.344      # 1 milla = 1609.344 metros
}

# Lista de las unidades de temperatura disponibles para conversión
TEMPS = ["Celsius", "Fahrenheit", "Kelvin"]

# Clase principal que maneja la ventana principal y las conversiones
class MultiConvertidor:
    # Inicializa la ventana principal con los botones de categorías
    def __init__(self, root):
        root.title("Conversor (Ventanas múltiples)") # Establece el título de la ventana
        # Crea el marco principal con espaciado interno
        frame = tk.Frame(root, padx=10, pady=10)
        frame.pack()
        # Crea la etiqueta de instrucciones
        tk.Label(frame, text="Selecciona categoría:").pack()
        # Crea los botones para abrir cada tipo de conversor
        tk.Button(frame, text="Monedas", width=20, command=self.abrir_monedas).pack(pady=5)
        tk.Button(frame, text="Longitud", width=20, command=self.abrir_longitud).pack(pady=5)
        tk.Button(frame, text="Temperatura", width=20, command=self.abrir_temperatura).pack(pady=5)

    # Función para abrir la ventana de conversión de monedas
    def abrir_monedas(self):
        win = tk.Toplevel() # Crea una nueva ventana secundaria
        win.title("Monedas") # Establece el título de la ventana
        # Crea el widget conversor con las monedas disponibles
        Convertidor(win, CURRENCIES, self.convertir_monedas)

    # Función para convertir entre diferentes monedas
    def convertir_monedas(self, valor, desde, hasta):
        if desde == hasta: # Si es la misma moneda, devuelve el mismo valor
            return valor
        if desde == "EUR": # Si convierte desde Eur
            rate = RATES.get(("EUR", hasta)) # Busca la tasa de cambio
            if rate:
                return valor * rate # Multiplica por la tasa
        elif hasta == "EUR": # Si convierte hacia Eur
            rate = RATES.get(("EUR", desde)) # Busca la tasa de cambio
            if rate:
                return valor / rate # Divide por la tasa
        else:
            # Conversión entre monedas no-EUR usando EUR como intermediario
            rate1 = RATES.get(("EUR", desde)) # Tasa desde la moneda origen a EUR
            rate2 = RATES.get(("EUR", hasta)) # Tasa desde EUR a la moneda destino
            if rate1 and rate2:
                eur = valor / rate1 # Convierte a EUR
                return eur * rate2 # Convierte desde EUR a la moneda destino
        # Si no hay tasa disponible, devuelve 0
        return 0.0

    # Función para abrir la ventana de conversión de longitud
    def abrir_longitud(self):
        win = tk.Toplevel() # Crea una nueva ventana secundaria
        win.title("Longitud") # Establece el título de la ventana
        unidades = list(LENGTH_UNITS.keys()) # Obtiene la lista de unidades de longitud
        # Crea el widget conversor con las unidades de longitud
        Convertidor(win, unidades, self.convertir_longitud)

    # Función para convertir entre diferentes unidades de longitud
    def convertir_longitud(self, valor, desde, hasta):
        # Convierte primero a metros y luego a la unidad destino
        metros = valor * LENGTH_UNITS[desde] # Convierte a metros
        return metros / LENGTH_UNITS[hasta] # Convierte desde metros a la unidad destino

    # Función para abrir la ventana de conversión de temperatura
    def abrir_temperatura(self):
        win = tk.Toplevel() # Crea una nueva ventana secundaria
        win.title("Temperatura") # Establece el título de la ventana
        # Crea el widget conversor con las unidades de temperatura
        Convertidor(win, TEMPS, self.convertir_temperatura)

    # Función para convertir entre diferentes unidades de temperatura
    def convertir_temperatura(self, valor, desde, hasta):
        # Normaliza primero a Celsius y luego convierte a la unidad destino
        if desde == hasta: # Si es la misma unidad, devuelve el mismo valor
            return valor
        if desde == "Celsius": # Si ya está en Celsius
            c = valor
        elif desde == "Fahrenheit": # Si viene de Fahrenheit
            c = (valor - 32) * 5/9 # Convierte a Celsius
        elif desde == "Kelvin": # Si viene de Kelvin
            c = valor - 273.15 # Convierte a Celsius
        # Convierte desde Celsius a la unidad destino
        if hasta == "Celsius": # Si el destino es Celsius
            return c
        elif hasta == "Fahrenheit": # Si el destino es Fahrenheit
            return c * 9/5 + 32 # Convierte desde Celsius
        elif hasta == "Kelvin": # Si el destino es Kelvin
            return c + 273.15 # Convierte desde Celsius

# Clase que crea el widget de conversión reutilizable para cada tipo de unidad
class Convertidor:
    # Inicializa el widget de conversión con los elementos de la interfaz
    def __init__(self, parent, units, convert_fn):
        self.convert_fn = convert_fn # Función de conversión específica para este tipo de unidad
        self.updating = False # Variable para evitar actualizaciones recursivas
        # Crea el marco principal con espaciado interno
        frame = tk.Frame(parent, padx=10, pady=10)
        frame.pack()
        # Crea un marco superior para los controles de entrada
        top = tk.Frame(frame)
        top.pack(pady=5)
        # Crea la etiqueta y campo para el valor a convertir
        tk.Label(top, text="Valor:").grid(row=0, column=0, sticky="e")
        self.val_var = tk.StringVar() # Variable para el valor de entrada
        tk.Entry(top, textvariable=self.val_var, width=15).grid(row=0, column=1, padx=5)
        # Crea las variables para las unidades de origen y destino
        self.from_var = tk.StringVar(value=units[0]) # Unidad de origen la primera de la lista
        self.to_var = tk.StringVar(value=units[1] if len(units) > 1 else units[0]) # Unidad de destino la segunda de la lista o la primera
        # Crea los comboboxes para seleccionar las unidades
        ttk.Combobox(top, values=units, textvariable=self.from_var, width=15, state="readonly").grid(row=1, column=0, padx=5, pady=5)
        ttk.Combobox(top, values=units, textvariable=self.to_var, width=15, state="readonly").grid(row=1, column=1, padx=5, pady=5)
        # Crea el botón para conversión manual
        tk.Button(frame, text="Convertir", command=self.convertir_manual).pack(pady=5)
        # Crea un marco para mostrar el resultado
        resf = tk.Frame(frame)
        resf.pack()
        tk.Label(resf, text="Resultado:").pack(side="left")
        self.result_label = tk.Label(resf, text="0") # Etiqueta que muestra el resultado
        self.result_label.pack(side="left", padx=5)

        # Configura la conversión en tiempo real: se actualiza cuando cambia el valor o las opciones
        self.val_var.trace_add("write", lambda *a: self.convertir_auto())
        self.from_var.trace_add("write", lambda *a: self.convertir_auto())
        self.to_var.trace_add("write", lambda *a: self.convertir_auto())

    # Función para conversión automática en tiempo real
    def convertir_auto(self):
        if self.updating: # Evita las llamadas recursivas infinitas
            return
        self.updating = True
        try:
            # Intenta convertir el valor de entrada a número decimal
            v = float(self.val_var.get())
            # Llama a la función de conversión específica con el valor y las unidades
            res = self.convert_fn(v, self.from_var.get(), self.to_var.get())
            # Actualiza la etiqueta del resultado con formato de 6 dígitos significativos
            self.result_label.config(text=f"{res:.6g}")
        except:
            # Si hay error en la conversión, limpia el resultado si el campo está vacío
            if self.val_var.get().strip() == "":
                self.result_label.config(text="0")
        self.updating = False

    # Función para conversión manual
    def convertir_manual(self):
        self.convertir_auto() # Simplemente llama a la conversión automática

# Inicia la aplicación principal
if __name__ == "__main__":
    root = tk.Tk() # Crea la ventana principal
    MultiConvertidor(root) # Crea una instancia de la clase MultiConvertidor
    root.mainloop() # Inicia el bucle principal de la ventana