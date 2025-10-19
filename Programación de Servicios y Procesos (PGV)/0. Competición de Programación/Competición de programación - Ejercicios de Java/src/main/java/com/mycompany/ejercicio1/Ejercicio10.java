/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejercicio1;
// Importa las librerías
import java.text.Normalizer;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio10 {
    // Método para eliminar tildes de una cadena.
    public static String eliminarTildes(String cadena) {
      // Normaliza la cadena en forma NFD y elimina los caracteres.
      String cadenaNormalizada = Normalizer.normalize(cadena, Normalizer.Form.NFD);
      // Remueve los caracteres que no son letras.
      return cadenaNormalizada.replaceAll("\\p{M}", "");
    }

    // Método para contar vocales en una frase.
    public static void contarVocal() {
        Scanner sc = new Scanner(System.in);
        // Le pide al usuario que introduzca una frase.
        System.out.print("Introduce una frase por favor: ");
        String palabra = sc.nextLine();
        // Inicializa las variables de los contadores.
        int cont_a = 0, cont_e = 0, cont_i = 0, cont_o = 0, cont_u = 0;

        // Elimina tildes para comparar vocales sin tilde.
        palabra = eliminarTildes(palabra);

        // Comprueba los distintos caracteres.
        for (char c : palabra.toLowerCase().toCharArray()) {
          switch (c) {
            case 'a' -> cont_a++;
            case 'e' -> cont_e++;
            case 'i' -> cont_i++;
            case 'o' -> cont_o++;
            case 'u' -> cont_u++;
            default -> {
            }
          }
      }

      // Imprime el contador de todas las vocales.
      System.out.println("Total: ");
      System.out.println("Cantidad a: " + cont_a + ";");
      System.out.println("Cantidad e: " + cont_e + ";");
      System.out.println("Cantidad i: " + cont_i + ";");
      System.out.println("Cantidad o: " + cont_o + ";");
      System.out.println("Cantidad u: " + cont_u + ";");
        
      sc.close();
    }

    public static void main(String[] args) {
        contarVocal();
    }
}
