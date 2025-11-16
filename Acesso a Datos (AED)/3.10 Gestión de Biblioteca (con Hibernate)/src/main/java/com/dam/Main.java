/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.dam;

// Importa las clases necesarias
import com.dam.gestion.GestionBiblioteca;
import com.dam.modelo.Ejemplar.EstadoEjemplar;
import com.dam.util.HibernateUtil;

// Importa las librerías necesarias
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Main {

    // Variables estáticas
    private static final Scanner sc = new Scanner(System.in);
    private static final GestionBiblioteca gestion = new GestionBiblioteca();

    public static void main(String[] args) {
        boolean salir = false; // Variable para controlar el bucle while

        // Bucle While: Muestra el menú y espera la opción del usuario.
        while (!salir) {
            try {
                System.out.print("\n--- SISTEMA DE GESTIÓN DE BIBLIOTECA ---\n1.Crear nuevo autor con libros\n2.Agregar ejemplar a libro existente\n3.Listar todos los autores\n4.Buscar libro por ID\n5.Buscar ejemplares por estado\n6.Mostrar estadísticas\n7.Actualizar estado de ejemplar\n8.Actualizar datos de libro\n9.Transferir libros entre autores\n10.Eliminar ejemplar\n11.Eliminar libro\n12.Eliminar autor (con cascada)\n0.Salir\n\nSelecciona una opción: ");
                int opcion = sc.nextInt(); // Lee la opción del usuario

                // Switch: Ejecuta la acción correspondiente a la opción seleccionada
                switch (opcion) {
                    case 1 ->
                        crearAutorConLibros(); // Crea un autor con sus libros
                    case 2 ->
                        agregarEjemplarALibro(); // Agrega un ejemplar a un libro existente
                    case 3 ->
                        listarAutores(); // Lista todos los autores
                    case 4 ->
                        buscarLibro(); // Busca un libro por su ID
                    case 5 ->
                        buscarEjemplaresPorEstado(); // Busca los ejemplares por su estado
                    case 6 ->
                        mostrarEstadisticas(); // Muestra las estadísticas de la biblioteca
                    case 7 ->
                        actualizarEstadoEjemplar(); // Actualiza el estado de un ejemplar
                    case 8 ->
                        actualizarDatosLibro(); // Actualiza los datos de un libro
                    case 9 ->
                        transferirLibrosEntreAutores(); // Transferir libros entre autores
                    case 10 ->
                        eliminarEjemplar(); // Elimina un ejemplar
                    case 11 ->
                        eliminarLibro(); // Elimina un libro
                    case 12 ->
                        eliminarAutor(); // Elimina un autor con cascada
                    case 0 ->
                        salir = true; // Cierra el bucle
                    default ->
                        System.out.println("La opción seleccionada no es válida. Intentalo de nuevo.");
                }
                // Captura las posibles excepciones
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Debe introducir un número válido.");
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }

        // Cierra los recursos
        HibernateUtil.shutdown();
        sc.close();
        System.out.println("\nSe ha cerrado la sessión.");
    }

    // Métodos para las diferentes opciones
    private static void crearAutorConLibros() {
        System.out.println("\n--- CREAR AUTOR CON LIBROS ---");
        gestion.crearAutorConLibros();
    }

    private static void agregarEjemplarALibro() {
        System.out.println("\n--- AGREGAR EJEMPLAR A LIBRO ---");
        // Pide al usuario el ISBN y el código del ejemplar
        System.out.print("Ingresa el ISBN del libro: ");
        String isbn = sc.next();
        sc.nextLine(); // Limpia el buffer

        System.out.print("Ingresa el código del ejemplar: ");
        String codigoEjemplar = sc.next();

        sc.nextLine(); // Limpia el buffer
        // Se lo pasa por parámetro llamando al método de GestionBiblioteca
        gestion.agregarEjemplarALibroExistente(isbn, codigoEjemplar);
    }

    private static void listarAutores() {
        System.out.println("\n--- LISTADO DE AUTORES ---");
        // Llama al método de GestionBiblioteca
        gestion.listarTodosLosAutores();
    }

    private static void buscarLibro() {
        System.out.println("\n--- BUSCAR LIBRO ---");
        // Pide al usuario el ID del libro
        System.out.print("Ingresa el ID del libro: ");
        Integer idLibro = sc.nextInt();

        // Se lo pasa por parámetro llamando al método de GestionBiblioteca
        gestion.buscarLibroPorId(idLibro);
    }

    private static void buscarEjemplaresPorEstado() {
        System.out.println("\n--- BUSCAR EJEMPLARES POR ESTADO ---");
        // Pide al usuario el estado que quiere buscar
        System.out.print("\nSelecciona un estado (1.Disponible, 2.Prestado, 3.Reparación, 4.Baja): ");
        int opcion = sc.nextInt();

        // Comprueba que el valor introducido comprenda entre 1 y 4
        if (opcion >= 1 && opcion <= 4) {
            EstadoEjemplar estado = EstadoEjemplar.values()[opcion - 1]; // Resta 1 a la opción introducida para asignarla al valor real del ENUM
            // Se lo pasa por parámetro llamando al método de GestionBiblioteca
            gestion.buscarEjemplaresPorEstado(estado);
        } else {
            // En caso de no ser un valor válido
            System.out.println("La opción seleccionada no es válida.");
        }
    }

    private static void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS DE LA BIBLIOTECA ---");
        // Llama al método de GestionBiblioteca
        gestion.obtenerEstadisticasBiblioteca();
    }

    private static void actualizarEstadoEjemplar() {
        System.out.println("\n--- ACTUALIZAR ESTADO DEL EJEMPLAR ---");
        // Pide al usuario el ID del ejemplar a buscar
        System.out.print("Ingresa el ID del ejemplar: ");
        Integer idEjemplar = sc.nextInt();

        // Pide al usuario el nuevo estado del ejemplar
        System.out.print("\nSelecciona el nuevo estado del ejemplar (1.Disponible, 2.Prestado, 3.Reparación, 4.Baja): ");
        int opcion = sc.nextInt();

        // Comprueba que el valor introducido comprenda entre 1 y 4
        if (opcion >= 1 && opcion <= 4) {
            EstadoEjemplar nuevoEstado = EstadoEjemplar.values()[opcion - 1]; // Resta 1 a la opción introducida para asignarla al valor real del ENUM
            // Se lo pasa por parámetro llamando al método de GestionBiblioteca
            gestion.actualizarEstadoEjemplar(idEjemplar, nuevoEstado);
        } else {
            // En caso de no ser un valor válido
            System.out.println("La opción seleccionada no es válida.");
        }
    }

    private static void actualizarDatosLibro() {
        System.out.println("\n--- ACTUALIZAR DATOS DE LIBRO ---");
        // Pide al usuario el ID del libro a buscar
        System.out.print("Ingresa el ID del libro: ");
        Integer idLibro = sc.nextInt();

        sc.nextLine(); // Limpia el buffer

        // Pide al usuario el nuevo título y número de páginas del libro
        System.out.print("Ingresa el nuevo título: ");
        String nuevoTitulo = sc.nextLine();

        System.out.print("Ingresa el nuevo número de páginas: ");
        Integer nuevasPaginas = sc.nextInt();

        // Se lo pasa por parámetro llamando al método de GestionBiblioteca
        gestion.actualizarDatosLibro(idLibro, nuevoTitulo, nuevasPaginas);
    }

    private static void transferirLibrosEntreAutores() {
        System.out.println("\n--- TRANSFERIR LIBROS ENTRE AUTORES ---");
        // Pide al usuario el ID del autor de Origen y Destino
        System.out.print("Ingresa el ID del autor de ORIGEN: ");
        Integer idAutorOrigen = sc.nextInt();
        System.out.print("Ingresa el ID del autor de DESTINO: ");
        Integer idAutorDestino = sc.nextInt();

        // Se lo pasa por parámetro llamando al método de GestionBiblioteca
        gestion.transferirLibrosEntreAutores(idAutorOrigen, idAutorDestino);
    }

    private static void eliminarEjemplar() {
        System.out.println("\n--- ELIMINAR EJEMPLAR ---");
        // Pide al usuario el ID del ejemplar a eliminar
        System.out.print("Ingresa el ID del ejemplar a eliminar: ");
        Integer idEjemplar = sc.nextInt();

        // Pregunta si está seguro al usuario y pasa el valor introducido a mayúsculas
        System.out.print("¿Estás seguro? (S/N): ");
        String confirmacion = sc.next().toUpperCase();
        sc.nextLine(); // Limpia el buffer

        // Comprueba la confirmación y si es S llama al método pasando el id como parámetro, sino, se cancela
        if (confirmacion.equalsIgnoreCase("S")) {
            gestion.eliminarEjemplar(idEjemplar);
        } else {
            System.out.println("La operación ha sido cancelada");
        }
    }

    private static void eliminarLibro() {
        System.out.println("\n--- ELIMINAR LIBRO ---");
        // Pide al usuario el ID del libro a eliminar
        System.out.print("Ingresa el ID del libro a eliminar: ");
        Integer idLibro = sc.nextInt();

        // Pregunta si está seguro al usuario y pasa el valor introducido a mayúsculas
        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = sc.next().toUpperCase();
        sc.nextLine(); // Limpia el buffer

        // Comprueba la confirmación y si es S llama al método pasando el id como parámetro, sino, se cancela
        if (confirmacion.equalsIgnoreCase("S")) {
            gestion.eliminarLibro(idLibro);
        } else {
            System.out.println("La operación ha sido cancelada");
        }
    }

    private static void eliminarAutor() {
        System.out.println("\n--- ELIMINAR AUTOR ---");
        // Pide al usuario el ID del autor a eliminar
        System.out.print("Ingresa el ID del autor a eliminar: ");
        Integer idAutor = sc.nextInt();

        // Pregunta si está seguro al usuario y pasa el valor introducido a mayúsculas
        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = sc.next().toUpperCase();
        sc.nextLine(); // Limpia el buffer

        // Comprueba la confirmación y si es S llama al método pasando el id como parámetro, sino, se cancela
        if (confirmacion.equalsIgnoreCase("S")) {
            gestion.eliminarAutorConCascada(idAutor);
        } else {
            System.out.println("La operación ha sido cancelada");
        }
    }
}
