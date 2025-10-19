# byAlexLR

# Calculadores geométrica usando funciones. Hay que calcular el Área del rectángulo, triángulo y circulo, así como, el volumen de un prisma y una esfera.
# Importa la librería math para realizar operaciones matemáticas.
import math

# Funciones para calcular las áreas y volúmenes.
def areaTriangulo():
    base = float(input("Introduce la base del triángulo: ")) # Pide al usuario la base y la almacena en la variable.
    altura = float(input("Introduce la altura del triángulo: ")) # Pide al usuario la altura y la almacena en la variable.
    area = (base * altura) / 2 # Calcula Área del triángulo, base por altura entre dos.
    print(f"El área del triángulo es: {area}") # Muestra el resultado.
    
def areaRectangulo():
    base = float(input("Introduce la base del rectángulo: ")) # Pide al usuario la base y la almacena en la variable.
    altura = float(input("Introduce la altura del rectángulo: ")) # Pide al usuario la altura y la almacena en la variable.
    area = base * altura # Calcula Área del rectángulo, la base por la altura.
    print(f"El área del rectángulo es: {area}") # Muestra el resultado.

def areaCirculo():
    radio = float(input("Introduce el radio del cÍrculo: ")) # Pide al usuario el radio y lo almacena en la variable.
    area = math.pi * radio ** 2 # Calcula Área del círculo, hace pi por radio elevado a dos.
    print(f"El área del círculo es: {area}") # Muestra el resultado.
    
def volumenPrisma():
    longitud = float(input("Introduce la longitud del prisma:")) # Pide al usuario la longitud y la almacena en la variable.
    ancho = float(input("Introduce el ancho del prisma: ")) # Pide al usuario el ancho y lo almacena en la variable.
    altura = float(input("Introduce la altura del prisma: ")) # Pide al usuario la altura y la almacena en la variable.
    volumen = longitud * ancho * altura # Calcula Volumen del prisma, la longitud por el ancho y la altura.
    print(f"El volumen del prisma es: {volumen}") # Muestra el resultado.
    
def volumenEsfera():
    radio = float(input("Introduce el radio de la esfera: ")) # Pide al usuario el radio y lo almacena en la variable.
    volumen = (4/3) * math.pi * (radio ** 3) # Calcula Volumen de la esfera, hace cuatro entre tres por pi por radio elevado al cubo.
    print(f"El volumen de la esfera es: {volumen}") # Muestra el resultado.

# Pregunta al usuario que quiere realizar.
elige = int(input("Indica la operación a realizar:\n1.Área Triángulo\n2.Área Rectángulo\n3.Área Círculo\n4.Volumen Prisma\n5.Volumen Esfera\nEscoge una opción: "))
# Según la opción introducida llama a una función.
if elige == 1:
    areaTriangulo() # Llama a la función del Triángulo.
elif elige == 2:
    areaRectangulo() # Llama a la función del Rectángulo.
elif elige == 3:
    areaCirculo() # Llama a la función del Círculo.
elif elige == 4:
    volumenPrisma() # Llama a la función del Prisma.
elif elige == 5:
    volumenEsfera() # Llama a la función de la Esfera.
else:
    print("La opción introducida es incorrecta.") # Si no corresponde a una opción.