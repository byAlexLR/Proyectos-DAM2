/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.alex.gestioncochessegundamano;

// Importación de librerías
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
public class GestionCochesSegundaMano {

  // Declaración de variables de MongoDB y Scanner.
  private static MongoClient mongoClient;
  private static MongoDatabase db;
  private static MongoCollection<Document> coleccion;
  private static Scanner sc;

  public static void main(String[] args) {
    // Declaración del Scanner
    sc = new Scanner(System.in);

    try {
      // Conexión a MongoDB
      mongoClient = MongoClients.create("mongodb://localhost:27017");
      System.out.println("\nSe ha realizado la conexión con Mongo");

      // Acceder o crear la base de datos
      db = mongoClient.getDatabase("concesionario");
      System.out.println("Conexión establecida con la BBDD: concesionario");

      // Acceder o crear la colección
      coleccion = db.getCollection("coches");
      System.out.println("Se ha seleccionado la colección: coches");

      // Bucle While: Crea un menú interactivo donde muestra las diferentes opciones.
      boolean salir = false;
      while (!salir) {
        System.out.print("\nSISTEMA DE GESTIÓN DE COCHES DE SEGUNDA MANO\n1.Añadir nuevo coche.\n2.Listar todos los coches.\n3.Buscar coche por marca.\n4.Actualizar precio de un coche.\n5.Eliminar coche.\n6.Buscar coches por rango de precio.\n0.Salir\nSelecciona una opción: ");
        try {
          int opcion = sc.nextInt(); // Obtiene la opción elegida
          sc.nextLine(); // Limpia el buffer

          // Switch para llamar a los diferentes métodos según la opción
          switch (opcion) {
            case 1 ->
              anadirNuevoCoche();
            case 2 ->
              listarTodosLosCoches();
            case 3 ->
              buscarPorMarca();
            case 4 ->
              actualizarPrecio();
            case 5 ->
              eliminarCoche();
            case 6 ->
              buscarPorRangoPrecio();
            case 0 -> {
              salir = true;
            }
            default ->
              System.err.println("La opción introducida no es correcta");
          }
          // Captura las posibles excepciones
        } catch (Exception ex) {
          System.err.println("ERROR AL INTRODUCIR LA OPCIÓN: " + ex.getMessage());
          sc.nextLine(); // Limpia el buffer
        }
      }
      // Captura las posibles excepciones
    } catch (Exception ex) {
      System.err.println("ERROR EN LA APLICACIÓN O CONEXIÓN: " + ex.getMessage());
    } finally {
      // Cierra los diferentes recursos
      if (sc != null) {
        sc.close();
      }
      if (mongoClient != null) {
        mongoClient.close();
        System.out.println("\nSe ha cerrado la conexión con MongoDB correctamente");
      }
    }
  }

  private static void anadirNuevoCoche() {
    System.out.println("\n--- AÑADIR NUEVO COCHE ---");

    // Pide que se inserten los diferentes datos
    try {
      System.out.print("Marca: ");
      String marca = sc.nextLine();

      System.out.print("Modelo: ");
      String modelo = sc.nextLine();

      System.out.print("Año de fabricación: ");
      int anio = sc.nextInt();
      sc.nextLine(); // Limpia el buffer

      System.out.print("Kilómetros: ");
      int kilometros = sc.nextInt();
      sc.nextLine(); // Limpia el buffer

      System.out.print("Precio: ");
      double precio = sc.nextDouble();
      sc.nextLine(); // Limpia el buffer

      System.out.print("Color: ");
      String color = sc.nextLine();

      System.out.print("Vendido (S/N): ");
      String vendidoONo = sc.nextLine().toLowerCase(); // Almacena la respuesta en minúsculas
      boolean vendido = vendidoONo.equalsIgnoreCase("s"); // Si el valor introducido es igual a s, lo pone true, sino, false

      // Crea un documento BSON con los datos insertados
      Document coche = new Document()
              .append("marca", marca)
              .append("modelo", modelo)
              .append("anio", anio)
              .append("kilometros", kilometros)
              .append("precio", precio)
              .append("color", color)
              .append("vendido", vendido);

      // Añade el nuevo documento a la coleccion seleccionada
      coleccion.insertOne(coche);

      // Obtiene el ID del nuevo vehículo insertado
      ObjectId id = coche.getObjectId("_id");
      System.out.println("\nSe ha insertado correctamente el coche");
      System.out.println("ID del coche: " + id.toHexString());
      // Captura las posibles excepciones
    } catch (Exception e) {
      System.err.println("ERROR AL INSERTAR EL COCHE: " + e.getMessage());
      sc.nextLine(); // Limpia el buffer
    }
  }

