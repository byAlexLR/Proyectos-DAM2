# byAlexLR

# Hacer una calculadora, cuyas operaciones sean funciones. Considerando que para sumar, restar y multiplicar hay n elementos.
# Declaración de las variables.
condi = True
lista = []

#Bucle While, que pide que introduzcas el número hasta que finalices el bucle utilizando "=", los almacena en una lista en formato float, en caso de no ser válido muestra un error.
while condi:
    elemento = input("Introduce el número(Para salir es '='): ")
    if elemento == "=":
        condi = False
    else:
        try:
            lista.append(float(elemento))
        except ValueError:
            print("Por favor, introduce un número válido.")
# Muestra los números introducidos
print(lista)

# Función que realiza la suma de todos los números de la lista y lo muestra por pantalla.
def sumarNumeros():
    resultado = 0
    for i in lista: # Realiza la suma de todos los dígitos de la lista utilizando el valor i.
        resultado += i
    print(f"El resultado de la suma es: {resultado}")

# Función que realiza la resta de todos los números de la lista y lo muestra por pantalla.
def restarNumeros():
    resultado = lista[0] # Resultado tiene el valor de 0 de la lista.
    for i in lista[1:]: # Realiza la resta de todos los dígitos de la lista empezando en el valor 1 de la lista.
        resultado -= i
    print(f"El resultado de la resta es: {resultado}")
    
# Función que realiza la multiplicación de todos los números de la lista y lo muestra por pantalla.
def multiplicarNumeros():
    resultado = 1 # Inicializa la variable en 1.
    for i in lista: # Realiza la multiplicación de todos los dígitos de la lista.
        resultado *= i
    print(f"El resultado de la multiplicación es: {resultado}")    

# Función que realiza la división de los números introducidos, mientras sean dos números y lo muestra por pantalla.
# En caso contrario mostrará un mensaje si hay más de dos números, el divisor es 0.
def dividirNumeros():
    if len(lista) == 0:
        print("No hay números para dividir.")
    elif len(lista) == 1:
        print("Se necesita al menos un divisor.")
    elif len(lista) > 2:
        print("Solo se pueden dividir dos números a la vez.")
    elif lista[1] == 0:
        print("No se puede dividir entre cero.")
    else:
        resultado = lista[0] / lista[1] # Resultado es igual al valor 0 entre el valor 1.
        print(f"El resultado de la división es: {resultado}")

# Pregunta al usuario que quiere realizar con los números.
elige = int(input("Indica la operación a realizar:\n1.Sumar\n2.Restar\n3.Multiplicar\n4.Dividir\nEscoge una opción: "))
# Según la opción introducida llama a una función.
if elige == 1:
    sumarNumeros() # Llama a la función de sumar.
elif elige == 2:
    restarNumeros() # Llama a la función de restar.
elif elige == 3:
    multiplicarNumeros() # Llama a la función de multiplcación.
elif elige == 4:
    dividirNumeros() # Llama a la función de dividir.
else:
    print("La opción introducida es incorrecta.") # En caso de que la opción no corresponda a ningún caso.