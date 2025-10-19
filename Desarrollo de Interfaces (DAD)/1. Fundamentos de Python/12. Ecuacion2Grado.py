# byAlexLR

# Programa que resuelva una ecuación de segundo grado.
# Importa la librería math para realizar operaciones matemáticas.
import math

# Pide al usuario que introduzca los valores de a, b y c, para almacenarlos en sus correspondidas variables.
a = float(input("Introduce el valor de a: "))
b = float(input("Introduce el valor de b: "))
c = float(input("Introduce el valor de c: "))

# Calcula el discriminante.
discriminante = b ** 2 - 4 * a * c # Realiza b al cuadrado menos cuatro por a por c.

# Según el valor del discriminante muestra un mensaje u otro.
if discriminante < 0: # Si el discriminante es menor a 0.
    print("La ecuación no tiene solución real.")
else: 
    # Calcula las dos soluciones de la ecuación.
    raiz = math.sqrt(discriminante) # Realiza la raíz del discriminante.
    x1 = (-b + raiz) / (2 * a) # Realiza -b más la raíz entre dos por a.
    x2 = (-b - raiz) / (2 * a) # Realiza -b menos la raíz entre dos por a.
    print(f"La solución de la ecuación de {a}x^2 + {b}x + {c} = 0, es: x1 = {x1} y x2 = {x2}") # Muestra la solución.