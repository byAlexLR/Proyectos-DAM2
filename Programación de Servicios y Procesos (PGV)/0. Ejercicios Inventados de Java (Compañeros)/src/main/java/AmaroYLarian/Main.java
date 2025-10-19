/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AmaroYLarian;

// Importa las librerías.
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author byAlexLR
 */
public class Main {

    public static void main(String[] args) {
        // Declaración del Arraylist.
        ArrayList<Vehiculo> coleccion = new ArrayList<>();

        // Objetos de la clase Formula.
        coleccion.add(new Formula("Deportivo", "Lisas", 250, "Media"));
        coleccion.add(new Formula("Superdeportivo", "Duras", 120, "Alta"));
        coleccion.add(new Formula("Off-Road", "4x4", 320, "Baja"));
        coleccion.add(new Formula("Deportivo", "Blandas", 250, "Alta"));
        coleccion.add(new Formula("Deportivo", "Lisas", 400, "Media"));

        // Objetos de la clase GranTurismo.
        coleccion.add(new GranTurismo("Deportivo", "Lisas", 250, 3500.20, 4));
        coleccion.add(new GranTurismo("Superdeportivo", "Blandas", 350, 1500, 4));
        coleccion.add(new GranTurismo("Off-Road", "4x", 120, 800.20, 4));
        coleccion.add(new GranTurismo("Deportivo", "Duras", 400, 1250.20, 4));
        coleccion.add(new GranTurismo("Deportivo", "Lisas", 270, 3500.20, 4));

        // Para escribir el fichero.
        try (FileWriter w = new FileWriter("coleccion.txt")) {
            // Bucle for que realiza la escritura de cada lectura.
            for (Vehiculo v : coleccion) {
                w.write(v.toString() + "\n");
            }
            // Confirma que se han guardado en el archivo.
            System.out.println("Colección guardada en coleccion.txt");
            // Recoge las posibles excepciones.
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
