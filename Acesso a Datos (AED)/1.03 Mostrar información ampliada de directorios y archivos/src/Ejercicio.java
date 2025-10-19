/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Importa las diferentes APIs
import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author byAlexLR
 */
public class Ejercicio {
  public static void main(String[] args) {
    String nombreArchivo = "C:\\Users\\Alumno\\Documents\\"; // Variable que contiene una ruta.

    File file = new File(nombreArchivo); // Inicializa la API File con la ruta.
    if (!file.exists()) { // Comprueba si existen ficheros.
      System.out.println("El archivo NO existe.");
    } else {
      if (file.isDirectory()) { // Comprueba si es un directorio y si lo es itinera todo su contenido.
        System.out.println("Es un directorio. Contenido: ");
        File[] files = file.listFiles();
        String texto;
        for (File f : files) {
//          if (f.isDirectory()) {
//            texto = "\\";
//          } else {
//            texto = "-";
//          }
          texto = f.isDirectory() ? "\\" : "-"; // Se?aliza lo que es un directorio con \ y los archivos con -.
          long tamanoArchivos = f.length(); // El tama?o del archivo.
          long modificacionArchivos = f.lastModified(); // La última modificación.

          Date modificacionFecha = new Date(modificacionArchivos); // Inicializa la API Date.
          Calendar calendar = java.util.Calendar.getInstance(); // Inicializa la API Calendar.
          calendar.setTime(modificacionFecha); // A calendar le pasa la variable de Date, la cual se inicializó con el long de modificacionArchivos.

          int dia = calendar.get(java.util.Calendar.DAY_OF_MONTH); // Saca el día.
          int mes = calendar.get(java.util.Calendar.MONTH) + 1; // Saca el mes y le suma 1 dado que se inicializa en 0.
          int ano = calendar.get(java.util.Calendar.YEAR); // Saca el a?o.

          System.out.printf("%2s | %4d bytes | %02d/%02d/%4d | %s%n", texto, tamanoArchivos, dia, mes, ano, f.getName()); // Muestra el contenido tabulado y formateado.
          //System.out.println(texto + " |\t" + tamanoArchivos + " bytes |\t" + dia + "/" + mes + "/" + a?o + " |\t" + f.getName());
        }
      } else {
        System.out.println("Es un archivo."); // Comunica que es un archivo.
      }
    }
  }
}
