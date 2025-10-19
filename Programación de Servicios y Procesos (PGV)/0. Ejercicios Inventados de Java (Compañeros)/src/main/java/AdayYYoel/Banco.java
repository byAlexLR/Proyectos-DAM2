/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdayYYoel;

// Importa las librerías.
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class Banco {

    // Método para ingresar dinero, pregunta al usuario y comprueba si el valor es válido o no.
    private static double ingresarDinero(Scanner sc, double saldo) {
        System.out.print("¿Cuánto dinero quiere ingresar?: ");
        double ingreso = sc.nextDouble();
        if (ingreso < 0) {
            System.out.println("El valor introducido es menor que 0.");
        } else {
            saldo += ingreso;
            System.out.println("Se han ingresado " + ingreso + "€ a su cuenta.");
        }
        return saldo;
    }

    // Método para retirar dinero, pregunta al usuario y comprueba si el valor es válido o no.
    private static double retirarDinero(Scanner sc, double saldo) {
        System.out.print("¿Cuánto dinero quiere retirar?: ");
        double retirar = sc.nextDouble();
        if (retirar < 0) {
            System.out.println("El valor introducido es menor que 0.");
        } else if (retirar > saldo) {
            System.out.println("No se puede hacer ese retiro.");
        } else {
            saldo -= retirar;
            System.out.println("Se han retirado " + retirar + "€ de su cuenta.");
        }
        return saldo;
    }

    // Muestra el saldo de la cuenta.
    private static void consultarDinero(double saldo) {
        System.out.println("Su saldo es de: " + saldo + "€");
    }

    public static void main(String[] args) {
        // Inicialización de las variables.
        Scanner sc = new Scanner(System.in);
        boolean empezar = true;
        double saldo = 100;

        // While para realizar las operaciones.
        while (empezar) {
            // Pregunta al usuario lo que quiere hacer.
            System.out.print("Qué quieres realizar?\n1.Ingresar Dinero.\n2.Retirar Dinero.\n3.Consultar Saldo.\n4.Salir\nIngrese el número: ");
            int opcion = sc.nextInt();

            // Entra en la opción elegida por el usuario.
            switch (opcion) {
                case 1 ->
                    saldo = ingresarDinero(sc, saldo);
                case 2 ->
                    saldo = retirarDinero(sc, saldo);
                case 3 ->
                    consultarDinero(saldo);
                case 4 -> {
                    System.out.println("Se está cerrando el banco");
                    empezar = false;
                }
                default ->
                    System.out.println("Opción no válida.");
            }
        }
    }
}
