/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

/**
 *
 * @author byAlexLR
 */
public class Alumnos {

  // Declaración de las variables.
  private final String nombre;
  private final String primerApellido;
  private int edad;
  private final String curso;

  // Constructor de las diferentes variables.
  public Alumnos(String nombre, String primerApellido, int edad, String curso) {
    this.nombre = nombre;
    this.primerApellido = primerApellido;
    this.edad = edad;
    this.curso = curso;
  }

  // Getters y Setters de la edad, para poder realizar los cálculos.
  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  // Método toString para imprimir por pantalla los diferentes alumnos.
  @Override
  public String toString() {
    return "Alumnos [Nombre: " + nombre + ", Primer Apellido: " + primerApellido + ", Edad: " + edad + ", Curso: " + curso + "]";
  }
}
