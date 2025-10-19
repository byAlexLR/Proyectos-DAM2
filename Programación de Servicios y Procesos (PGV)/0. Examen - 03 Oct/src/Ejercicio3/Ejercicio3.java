/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

// Importación de las librerías.
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio3 {

  // Método Pesetas.
  public static void Pesetas(Scanner sc) {
    // Declaración e inicialización de las variables.
    double eur, pesetas = 166.386, cambio;
    // Pide al usuario que introduzca la cantidad a cambiar, y la almacena.
    System.out.print("Introduce la cantidad de euros a pasar a pesetas.\nCantidad: ");
    eur = sc.nextDouble();

    // Realiza el cambio de euros a pesetas. Por último, imprime un mensaje formateado con el resultado.
    cambio = eur * pesetas;
    System.out.printf("\nEl cambio de " + eur + "€ a Pesetas, es de: %.3f pesetas.\n", cambio);
  }

  // Método main que realiza la llamada al método Pesetas importando el Scanner.
  public static void main(String[] args) {
    // Inicialización del Scanner.
    Scanner sc = new Scanner(System.in);
    // Llama al método Pesetas con el Scanner.
    Pesetas(sc);
  }
}