  private static void listarTodosLosCoches() {
    System.out.println("\n--- LISTAR TODOS LOS COCHES ---");

    try {
      // Recupera todos los documentos de la colección
      FindIterable<Document> resultados = coleccion.find();

      // Declaración del contador
      int contador = 0;

      // Itinera cada resultado obtenido de la colección y le suma 1 al contador
      for (Document coche : resultados) {
        contador++;
        // Muestra un mensaje con los datos del coche
        mostrarDatosCoche(coche);
      }

      // En caso de que la colección sea vacía
      if (contador == 0) {
        System.out.println("No hay coches registrados en la base de datos");
      } else {
        // Comunica el número total de coches
        System.out.println("\nNúmero Total de Coches: " + contador);
      }
      // Captura las posibles excepciones
    } catch (Exception ex) {
      System.err.println("ERROR AL INTENTAR LISTAR LOS COCHES: " + ex.getMessage());
    }
  }

  private static void buscarPorMarca() {
    System.out.println("\n--- BUSCAR COCHE POR MARCA ---");

    try {
      // Le pide al usuario que introduzca la marca
      System.out.print("Introduce la marca: ");
      String marca = sc.nextLine();

      // Busca con Filters.regex, que es insensible a mayúsculas
      FindIterable<Document> resultados = coleccion.find(
              Filters.regex("marca", marca, "i") // Busca por la marca
      );

      // Declaración del contador
      int contador = 0;

      // Itinera cada resultado obtenido de la colección y le suma 1 al contador
      for (Document coche : resultados) {
        contador++;
        // Muestra un mensaje con los datos del coche
        mostrarDatosCoche(coche);
      }

      // En caso de que la colección este vacía
      if (contador == 0) {
        System.out.println("No hay coches registrados con la marca " + marca + " en la base de datos");
      } else {
        // Comunica el número total de coches por la marca
        System.out.println("\nNúmero Total de Coches de la Marca " + marca + ": " + contador);
      }
      // Captura las posibles excepciones
    } catch (Exception ex) {
      System.out.println("ERROR AL INTENTAR BUSCAR POR LA MARCA: " + ex.getMessage());
    }
  }

  private static void actualizarPrecio() {
    System.out.println("\n--- ACTUALIZAR PRECIO DE UN COCHE ---");

    try {
      // Le pide al usuario que introduzca el ID del coche
      System.out.print("Introduce el ID del coche: ");
      String idString = sc.nextLine();

      // Comprueba que el ID sea válido y muestra el coche
      Document coche = buscarCochePorId(idString);

      // Si el coche es null, sale del método
      if (coche == null) {
        return;
      }

      // Asigna el ID del coche
      ObjectId id = coche.getObjectId("_id");

      // Le pide al usuario que introduzca el nuevo precio del coche
      System.out.print("\nIntroduce el nuevo precio: ");
      double nuevoPrecio = sc.nextDouble();
      sc.nextLine(); // Limpia el buffer

      // Actualiza los datos del documento del coche, con el nuevo precio
      coleccion.updateOne(
              Filters.eq("_id", id), // Filtra por la id que le pasa el usuario
              Updates.set("precio", nuevoPrecio) // Le añade el nuevo precio al documento del coche
      );

      // Comunica que se ha realizado el cambio correctamente
      System.out.println("Se ha actualizado correctamente el precio del coche " + id + " a " + nuevoPrecio + "€");
      // Captura las posibles excepciones
    } catch (Exception ex) {
      System.out.println("ERROR AL INTENTAR ACTUALIZAR EL PRECIO: " + ex.getMessage());
      sc.nextLine(); // Limpia el buffer
    }
  }

