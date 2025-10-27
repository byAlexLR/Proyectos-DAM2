# 2. Crear una calculadora simple que sume dos números ingresados en campos de texto.

# Importa la biblioteca tkinter para la interfaz gráfica
import tkinter as tk
from tkinter import *

# Variable global para saber si ya se ingresó un número o resultado
nuevo_numero = True

# Función para actualizar el valor de la entrada
def actualizar(entrada, texto):
    global nuevo_numero

    # Si se introduce un nuevo número, borra el contenido anterior
    if nuevo_numero:
        entrada.delete(0, END)
        nuevo_numero = False

    entrada.insert(END, texto)

# Función para borrar el contenido de la entrada
def borrar(entrada):
    entrada.delete(0, END)
    global nuevo_numero
    nuevo_numero = True

# Función para realizar las operaciones
def calcular(entrada):
    global nuevo_numero

    # Evalúa la expresión ingresada
    try:
        expresion = entrada.get()
        nueva_expresion = ""
        i = 0
        # Reemplaza los porcentajes por su equivalente decimal
        while i < len(expresion):
            if expresion[i].isdigit() or expresion[i] == ",":
                num = ""
                while i < len(expresion) and (expresion[i].isdigit() or expresion[i] == "." or expresion[i] == ","):
                    if expresion[i] == ",":
                        num += "."
                    else:
                        num += expresion[i]
                    i += 1
                if i < len(expresion) and expresion[i] == "%":
                    nueva_expresion += f"({num}/100)"
                    i += 1
                else:
                    nueva_expresion += num
            else:
                nueva_expresion += expresion[i]
                i += 1

        # Calcula el resultado usando eval
        resultado = str(eval(nueva_expresion))
        entrada.delete(0, END)
        entrada.insert(0, resultado)
        nuevo_numero = True
    except:
        # Si hay un error, muestra "Error"
        entrada.delete(0, END)
        entrada.insert(0, "Error")
        nuevo_numero = True

# Crear la ventana
ventana = Tk()
ventana.title("Calculadora")
# Centra la ventana en la pantalla
window_width = 392 # Ancho de la ventana
window_height = 432 # Alto de la ventana
screen_width = ventana.winfo_screenwidth() # Ancho de la pantalla
screen_height = ventana.winfo_screenheight() # Alto de la pantalla
# Calcula la posición x e y para centrar la ventana
center_x = int((screen_width / 2) - (window_width / 2))
center_y = int((screen_height / 2) - (window_height / 2))
# Establece las dimensiones y la posición de la ventana
ventana.geometry(f'{window_width}x{window_height}+{center_x}+{center_y}')
ventana.resizable(width=False, height=False)
ventana.config(bg="#E0E0E0")

# Dimensiones de los botones
botonAncho = 11
botonAlto = 3

# Crear el campo de entrada
entrada = Entry(ventana, font=("arial", 20, "bold"), width=24, insertwidth=4, justify="right")
entrada.place(x=15, y=60)

# Pone el foco (cursor) en el campo de entrada al iniciar
entrada.focus()

# Botones de la calculadora
Boton0 = Button(ventana, text="0", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "0")).place(x=107, y=360)
Boton1 = Button(ventana, text="1", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "1")).place(x=17, y=300)
Boton2 = Button(ventana, text="2", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "2")).place(x=107, y=300)
Boton3 = Button(ventana, text="3", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "3")).place(x=197, y=300)
Boton4 = Button(ventana, text="4", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "4")).place(x=17, y=240)
Boton5 = Button(ventana, text="5", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "5")).place(x=107, y=240)
Boton6 = Button(ventana, text="6", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "6")).place(x=197, y=240)
Boton7 = Button(ventana, text="7", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "7")).place(x=17, y=180)
Boton8 = Button(ventana, text="8", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "8")).place(x=107, y=180)
Boton9 = Button(ventana, text="9", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "9")).place(x=197, y=180)
BotonComa = Button(ventana, text=",", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, ",")).place(x=17, y=360)
BotonSuma = Button(ventana, text="+", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "+")).place(x=287, y=360)
BotonResta = Button(ventana, text="-", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "-")).place(x=287, y=300)
BotonMulti = Button(ventana, text="*", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "*")).place(x=287, y=240)
BotonDiv = Button(ventana, text="/", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "/")).place(x=287, y=180)
BotonRest = Button(ventana, text="%", width=botonAncho, height=botonAlto, command=lambda: actualizar(entrada, "%")).place(x=197, y=360)
BotonResu = Button(ventana, text="=", width=24, height=botonAlto, command=lambda: calcular(entrada)).place(x=197, y=120)
BotonC = Button(ventana, text="C", width=24, height=botonAlto, command=lambda: borrar(entrada)).place(x=17, y=120)

# Vincula las teclas Enter del teclado al botón de igual, para que muestre el resultado al presionarlas
ventana.bind('<Return>', lambda event: calcular(entrada))
ventana.bind('<KP_Enter>', lambda event: calcular(entrada))

# Inicia el bucle principal de la ventana
ventana.mainloop()
