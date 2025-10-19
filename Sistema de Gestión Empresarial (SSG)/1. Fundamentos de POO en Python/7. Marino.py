# byAlexLR

# 7. Crear una clase llamada Marino(), con un método que sea hablar, en donde muestre un
# mensaje que diga «Hola, soy un animal marino!». Luego, crear una clase Pulpo() que herede
# Marino, pero modificar el mensaje de hablar por «Hola soy un Pulpo!».
# Por último, crear una clase Foca(), heredada de Marino, pero que tenga un atributo nuevo
# llamado mensaje y que muestre ese mensaje como parámetro.

# Clase Marino.
class Marino:
    # Método hablar que muestra un mensaje.
    def hablar(self):
        print("¡Hola, soy un animal marino!")

# Clase Pulpo que hereda de Marino.
class Pulpo(Marino):
    # Método hablar que muestra un mensaje.
    def hablar(self):
        print("¡Hola, soy un Pulpo!") 

# Clase Foca que hereda de Marino.
class Foca(Marino):
    # Método hablar que recibe un mensaje como parámetro y lo muestra.
    def hablar(self, mensaje):
        self.mensaje = mensaje
        print(mensaje)

# Declaración de los distintos tipos de animales marinos e impresión de sus mensajes.
marino = Marino()
marino.hablar()

pulpo=Pulpo()
pulpo.hablar()

foca=Foca()
foca.hablar("¡Hola, soy una foca!")