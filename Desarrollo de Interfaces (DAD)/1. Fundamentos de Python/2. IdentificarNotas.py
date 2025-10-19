# byAlexLR

# Hacer un programa que al introducir una calificación, se muestre por pantalla si esta es INSUFICIENTE, SUFICIENTE, BIEN, NOTABLE O SOBRESALIENTE.
# Pregunta al usuario la nota del alumno.
nota = int(input("Introduce la nota del alumno (0-10): "))

# Según el valor introducido muestra un mensaje u otro.
if nota < 0 or nota > 10: # Si el número no está comprendido entre 0 y 10, muestra el mensaje.
    print("Dato introducido incorrecto.")
elif nota >= 9: # Si la nota es igual o mayor a 9 muestra el mensaje.
    print("Sobresaliente")
elif nota >= 7: # Si la nota es igual o mayor a 7 muestra el mensaje.
    print("Notable")
elif nota >= 6: # Si la nota es igual o mayor a 6 muestra el mensaje.
    print("Bien")
elif nota >= 5: # Si la nota es igual o mayor a 5 muestra el mensaje.
    print("Suficiente")
elif nota >= 0: # Si la nota es igual o mayor a 0 muestra el mensaje.
    print("Insuficiente")