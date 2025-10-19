/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Alexander;

// Importa las librerías.
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Tienda {

    public static void main(String[] args) {
        // Inicializa el Scanner y las listas de nombres, precios y stocks.
        Scanner sc = new Scanner(System.in);
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Double> precios = new ArrayList<>();
        ArrayList<Integer> stocks = new ArrayList<>();

        // Añade algunos productos a la colección.
        nombres.add("Teclado");
        precios.add(89.99);
        stocks.add(15);
        nombres.add("Raton");
        precios.add(45.50);
        stocks.add(25);
        nombres.add("Monitor");
        precios.add(150.00);
        stocks.add(10);

        // Variable para controlar el bucle del menú.
        boolean salir = false;

        // Bucle principal del programa.
        while (!salir) {
            // Muestra el menú de opciones.
            System.out.print("\n--- TIENDA BÁSICA ---\n1. Agregar producto\n2. Visualizar inventario\n3. Realizar venta\n4. Salir\nElige una opción: ");
            int opcion = sc.nextInt();

            // Evalúa la opción elegida por el usuario.
            switch (opcion) {
                // Caso 1: Añadir un producto.
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    // Añade los datos a sus respectivas listas.
                    nombres.add(nombre);
                    precios.add(precio);
                    stocks.add(stock);
                    System.out.println("Producto agregado.");
                }
                // Caso 2: Mostrar el inventario.
                case 2 -> {
                    System.out.println("\n--- INVENTARIO ---");
                    // Recorre las listas usando el tamaño de la lista nombres.
                    for (int i = 0; i < nombres.size(); i++) {
                        // Muestra los datos de cada producto.
                        System.out.println("Producto: " + nombres.get(i) + " | Precio: " + precios.get(i) + "€ | Stock: " + stocks.get(i));
                    }
                }
                // Caso 3: Realizar una venta.
                case 3 -> {
                    System.out.print("Nombre del producto a vender: ");
                    String nombreVenta = sc.next();

                    int indiceProducto = -1; // Guarda la posición del producto, -1 si no se encuentra.
                    // Busca el producto.
                    for (int i = 0; i < nombres.size(); i++) {
                        if (nombres.get(i).equalsIgnoreCase(nombreVenta)) {
                            indiceProducto = i; // Guarda la posición.
                            break; // Sale del bucle al encontrarlo.
                        }
                    }

                    // Si se encontró el producto.
                    if (indiceProducto != -1) {
                        System.out.print("Cantidad a vender: ");
                        int cantidadVenta = sc.nextInt();
                        // Comprueba si hay stock suficiente en esa posición.
                        if (stocks.get(indiceProducto) >= cantidadVenta) {
                            // Calcula el nuevo stock y lo actualiza en la lista.
                            int nuevoStock = stocks.get(indiceProducto) - cantidadVenta;
                            stocks.set(indiceProducto, nuevoStock);
                            System.out.println("Venta realizada. Stock actualizado.");
                        } else {
                            System.out.println("Error: Stock insuficiente.");
                        }
                    } else {
                        System.out.println("Error: Producto no encontrado.");
                    }
                }
                // Caso 4: Salir.
                case 4 -> {
                    salir = true;
                    System.out.println("Saliendo del programa...");
                }
                default ->
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}
