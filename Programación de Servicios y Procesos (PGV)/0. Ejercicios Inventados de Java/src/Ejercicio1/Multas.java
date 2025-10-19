/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

/**
 *
 * @author byAlexLR
 */
public class Multas extends Vehiculo {

  // Declaración de las variables.
  private final String descripcion;
  private final double importe;

  // Constructor de la clase Multas.
  public Multas(String descripcion, double importe, String matricula) {
    super(matricula);
    this.descripcion = descripcion;
    this.importe = importe;
  }

  // Método para obtener la descripción de la multa.
  public String getDescripcion() {
    return descripcion;
  }

  // Método para obtener el importe a pagar de la multa.
  public double getImporteAPagar() {
    return importe;
  }
}
