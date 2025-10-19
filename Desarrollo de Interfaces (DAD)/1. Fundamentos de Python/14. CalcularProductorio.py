# byAlexLR

# Calcular el productorio.
# Pide al usuario que introduzca un número.
n = int(input("Introduce un número para hacer el productorio: "))

# Inicializa las variables.
resultado = 1 # Variable que almacenará el resultado del productorio.

# Bucle for que calcula el productorio y lo muestra posteriormente.
for i in range(1, n + 1): # Recorre los números desde 1 hasta n.
    resultado *= i # Resultado es igual a resultado por el valor de i.
print(f"El resultado es: {resultado}")