# byAlexLR

# 6. Crear una clase Fabrica que tenga los atributos de Llantas, Color y Precio; luego crear dos
# clases más que hereden de Fabrica, las cuales son Moto y Carro.
# Crear métodos que muestren la cantidad de llantas, color y precio de ambos transportes. Por
# último, crear objetos para cada clase y mostrar por pantalla los atributos de cada uno.

# Clase Fabrica.
class Fabrica:
    # Método constructor que recibe llantas, color y precio.
    def __init__(self, llantas, color, precio):
        self.llantas = llantas
        self.color = color
        self.precio = precio

    # Método para mostrar la descripción del vehículo.
    def mostrar_descripcion(self):
        return f"Llantas: {self.llantas}, Color: {self.color}, Precio: {self.precio}€"

# Clase Moto que hereda de Fabrica.
class Moto(Fabrica):
    # Método constructor de la clase Moto.
    def __init__(self, llantas, color, precio, tipo_moto):
        super().__init__(llantas, color, precio)
        self.tipo_moto = tipo_moto

    # Método para mostrar la descripción completa de la moto junto a los de la clase Fábrica.
    def mostrar_vehiculos(self):
        return f"Moto - {self.mostrar_descripcion()} - Tipo: {self.tipo_moto}"
    
# Clase Carro que hereda de Fabrica.
class Carro(Fabrica):
    # Método constructor de la clase Carro.
    def __init__(self, llantas, color, precio, tipo_carro):
        super().__init__(llantas, color, precio)
        self.tipo_carro = tipo_carro

    # Método para mostrar la descripción completa del carro junto a los de la clase Fábrica.
    def mostrar_vehiculos(self):
        return f"Coche - {self.mostrar_descripcion()} - Tipo: {self.tipo_carro}"

# Declaración de los distintos tipos de vehículos e impresión de sus datos, con un bucle for.
moto = Moto(2, "Rojo", 1500, "Deportiva")
carro = Carro(4, "Azul", 20000, "Sedán")
vehiculos = [moto, carro]

for vehiculo in vehiculos:
    print(vehiculo.mostrar_vehiculos())