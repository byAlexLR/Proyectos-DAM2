# byAlexLR

# 15. Crear un programa en Python de una pasarela de compra de un billete de avión. Este
# programa debe tener la posibilidad de comprar 5 posibles destinos desde Lanzarote. En dos
# clases posibles (Business y Turista) y dos precios diferenciados (incluso con descuento de
# residente canario). Que permita compra de servicios como añadir una maleta más (más dinero),
# seguro del viaje o elegir asiento (Se deben definir los asientos de un avión al inicio, por
# ejemplo, un Boeing 737). Debe tener un contador de asientos, para evitar el overbooking.

# Clase SistemaVuelos.
class SistemaVuelos:
    # Método constructor para inicializar datos.
    def __init__(self):
        self.destinos = {
            1: {"ciudad": "Madrid", "turista": 90, "business": 250},
            2: {"ciudad": "Barcelona", "turista": 100, "business": 280},
            3: {"ciudad": "Londres", "turista": 150, "business": 450},
        }
        self.servicios = {"maleta": 35, "seguro": 15, "asiento": 12}
        self.descuento_residente = 0.75
        # Define la cantidad de asientos del avión.
        self.asientos_turista_total = 162
        self.asientos_business_total = 18
        # Contadores de asientos ocupados.
        self.asientos_turista_ocupados = 0
        self.asientos_business_ocupados = 0

    # Método para mostrar opciones y obtener una selección.
    def _obtener_seleccion(self, opciones, titulo):
        print(f"\n--- {titulo} ---")
        if isinstance(opciones, dict):
            for clave, valor in opciones.items():
                print(f"{clave}. {valor['ciudad']} (Turista: {valor['turista']}€, Business: {valor['business']}€)")
        else:
            for i, item in enumerate(opciones, 1):
                print(f"{i}. {item.capitalize()}")

        # While para elegir la opción.
        while True:
            try:
                opcion = int(input(f"Elige una opción (1-{len(opciones)}): "))
                if 1 <= opcion <= len(opciones):
                    return opcion
                else:
                    print("Error: Opción no válida.")
            except ValueError:
                print("Error: Introduce un número.")

    # Método para hacer preguntas de sí o no.
    def _preguntar_si_no(self, mensaje):
        respuesta = input(f"{mensaje} (S/N): ").upper()
        return respuesta == "S"

    # Método para ver la disponibilidad de asientos.
    def ver_asientos_disponibles(self):
        disponibles_turista = self.asientos_turista_total - self.asientos_turista_ocupados
        disponibles_business = self.asientos_business_total - self.asientos_business_ocupados
        
        print(f"\n--- ASIENTOS DISPONIBLES ---\nClase Turista: {disponibles_turista}/{self.asientos_turista_total}\nClase Business: {disponibles_business}/{self.asientos_business_total}")

    # Método principal para gestionar la compra.
    def realizar_compra(self):
        # Selección de destino y clase.
        destino_id = self._obtener_seleccion(self.destinos, "DESTINOS DESDE LANZAROTE")
        destino = self.destinos[destino_id]

        clase_id = self._obtener_seleccion(["Turista", "Business"], "CLASE DE VIAJE")
        clase = "turista" if clase_id == 1 else "business"

        # Verificacion de si existe overbooking.
        if clase == "turista":
            if self.asientos_turista_ocupados >= self.asientos_turista_total:
                print("\nError: No quedan asientos disponibles en clase Turista.")
                return
        else: # Sino, es turista es Business.
            if self.asientos_business_ocupados >= self.asientos_business_total:
                print("\nError: No quedan asientos disponibles en clase Business.")
                return

        precio_base = destino[clase]

        # Aplicar descuento de residente si corresponde.
        precio_vuelo = precio_base
        descuento_aplicado = 0
        if self._preguntar_si_no("¿Eres residente canario?"):
            descuento_aplicado = precio_base * (1 - self.descuento_residente)
            precio_vuelo -= descuento_aplicado

        # Añadir servicios adicionales.
        precio_servicios = 0
        servicios_contratados = []
        if self._preguntar_si_no(f"¿Añadir maleta extra (+{self.servicios['maleta']}€)?"):
            precio_servicios += self.servicios["maleta"]
            servicios_contratados.append("Maleta extra")

        if self._preguntar_si_no(f"¿Añadir seguro de viaje (+{self.servicios['seguro']}€)?"):
            precio_servicios += self.servicios["seguro"]
            servicios_contratados.append("Seguro de viaje")

        if self._preguntar_si_no(f"¿Seleccionar asiento (+{self.servicios['asiento']}€)?"):
            precio_servicios += self.servicios["asiento"]
            servicios_contratados.append("Selección de asiento")

        total = precio_vuelo + precio_servicios

        # Resumen y confirmación de la reserva.
        print(f"\n--- RESUMEN DE LA RESERVA ---\nDestino: {destino['ciudad']}\nClase: {clase.capitalize()}\nPrecio base: {precio_base:.2f}€")
        if descuento_aplicado > 0:
            print(f"Descuento residente: -{descuento_aplicado:.2f}€")
        if servicios_contratados:
            print(f"Servicios: {', '.join(servicios_contratados)} ({precio_servicios:.2f}€)")
        print(f"TOTAL: {total:.2f}€")

        if self._preguntar_si_no("\n¿Confirmar compra?"):
            # Añade un asiento ocupado al avión.
            if clase == "turista":
                self.asientos_turista_ocupados += 1
            else:
                self.asientos_business_ocupados += 1
            print("\nCompra realizada con éxito. ¡Buen viaje!")
        else:
            print("\nCompra cancelada.")
            
    # Método para iniciar el sistema.
    def iniciar(self):
        while True:

            opcion = int(input("\n--- AEROLÍNEAS CANARIAS ---\n1. Comprar billete\n2. Ver asientos disponibles\n3. Salir\nSelecciona una opción: "))

            # Según la opción llama a la función.
            if opcion == 1:
                self.realizar_compra()
            elif opcion == 2:
                self.ver_asientos_disponibles()
            elif opcion == 3:
                print("Gracias por volar con nosotros.")
                break
            else:
                print("Error: Opción no válida.")


# Inicializa la función de iniciar.
if __name__ == "__main__":
    sistema = SistemaVuelos()
    sistema.iniciar()
