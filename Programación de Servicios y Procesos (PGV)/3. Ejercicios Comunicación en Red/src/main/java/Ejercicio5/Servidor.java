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
public class Servidor {

  public static void main(String[] args) {
    try {
      // Crea el socket para recibir datagramas por el puerto 5000
      DatagramSocket sk = new DatagramSocket(5000);
      byte[] buffer = new byte[1024]; // Buffer para recibir datos
      System.out.println("El servidor UDP está escuchando el puerto 5000");

      // Recibe el paquete del cliente
      DatagramPacket paqRecibido = new DatagramPacket(buffer, buffer.length);
      sk.receive(paqRecibido);

      // Muestra el mensaje recibido
      String mensaje = new String(paqRecibido.getData(), 0, paqRecibido.getLength());
      System.out.println("Recibido: " + mensaje);

      // Envía una respuesta al cliente
      String respuesta = "Mensaje recibido";
      byte[] bufferRespuesta = respuesta.getBytes(); // Convierte la respuesta a bytes

      // Obtiene la dirección y el puerto del cliente desde el paquete recibido
      InetAddress direccionCliente = paqRecibido.getAddress();
      int puertoCliente = paqRecibido.getPort();

      // Crea el paquete de respuesta para enviarlo al cliente
      DatagramPacket paqEnvio = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);

      // Envía el paquete de respuesta al cliente
      sk.send(paqEnvio);
      
      // Cierra el socket
      sk.close();

      // Captura las posibles excepciones
    } catch (IOException ex) {
      System.err.println("ERROR: " + ex.getMessage());
    }
  }
}
