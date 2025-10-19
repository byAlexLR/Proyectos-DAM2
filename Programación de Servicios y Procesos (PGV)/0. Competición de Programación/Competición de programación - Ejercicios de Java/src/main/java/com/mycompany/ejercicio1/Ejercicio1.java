/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ejercicio1;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio1 {

  public static void main(String[] args) {
    //Aqui declaramos las variables que vamos a usar.
    int num1 = 5, num2 = 10;
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
