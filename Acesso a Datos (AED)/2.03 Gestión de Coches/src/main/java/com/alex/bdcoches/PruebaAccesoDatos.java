/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.alex.bdcoches;

// Importa las librerías.
import java.sql.SQLException;

/**
 *
 * @author byAlexLR
 */
public class PruebaAccesoDatos {

    // Método Main, que realiza las llamadas y pasa los datos a los diferentes métodos.
    public static void main(String args[]) throws SQLException {
        AccesoDatos AD = new AccesoDatos();
        AD.abrirConexion();
        AD.mostrarDatosCoches();
        AD.modificarCoche("BA-3333", 5000);
        AD.borrarCoche("MA-2222");
        AD.insertarCoche("AA-0005", "Ford", 4500, "1A");
        AD.insertarCoche("AL-1818", "Porsche", 18000, "18AL"); // DNI No Existe.
        AD.insertarPropietario("X25", "Jose", 54);
        AD.listarCochesPorPropietario("1A");
        AD.borrarPropietarioConCoches("1A");
        AD.cerrarConexion();
    }
}
