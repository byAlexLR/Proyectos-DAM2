/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.alex.bdvuelospasajeros;

import java.sql.SQLException;

/**
 *
 * @author byAlexLR
 */
public class BDVuelosPasajeros {

  public static void main(String[] args) throws SQLException {
    AccesoDatos AD = new AccesoDatos();

    AD.abrirConexion();
    AD.mostrarVuelos();
    AD.mostrarPasajeros();
    AD.mostrarInformacionVuelos();
    AD.mostrarInformacionCodigo("IB-SP-4567");
    AD.insertarVuelo("IB-SP-1818", "18/02/20-10:30", "LANZAROTE", "LONDRES", 120, 80, 150, 50);
    AD.borrarVuelo("IB-SP-1818");
    AD.actualizarPlazasFumador();
    AD.crearTablasYActualizar();
    String codigo = "IB-SP-4567";
    String[][] datosPasajeros = {
      {"123", "12345678A", "Ana", "García López"},
      {"124", "87654321B", "Lucía", "Ruiz Martín"},
      {"125", "11223344C", "Pedro", "Jimenez Sanz"}
    };
    AD.insertarPasajeros(codigo, datosPasajeros);
    AD.cerrarConexion();
  }
}
