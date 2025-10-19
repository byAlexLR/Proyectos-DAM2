/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

// Importación de las librerías.
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Main {

  // Método main.
  public static void main(String[] args) {
    // Bloque try, para inicializar y cerrar el Scanner.
    try (Scanner sc = new Scanner(System.in)) {
      // Inicialización del Arraylist.
      ArrayList<Alumnos> alumno = new ArrayList<>();

      // Bucle While, para pedir los datos del alumno y posteriormente almacenarlos en el arraylist.
      while (true) {
        // Pide el nombre y lo almacena en la variable nombre.
        System.out.print("Por favor, introduce los siguientes datos\nEl nombre: ");
        String nombre = sc.next();

        // Comprueba que el valor introducido en la variable nombre no sea un asterisco(*).
        if ("*".equals(nombre)) {
          break;
        }

        // Pide al usuario el primer apellido y lo guardo.
        System.out.print("El primer apellido: ");
        String primerApellido = sc.next();

        // Pide la edad y la guarda.
        System.out.print("La edad: ");
        int edad = sc.nextInt();

        // Pide el curso y lo almacena en una variable.
        System.out.print("El curso: ");
        String curso = sc.next();

        // Añade los datos al arraylist inicializado anteriormente.
        alumno.add(new Alumnos(nombre, primerApellido, edad, curso));
      }

      // Bucle for para iterar todos los alumnos de la lista e imprimirlos por pantalla.
      System.out.println("\nListado de Alumnos:");
      for (Alumnos alu : alumno) {
        System.out.println(alu.toString());
      }

      // Bucle for con un condicional anidado para iterar todos los alumnos que son mayores de edad, es decir, 18 años o más.
      System.out.println("\nListado de Alumnos Mayores de Edad:");
      for (Alumnos alu : alumno) {
        if (alu.getEdad() >= 18) {
          System.out.println(alu.toString());
        }
      }

      // Variable para guardar la suma de todas las edades.
      int sumaEdad = 0;
      // Bucle for para iterar todos los alumnos y realizar la suma de las edades a la variable inicializada anteriormente.
      for (Alumnos alu : alumno) {
        sumaEdad += alu.getEdad();
      }
      // Se realiza la media obteniendo la suma de todas las edades entre la longitud del arraylist. 
      // Posteriormente, lo imprime por pantalla con un mensaje formateado a dos decimales.
      double mediaEdad = sumaEdad / alumno.size();
      System.out.printf("\nMedia de Edad del Alumnado: %.2f\n", mediaEdad);

      // Inicialización de las variables de menor y mayor edad con el alumno 0 del arraylist. 
      Alumnos menorEdad = alumno.get(0);
      Alumnos mayorEdad = alumno.get(0);
      // Bucle for con condicionales anidados que compara la edad del alumno almacenado en la variable anterior con la edad del iterado.
      for (Alumnos alu : alumno) {
        if (alu.getEdad() < menorEdad.getEdad()) {
          menorEdad = alu;
        }
        if (alu.getEdad() > mayorEdad.getEdad()) {
          mayorEdad = alu;
        }
      }
      // Muestra por pantalla el alumno con más y menor edad.
      System.out.println("El alumno más joven es: " + menorEdad);
      System.out.println("El alumno más mayor es: " + mayorEdad);
    }
  }
}
