/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Impotaci�n de las librer�as.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author byAlexLR
 */
// Clase principal.
public class Main {

    // M�todo para obtener aeropuertos de un pa�s espec�fico o de cualquiera.
    public static List<String> obtenerAeropuertos(String pais, String localizacion) {
        // Lista para almacenar los aeropuertos encontrados.
        List<String> aeropuertosEncontrados = new ArrayList<>();

        // Lectura del archivo de localizaci�n de aeropuertos.
        try (BufferedReader br = new BufferedReader(new FileReader(localizacion));) {
            String lineaActual;
            System.out.println("\nBuscando aeropuertos en " + pais + ":");

            // Bucle para leer cada l�nea del archivo.
            while ((lineaActual = br.readLine()) != null) {
                // Divide la l�nea en datos separados por comas.
                String[] datos = lineaActual.split(",");
                // Compreba si la l�nea tiene al menos dos datos (c�digo y pa�s).
                if (datos.length >= 2) {
                    String paisEncontrado = datos[1].trim();
                    if (pais.equalsIgnoreCase(paisEncontrado) || pais.equalsIgnoreCase("cualquiera")) {
                        // A�ade el aeropuerto a la lista si coincide con alg�n criterio.
                        aeropuertosEncontrados.add(lineaActual);
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Error al leer el fichero de aeropuertos: " + ex.getMessage());
        }
        // Retorna la lista.
        return aeropuertosEncontrados;
    }

    // M�todo para obtener rutas entre aeropuertos cargados.
    public static List<String> obtenerRutas(String precios, List<String> aeropuertosCargados, String pais) {
        // Lista para almacenar las rutas encontradas.
        List<String> rutasEncontradas = new ArrayList<>();
        // Extrae los c�digos de los aeropuertos cargados.
        List<String> codigosAeropuertos = aeropuertosCargados.stream()
                .map(linea -> linea.split(",")[0].trim())
                .collect(Collectors.toList());

        // Lectura del archivo de precios de rutas.
        try (BufferedReader br = new BufferedReader(new FileReader(precios))) {
            String lineaActual;
            System.out.println("\nBuscando rutas desde " + pais + ":");

            // Bucle para leer cada l�nea del archivo.
            while ((lineaActual = br.readLine()) != null) {
                // Divide la �nea en datos separados por comas.
                String[] datos = lineaActual.split(",");
                if (datos.length >= 2) {
                    // Obtiene el c�digo de origen y destino.
                    String origen = datos[0].trim();
                    String destino = datos[1].trim();
                    // Comprueba si ambos c�digos est�n en la lista de aeropuertos cargados.
                    if (codigosAeropuertos.contains(origen) && codigosAeropuertos.contains(destino)) {
                        // A�ade la ruta a la lista si ambos c�digos coinciden.
                        rutasEncontradas.add(lineaActual);
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Error al leer el fichero de rutas: " + ex.getMessage());
        }
        // Retorna la lista.
        return rutasEncontradas;
    }

    // M�todo para filtrar rutas por origen y destino.
    public static List<String> filtrarRutas(List<String> rutas, String origen, String destino) {
        // Lista para almacenar las rutas filtradas.
        List<String> rutasFiltradas = new ArrayList<>();
        System.out.println("\nBuscando rutas desde " + origen + " a " + destino + ":");

        // Bucle para revisar cada ruta y aplicar los filtros.
        for (String ruta : rutas) {
            // Divide la l�nea en datos separados por comas.
            String[] datos = ruta.split(",");
            if (datos.length >= 2) {
                // Obtiene el c�digo de origen y destino.
                String origenRuta = datos[0].trim();
                String destinoRuta = datos[1].trim();
                // Comprueba si la ruta coincide con los criterios de origen y destino.
                boolean origenCoincide = origenRuta.equalsIgnoreCase(origen);
                boolean destinoCoincide = destino.equalsIgnoreCase("cualquiera") || destinoRuta.equalsIgnoreCase(destino);

                // A�ade la ruta a la lista si ambos criterios coinciden.
                if (origenCoincide && destinoCoincide) {
                    rutasFiltradas.add(ruta);
                }
            }
        }
        // Retorna la lista.
        return rutasFiltradas;
    }

    // M�todo para guardar rutas en un archivo.
    public static void guardarRutas(String nombreFichero, List<String> rutas, int posicion) {
        // Lista para almacenar las rutas a guardar.
        List<String> rutasParaGuardar = new ArrayList<>();

        // Determina que rutas guardar seg�n la posici�n indicada.
        if (posicion == rutas.size()) {
            rutasParaGuardar = rutas;
        } else if (posicion > 0 && posicion <= rutas.size()) {
            rutasParaGuardar.add(rutas.get(posicion - 1));
        } else {
            System.out.println("La posici�n " + posicion + " no es v�lida.");
            return;
        }

        // Escritura de las rutas en el archivo especificado.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
            // Bucle para escribir cada ruta en el archivo.
            for (String ruta : rutasParaGuardar) {
                // Divide la l�nea en datos separados por comas.
                String[] datos = ruta.split(",");
                // Comprueba si la l�nea tiene al menos cinco datos.
                if (datos.length >= 5) {
                    String origen = datos[0].trim();
                    String destino = datos[1].trim();
                    String coste = datos[2].trim();
                    String duracion = datos[3].trim();
                    String aerolinea = datos[4].trim();

                    bw.write("---");
                    bw.newLine();
                    bw.write("Flight: " + origen + " to " + destino);
                    bw.newLine();
                    bw.write("Carrier: " + aerolinea);
                    bw.newLine();
                    bw.write("Duration: " + duracion + " minutes");
                    bw.newLine();
                    bw.write("Total Cost: " + coste + " euros");
                    bw.newLine();
                    bw.write("---");
                    bw.newLine();
                }
            }
            // Mensaje de confirmaci�n.
            System.out.println("Se han guardado " + rutasParaGuardar.size() + " rutas en " + nombreFichero + ".");

        } catch (IOException ex) {
            System.err.println("Error al guardar el fichero: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Scanner para leer la entrada del usuario.
        Scanner sc = new Scanner(System.in);
        // Variables necesarias.
        String localizacion = "localizacion.txt", precios = "precios.txt", pais = null, origen, destino;
        boolean salir = false;
        int contador = 0, posicion = 0;

        // Variables para almacenar los datos cargados.
        List<String> aeropuertosCargados = null;
        List<String> rutasCargadas = null;
        List<String> rutasFiltradas = null;

        // Bucle principal del men�.
        while (!salir) {
            // Menú de opciones.
            System.out.print("\n--->> MEN� PRINCIPAL <<---\n1. Buscar aeropuertos de un pa�s.\n2. Buscar rutas entre aeropuertos.\n3. Filtrar rutas por origen y destino.\n4. Guardar rutas en un fichero.\n5. Salir.\nSelecciona una opci�n: ");
            int opcion = sc.nextInt(); // Lee la opci�n del usuario.
            // Switch para manejar las opciones del men�.
            switch (opcion) {
                // Opci�n 1: Buscar aeropuertos de un pa�s.
                case 1 -> {
                    // Solicita el pa�s para buscar aeropuertos.
                    System.out.print("\n�Qu� pa�s quieres buscar? (o cualquiera): ");
                    pais = sc.next();
                    aeropuertosCargados = obtenerAeropuertos(pais, localizacion);
                    // Muestra los aeropuertos encontrados.
                    if (aeropuertosCargados.isEmpty()) {
                        System.out.println("No se encontraron aeropuertos.");
                    } else {
                        // Muestra todos los aeropuertos si el pa�s es "cualquiera".
                        if (pais.equalsIgnoreCase("cualquiera")) {
                            for (String aeropuerto : aeropuertosCargados) {
                                String[] datos = aeropuerto.split(",");
                                System.out.println(" � " + datos[0].trim() + " en " + datos[1].trim() + " - (" + datos[2].trim() + ", " + datos[3].trim() + ")");
                            }
                        } else {
                            // Muestra solo los primeros 5 aeropuertos si el pa�s no es "cualquiera".
                            contador = 0;
                            for (String aeropuerto : aeropuertosCargados) {
                                if (contador < 5) {
                                    String[] datos = aeropuerto.split(",");
                                    System.out.println(" � " + datos[0].trim() + " en " + datos[1].trim() + " - (" + datos[2].trim() + ", " + datos[3].trim() + ")");
                                    contador++;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
                // Opci�n 2: Buscar rutas entre aeropuertos.
                case 2 -> {
                    // Comprueba si hay aeropuertos cargados para buscar rutas.
                    if (aeropuertosCargados == null) {
                        System.out.println("\nNo existen aeropuertos cargados actualmente.");
                    } else {
                        // Busca las rutas entre los aeropuertos cargados.
                        rutasCargadas = obtenerRutas(precios, aeropuertosCargados, pais);
                        if (rutasCargadas.isEmpty()) {
                            System.out.println("\nNo se han encontrado rutas entre aeropuertos " + pais);
                        } else {
                            // Muestra las rutas encontradas.
                            contador = 0;
                            for (String rutas : rutasCargadas) {
                                // Muestra solo las primeras 5 rutas.
                                if (contador < 5) {
                                    String[] datos = rutas.split(",");
                                    System.out.println(" �" + datos[0].trim() + " a " + datos[1].trim() + " - Precio: " + datos[2].trim() + "� - Duraci�n: " + datos[3].trim() + " min. - Aerol�nea: " + datos[4].trim());
                                    contador++;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
                // Opci�n 3: Filtrar rutas por origen y destino.
                case 3 -> {
                    // Comprueba si hay rutas cargadas para filtrar.
                    if (rutasCargadas == null) {
                        System.out.println("\nNo existen rutas cargadas actualmente.");
                    } else {
                        // Solicita el origen y destino para filtrarlo.
                        System.out.print("\nIntroduce el aeropuerto de ORIGEN (EJ: LPA): ");
                        origen = sc.next().toUpperCase();
                        System.out.print("Introduce el aeropuerto de DESTINO (EJ: MAD o cualquiera): ");
                        destino = sc.next().toUpperCase();

                        // Filtra las rutas seg�n el origen y destino indicados.
                        rutasFiltradas = filtrarRutas(rutasCargadas, origen, destino);
                        if (rutasFiltradas.isEmpty()) {
                            System.out.println("No se encontraron rutas de " + origen + " a " + destino + ".");
                        } else {
                            // Muestra las rutas filtradas.
                            for (String rutas : rutasFiltradas) {
                                String[] datos = rutas.split(",");
                                System.out.println(" �" + datos[0].trim() + " a " + datos[1].trim() + " - Precio: " + datos[2].trim() + "� - Duraci�n: " + datos[3].trim() + " min. - Aerol�nea: " + datos[4].trim());
                            }
                        }
                    }
                }
                // Opci�n 4: Guardar rutas en un fichero.
                case 4 -> {
                    // Comprueba si hay rutas filtradas para guardarlo.
                    if (rutasFiltradas == null || rutasFiltradas.isEmpty()) {
                        System.out.println("\nNo existen rutas filtradas para guardar.");
                    } else {
                        // Solicita el nombre del fichero y la posici�n a guardar.
                        System.out.print("\nIntroduce el nombre del fichero de salida (EJ: resultado.txt): ");
                        String ficheroSalida = sc.next();
                        System.out.print("Introduce la posici�n a guardar (1-" + rutasFiltradas.size() + ") o " + rutasFiltradas.size() + " para guardarlas todas: ");
                        posicion = sc.nextInt();

                        // Intenta guardar las rutas en el fichero.
                        try {
                            guardarRutas(ficheroSalida, rutasFiltradas, posicion);
                        } catch (NumberFormatException ex) {
                            System.err.println("Debes introducir un n�mero v�lido.");
                        }
                    }
                }
                // Opci�n 5: Salir del programa.
                case 5 -> {
                    System.out.println("\nSaliendo del Programa..\n");
                    salir = true;
                }
                default -> {
                    // En caso de no introducir un n�mero v�lido.
                    System.out.println("\nLa opci�n introducida no es v�lida.");
                }
            }
        }
        sc.close();
    }
}
