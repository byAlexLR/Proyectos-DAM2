/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio1;

// Importa las librerías necesarias
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author byAlexLR
 */
public class Servidor {

  public static void main(String[] args) {
    try {
      // Crea el ServerSocket en el puerto 5000
      ServerSocket serSk = new ServerSocket(5000);
      System.out.println("El servidor TCP está escuchando el puerto 5000");

      // Acepta la conexión del cliente
      Socket sk = serSk.accept();
      System.out.println("Se ha conectado el Cliente");

      // Flujo de salida para enviar datos al cliente
      DataOutputStream salida = new DataOutputStream(sk.getOutputStream());
      salida.writeUTF("Conexión establecida con el servidor");

      // Cierra los sockets
      sk.close();
      serSk.close();

      // Captura las posibles excepciones
    } catch (IOException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
