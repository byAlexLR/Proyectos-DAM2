# byAlexLR

# 5. Crear una clase "Persona" que sea la clase padre de otra clase "Estudiante". Por tanto: En la clase "Persona" su método __init__()
# debe de estar preparando para recibir nombre y apellido. Además , en esta clase, debe tener un método para mostrar nombre_completo() el
# cual debe mostrar el nombre acompañado del apellido. La otra clase "Estudiante", debe de poder heredar de "Persona" y además recibir los
# argumentos nombre y edad. También la clase "Estudiante", recibe el valor "carrera", y además contar con un método mostrar_carrera().

# Clase Persona.
class Persona:
    # Constructor que recibe nombre, apellido y edad.
    def __init__(self, nombre, apellido, edad):
        self.nombre = nombre
        self.apellido = apellido
        self.edad = edad

    # Método para mostrar el nombre completo junto con la edad.
    def nombre_completo(self):
        return f"Nombre Completo: {self.nombre} {self.apellido} - Edad: {self.edad}"

# Clase Estudiante que hereda de Persona.
class Estudiante(Persona):
    # Constructor que recibe nombre, apellido, edad y carrera.
    def __init__(self, nombre, apellido, edad, carrera):
        super().__init__(nombre, apellido, edad)
        self.carrera = carrera

    # Método para mostrar la carrera del estudiante.
    def mostrar_carrera(self):
        return f"Carrera: {self.carrera}"

# Declaración de los estudiantes e impresión de sus datos, con un bucle for.
estudiante1 = Estudiante("Juan", "Perez", 20, "Ingeniería")
estudiante2 = Estudiante("Ana", "Gomez", 22, "Medicina")
estudiantes = [estudiante1, estudiante2]

for estudiante in estudiantes:
    print(estudiante.nombre_completo() + " - " + estudiante.mostrar_carrera())