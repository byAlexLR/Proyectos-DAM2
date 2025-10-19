package com.mycompany.ejercicio1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Importa la librería de scanner.
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio7 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a, b, c;
    String nombre;

    System.out.print("¡Bienvenido! Por favor, introduzca su nombre: ");
    nombre = sc.next();

    System.out.print(nombre + ", introduzca el valor de a: ");
    a = sc.nextInt();

    // Validar que 'a' no sea cero
    if (a == 0) {
      System.out.println("El valor de 'a' no puede ser cero. No es una ecuación cuadrática.");
      return;
    }

    System.out.print("Introduzca el valor de b: ");
    b = sc.nextInt();

    System.out.print("Introduzca el valor de c: ");
    c = sc.nextInt();

    if (b == 0 && c == 0) {
      System.out.println("La ecuación tiene solución: x = 0");
    } else if (c == 0) {
      System.out.println("Primera solución: 0");
      System.out.println("Segunda solución: " + (-(double) b / a));
    } else if (b == 0) {
      double div = -(double) c / a;
      if (div < 0) {
        System.out.println("La ecuación no tiene solución real.");
      } else {
        System.out.println("Primera solución: " + Math.sqrt(div));
        System.out.println("Segunda solución: " + (-Math.sqrt(div)));
      }
    } else {
      double discriminante = (double) b * b - 4 * a * c;
      if (discriminante < 0) {
        System.out.println("La ecuación no tiene solución real.");
      } else {
        double raiz = Math.sqrt(discriminante);
        double x1 = (-b + raiz) / (2 * a);
        double x2 = (-b - raiz) / (2 * a);
        System.out.println("La primera solución es: " + x1);
        System.out.println("La segunda solución es: " + x2);
      }
    }

    sc.close();
  }
}
