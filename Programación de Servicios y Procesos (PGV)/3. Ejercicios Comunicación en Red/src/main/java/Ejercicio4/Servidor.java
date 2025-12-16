/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio4;

// Importa las librerías necesarias
import java.io.IOException;
import java.io.ObjectInputStream;
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
      System.out.println("El servidor de objetos TCP está escuchando el puerto 5000");

      // Acepta la conexión del cliente
      Socket sk = serSk.accept();
      System.out.println("Se ha conectado el Cliente");

      // Flujo de entrada para recibir el objeto Persona del cliente
      ObjectInputStream entrada = new ObjectInputStream(sk.getInputStream());

      // Lee el objeto Persona enviado por el cliente
      Persona pers = (Persona) entrada.readObject();
      System.out.println("Persona: " + pers);

      // Cierra los sockets
      sk.close();
      serSk.close();

      // Captura las posibles excepciones
    } catch (IOException | ClassNotFoundException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
