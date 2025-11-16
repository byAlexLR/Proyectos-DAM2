/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.modelo;

// Importa las librerías necesarias
import javax.persistence.*;

/**
 *
 * @author byAlexLR
 */
@Entity
@Table(name = "ejemplares")
public class Ejemplar {

    // ENUM que define los posibles estados del Ejemplar
    public enum EstadoEjemplar {
        DISPONIBLE,
        PRESTADO,
        REPARACION,
        BAJA
    }

    // Declaración de variables con Mapeo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejemplar")
    private Integer idEjemplar;

    @Column(name = "codigo_ejemplar")
    private String codigoEjemplar;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoEjemplar estado;

    @Column(name = "ubicacion")
    private String ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro")
    private Libro libro;

    // Constructor Sin Parámetros
    public Ejemplar() {
    }

    // Constructor Con Parámetros
    public Ejemplar(String codigoEjemplar, EstadoEjemplar estado, String ubicacion) {
        this.codigoEjemplar = codigoEjemplar;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    public int getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(Integer idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    // Método toString
    @Override
    public String toString() {
        return "Ejemplar[ID: " + idEjemplar + ", Código: " + codigoEjemplar + ", Estado: " + estado + ", Ubicación: " + ubicacion + "]";
    }
}
