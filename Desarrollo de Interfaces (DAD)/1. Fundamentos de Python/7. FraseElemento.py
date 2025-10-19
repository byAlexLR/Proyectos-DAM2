# byAlexLR

# Programa que solicite una frase y que luego muestre un elemento concreto pedido al usuario.
# Pide al usuario que introduzca una frase y una posición. Posteriormente, lo almacena en una variable.
frase = input("Por favor, introduce una frase: ")
posicion = int(input("Indica la posición del elemento que quieres mostrar: "))

for i in range(len(frase)): # Recorre la frase.
    if i == posicion: # Si la posición es igual a la introducida por el usuario, muestra el elemento.
        print(f"El elemento de la posición {posicion} es: {frase[i]}")
        break # Rompe el bucle.
    elif posicion >= len(frase): # Si la posición es mayor que la longitud de la frase, muestra un mensaje de error.
        print("Error. La posición introducida es mayor que la longitud de la frase.")
        break # Rompe el bucle.
    elif posicion < 0: # Si la posición es menor que 0, muestra un mensaje de error.
        print("Error. La posición introducida es menor que 0.")
        break # Rompe el bucle.