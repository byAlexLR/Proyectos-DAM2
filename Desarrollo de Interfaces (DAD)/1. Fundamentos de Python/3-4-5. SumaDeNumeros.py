# byAlexLR

# Programa que sume los n primeros números.
# Pide al usuario que introduzca un número.
num = int(input("Introduce un número para realizar la suma: "))

# Inicializa las variables.
suma = 0
sumaPares = 0
TotalPares = 0

# Muestra un mensaje con el apartado.
print("-- Suma de n Números --")
# Bucle for que suma los n primeros números y lo muestra posteriormente.
for i in range(num + 1):
    suma += i # Suma i a la variable.
print("La suma total de los",num,"primeros números es:",suma)

# Programa que sume los pares inferiores a n.
# Muestra un mensaje con el apartado.
print("\n-- Suma de los Números Pares Menores que N --")
# Bucle for que suma los n números pares menores que n y lo muestra posteriormente.
for i in range(num + 1):
    if (i % 2 == 0): # Comprueba si el número es par.
        sumaPares += i # Si es par lo suma a la variable.
        TotalPares += 1 # Suma un dígito al contador.
print("El total de la suma de los números pares es:",sumaPares,". Se han sumado",TotalPares,"números pares, incluyendo el valor 0.")

# Programa que sume los n primeros números pares.
# Muestra un mensaje con el apartado.
print("\n-- Suma de los n Primeros Números Pares --")
# Bucle for que suma los n primeros números pares y lo muestra posteriormente.
sumaPares = 0 # Resetea la variable.
for i in range(num + 1):
    sumaPares += (i * 2) # Multiplica i por 2 y lo suma a la variable pares.
print("El total de la suma de los",num,"primeros números pares es:",sumaPares)