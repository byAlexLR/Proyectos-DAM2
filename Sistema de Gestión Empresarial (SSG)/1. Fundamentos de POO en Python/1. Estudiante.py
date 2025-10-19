# byAlexLR

# 1. Realizar un programa que conste de una clase llamada Estudiante, que tenga como atributos el nombre y la nota del alumno. 
# Definir los métodos para iniciar sus atributos, imprimirlos y mostrar un mensaje con el resultado de la nota y si ha aprobado o no.

# Declaración de la clase Estudiante
class Estudiante:
    # Método constructor de la clase Estudiante
    def __init__(self, nombre, nota):
        self.nombre = nombre
        self.nota = nota
    
    # Método que comprueba si el alumno ha aprobado o no
    def comprobarAprobado(self):
        if self.nota < 0 or self.nota > 10: # Si el número no está comprendido entre 0 y 10, muestra el mensaje.
            return "Dato introducido incorrecto."
        elif self.nota >= 9: # Si la nota es igual o mayor a 9 muestra el mensaje.
            return "Sobresaliente"
        elif self.nota >= 7: # Si la nota es igual o mayor a 7 muestra el mensaje.
            return "Notable"
        elif self.nota >= 6: # Si la nota es igual o mayor a 6 muestra el mensaje.
            return "Bien"
        elif self.nota >= 5: # Si la nota es igual o mayor a 5 muestra el mensaje.
            return "Suficiente"
        elif self.nota >= 0: # Si la nota es igual o mayor a 0 muestra el mensaje.
            return "Insuficiente"
    
    # Método que muestra la información del alumno
    def mostrarInformacion(self):
        print(f"Alumno: {self.nombre} - Nota: {self.nota} | Resultado: {self.comprobarAprobado()}")

# Solicita al usuario que ingrese los datos del alumno
print("Introduce los siguientes datos del alumno:")
nombreA = input("Ingrese el nombre: ")
notaA = float(input("Ingrese la nota: "))

# Crea un objeto de la clase Estudiante y muestra la información
miEstudiante = Estudiante(nombreA, notaA)
miEstudiante.mostrarInformacion()