  private static void eliminarCoche() {
    System.out.println("\n--- ELIMINAR COCHE ---");

    try {
      // Le pide al usuario que introduzca el ID del coche
      System.out.print("Introduce el ID del coche: ");
      String idString = sc.nextLine();

      // Comprueba que el ID sea válido y muestra el coche
      Document coche = buscarCochePorId(idString);

      // Si el coche es null, sale del método
      if (coche == null) {
        return;
      }

      // Asigna el ID del coche
      ObjectId id = coche.getObjectId("_id");

      // Pregunta al usuario si está seguro de eliminar el coche mostrado
      System.out.print("\n¿Seguro que quieres eliminar el coche? (S/N): ");
      // Recibe la respuesta del usuario, elimina los espacios y lo pasa a minúsculas
      String confirmacion = sc.nextLine().trim().toLowerCase();

      // Comprueba si el usuario quiere eliminar o no el coche
      if (confirmacion.equals("s")) {
        // Elimina el documento con deleteOne, filtrando por el ID
        coleccion.deleteOne(Filters.eq("_id", id));
        System.out.println("El coche se ha eliminado correctamente");
      } else {
        System.out.println("La operación ha sido cancelada");
      }
      // Captura las posibles excepciones
    } catch (Exception ex) {
      System.out.println("ERROR AL INTENTAR ELIMINAR EL COCHE: " + ex.getMessage());
    }
  }

  private static void buscarPorRangoPrecio() {
    System.out.println("\n--- BUSCAR POR RANGO DE PRECIO ---");

    try {
      // Le pide al usuario que introduzca un precio mínimo y máximo
      System.out.print("Introduce el precio mínimo: ");
      double precioMin = sc.nextDouble();
      sc.nextLine(); // Limpia el buffer
      System.out.print("Introduce el precio máximo: ");
      double precioMax = sc.nextDouble();
      sc.nextLine(); // Limpia el buffer

      // Busca con Filters.and entre dos precios: Mínimo (Filters.gte) y Máximo (Filters.lte)
      FindIterable<Document> resultados = coleccion.find(
              Filters.and(
                      Filters.gte("precio", precioMin),
                      Filters.lte("precio", precioMax)
              )
      );

      // Declaración del contador
      int contador = 0;

      // Itinera cada resultado obtenido de la colección y le suma 1 al contador
      for (Document coche : resultados) {
        contador++;
        mostrarDatosCoche(coche);
      }

      // En caso de que la colección este vacía
      if (contador == 0) {
        System.out.println("No se han encontrado coches en el rango de precio [" + precioMin + " - " + precioMax + "]");
      } else {
        // Comunica el número total de coches que se han encontrado en el rango de precio
        System.out.println("\nNúmero Total de Coches en el Rango de Precio [" + precioMin + " - " + precioMax + "]: " + contador);
      }
      // Captura las posibles excepciones
    } catch (Exception ex) {
      System.out.println("ERROR AL INTENTAR BUSCAR POR EL RANGO DE PRECIO: " + ex.getMessage());
      sc.nextLine(); // Limpia el buffer
    }
  }

  // Método auxiliar para evitar la duplicación a la hora de mostrar la información
  private static void mostrarDatosCoche(Document coche) {
    System.out.println("Coche[ID: " + coche.getObjectId("_id").toHexString()
            + ", Marca: " + coche.getString("marca") + ", Modelo: " + coche.getString("modelo")
            + ", Año: " + coche.getInteger("anio") + ", Kilómetros: " + coche.getInteger("kilometros")
            + " km, Precio: " + coche.getDouble("precio") + " €, Color: " + coche.getString("color")
            + ", Estado: " + (coche.getBoolean("vendido") ? "Vendido" : "Disponible") + "]");
  }

  // Método auxiliar para buscar un coche por ID y validar su formato
  private static Document buscarCochePorId(String idString) {
    // Comprueba que el ID sea válido.
    if (!ObjectId.isValid(idString)) {
      System.out.println("El ID que ha introducido no es válido");
      return null;
    }

    // Declaración del id, pasando el id introducido como parámetro
    ObjectId id = new ObjectId(idString);

    // Busca el coche por la id que se ha introducido
    Document coche = coleccion.find(Filters.eq("_id", id)).first();

    // Comprueba que no sea null, sino, muestra un mensaje
    if (coche == null) {
      System.out.println("No se ha encontrado ningún coche con el ID indicado");
      return null;
    } else {
      mostrarDatosCoche(coche);
    }

    // Devuelve el coche encontrado
    return coche;
  }
}
