def entradas():
    # Pregunta al usuario su nombre y edad, la almacena en una variable.
    nombre = str(input("Introduce tu nombre: "))
    edad= int(input("Introduce tu edad: "))
    
    # Comparamos las edades y según la introducida muestra un mensaje u otro.
    if(edad < 4):
        print("Pasa gratis")
    elif (edad >= 4 or edad <= 18):
        print("Debes pagar 5€")
    else:
        print("Tienes que pagar 10€")
    
def main():
    entradas()
    
main()