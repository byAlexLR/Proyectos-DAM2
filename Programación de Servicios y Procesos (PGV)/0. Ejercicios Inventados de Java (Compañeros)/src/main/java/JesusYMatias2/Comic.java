/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JesusYMatias2;

/**
 *
 * @author byAlexLR
 */
// La clase Comic que hereda de la clase Libro.
public class Comic extends Libro {

    // Declaración de la variable.
    private final String editorial;

    // Constructor.
    public Comic(String nombre, String autor, double precio, String editorial) {
        super(nombre, autor, precio);
        this.editorial = editorial;
    }

    // Método toString que muestra el Comic.
    @Override
    public String toString() {
        return "Comic [" + super.toString() + ", Editorial: " + editorial + "]";
    }
}
