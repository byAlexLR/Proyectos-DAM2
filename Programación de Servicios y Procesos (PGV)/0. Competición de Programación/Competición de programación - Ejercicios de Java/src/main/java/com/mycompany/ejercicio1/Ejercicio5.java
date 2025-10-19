/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;

// Importa la librería
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio5 {

  public static void main(String[] args) {
    //Aqui creamos el scanner y tambien declaramos las variables que vamos a usar (edad, nombre).
    Scanner sc = new Scanner(System.in);
    int edad;
    String nombre;

    //Aqui solcitamos al usuario su nombre y que introduzca su edad.
    System.out.print("¡Bienvenido!, introduzca su nombre: ");
    nombre = sc.next();
    System.out.print(nombre + " por favor, introduzca su edad: ");
    edad = sc.nextInt();

    // Comparamos las edades y según la introducida muestra un mensaje u otro.
    if (edad < 4) {
      System.out.println("Pasa gratis");
    } else if (edad >= 4 || edad <= 18) {
      System.out.println("Debes pagar 5€");
    } else {
      System.out.println("Tienes que pagar 10€");
    }
  }
}
