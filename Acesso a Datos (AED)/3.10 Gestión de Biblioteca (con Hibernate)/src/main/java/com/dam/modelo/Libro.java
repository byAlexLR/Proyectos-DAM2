/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.modelo;

// Importa las librerías necesarias
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author byAlexLR
 */
@Entity
@Table(name = "libros")
public class Libro {

    // Declaración de variables con Mapeo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idLibro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "numero_paginas")
    private int numeroPaginas;

    // Relación muchos a uno. Carga el autor.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    // Relación uno a muchos. Todas las operaciones se propagan a los ejemplares y a los huérfanos.
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejemplar> ejemplares = new ArrayList<>();

    // Constructor Sin Parámetros
    public Libro() {
    }

    // Constructor Con Parámetros
    public Libro(String titulo, String isbn, LocalDate fechaPublicacion, int numeroPaginas) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.fechaPublicacion = fechaPublicacion;
        this.numeroPaginas = numeroPaginas;
    }

    // Métodos Helper (Añade o elimina un ejemplar al libro)
    public void addEjemplar(Ejemplar ejemplar) {
        ejemplares.add(ejemplar);
        ejemplar.setLibro(this);
    }

    public void removeEjemplar(Ejemplar ejemplar) {
        ejemplares.remove(ejemplar);
        ejemplar.setLibro(null);
    }

    // Getters y Setters
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    // Método toString
    @Override
    public String toString() {
        return "Libro[ID: " + idLibro + ", Título: " + titulo + ", ISBN: " + isbn + ", Fecha de Publicación: " + fechaPublicacion + ", Nº de Páginas: " + numeroPaginas + ", Autor: " + autor.toString() + ", Número de Ejemplares: " + ejemplares.toString() + "]";
    }
}
