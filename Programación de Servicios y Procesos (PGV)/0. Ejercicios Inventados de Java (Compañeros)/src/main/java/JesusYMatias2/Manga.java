/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JesusYMatias2;

/**
 *
 * @author byAlexLR
 */
// La clase Manga que hereda de la clase Libro.
public class Manga extends Libro {

    // Declaración de la variable.
    private boolean color;

    // Constructor.
    public Manga(String nombre, String autor, double precio, boolean color) {
        super(nombre, autor, precio);
        this.color = color;
    }

    // Método toString que muestra el Manga.
    @Override
    public String toString() {
        return "Manga [" + super.toString() + ", Es a color: " + color + "]";
    }
}
