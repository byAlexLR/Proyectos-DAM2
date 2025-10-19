# byAlexLR

# Programa que al introducir un cadena y contar las veces que se repite una letra.
# Pide al usuario que introduzca una frase y una letra. Posteriormente, lo almacena en una variable.
frase = input("Por favor, introduce una frase: ")
letra = input("Indica una letra a contar: ")

# Inicializa el contador.
contador = 0

# Bucle for que itera cada letra de la frase y comprueba si es la letra introducida.
for i in frase:
    if i == letra: # Si i es igual a letra, suma uno al contador.
        contador += 1

# Invierte la frase.
fraseInvertida = frase[::-1]

# Muestra el resultado por pantalla.
print("La letra",letra,"aparece",contador,"veces en la frase.\nLa frase invertida sería:",fraseInvertida)