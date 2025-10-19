/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Alexander;

// Importa las librerías.
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author byAlexLR
 */
public class Adivina {

    public static void main(String[] args) {
        // Inicializa Scanner y Random.
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        // Genera un número aleatorio entre 1 y 100.
        int numeroSecreto = rd.nextInt(100) + 1;

        // Variables para el juego.
        int intentoUsuario = 0;
        int contadorDeIntentos = 0;

        System.out.println("¡Adivina el número secreto entre 1 y 100!");

        // Bucle do-while que se ejecuta hasta que el usuario acierte.
        do {
            System.out.print("Introduce tu número: ");
            // Lee el número del usuario.
            intentoUsuario = sc.nextInt();
            // Incrementa el contador de intentos.
            contadorDeIntentos++;

            // Compara el número del usuario con el número secreto.
            if (intentoUsuario < numeroSecreto) {
                System.out.println("El número secreto es MAYOR.");
            } else if (intentoUsuario > numeroSecreto) {
                System.out.println("El número secreto es MENOR.");
            }
        } while (intentoUsuario != numeroSecreto);

        // Mensaje de victoria cuando el bucle termina.
        System.out.println("Has adivinado el número secreto: " + numeroSecreto + ". Lo has conseguido en " + contadorDeIntentos + " intentos.");

        sc.close();
    }
}
