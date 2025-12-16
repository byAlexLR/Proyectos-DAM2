/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

// Importa las librerías necesarias
import java.io.Serializable;

/**
 *
 * @author byAlexLR
 */
// Clase Persona que implementa Serializable para poder enviarla como objeto
public class Persona implements Serializable {

  // Variables
  private static final long serialVersionUID = 1L;
  private String nombre;
  private int edad;

  // Constructor
  public Persona(String nombre, int edad) {
    this.nombre = nombre;
    this.edad = edad;
  }

  // Getters y Setters
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  // Método toString
  @Override
  public String toString() {
    return "[Nombre: " + nombre + " - Edad: " + edad + "]";
  }
}
