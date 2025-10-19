/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AmaroYLarian;

/**
 *
 * @author byAlexLR
 */
// La clase Formula que hereda de la clase Vehiculo.
public class GranTurismo extends Vehiculo {

    // Declaración de las variables.
    private final double peso;
    private final int numPuertas;

    // Constructor.
    public GranTurismo(String tipoVehiculo, String tipoRuedas, int numPuertas, double peso, int cv) {
        super(tipoVehiculo, tipoRuedas, cv);
        this.peso = peso;
        this.numPuertas = numPuertas;
    }

    // Método toString para proporcionar la información del vehículo.
    @Override
    public String toString() {
        return "GranTurismo[" + super.toString() + ", Peso:" + peso + ", Número de Puertas: " + numPuertas + "]";
    }
}
