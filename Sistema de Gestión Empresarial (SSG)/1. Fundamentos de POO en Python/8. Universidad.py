# byAlexLR

# 8. Desarrollar un programa con tres clases: La primera debe ser Universidad, 
# con atributos nombre (Donde se almacena el nombre de la Universidad). La segunda 
# llamada Carrera, con los atributos especialidad (En donde me guarda la especialidad
# de un estudiante). Y por último, una llamada Estudiante, que tenga como atributos su
# nombre y edad. El programa debe imprimir la especialidad, edad, nombre y universidad 
# de dicho estudiante con un objeto llamado persona.

# Clase Universidad.
class Universidad:
    # Método constructor que recibe el nombre de la universidad.
    def __init__(self, nombre):
        self.nombre = nombre
        
    # Método para mostrar el nombre de la universidad.
    def mostrar_universidad(self):
        return self.nombre

# Clase Carrera que hereda de Universidad.
class Carrera(Universidad):
    # Método constructor que recibe la especialidad y el nombre de la universidad.
    def __init__(self, especialidad, nombre):
        super().__init__(nombre)
        self.especialidad = especialidad

    # Método para mostrar la especialidad y el nombre de la universidad.
    def mostrar_carrera(self):
        return f"Especialidad: {self.especialidad}, Universidad: {self.mostrar_universidad()}"

# Clase Estudiante que hereda de Carrera.
class Estudiante(Carrera):
    # Método constructor que recibe el nombre, edad, especialidad y nombre de la universidad.
    def __init__(self, nombre, edad, especialidad, nombre_universidad):
        super().__init__(especialidad, nombre_universidad)
        self.nombre = nombre
        self.edad = edad

    # Método para mostrar los datos completos del estudiante.
    def mostrar_datos(self):
        return f"Nombre: {self.nombre}, Edad: {self.edad} - {self.mostrar_carrera()}"
    
# Declaración de los distintos estudiantes e impresión de sus datos, con un bucle for.
estudiantes = [Estudiante("Ana", 21, "Ingeniería Informática", "Universidad de Sevilla"), Estudiante("Luis", 22, "Medicina", "Universidad Complutense de Madrid")]
for estudiante in estudiantes:
    print(estudiante.mostrar_datos())