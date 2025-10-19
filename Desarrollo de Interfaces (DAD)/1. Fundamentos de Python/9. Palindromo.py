# byAlexLR

# Programa que detecte si una cadena es palíndromo o no.
# Pide al usuario que introduzca una palabra. Posteriormente, lo almacena en una variable.
frase = input("Introduce una palabra: ")

# Invierte la frase.
for i in range(len(frase) // 2): # Recorre la frase hasta la mitad.
    if frase[i] != frase[-(i + 1)]: # Si el carácter de la posición i es distinto al carácter de la posición -(i+1), no es palíndromo.
        print("No es palíndromo")
        break # Rompe el bucle.
    else: # Si el bucle for termina sin encontrar diferencias, es palíndromo.
        print("Es palíndromo")
        break # Rompe el bucle.