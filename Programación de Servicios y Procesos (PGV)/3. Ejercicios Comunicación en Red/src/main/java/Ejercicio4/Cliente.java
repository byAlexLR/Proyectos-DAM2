/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

// Importa las librerías necesarias
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author byAlexLR
 */
public class Cliente {

  public static void main(String[] args) {
    try {
      // Crea el socket para conectarse al servidor por el puerto 5000
      Socket sk = new Socket("localhost", 5000);
      System.out.println("Se ha conectado al servidor");

      // Flujo de salida para enviar el objeto Persona al servidor
      ObjectOutputStream salida = new ObjectOutputStream(sk.getOutputStream());

      // Crea el objeto Persona y lo envía al servidor
      Persona pers = new Persona("Alex", 23);
      salida.writeObject(pers); // Envía el objeto
      salida.flush(); // Asegura que se envíe inmediatamente

      System.out.println("Se ha enviado el objecto persona");

      // Cierra el socket
      sk.close();

      // Captura las posibles excepciones
    } catch (IOException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
