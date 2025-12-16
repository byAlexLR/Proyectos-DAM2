/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

// Importa las librerías necesarias
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

      // Flujos de entrada y salida para comunicarse con el servidor
      DataOutputStream salida = new DataOutputStream(sk.getOutputStream());
      DataInputStream entrada = new DataInputStream(sk.getInputStream());

      // Envía un saludo al servidor
      salida.writeUTF("Hola");

      // Lee la respuesta del servidor
      String respuesta = entrada.readUTF();
      System.out.println("Respuesta del servidor: " + respuesta);

      // Cierra el socket
      sk.close();
      
      // Captura las posibles excepciones
    } catch (IOException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
