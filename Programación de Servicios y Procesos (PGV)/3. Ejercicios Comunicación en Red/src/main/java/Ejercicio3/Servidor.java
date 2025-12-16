/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3;

// Importa las librerías necesarias
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author byAlexLR
 */
public class Servidor {

  // Variable para contar los clientes
  private static int contadorCliente = 0;

  public static void main(String[] args) {
    try {
      // Crea el ServerSocket en el puerto 5000
      ServerSocket serSk = new ServerSocket(5000);
      System.out.println("El servidor TCP concurrente está escuchando el puerto 5000");

      // Bucle infinito para aceptar múltiples conexiones de clientes
      while (true) {
        // Acepta la conexión del cliente
        Socket sk = serSk.accept();
        contadorCliente++; // Incrementa el contador
        System.out.println("El cliente " + contadorCliente + " se ha conectado");

        // Crea e inicia un nuevo hilo para manejar la conexión del cliente
        Hilo hiloCliente = new Hilo(sk, contadorCliente);
        hiloCliente.start();
      }

      // Captura las posibles excepciones
    } catch (IOException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
