# byAlexLR

# 2. Crea una clase Persona, con los atributos nombre y edad. Además, hay que crear un método Cumpleaños, 
# que aumente la edad de la persona cuando se invoque sobre un objeto de la clase persona.

# Declaración de la clase Persona
class Persona:
    # Método constructor de la clase Persona
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad
    
    # Método que simula el cumpleaños de la persona
    def cumple(self):
        print("¡Es tu cumpleaños, felicidades!")
        self.edad += 1 # Aumenta la edad en 1
        return self.edad
    
    # Método que muestra la información de la persona
    def mostrarInformacion(self):
        print(f"{self.nombre} tienes {self.edad} años de edad.")
        
# Solicita al usuario que ingrese los datos de la persona 
print("Introduce los siguientes datos:")
nombreP = input("Ingrese el nombre: ")
edadP = int(input("Ingrese la edad: "))
cumpleP = input("¿Es tu cumpleaños?(si/no): ").lower() # Convierte la respuesta a minúsculas para evitar errores

# Crea un objeto de la clase Persona
miPersona = Persona(nombreP, edadP)
# Si es el cumpleaños, llama al método cumple para aumentar la edad
if cumpleP == "si":
    miPersona.cumple()
# Muestra la información de la persona
miPersona.mostrarInformacion()