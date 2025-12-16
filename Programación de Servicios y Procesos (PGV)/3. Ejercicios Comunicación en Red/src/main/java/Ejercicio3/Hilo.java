/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

// Importa las librerías necesarias
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author byAlexLR
 */
public class Hilo extends Thread {

  // Variables
  private final Socket sk;
  private final int id;

  // Constructor
  public Hilo(Socket sk, int id) {
    this.sk = sk;
    this.id = id;
  }

  // Método run
  @Override
  public void run() {
    try {
      // Flujos de entrada y salida para comunicarse con el cliente
      DataInputStream entrada = new DataInputStream(sk.getInputStream());
      DataOutputStream salida = new DataOutputStream(sk.getOutputStream());

      // Lee el mensaje del cliente
      String respuesta = entrada.readUTF();
      System.out.println("Respuesta del cliente " + id + ": " + respuesta);

      // Envía una respuesta al cliente
      salida.writeUTF("Hola " + id);

      // Cierra el socket
      sk.close();

      // Captura las posibles excepciones
    } catch (IOException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
