# byAlexLR

# 10. Debes crear un programa que simule el juego de Piedra, Papel o Tijera entre un jugador 
# humano y la computadora. El sistema debe generar elecciones aleatorias para la
# computadora, validar las entradas del usuario, determinar el ganador de cada ronda según
# las reglas clásicas, y mantener un historial de partidas

# Importar la librería random.
import random

# Clase PiedraPapelTijera.
class PiedraPapelTijera:
    # Método constructor.
    def __init__(self):
        self.opciones = ["Piedra", "Papel", "Tijera"]
        self.historial = []

    # Método para obtener la elección de la computadora.
    def obtener_eleccion_computadora(self):
        return random.choice(self.opciones) # Selecciona aleatoriamente una de las opciones.

    # Método para determinar el ganador.
    def determinar_ganador(self, eleccion_jugador, eleccion_computadora):
        # Condicionales para determinar el ganador.
        if eleccion_jugador == eleccion_computadora:
            return "Empate"
        elif (eleccion_jugador == "Piedra" and eleccion_computadora == "Tijera") or \
             (eleccion_jugador == "Papel" and eleccion_computadora == "Piedra") or \
             (eleccion_jugador == "Tijera" and eleccion_computadora == "Papel"):
            return "Jugador"
        else:
            return "Computadora"
        
    # Método principal para jugar.
    def jugar(self):
        # Bucle principal del juego.
        while True:
            # Solicitar la elección del jugador, la pasa a minúsculas y luego la capitaliza.
            eleccion_jugador = input("Elige Piedra, Papel o Tijera (o 'salir' para terminar): ").lower().capitalize()
            # Si el jugador escribe 'salir', se termina el juego.
            if eleccion_jugador.lower() == 'salir':
                break
            # Valida la elección del jugador.
            if eleccion_jugador not in self.opciones:
                print("Elección inválida. Intenta de nuevo.")
                continue

            # Obtiene la elección de la computadora y determina el ganador.
            eleccion_computadora = self.obtener_eleccion_computadora()
            ganador = self.determinar_ganador(eleccion_jugador, eleccion_computadora)
            self.historial.append((eleccion_jugador, eleccion_computadora, ganador))

            # Muestra los resultados de la ronda.
            print(f"Jugador eligió: {eleccion_jugador}")
            print(f"Computadora eligió: {eleccion_computadora}")
            # Muestra el resultado.
            if ganador == "Empate":
                print("¡Es un empate!")
            elif ganador == "Jugador":
                print("¡Ganaste!")
            else:
                print("¡La computadora gana!")
        # Muestra el historial de partidas.
        self.mostrar_historial()
    # Método para mostrar el historial de partidas.    
    def mostrar_historial(self):
        print("\nHistorial de partidas:")
        # Recorre el historial e imprime los resultados.
        for i, (jugador, computadora, ganador) in enumerate(self.historial, 1):
            print(f"Partida {i}: Jugador - {jugador}, Computadora - {computadora}, Ganador - {ganador}")

# Iniciar el juego.
if __name__ == "__main__":
    juego = PiedraPapelTijera()
    juego.jugar()