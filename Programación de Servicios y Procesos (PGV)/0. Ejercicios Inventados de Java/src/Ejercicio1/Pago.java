/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

/**
 *
 * @author byAlexLR
 */
public class Pago {
  
  // Declaración de las variables.
  private final double importePagado;
  private final String matricula;
  private final String descripcion;

  // Constructor de la clase Pago.
  public Pago(double importePagado, String matricula, String descripcion) {
    this.importePagado = importePagado;
    this.matricula = matricula;
    this.descripcion = descripcion;
  }

  // Método para obtener el importe pagado.
  public double getImportePagado() {
    return importePagado;
  }

  // Método para obtener la matrícula.
  public String getMatricula() {
    return matricula;
  }

  // Método para obtener la descripción.
  public String getDescripcion() {
    return descripcion;
  }
}
