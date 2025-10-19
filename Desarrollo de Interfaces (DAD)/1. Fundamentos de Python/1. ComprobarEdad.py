# byAlexLR

# Hacer un programa que tras la lectura por pantalla de la edad de una persona, determina si es o no mayor de edad.
# Da la bienvenida. Luego, pregunta el nombre y la edad para almacenarlo en una variable.
print("Bienvenido a la plataforma de comprobación de edad.")
nombre = input("Podría indicarme su nombre: ")
edad = input("De acuerdo, " + nombre + ".Indica tu edad por favor: ")
# Según el valor introducido muestra un mensaje u otro. Si es mayor o igual a 18 muestra que es mayor, sino, menor.
if int(edad) >= 18:
    print("Eres mayor de edad, puedes acceder a la plataforma.")
else:
    print("Eres menor de edad, no puedes acceder a la plataforma.")