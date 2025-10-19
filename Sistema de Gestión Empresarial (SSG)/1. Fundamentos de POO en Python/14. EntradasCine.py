# byAlexLR

# 14. Crea un programa en Python que simule un sistema de compra de entradas de cine donde el
# usuario pueda seleccionar entre 5 películas diferentes, elegir entre 3 sesiones horarias
# disponibles, y seleccionar entre 4 tipos de combos de palomitas o la opción de no comida. El
# programa debe mostrar los precios de cada opción, calcular el total automáticamente, validar
# que las selecciones sean correctas, y finalmente mostrar un resumen detallado de la compra
# con el desglose de costos, así como llevar el control de asientos disponibles.

# Clase SistemaCine.
class SistemaCine:
    # Método constructor para inicializar los datos.
    def __init__(self):
        self.peliculas = {
            1: {"titulo": "Una batalla tras otra", "precio": 9.50},
            2: {"titulo": "Expediente Warren: El último rito", "precio": 9.50},
            3: {"titulo": "The Smashing Machine", "precio": 9.50},
        }
        self.sesiones = {
            1: {"horario": "17:00", "recargo": 0},
            2: {"horario": "20:30", "recargo": 1.50},
            3: {"horario": "22:00", "recargo": 2.00},
        }
        self.combos = {
            1: {"nombre": "Combo Pequeño", "precio": 5.50},
            2: {"nombre": "Combo Mediano", "precio": 7.50},
            3: {"nombre": "Combo Grande", "precio": 9.50},
            4: {"nombre": "Sin comida", "precio": 0},
        }
        self.asientos_disponibles = 60

    # Método para seleccionar una opción de un diccionario.
    def _seleccionar_opcion(self, opciones, titulo):
        print(f"\n ==== {titulo} ==== ")
        for clave, valor in opciones.items():
            # Formatea la salida de manera uniforme.
            nombre = valor.get("titulo") or valor.get("horario") or valor.get("nombre")
            precio = valor.get("precio")
            recargo = valor.get("recargo")

            linea = f"{clave}. {nombre}"
            if precio is not None:
                linea += f" - {precio:.2f}€"
            elif recargo > 0:
                linea += f" (+{recargo:.2f}€)"
            print(linea)

        while True:
            try:
                seleccion = int(input(f"Elige una opción (1-{len(opciones)}): "))
                if seleccion in opciones:
                    return seleccion
                else:
                    print("Error: Opción no válida.")
            except ValueError:
                print("Error: Introduce un número.")

    # Método para realizar el proceso de compra.
    def realizar_compra(self):
        if self.asientos_disponibles == 0:
            print("Lo sentimos, no quedan asientos disponibles.")
            return

        print(f"\nAsientos disponibles: {self.asientos_disponibles}")

        # Solicitar número de entradas.
        while True:
            try:
                cantidad = int(input("¿Cuántas entradas quieres? "))
                if 0 < cantidad <= self.asientos_disponibles:
                    break
                else:
                    print(f"Error: El número debe estar entre 1 y {self.asientos_disponibles}.")
            except ValueError:
                print("Error: Introduce un número válido.")

        # Selección de película, sesión y combo.
        pelicula_id = self._seleccionar_opcion(self.peliculas, "PELÍCULAS")
        sesion_id = self._seleccionar_opcion(self.sesiones, "SESIONES")
        combo_id = self._seleccionar_opcion(self.combos, "COMBOS DE COMIDA")

        # Cálculo del total.
        precio_base = self.peliculas[pelicula_id]["precio"]
        recargo_sesion = self.sesiones[sesion_id]["recargo"]
        precio_combo = self.combos[combo_id]["precio"]

        total = (precio_base + recargo_sesion) * cantidad
        if combo_id != 4:  # Si no elige ningún combo.
            total += precio_combo * cantidad

        # Resumen de la compra.
        print(f"\n--- RESUMEN DE LA COMPRA ---\nPelícula: {self.peliculas[pelicula_id]['titulo']}\nSesión: {self.sesiones[sesion_id]['horario']}\nEntradas: {cantidad}\nCombo: {self.combos[combo_id]['nombre']}\nTOTAL: {total:.2f}€")

        # Confirmación.
        confirmacion = input("\n¿Confirmar compra? (S/N): ").upper()
        if confirmacion == "S":
            self.asientos_disponibles -= cantidad
            print("\nCompra realizada con éxito. ¡Disfruta de la película!")
        else:
            print("\nCompra cancelada.")

    # Método para iniciar el sistema.
    def iniciar(self):
        while True:
            opcion = int(input("\nSISTEMA DE GESTIÓN DE CINES PEDROS\n1. Comprar entradas\n2. Ver asientos disponibles\n3. Salir\nSelecciona una opción: "))

            if opcion == 1:
                self.realizar_compra()
            elif opcion == 2:
                print(f"\nAsientos disponibles: {self.asientos_disponibles}")
            elif opcion == 3:
                print("Gracias por tu visita.")
                break
            else:
                print("Error: Opción no válida.")

# Inicializa la función de iniciar.
if __name__ == "__main__":
    cine = SistemaCine()
    cine.iniciar()
