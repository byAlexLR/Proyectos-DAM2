/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.alex.bdcoches;

// Importa las librerías.
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

    // Declaración de la variable de conexión.
    private Connection con;

// Método para establecer la conexión con la base de datos.
    public void abrirConexion() {
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/DatosCoches";
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("\nConexión establecida con la base de datos.");
        } catch (SQLException e) {
            System.err.println("\nERROR AL CONECTARSE CON LA BBDD: " + e.getMessage());
        }
    }

    // Método para cerrar la conexión con la base de datos.
    public void cerrarConexion() {
        try {
            if (con != null) {
                con.close();
                System.out.println("\nConexión cerrada con la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("\nERROR AL CERRAR LA CONEXIÓN CON LA BBDD: " + e.getMessage());
        }
    }

    // Método para mostrar los coches ordenados por precio descendente.
    public void mostrarDatosCoches() {
        System.out.println("\n--- Listado de Coches ---");
        String sql = "SELECT * FROM Coches ORDER BY Precio DESC";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("Matrícula: %-10s | Marca: %-10s | Precio: %-6d | DNI: %s\n",
                        rs.getString("Matricula"),
                        rs.getString("Marca"),
                        rs.getInt("Precio"),
                        rs.getString("DNI"));
            }
        } catch (SQLException e) {
            System.err.println("\nERROR AL MOSTRAR LOS DATOS: " + e.getMessage());
        }
    }

    // Método para modificar el precio de un coche.
    public void modificarCoche(String matricula, int precio) {
        String sql = "UPDATE Coches SET Precio = ? WHERE Matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            int i = 1;
            pstm.setInt(i++, precio);
            pstm.setString(i++, matricula);
            int filasAfectadas = pstm.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.printf("\nEl coche con matrícula %s ha sido actualizado. Nuevo precio: %d\n", matricula, precio);
            } else {
                System.out.printf("\nAVISO: No se encontró ningún coche con matrícula %s\n", matricula);
            }
        } catch (SQLException e) {
            System.err.println("\nERROR AL MODIFICAR LOS DATOS: " + e.getMessage());
        }
    }

    // Método para borrar un coche por su matrícula.
    public void borrarCoche(String matricula) {
        String sql = "DELETE FROM Coches WHERE Matricula = ?";
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, matricula);
            int filasAfectadas = pstm.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.printf("\nEl coche con matrícula %s ha sido borrado.\n", matricula);
            } else {
                System.out.printf("\nAVISO: No se encontró ningún coche con matrícula %s\n", matricula);
            }
        } catch (SQLException e) {
            System.err.println("\nERROR AL BORRAR LOS DATOS: " + e.getMessage());
        }
    }

    // Método modificado para insertar un coche, verificando que el propietario existe.
    public void insertarCoche(String matricula, String marca, int precio, String dni) {
        // Comprobar que el propietario existe.
        String PropietarioSQL = "SELECT DNI FROM Propietarios WHERE DNI = ?";
        boolean propietarioExiste = false;

        try (PreparedStatement pstm = con.prepareStatement(PropietarioSQL)) {
            pstm.setString(1, dni);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    propietarioExiste = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("\nERROR AL VERIFICAR LOS DATOS DEL PROPIETARIO: " + e.getMessage());
            return;
        }

        // Insertar el coche si el propietario exsite.
        if (propietarioExiste) {
            String CocheSql = "INSERT INTO Coches (Matricula, Marca, Precio, DNI) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstm = con.prepareStatement(CocheSql)) {
                int i = 1;
                pstm.setString(i++, matricula);
                pstm.setString(i++, marca);
                pstm.setInt(i++, precio);
                pstm.setString(i++, dni);
                pstm.executeUpdate();
                System.out.printf("\nEl coche %s - %s ha sido insertado correctamente.\n", matricula, marca);
            } catch (SQLException e) {
                System.err.println("\nLa matrícula " + matricula + " ya existe.");
            }
        } else {
            System.err.printf("\nNo se puede insertar el coche, porque el propietario con DNI %s no existe.\n", dni);
        }
    }

    // Método para insertar un nuevo propietario.
    public void insertarPropietario(String dni, String nombre, int edad) {
        String sql = "INSERT INTO Propietarios (DNI, Nombre, Edad) VALUES (?, ?, ?)";
        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            int i = 1;
            pstm.setString(i++, dni);
            pstm.setString(i++, nombre);
            pstm.setInt(i++, edad);
            pstm.executeUpdate();
            System.out.printf("\nEl propietario %s - %s ha sido insertado correctamente.\n", dni, nombre);
        } catch (SQLException e) {
            System.err.println("\nEl DNI " + dni + " ya existe.");
        }
    }

    // Método para listar los datos de un propietario y sus coches.
    public void listarCochesPorPropietario(String dni) {
        // Utilizando LEFT JOIN muestro los datos de ambas tablas.
        String sql = "SELECT p.Nombre, p.Edad, c.Matricula, c.Marca, c.Precio "
                + "FROM Propietarios p LEFT JOIN Coches c ON p.DNI = c.DNI "
                + "WHERE p.DNI = ?";

        try (PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, dni);
            ResultSet rs = pstm.executeQuery();

            boolean propietarioMostrado = false;
            boolean tieneCoches = false;

            while (rs.next()) {
                // Muestra los datos del propietario.
                if (!propietarioMostrado) {
                    System.out.printf("\n--- Datos del Propietario %s y sus Coches  ---\n", dni);
                    System.out.printf("Nombre: %s - Edad: %d\nCoches:\n", rs.getString("Nombre"), rs.getInt("Edad"));
                    propietarioMostrado = true;
                }

                // Mientras no se encuentre un null, mostrará todos los coches.
                if (rs.getString("Matricula") != null) {
                    tieneCoches = true;
                    System.out.printf("  - Matrícula: %s, Marca: %s, Precio: %d\n",
                            rs.getString("Matricula"),
                            rs.getString("Marca"),
                            rs.getInt("Precio"));
                }
            }

            // En el caso de que no se encuentre el propietario o no tenga coches.
            if (!propietarioMostrado) {
                System.out.printf("\nNo se encontró ningún propietario con DNI %s\n", dni);
            } else if (!tieneCoches) {
                System.out.println("  - El propietario no tiene coches registrados.");
            }
        } catch (SQLException e) {
            System.err.println("\nERROR AL MOSTRAR LOS DATOS DEL PROPIETARIO Y SUS COCHES: " + e.getMessage());
        }
    }

    // Método para borrar un propietario y todos sus coches.
    public void borrarPropietarioConCoches(String dni) {
        String borrarCochesSql = "DELETE FROM Coches WHERE DNI = ?";
        String borrarPropietarioSql = "DELETE FROM Propietarios WHERE DNI = ?";

        try {
            con.setAutoCommit(false); // Desactiva el auto-commit.

            // Borra los coches del propietario.
            try (PreparedStatement pstm = con.prepareStatement(borrarCochesSql)) {
                pstm.setString(1, dni);
                int cochesBorrados = pstm.executeUpdate();
                if (cochesBorrados > 0) {
                    System.out.printf("\nSe han borrado %d coches del propietario %s.", cochesBorrados, dni);
                } else {
                    // Si no poseé coches.
                    System.out.printf("\nNo se han encontrado coches del propietario con DNI %s.", dni);
                }
            }

            // Borra al propietario.
            try (PreparedStatement pstm = con.prepareStatement(borrarPropietarioSql)) {
                pstm.setString(1, dni);
                int propietarioBorrado = pstm.executeUpdate();
                if (propietarioBorrado > 0) {
                    System.out.printf("\nEl propietario con DNI %s ha sido borrado con éxito.", dni);
                } else {
                    // Si no se encontró el propietario, no hay nada que borrar.
                    System.out.printf("\nNo se ha encontrado ningún propietario con DNI %s.", dni);
                }
            }

            // Confirma los cambios en la BBDD.
            con.commit();
            System.out.printf("\nEl propietario %s y sus coches han sido borrados con éxito.\n", dni);

        } catch (SQLException e) {
            System.err.println("\nERROR AL BORRAR LOS DATOS DEL PROPIETARIO Y SUS COCHES (Revirtiendo cambios): " + e.getMessage());
            try {
                con.rollback(); // Realiza un rollback.
            } catch (SQLException se) {
                System.err.println("\nERROR AL REALIZAR EL ROLLBACK: " + e.getMessage());
            }
        } finally {
            try {
                con.setAutoCommit(true); // Reactiva el auto-commit.
            } catch (SQLException e) {
                System.err.println("\nERROR AL REACTIVAR EL AUTO-COMMIT: " + e.getMessage());
            }
        }
    }
}
