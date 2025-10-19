# byAlexLR

# 12. Crea un programa que simule el funcionamiento de una máquina expendedora que debe permitir al usuario ver los productos disponibles, 
# seleccionar un producto ingresando su código, insertar dinero para pagar, calcular el cambio si es necesario y dispensar el producto, 
# validando en cada paso que el producto existe, que hay stock disponible y que el dinero insertado es suficiente, además de mantener 
# actualizado el inventario y el dinero en la caja después de cada transacción.

# Clase MaquinaExpendedora.
class MaquinaExpendedora:
    # Método constructor, que inicializa un array de productos, junto a la caja a 0.0.
    def __init__(self):
        self.productos = {
            "A1": {"nombre": "Agua", "precio": 1.00, "stock": 10},
            "B2": {"nombre": "Refresco", "precio": 1.50, "stock": 5},
            "C3": {"nombre": "Snack", "precio": 2.00, "stock": 8}
        }
        self.caja = 0.0

    # Método que imprime todos lo productos de la máquina expendedora.
    def mostrar_productos(self):
        print("Productos disponibles:")
        for codigo, info in self.productos.items():
            print(f"Código: {codigo}, Producto: {info['nombre']}, Precio: {info['precio']}€, Stock: {info['stock']}")

    # Método que selecciona un producto y comprueba si hay stock y si es un producto válido.
    def seleccionar_producto(self, codigo):
        if codigo in self.productos:
            producto = self.productos[codigo]
            if producto["stock"] > 0:
                return producto
            else:
                print("Lo siento, el producto está agotado.")
                return None
        else:
            print("Código del producto inválido.")
            return None
    
    # Revisa que el dinero introducido sea mayor que 0.
    def insertar_dinero(self, dinero):
        if dinero > 0:
            return dinero
        else:
            print("La cantidad introducida es inválida.")
            return 0

    # Procesa la compra del producto.
    def procesar_compra(self, codigo, dinero_insertado):
        producto = self.seleccionar_producto(codigo)
        # Si el dinero insertado es correcto para comprar el producto, se lo quita y lo suma a la caja. Posteriormente, quita un producto del stock y lo dispensa.
        if producto:
            precio = producto["precio"]
            if dinero_insertado >= precio:
                cambio = dinero_insertado - precio
                self.caja += precio
                producto["stock"] -= 1
                print(f"Producto dispensado: {producto['nombre']}.")
                # En el caso de existir cambio lo muestra.
                if cambio > 0:
                    print(f"Su cambio es: {cambio:.2f}€.")
                else:
                    print("No hay cambio.")
            # Si el dinero no es suficiente para la compra.
            else:
                print(f"Dinero insuficiente. El precio del producto es {precio}€.")
        else:
            print("No se pudo procesar la compra.")

# Función que muestra el menú.
def menu():
    maquina = MaquinaExpendedora()
    # Bucle While: Que pregunta el producto que desea.
    while True:
        maquina.mostrar_productos() # Muestra los productos.
        # Pregunta al usuario el código y lo pasa a mayúsculas. 
        codigo = input("Indica el código del producto (o 00 para salir): ").upper()
        # En el caso de que el código sea 00, sale del programa.
        if codigo == "00":
            print("Gracias por usar la máquina expendedora. ¡Hasta luego!")
            break
        producto = maquina.seleccionar_producto(codigo) # Pasa el código introducido a la variable producto para seleccionar y procesar la petición.
        if producto:
            try:
                # Le dice al cliente que inserte el dinero y lo pasa, en el caso de que sea mayor que 0.
                dinero_insertado = float(input(f"El precio del producto es {producto['precio']}€. Inserte dinero: "))
                dinero_valido = maquina.insertar_dinero(dinero_insertado)
                if dinero_valido > 0:
                    maquina.procesar_compra(codigo, dinero_valido)
            except ValueError:
                print("Por favor, ingrese un dinero válido.")

# Inicializa la función menu.
if __name__ == "__main__":
    menu()