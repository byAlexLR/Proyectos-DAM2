# byAlexLR

# 9. Debes crear un programa que permita a dos jugadores jugar al Tic Tac Toe. El juego debe
# mostrar un tablero 3x3, alternar turnos entre los jugadores (X y O), validar movimientos,
# detectar cuando hay un ganador o empate, y proporcionar una interfaz clara para los jugadores.

# Clase TicTacToe.
class TicTacToe:
    # Método constructor.
    def __init__(self):
        # Inicializa el tablero vacío y el jugador actual.
        self.tablero = [[" " for _ in range(3)] for _ in range(3)]
        self.jugador_actual = "X"

    # Método para mostrar el tablero.
    def mostrar_tablero(self):
        # Imprime el tablero en la consola.
        for fila in self.tablero:
            print("|".join(fila))
            print("-" * 5)

    # Método para realizar un movimiento.
    def mover(self, fila, columna):
        # Verificar si el movimiento es válido.
        if self.tablero[fila][columna] == " ":
            self.tablero[fila][columna] = self.jugador_actual
            return True
        else:
            print("Movimiento inválido. Intenta de nuevo.")
            return False

    # Método para cambiar de jugador.
    def cambiar_jugador(self):
        # Alterna entre los jugadores X y O.
        self.jugador_actual = "O" if self.jugador_actual == "X" else "X"

    # Método para verificar si hay un ganador.
    def verificar_ganador(self):
        # Verificación de filas.
        for fila in self.tablero:
            if fila[0] == fila[1] == fila[2] != " ":
                return True
        # Verificación de columnas.
        for col in range(3):
            if (
                self.tablero[0][col]
                == self.tablero[1][col]
                == self.tablero[2][col]
                != " "
            ):
                return True
        # Verificación de diagonales.
        if self.tablero[0][0] == self.tablero[1][1] == self.tablero[2][2] != " ":
            return True
        if self.tablero[0][2] == self.tablero[1][1] == self.tablero[2][0] != " ":
            return True
        return False

    # Método para verificar si hay un empate.
    def verificar_empate(self):
        # Si no hay espacios vacíos y no hay ganador, es un empate.
        for fila in self.tablero:
            if " " in fila:
                return False
        return True

    # Método principal para jugar.
    def jugar(self):
        # Bucle principal del juego.
        while True:
            # Muestra el tablero actual.
            self.mostrar_tablero()
            try:
                # Solicita la fila y columna para el movimiento.
                fila = int(
                    input(f"Jugador {self.jugador_actual}, ingresa la fila (0-2): ")
                )
                columna = int(
                    input(f"Jugador {self.jugador_actual}, ingresa la columna (0-2): ")
                )
                # Verifica que la entrada esté dentro del rango válido.
                if fila not in range(3) or columna not in range(3):
                    print("Entrada inválida. Por favor ingresa números entre 0 y 2.")
                    continue
                # Intenta realizar el movimiento.
                if self.mover(fila, columna):
                    # Verifica si hay un ganador o un empate.
                    if self.verificar_ganador():
                        self.mostrar_tablero()
                        print(f"¡Jugador {self.jugador_actual} gana!")
                        break
                    if self.verificar_empate():
                        self.mostrar_tablero()
                        print("¡Empate!")
                        break
                    self.cambiar_jugador()
            # Maneja los posibles errores de entrada.
            except ValueError:
                print("Entrada inválida. Por favor ingresa números entre 0 y 2.")


# Iniciar el juego.
if __name__ == "__main__":
    juego = TicTacToe()
    juego.jugar()