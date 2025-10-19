/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AmaroYLarian2;

// Importa las librerías.
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Numeros {

    public static void main(String[] args) {
        // Declara la variable Scanner y posteriormente el Arraylist.
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numero = new ArrayList<>();
        // Declaración de las variables.
        int contador = 0, num, sumaNumeros = 0;
        double mediaNumeros = 0;

        System.out.println("Vamos a introducir un máximo de 5 números: ");
        // Bucle While que itera hasta 5 veces, para pedir los números.
        while (contador < 5) {
            System.out.print("Introduce un número: ");
            num = sc.nextInt(); // Los lee y lo almacena en la variable num.
            numero.add(num); // Añade el número leído al arraylist.
            contador++; // Suma uno al contador.
        }

        // Bucle for que itinera todos los números y los va sumando a la variable.
        for (Integer numeros : numero) {
            sumaNumeros += numeros;
        }

        // Condicional if que comprueba que la lista no esté vacía y realiza la media con el tamaño del arraylist.
        if (!numero.isEmpty()) {
            mediaNumeros = (double) sumaNumeros / numero.size();
        }

        // Muestra los resultados por pantalla.
        System.out.println("- Resultados:\n Los números introducidos son: " + numero + "\n La suma de todos los números es: " + sumaNumeros + "\n La media de total es: " + String.format("%.2f", mediaNumeros));
        sc.close();
    }
}
