/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

// Importación de las librerías.
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Main {
  
  // Declaración de las variables.
  private static final ArrayList<Multas> multasPendientes = new ArrayList<>();
  private static final ArrayList<Pago> multaAPagar = new ArrayList<>();
  private static double dinero = 1000.0;
  private static int opcion = 0;

  public static void main(String args[]) {
    // Datos de ejemplo para probar el sistema.
    try (Scanner sc = new Scanner(System.in)) {
      // Vehículos almacenados.
      multasPendientes.add(new Multas("Exceso de velocidad", 120.50, "2548DHK"));
      multasPendientes.add(new Multas("No usar cinturón", 60, "2548DHK"));
      multasPendientes.add(new Multas("Estacionamiento indebido", 80.00, "6503ALP"));
      multasPendientes.add(new Multas("Semáforo en rojo", 200.00, "1789XYZ"));
      multasPendientes.add(new Multas("No usar cinturón", 90.00, "4566DEF"));

      // Bucle principal del menú, muestra el menú y espera a que el usuario elija una opción.
      while (opcion != 4) {
        System.out.printf(
                "\nSISTEMA DE GESTIÓN DE MULTAS\n1. Consultar multas pendientes\n2. Pagar multa\n3. Consultar multas anteriores\n4. Salir\n\nSaldo Actual: %.2f€\nElige una opción: ",
                dinero);
        opcion = sc.nextInt();
        // Selecciona una opción del menú.
        switch (opcion) {
          case 1 ->
            consultarMultas(sc);
          case 2 ->
            pagarMulta(sc);
          case 3 ->
            anterioresMultas(sc);
          case 4 ->
            System.out.println("Ha salido del Sistema de Gestión de Multas (SGM).");
          default ->
            System.out.print("La opción introducida no es válida.");
        }
      }
      // Cierre del scanner.
      sc.close();
      // Manejo de excepciones.
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  // Método para consultar las multas pendientes.
  private static void consultarMultas(Scanner sc) {
    // Pedir la matrícula del vehículo y convertirla a mayúsculas.
    System.out.print("Por favor, indica la matrícula del vehículo: ");
    sc.nextLine();
    String matricula = sc.nextLine().toUpperCase();
    // Contador para verificar si el vehículo tiene multas pendientes.
    int contador = 0;

    // Mostrar las multas pendientes del vehículo.
    System.out.println("\nMULTAS PENDIENTES DEL VEHÍCULO " + matricula);
    for (Multas multa : multasPendientes) {
      // Verifica si la matrícula del vehículo coincide con la matrícula de la multa.
      if (multa.getMatricula().equals(matricula)) {
        // Muestra la multa.
        System.out.println("Multa: " + multa.getDescripcion() + " - Importe: " + multa.getImporteAPagar());
        // Incrementa el contador.
        contador++;
      }
    }

    // Verifica si el vehículo no tiene multas pendientes.
    if (contador == 0) {
      System.out.println("El vehículo con matrícula " + matricula + " NO tiene multas pendientes.");
    }
  }

  // Método para pagar una multa.
  private static void pagarMulta(Scanner sc) {
    // Pedir la matrícula del vehículo y convertirla a mayúsculas.
    System.out.print("Por favor, indica la matrícula del vehículo: ");
    sc.nextLine();
    String matricula = sc.nextLine().toUpperCase();

    // Crear una lista temporal para almacenar las multas del vehículo.
    ArrayList<Multas> multasVehiculo = new ArrayList<>();
    // Recorre las multas pendientes y agrega las multas del vehículo a la lista temporal.
    for (Multas multa : multasPendientes) {
      if (multa.getMatricula().equals(matricula)) {
        multasVehiculo.add(multa);
      }
    }

    // Verifica si el vehículo no tiene multas pendientes.
    if (multasPendientes.isEmpty() || multasVehiculo.isEmpty()) {
      System.out.println("El vehículo con matrícula " + matricula + " NO tiene multas pendientes.");
      return;
    }

    // Muestra las multas pendientes del vehículo.
    System.out.println("\nMULTAS PENDIENTES DEL VEHÍCULO " + matricula);
    // Recorre la lista temporal y muestra las multas del vehículo.
    for (int i = 0; i < multasVehiculo.size(); i++) {
      // Muestra la multa.
      System.out.println((i + 1) + ". " + multasVehiculo.get(i).toString());
    }

    // Pedir el número de la multa que desea pagar.
    System.out.print("Indica el número de la multa que desea pagar: ");
    int opcionMulta = sc.nextInt();

    // Verifica si la selección es válida.
    if (opcionMulta < 1 || opcionMulta > multasVehiculo.size()) {
      System.out.println("Selección inválida");
      return;
    }

    // Obtiene la multa a pagar.
    double importe = multasVehiculo.get(opcionMulta - 1).getImporteAPagar();

    // Verifica si el saldo es suficiente para pagar la multa.
    if (dinero >= importe) {
      // Paga la multa.
      dinero -= importe;
      // Crea un objeto Pago a partir de la multa seleccionada.
      Multas multaSeleccionada = multasVehiculo.get(opcionMulta - 1);
      multaAPagar.add(new Pago(importe, matricula, multaSeleccionada.getDescripcion()));
      // Elimina la multa de la lista de multas pendientes.
      multasPendientes.remove(multaSeleccionada);
      // Muestra el mensaje de pago exitoso.
      System.out.printf("\nSe ha pagado correctamente la multa. Su saldo es de: %.2f€\n", dinero);
    } else {
      // Muestra el mensaje de saldo insuficiente.
      System.out.printf("\nSaldo insuficiente para tramitar el pago. Necesitas %.2f€ para pagar la multa, su saldo es de %.2f€\n", importe, dinero);
    }
  }

  // Método para consultar las multas anteriores.
  private static void anterioresMultas(Scanner sc) {
    // Pedir la matrícula del vehículo y convertirla a mayúsculas.
    System.out.print("Por favor, indica la matrícula del vehículo: ");
    sc.nextLine();
    String matricula = sc.nextLine().toUpperCase();

    // Crear una lista temporal para almacenar las multas del vehículo.
    ArrayList<Pago> multasVehiculo = new ArrayList<>();
    // Recorre las multas pagadas y agrega las multas del vehículo a la lista temporal.
    for (Pago pago : multaAPagar) {
      if (pago.getMatricula().equals(matricula)) {
        multasVehiculo.add(pago);
      }
    }

    // Verifica si el vehículo no tiene multas anteriores.
    if (multasVehiculo.isEmpty()) {
      System.out.println("El vehículo con matrícula " + matricula + " NO tiene multas anteriores.");
      return;
    }

    // Muestra las multas anteriores.
    System.out.println("\nMULTAS ANTERIORES DEL VEHÍCULO " + matricula);
    // Recorre la lista y muestra las multas del vehículo.
    for (int i = 0; i < multasVehiculo.size(); i++) {
      System.out.println((i + 1) + ". " + multasVehiculo.get(i).getDescripcion() + " - Importe: " + multasVehiculo.get(i).getImportePagado() + "�");
    }
  }
}
