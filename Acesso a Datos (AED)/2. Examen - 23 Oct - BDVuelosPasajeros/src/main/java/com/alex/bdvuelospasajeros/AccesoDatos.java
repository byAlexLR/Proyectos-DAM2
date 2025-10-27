/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alex.bdvuelospasajeros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author byAlexLR
 */
public class AccesoDatos {

  private Connection con;
  private static final String DB_URL = "jdbc:mysql://localhost:3306/AEROLINEAS";
  private static final String DB_USER = "root";
  private static final String DB_PASS = "root";

  // Método para abrir la conexión.
  public void abrirConexion() {
    try {
      con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
      System.out.println("\nConexión establecida con la base de datos.");
    } catch (SQLException ex) {
      System.err.println("\nERROR AL CONECTARSE: " + ex.getMessage());
    }
  }

  // Método para cerrar la conexión.
  public void cerrarConexion() {
    try {
      if (con != null) {
        con.close();
        System.out.println("\nConexión cerrada con la base de datos.");
      }
    } catch (SQLException ex) {
      System.err.println("\nERROR AL CERRAR LA CONEXIÓN: " + ex.getMessage());
    }
  }

  // Muestra los vuelos.
  public void mostrarVuelos() {
    String sqlVuelos = "SELECT * FROM VUELOS";
    System.out.println("\n--- INFORMACIÓN DE LOS VUELOS ---");
    try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sqlVuelos)) {
      while (rs.next()) {
        System.out.printf("Código: %-10s | Hora de Salida: %-15s | Destino: %-10s | Procedencia: %-10s | Plazas Fumador: %-3d | Plazas No Fumador: %-3d | Plazas Turista: %-3d | Plazas Primera: %-3d\n",
                rs.getString("COD_VUELO"),
                rs.getString("HORA_SALIDA"),
                rs.getString("DESTINO"),
                rs.getString("PROCEDENCIA"),
                rs.getInt("PLAZAS_FUMADOR"),
                rs.getInt("PLAZAS_NO_FUMADOR"),
                rs.getInt("PLAZAS_TURISTA"),
                rs.getInt("PLAZAS_PRIMERA"));
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL MOSTRAR LOS DATOS DE LOS VUELOS: " + ex.getMessage());
    }
  }

  // Mostrar vuelos por código.
  public void mostrarVuelosCodigo(String codigo) {
    String sqlVuelos = "SELECT * FROM VUELOS WHERE COD_VUELO = ?";
    System.out.println("\n--- INFORMACIÓN DEL VUELO " + codigo + " ---");
    try (PreparedStatement pstm = con.prepareStatement(sqlVuelos)) {
      pstm.setString(1, codigo);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {
        System.out.printf("Código: %-10s | Hora de Salida: %-15s | Destino: %-10s | Procedencia: %-10s | Plazas Fumador: %-3d | Plazas No Fumador: %-3d | Plazas Turista: %-3d | Plazas Primera: %-3d\n",
                rs.getString("COD_VUELO"),
                rs.getString("HORA_SALIDA"),
                rs.getString("DESTINO"),
                rs.getString("PROCEDENCIA"),
                rs.getInt("PLAZAS_FUMADOR"),
                rs.getInt("PLAZAS_NO_FUMADOR"),
                rs.getInt("PLAZAS_TURISTA"),
                rs.getInt("PLAZAS_PRIMERA"));
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL MOSTRAR EL VUELO " + codigo + ": " + ex.getMessage());
    }
  }

  // Muestra los pasajeros.
  public void mostrarPasajeros() {
    String sqlPasajeros = "SELECT * FROM PASAJEROS";
    try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sqlPasajeros)) {
      System.out.println("\n--- INFORMACIÓN DE LOS PASAJEROS --- ");
      while (rs.next()) {
        System.out.printf("Número: %-3d | Código de Vuelo: %-10s | Tipo de Plaza: %-2s | Fumador: %-2s\n",
                rs.getInt("NUM"),
                rs.getString("COD_VUELO"),
                rs.getString("TIPO_PLAZA"),
                rs.getString("FUMADOR"));
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL MOSTRAR LOS DATOS DE LOS PASAJEROS: " + ex.getMessage());
    }
  }

  // Muestra la información de los pasajeros de un vuelo.
  public void mostrarInformacionVuelos() {
    String sqlCombi = "SELECT p.NUM, p.COD_VUELO, p.TIPO_PLAZA, p.FUMADOR, v.HORA_SALIDA, v.DESTINO, v.PROCEDENCIA FROM PASAJEROS p LEFT JOIN VUELOS v ON p.COD_VUELO = v.COD_VUELO";
    System.out.println("\n--- INFORMACIÓN DE LOS PASAJEROS CON VUELO ---");
    try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sqlCombi)) {
      while (rs.next()) {
        System.out.printf("Número: %-3d | Código de Vuelo: %-10s | Tipo de Plaza: %-2s | Fumador: %-2s | Hora de Salida: %-15s | Destino: %-10s | Procedencia: %-10s\n",
                rs.getInt("NUM"),
                rs.getString("COD_VUELO"),
                rs.getString("TIPO_PLAZA"),
                rs.getString("FUMADOR"),
                rs.getString("HORA_SALIDA"),
                rs.getString("DESTINO"),
                rs.getString("PROCEDENCIA"));
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL MOSTRAR LOS DATOS EN CONJUNTO: " + ex.getMessage());
    }
  }

  // Mostrar los pasajeros de un vuelo.
  public void mostrarInformacionCodigo(String codigo) {
    String sqlPasajeros = "SELECT * FROM PASAJEROS WHERE COD_VUELO = ?";
    System.out.println("\n--- INFORMACIÓN DE LOS PASAJEROS DEL VUELO " + codigo + " ---");
    try (PreparedStatement pstm = con.prepareStatement(sqlPasajeros)) {
      pstm.setString(1, codigo);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {
        System.out.printf("Número: %-3d | Código de Vuelo: %-10s | Tipo de Plaza: %-2s | Fumador: %-2s\n",
                rs.getInt("NUM"),
                rs.getString("COD_VUELO"),
                rs.getString("TIPO_PLAZA"),
                rs.getString("FUMADOR"));
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL MOSTRAR LOS DATOS DE LOS PASAJEROS CON VUELO" + codigo + ": " + ex.getMessage());
    }
  }

  // Mostrar los pasajeros de un vuelo junto al DNI.
  public void mostrarInformacionPersonasDNI(String codigo) {
    String sqlPasajeros = "SELECT per.DNI, per.NOMBRE, per.APELLIDOS, p.NUM, p.COD_VUELO, p.TIPO_PLAZA, p.FUMADOR "
            + "FROM PERSONAS per "
            + "JOIN PASAJEROS p ON per.DNI = p.DNI_PASAJERO "
            + "WHERE p.COD_VUELO = ?";

    System.out.println("\n--- INFORMACIÓN PERSONAL DE LOS PASAJEROS DEL VUELO " + codigo + " ---");
    try (PreparedStatement pstm = con.prepareStatement(sqlPasajeros)) {
      pstm.setString(1, codigo);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {
        System.out.printf("DNI: %-9s | Nombre: %-8s | Apellidos: %-15s | Número: %-3d | Código de Vuelo: %-10s | Tipo de Plaza: %-2s | Fumador: %-2s\n",
                rs.getString("DNI"),
                rs.getString("NOMBRE"),
                rs.getString("APELLIDOS"),
                rs.getInt("NUM"),
                rs.getString("COD_VUELO"),
                rs.getString("TIPO_PLAZA"),
                rs.getString("FUMADOR"));
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL MOSTRAR LOS DATOS DE LOS PASAJEROS CON VUELO" + codigo + ": " + ex.getMessage());
    }
  }

  // Insertar vuelo por parametro.
  public void insertarVuelo(String codigo, String fecha, String destino, String origen, int num1, int num2, int num3, int num4) {
    String sql = "INSERT INTO VUELOS (COD_VUELO, HORA_SALIDA, DESTINO, PROCEDENCIA, PLAZAS_FUMADOR, PLAZAS_NO_FUMADOR, PLAZAS_TURISTA, PLAZAS_PRIMERA) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    System.out.println("\n--- INSERCCIÓN DEL VUELO CON CÓDIGO " + codigo + " ---");
    try (PreparedStatement pstm = con.prepareStatement(sql)) {
      int i = 1;
      pstm.setString(i++, codigo);
      pstm.setString(i++, fecha);
      pstm.setString(i++, destino);
      pstm.setString(i++, origen);
      pstm.setInt(i++, num1);
      pstm.setInt(i++, num2);
      pstm.setInt(i++, num3);
      pstm.setInt(i++, num4);
      int filasAfectadas = pstm.executeUpdate();
      if (filasAfectadas > 0) {
        System.out.println("El vuelo " + codigo + " se ha insertado.");
      } else {
        System.err.println("No se pudo insertar el vuelo " + codigo);
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL INSERTAR LOS DATOS DEL VUELOS: " + ex.getMessage());
    }
    // Muestra la insercción del vuelo.
    mostrarVuelosCodigo("IB-SP-1818");
  }

  // Borrar vuelo por parametro.
  public void borrarVuelo(String codigo) {
    String sqlVueloBorrar = "DELETE FROM VUELOS WHERE COD_VUELO = ?";
    System.out.println("\n--- BORRADO DEL VUELO CON CÓDIGO " + codigo + " ---");
    try (PreparedStatement pstm = con.prepareStatement(sqlVueloBorrar)) {
      pstm.setString(1, codigo);
      int vuelosBorrados = pstm.executeUpdate();
      if (vuelosBorrados > 0) {
        System.out.printf("Se ha borrado %d vuelo con el código %s.\n", vuelosBorrados, codigo);
      } else {
        System.err.println("No se ha encontrado ningún vuelo con el código: " + codigo);
      }
    } catch (SQLException ex) {
      System.err.println("ERROR AL BORRAR LOS DATOS DEL VUELO " + codigo + ": " + ex.getMessage());
    }
    // Muestra si existe el vuelo.
    mostrarVuelosCodigo("IB-SP-1818");
  }

  // Actualizar plazas.
  public void actualizarPlazasFumador() {
    String sqlUpdate = "UPDATE VUELOS SET PLAZAS_NO_FUMADOR = PLAZAS_NO_FUMADOR + PLAZAS_FUMADOR, PLAZAS_FUMADOR = 0 WHERE PLAZAS_FUMADOR > 0";
    System.out.println("\n--- ACTUALIZACIÓN DE PLAZAS DE FUMADOR A NO FUMADOR ---");
    try (PreparedStatement pstm = con.prepareStatement(sqlUpdate)) {
      int filasAfectadas = pstm.executeUpdate();
      System.out.println("Actualización de plazas completada. Filas afectadas: " + filasAfectadas);
    } catch (SQLException ex) {
      System.err.println("ERROR AL ACTUALIZAR LAS PLAZAS: " + ex.getMessage());
    }
    // Muestra los cambios en la tabla vuelos.
    mostrarVuelos();
  }

  // Crear y actualizar tablas.
  public void crearTablasYActualizar() {
    String sqlCrearPersonas = "CREATE TABLE IF NOT EXISTS PERSONAS ("
            + "  DNI VARCHAR(10) NOT NULL PRIMARY KEY,"
            + "  NOMBRE VARCHAR(100) NOT NULL,"
            + "  APELLIDOS VARCHAR(150) NOT NULL)";
    String sqlModificadoPasajeros = "ALTER TABLE PASAJEROS "
            + "ADD COLUMN DNI_PASAJERO VARCHAR(10) NULL";

    System.out.println("\n--- CREACIÓN DE LA TABLA PERSONAS ---");
    try (Statement stm = con.createStatement()) {
      stm.executeUpdate(sqlCrearPersonas);
      System.out.println("La tabla PERSONAS ha sido creada.");
    } catch (SQLException ex) {
      System.err.println("ERROR AL CREAR LA TABLA PERSONAS: " + ex.getMessage());
    }

    System.out.println("\n--- ACTUALIZACIÓN DE LA TABLA PASAJEROS ---");
    try (Statement stm = con.createStatement()) {
      stm.executeUpdate(sqlModificadoPasajeros);
      System.out.println("Se ha actualizado la tabla PASAJEROS.");
    } catch (SQLException ex) {
      System.err.println("ERROR AL MODIFICAR LA TABLA PASAJEROS: " + ex.getMessage());
    }
  }

  // Insercción de datos de las personas.
  public void insertarPasajeros(String codigo, String[][] datosPasajeros) {
    String sqlPersona = "INSERT INTO PERSONAS (DNI, NOMBRE, APELLIDOS) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE NOMBRE=VALUES(NOMBRE), APELLIDOS=VALUES(APELLIDOS)";
    String sqlPasajero = "UPDATE PASAJEROS SET DNI_PASAJERO = ? WHERE NUM = ?";

    System.out.println("\n--- INSERTACCIÓN DE DATOS DE LAS PERSONAS DEL VUELO " + codigo + " ---");
    try (PreparedStatement pstm = con.prepareStatement(sqlPersona)) {
      for (String[] persona : datosPasajeros) {
        pstm.setString(1, persona[1]);
        pstm.setString(2, persona[2]);
        pstm.setString(3, persona[3]);
        pstm.addBatch();
      }
      int[] resultadosPersonas = pstm.executeBatch();
      System.out.println("Se han insertado un total de " + resultadosPersonas.length + " personas.");
    } catch (SQLException ex) {
      System.err.println("ERROR AL INSERTAR LOS DATOS EN PERSONAS: " + ex.getMessage());
    }

    try (PreparedStatement pstm = con.prepareStatement(sqlPasajero)) {
      for (String[] persona : datosPasajeros) {
        pstm.setString(1, persona[1]);
        pstm.setInt(2, Integer.parseInt(persona[0]));
        pstm.addBatch();
      }
      int[] resultadosPersonas = pstm.executeBatch();
      System.out.println("Se han actualizado " + resultadosPersonas.length + " personas del vuelo " + codigo + ".");
    } catch (SQLException ex) {
      System.err.println("ERROR AL INSERTAR LOS DATOS EN PASAJEROS: " + ex.getMessage());
    }

    // Muestra el resultado tras las insercciones
    mostrarInformacionPersonasDNI(codigo);
  }
}
