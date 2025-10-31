/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author byAlexLR
 */
public class Libro {

    // Atributos.
    private int id;
    private String titulo;
    private String isbn;
    private int anioPublicacion;
    private Autor autor;

    // Constructores.
    public Libro() {
    }

    public Libro(int id, String titulo, String isbn, int anioPublicacion, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    // Getters y Setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    // Método toString.
    @Override
    public String toString() {
        return "Libro[ID: " + id + ", Título: " + titulo + ", ISBN: " + isbn + ", Año de Publicación: " + anioPublicacion + " - " + autor + "]";
    }
}
