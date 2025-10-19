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
public class Formula extends Vehiculo {

    // Declaración de la variable.
    private final String aerodinamica;

    // Constructor.
    public Formula(String aerodinamica, String tipoVehiculo, int cv, String tipoRuedas) {
        super(tipoVehiculo, tipoRuedas, cv);
        this.aerodinamica = aerodinamica;
    }

    // Método toString para proporcionar la información del vehículo.
    @Override
    public String toString() {
        return "Formula [" + super.toString() + ", Aerodinamica: " + aerodinamica + "]";
    }
}
