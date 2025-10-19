/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

// Importación de las librerías.
import javax.swing.JOptionPane;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio1 {

  // Método NotaFinal que realiza la media aritmética del alumno.
  public static void NotaFinal() {
    // Pide al usuario que introduzca su nombre.
    String nombre = JOptionPane.showInputDialog("¡Bienvenido, a la interfaz de Nota Final! Indique su nombre, por favor:");
    // Pide al usuario por JOptionPane que introduzca las diferentes notas y las parsea a double.
    double nota1 = Double.parseDouble(JOptionPane.showInputDialog("De acuerdo, " + nombre + ". Introduce la primera nota:"));
    double nota2 = Double.parseDouble(JOptionPane.showInputDialog(nombre + ", ahora introduce la segunda nota:"));
    double nota3 = Double.parseDouble(JOptionPane.showInputDialog(nombre + ", por último introduce la tercera nota:"));

    // Realiza la suma y la media de las tres notas. Por último, lo imprime por pantalla.
    double mediaAritmetica = (nota1 + nota2 + nota3) / 3;
    JOptionPane.showMessageDialog(null, "La media aritmética de [1º Trimestre: " + nota1 + ", 2º Trimestre: " + nota2 + " y 3º Trimestre: " + nota3 + "] es: " + mediaAritmetica);
  }

  // Método main que realiza la llamada al método NotaFinal.
  public static void main(String[] args) {
    // Realiza la llamada al método.
    NotaFinal();
  }
}
