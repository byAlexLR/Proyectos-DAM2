/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JesusYMatias2;

// Importa las librerías.
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Main {

    public static void main(String[] args) {
        // Inicializa el Scanner y la lista de libros.
        Scanner sc = new Scanner(System.in);
        ArrayList<Libro> libros = new ArrayList<>();

        // Añade algunos libros a la colección.
        libros.add(new Manga("Como ser Pedritos", "Pedrito", 19.99, true));
        libros.add(new Manga("Alfredos Pedritos", "Alfredos", 30.99, false));
        libros.add(new Comic("Como ser Pedritos", "Pedrito", 30.99, "La casa de la abuela"));
        libros.add(new Comic("Alfredos Pedritos", "Alfredos", 19.99, "El pedro edita"));

        // Variable para controlar el bucle del menú.
        boolean empieza = true;

        // Bucle principal del programa.
        while (empieza) {
            // Muestra el menú de opciones.
            System.out.print("\n¿Qué desea hacer:?\n1.Agregar nuevos elementos.\n2.Consultar los libros disponibles.\n3.Filtrar por categoría.\n4.Salir del programa.\nElige tu opción: ");
            int opcion = sc.nextInt();

            // Evalúa la opción elegida por el usuario.
            switch (opcion) {
                // Caso 1: Agregar un nuevo elemento.
                case 1 -> {
                    // Pide los datos comunes del libro.
                    System.out.print("Introduce el nombre: ");
                    String nombre = sc.next();
                    System.out.print("Introduce el autor: ");
                    String autor = sc.next();
                    System.out.print("Introduce el precio: ");
                    double precio = sc.nextDouble();
                    boolean color = false;

                    // Pregunta por la categoría específica a añadir.
                    System.out.print("\n¿Qué categoría de libro quieres ingresar? (1.Manga y 2.Comic): ");
                    opcion = sc.nextInt();
                    switch (opcion) {
                        // Si es un Manga, pide si es a color.
                        case 1 -> {
                            System.out.print("¿Es a color?(Si o No): ");
                            String colorTiene = sc.next().toLowerCase();
                            if ("si".equals(colorTiene)) {
                                color = true;
                            } else if ("no".equals(colorTiene)) {
                                color = false;
                            } else {
                                System.out.println("Opción inválida.");
                            }
                            // Añade el nuevo Manga a la lista.
                            libros.add(new Manga(nombre, autor, precio, color));
                        }
                        // Si es un Comic, pide la editorial.
                        case 2 -> {
                            System.out.print("¿Cuál es su editorial?:");
                            String editorial = sc.next();
                            // Añade el nuevo Comic a la lista.
                            libros.add(new Comic(nombre, autor, precio, editorial));
                        }
                        default -> {
                            System.out.println("El valor introducido es inválido.");
                        }
                    }
                }
                // Caso 2: Consultar todos los libros.
                case 2 -> {
                    System.out.println("\nHistorial de libros:");
                    // Recorre la lista y muestra cada libro.
                    for (Libro lib : libros) {
                        System.out.println(lib.toString());
                    }
                }
                // Caso 3: Filtrar por categoría.
                case 3 -> {
                    // Crea una lista para guardar los resultados del filtro.
                    ArrayList<Libro> filtrado = new ArrayList<>();
                    System.out.print("¿Qué categoría te gustaría buscar? (1.Manga y 2.Comic): ");
                    int catego = sc.nextInt();
                    switch (catego) {
                        // Filtra y guarda solo los Mangas.
                        case 1 -> {
                            for (Libro lib : libros) {
                                if (lib instanceof Manga manga) {
                                    filtrado.add(manga);
                                }
                            }
                        }
                        // Filtra y guarda solo los Comics.
                        case 2 -> {
                            for (Libro lib : libros) {
                                if (lib instanceof Comic comic) {
                                    filtrado.add(comic);
                                }
                            }
                        }
                        default -> {
                            System.out.println("El valor introducido es inválido.");
                        }
                    }
                    // Muestra los libros encontrados en el filtro.
                    System.out.println("\nSe han encontrado los siguientes libros:");
                    for (Libro filtra : filtrado) {
                        System.out.println(filtra.toString());
                    }
                }
                // Caso 4: Salir del programa.
                case 4 -> {
                    System.out.println("\nSaliendo del programa.");
                    empieza = false;
                }
                default -> {
                    System.out.println("\nEl valor introducido es inválido.");
                }
            }

        }
    }
}
