# 1. Crear una ventana con un contador que incremente y decremente
# su valor usando botones. Mostrar el valor actual en una etiqueta.

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import *

# Función para aumentar el contador en 1 y actualizar la etiqueta
def incrementar():
    global contador
    contador += 1
    etiqueta_contador.config(text=str(contador))

# Función para disminuir el contador en 1 y actualizar la etiqueta
def decrementar():
    global contador
    contador -= 1
    etiqueta_contador.config(text=str(contador))

# Crea la ventana principal
ventana = tk.Tk()
ventana.title("Contador")

# Centra la ventana en la pantalla
window_width = 300 # Ancho de la ventana
window_height = 120 # Alto de la ventana
screen_width = ventana.winfo_screenwidth() # Ancho de la pantalla
screen_height = ventana.winfo_screenheight() # Alto de la pantalla
# Calcula la posición x e y para centrar la ventana
center_x = int((screen_width / 2) - (window_width / 2))
center_y = int((screen_height / 2) - (window_height / 2))
# Establece las dimensiones y la posición de la ventana
ventana.geometry(f'{window_width}x{window_height}+{center_x}+{center_y}')

# Define la variable global del contador
contador = 0

# Crea la etiqueta que muestra el número actual del contador
etiqueta_contador = tk.Label(ventana, text=str(contador), font=("Arial", 24))
etiqueta_contador.pack(pady=10) # Agrega espacio vertical alrededor de la etiqueta

# Crea un marco para los botones
frame_botones = tk.Frame(ventana)
frame_botones.pack()

# Crea el botón de incrementar
boton_incrementar = tk.Button(frame_botones, text="Incrementar", command=incrementar)
boton_incrementar.pack(side=tk.LEFT, expand=True, padx=10, pady=5) # Lo coloca a la izquierda del marco

# Crea el botón de decrementar
boton_decrementar = tk.Button(frame_botones, text="Decrementar", command=decrementar)
boton_decrementar.pack(side=tk.RIGHT, expand=True, padx=10, pady=5) # Lo coloca a la derecha del marco

# Inicia el bucle principal de la ventana
ventana.mainloop()