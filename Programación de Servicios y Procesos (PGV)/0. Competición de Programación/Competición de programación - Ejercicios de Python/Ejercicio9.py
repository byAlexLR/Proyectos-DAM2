import unicodedata

# Usa la libreria "unicodedata", la cual sirve para ignorar las tildes a la hora de comprobar las vocales proximamente
def eliminar_tildes(cadena):
    # Normaliza la cadena a la forma NFD (composición de caracteres)
    # y luego se unen los caracteres que no son de la categoría 'Mn' (Marca sin espacio)
    s = ''.join((c for c in unicodedata.normalize('NFD', cadena) if unicodedata.category(c) != 'Mn'))
    # Devuelve la cadena convirtiendo los caracteres con tilde a sin tilde
    return s

# Cuenta cada vocal en la frase escrita al inicio del programa
def contarVocal():
    # Declaracion de variables
    palabra = str(input("Introduce una frase por favor: "))
    palabra = eliminar_tildes(palabra) # Se usa la función "eliminar_tildes" para ignorar las tildes a la hora de comparar
    vocales = ["a", "e", "i", "o", "u"]
    cont_a : int = 0
    cont_e : int = 0
    cont_i : int = 0
    cont_o : int = 0
    cont_u : int = 0
    for i in vocales: # Recorre cada vocal
        for j in palabra: # Recorre cada caracter de la palabra
            if i == j.lower(): # Si la palabra tiene mayúsculas las pone minúscula y comprueba si coincide si es una vocal
                # Comprueba cada vocal para poder añadir cuantas hay singularmente
                if j.lower() == "a":
                    cont_a += 1
                elif j.lower() == "e":
                    cont_e += 1
                elif j.lower() == "i":
                    cont_i += 1
                elif j.lower() == "o":
                    cont_o += 1
                elif j.lower() == "u":
                    cont_u += 1
    print(f"Total: \ncantidad a: {cont_a}; \ncantidad e: {cont_e}; \ncantidad i: {cont_i}; \ncantidad o: {cont_o};  \ncantidad u: {cont_u};")

def main():
    contarVocal()

main()