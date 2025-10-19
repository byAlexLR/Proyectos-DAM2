/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JesusYMatias2;

/**
 *
 * @author byAlexLR
 */
public class Libro {

    // Declaración de las variables.
    private final String nombre;
    private final String autor;
    private final double precio;

    // Constructor.
    public Libro(String nombre, String autor, double precio) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
    }

    // Método toString que muestra el libro.
    @Override
    public String toString() {
        return "Libro [Título: " + nombre + ", Autor: " + autor + ", Precio: " + precio + "]";
    }
}
