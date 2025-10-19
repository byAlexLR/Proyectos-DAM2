# byAlexLR

# Programa que cuenta los n primeros números primos.
# Pide al usuario que introduzca un número, para almacenarlo en una variable.

# Función que comprueba si el número es primo o no.
def compruebaPrimo(inicia):
    # Si el valor es menor o igual a uno retorna un false.
    if inicia <= 1:
        return False
    # Recorre desde el 2 hasta la raíz cuadrada del número.
    for i in range(2, int(inicia**0.5)+1):
        # Si el número es divisible por cualquiera de estos números, no es primo.
        if inicia % i == 0:
            return False
    # Si no es divisible por ninguno, es primo.
    return True

# Función que busca los n primeros números primos.
def buscarPrimos(n):
    # Lista para almacenar los números primos encontrados e inicialiación de la variable inicia con el valor 2.
    primos = []
    inicia = 2
    # Bucle While que continúa hasta encontrar n números primos.
    while len(primos) < n:
        # Si el número es primo, se añade a la lista.
        if compruebaPrimo(inicia):
            primos.append(inicia)
        # Incrementa el valor de inicia para comprobar el siguiente número.
        inicia += 1
    # Devuelve la lista de números primos encontrados.
    return primos

# Pide al usuario que introduzca un número.
num = int(input("Introduce un número para contar los n primeros números primos: "))
# Si el número es positivo, llama a la función buscarPrimos y muestra los resultados.
if num > 0:
    nPrimos = buscarPrimos(num)
    print(f"Los primeros {num} números primos son: {nPrimos}")
# Si el número no es positivo, muestra un mensaje de error.
else:
    print("Por favor, ingresa un número positivo.")