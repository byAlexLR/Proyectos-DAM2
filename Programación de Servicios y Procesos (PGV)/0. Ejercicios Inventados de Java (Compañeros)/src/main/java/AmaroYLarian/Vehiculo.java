/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AmaroYLarian;

/**
 *
 * @author byAlexLR
 */
// Clase Vehiculo.
public class Vehiculo {

    // Declaración de las variables.
    private final String tipoVehiculo;
    private final String tipoRuedas;
    private final int cv;

    // Constructor.
    public Vehiculo(String tipoVehiculo, String tipoRuedas, int cv) {
        this.tipoVehiculo = tipoVehiculo;
        this.tipoRuedas = tipoRuedas;
        this.cv = cv;
    }

    // Método toString para proporcionar la información del vehículo.
    @Override
    public String toString() {
        return "Vehiculo[Tipo de Vehículo: " + tipoVehiculo + ", Tipo de Ruedas: " + tipoRuedas + ", CV: " + cv + "]";
    }
}
