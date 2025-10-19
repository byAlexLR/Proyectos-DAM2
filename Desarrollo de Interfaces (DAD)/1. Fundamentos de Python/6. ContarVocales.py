# byAlexLR

# Programa que cuente la cantidad total de vocales, de "a", "e", "i", "o" y "u". De una cadena solicitada al usuario.
# Pide al usuario que introduzca una palabra o texto.
texto = input("Introduce una palabra o texto: ").lower() # Pasa la frase a minús. para contabilizar todas las letras correctamente.
# Inicializa las variables.
vocales = ["a","e","i","o","u"] # Array con las diferentes vocales.
contador = 0 # Contador inicializado en 0.
contadorEspacios = 0 # Contador inicializado en 0.

# Bucle for que itera cada letra del texto y comprueba si es una vocal o un espacio, y lo muestra por pantalla.
for i in texto:
    if i in vocales: # Comprueba si i tiene el valor de alguna vocal y suma uno al contador.
        contador += 1
    elif i == " ": # Comprueba si i es un espacio y suma uno al contador.
        contadorEspacios += 1
print("El número de vocales encontradas es:",contador,". El número de espacios es:",contadorEspacios)