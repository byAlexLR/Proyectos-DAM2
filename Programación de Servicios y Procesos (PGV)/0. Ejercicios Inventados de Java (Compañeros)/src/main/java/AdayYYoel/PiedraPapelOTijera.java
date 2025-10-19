/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdayYYoel;

// Importa las librerías.
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author byAlexLR
 */
public class PiedraPapelOTijera {

    public static void main(String[] args) {
        // Inicialización de las variables.
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String[] opciones = {"piedra", "papel", "tijera"};

        // Pregunta al usuario que quiere sacar y lo pasa a minúsculas.
        System.out.print("¿Qué vas a sacar? (Piedra, Papel o Tijera): ");
        String opcionUsuario = sc.next().toLowerCase();

        // Opción para sacar una opción random del String[] y lo muestra.
        String opcionMaquina = opciones[rand.nextInt(opciones.length)];
        System.out.println("La máquina ha sacado: " + opcionMaquina);

        // Comprueba si el valor sacado es igual a empate, ganado o perdido.
        if (opcionUsuario.equals(opcionMaquina)) {
            System.out.println("Empate.");
        } else if ((opcionUsuario.equals("piedra") && opcionMaquina.equals("tijera"))
                || (opcionUsuario.equals("papel") && opcionMaquina.equals("piedra"))
                || (opcionUsuario.equals("tijera") && opcionMaquina.equals("papel"))) {
            System.out.println("¡Has ganado!");
        } else if (opcionUsuario.equals("piedra") || opcionUsuario.equals("papel") || opcionUsuario.equals("tijera")) {
            System.out.println("Has perdido.");
        } else {
            System.out.println("Opción inválida. Debes introducir la palabra.");
        }
    }
}
