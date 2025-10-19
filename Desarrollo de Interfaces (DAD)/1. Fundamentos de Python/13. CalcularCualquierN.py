# byAlexLR

# Calcular para cualquier N. (Sumatorio)
# Pide al usuario que introduzca un número.
n = int(input("Introduce un número para hacer el sumatorio: "))

# Inicializa las variables.
facto = 1 # Facto en valor 1 para multiplicar.
resultado = 0

# Bucle for que calcula el sumatorio y lo muestra posteriormente.
for i in range(1, n + 1):
    facto *= i # Multiplica el valor de i con el facto
    calculo = (i * (i - 1)) / facto # Calculo es igual a i por i menos 1 entre facto
    resultado += calculo # Lo suma a la variable resultado.
print(f"El resultado es: {round(resultado, 3)}") # Imprime el resultado redondeado a tres decimales.