/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio5;

// Importa las librerías necesarias
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author byAlexLR
 */
public class Cliente {

  public static void main(String[] args) {
    try {
      // Crea el socket para conectarse al servidor
      DatagramSocket sk = new DatagramSocket();
      InetAddress dirServidor = InetAddress.getByName("localhost"); // Dirección del servidor

      // Envía un mensaje al servidor
      String mensaje = "Hola UDP";
      byte[] buffer = mensaje.getBytes(); // Convierte el mensaje a bytes

      // Envía el paquete al servidor por el puerto 5000
      DatagramPacket paqEnvio = new DatagramPacket(buffer, buffer.length, dirServidor, 5000);
      sk.send(paqEnvio);
      System.out.println("Se ha enviado el mensaje");

      // Recibe la respuesta del servidor
      byte[] bufferRecepcion = new byte[1024];
      DatagramPacket paqRecibido = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
      sk.receive(paqRecibido);

      // Muestra la respuesta del servidor
      String respuesta = new String(paqRecibido.getData(), 0, paqRecibido.getLength());
      System.out.println("Respuesta del servidor: " + respuesta);

      // Cierra el socket
      sk.close();

      // Captura las posibles excepciones
    } catch (IOException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
