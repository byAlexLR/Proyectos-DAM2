/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ejercicio1;
// Importa la librería.

import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio2 {

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

    //Aqui mostramos los resultados del suma, resta, multiplición y división.
    System.out.println("La suma de " + num1 + " + " + num2 + " = " + (num1 + num2));
    System.out.println("La resta de " + num1 + " - " + num2 + " = " + (num1 - num2));
    System.out.println("La multiplicación de " + num1 + " x " + num2 + " = " + (num1 * num2));
    //Aqui comprobamos si igual o menor que cero, y tambien comprobamos que num1 sea inferior a num2.
    if (num1 < num2) {
      System.out.println("No es divisible entre " + num1);
    } else if (num1 <= 0 || num2 <= 0) {
      System.out.println("No es divisible entre 0");
    } else {
      //Aqui mostramos el resultado de la división.
      System.out.println("La división de " + num1 + " : " + num2 + " = " + (num1 / num2));
    }
  }
}
