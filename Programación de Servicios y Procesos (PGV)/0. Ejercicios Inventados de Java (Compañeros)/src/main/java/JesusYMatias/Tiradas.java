/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JesusYMatias;

// Importa las librerías.
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Tiradas {

    public static void main(String[] args) {
        // Inicialización de las variables.
        Scanner sc = new Scanner(System.in);
        System.out.println("¡Bienvenido, vamos a crear un personaje!");
        System.out.print("Introduce el nombre del personaje: "); // Pide el nombre y lo almacena en la variable.
        String nombre = sc.next();
        System.out.print("Ahora, introduce el peso: "); // Pide el peso y lo almacena en la variable.
        double peso = sc.nextDouble();
        System.out.print("Por último, introduce la altura: "); // Pide la altura y lo almacena en la variable.
        double altura = sc.nextDouble();

        System.out.println("Personaje: " + nombre + " , " + peso + "kg, " + altura + "m."); // Muestra los datos introducidos.
        int fuerza = (int) (Math.random() * 20 + 1); // Asigna una fuerza aleatoria al personaje.
        int carisma = (int) (Math.random() * 20 + 1); // Asigna un carisma aleatoria al personaje.
        int inteligencia = (int) (Math.random() * 20 + 1); // Asigna una inteligencia aleatoria al personaje.
        int destreza = (int) (Math.random() * 20 + 1); // Asigna una destreza aleatoria al personaje.
        System.out.println("\nEstadísticas del personaje [Fuerza: " + fuerza + ", Carisma: " + carisma + ", Inteligencia: " + inteligencia + ", Destreza: " + destreza + "]\n"); // Muestra los resultados.

        // Contador de 4 intentos.
        int intentos = 4;
        // Bucle while que compara los intentos.
        while (intentos > 0) {
            // Pide al usuario que quiere quiere cambiar.
            System.out.print("¿Qué desea cambiar? (1.Fuerza, 2.Carisma, 3.Inteligencia, 4.Destreza, 5.Salir): ");
            int opcion = sc.nextInt();

            // Switch, con las diferentes opciones y vuelve a asignar un valor nuevo aleatorio a cada destreza.
            switch (opcion) {
                case 1 -> {
                    fuerza = (int) (Math.random() * 20 + 1);
                    System.out.println("Tu fuerza ahora es de: " + fuerza);
                    intentos--;
                }
                case 2 -> {
                    carisma = (int) (Math.random() * 20 + 1);
                    System.out.println("Tu fuerza ahora es de: " + carisma);
                    intentos--;
                }
                case 3 -> {
                    inteligencia = (int) (Math.random() * 20 + 1);
                    System.out.println("Tu fuerza ahora es de: " + inteligencia);
                    intentos--;
                }
                case 4 -> {
                    destreza = (int) (Math.random() * 20 + 1);
                    System.out.println("Tu fuerza ahora es de: " + destreza);
                    intentos--;
                }
                case 5 -> {
                    System.out.println("Saliendo del programa.");
                    break;
                }
                default -> {
                    System.out.println("Valor introducido incorrecto.");
                }
            }
        }
        // Muestra los valores finales.
        System.out.println("\nEstadísticas del personaje [Fuerza: " + fuerza + ", Carisma: " + carisma + ", Inteligencia: " + inteligencia + ", Destreza: " + destreza + "]\n");
    }
}
