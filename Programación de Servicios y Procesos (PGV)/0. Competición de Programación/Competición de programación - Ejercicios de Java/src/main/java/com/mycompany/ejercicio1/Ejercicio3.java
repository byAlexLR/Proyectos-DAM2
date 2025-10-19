/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio1;
// Importa la librería.

import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio3 {

  public static void main(String[] args) {
    //Aqui creamos el scanner y tambien declaramos las variables que vamos a usar.
    Scanner sc = new Scanner(System.in);
    int num1, num2;
    String nombre;

    //Aqui solcitamos al usuario su nombre y que introduzca el primer y el segundo numero.
    System.out.print("¡Bienvenido!, introduzca su nombre: ");
    nombre = sc.next();
    System.out.print(nombre + " por favor, introduzca el primer número: ");
    num1 = sc.nextInt();
    System.out.print("Ahora, introduzca el segundo número: ");
    num2 = sc.nextInt();

    // Comprueba si es num1 es menor que num2
    if (num1 < num2) {
      System.out.println("El número " + num2 + " es mayor que " + num1);
      // Comprueba si es num2 es menor que num1
    } else if (num1 > num2) {
      System.out.println("El número " + num1 + " es mayor que " + num2);
    } else {
      // Aqui si los dos numeros son iguales.
      System.out.println("El número " + num1 + " es igual que " + num2);
    }
  }
}
