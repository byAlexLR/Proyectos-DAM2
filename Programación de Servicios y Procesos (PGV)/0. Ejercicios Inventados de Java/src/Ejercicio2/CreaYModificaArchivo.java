/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

// Importación de las librerías.
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author byAlexLR
 */
// Clase principal que extiende JFrame para crear la interfaz gráfica.
public class CreaYModificaArchivo extends JFrame {
    // Declaración de los botones y el selector de archivos.

    private final JButton crearBtn, modificarBtn;
    private final JFileChooser archivoChooser;

    // Constructor.
    public CreaYModificaArchivo() {
        // Configuración de la ventana principal.
        super("Gestor de Archivos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 180); // Tamaño adecuado para los botones.
        setLocationRelativeTo(null);

        // Título de la ventana.
        JLabel titulo = new JLabel("Gestor de Archivos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Inicialización de los componentes.
        crearBtn = new JButton("Crear");
        modificarBtn = new JButton("Modificar");
        archivoChooser = new JFileChooser();

        // Asignación de acciones a los botones.
        crearBtn.addActionListener(evt -> crearArchivo());
        modificarBtn.addActionListener(evt -> modificarArchivo());

        // Configuración del panel y distribución de los botones.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 16, 16));
        panel.add(crearBtn);
        panel.add(modificarBtn);

        // Añadir el panel a la ventana principal.
        add(panel, BorderLayout.CENTER);
    }

    // Método para crear un nuevo archivo.
    private void crearArchivo() {
        // Mostrar la ventana para seleccionar la ubicación y nombre del archivo.
        if (archivoChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Declaración del archivo seleccionado.
            File archivo = archivoChooser.getSelectedFile();
            JTextArea areaTexto = new JTextArea(12, 30); // Área de texto para el contenido del archivo.
            // Mostrar un diálogo para ingresar el contenido del archivo.
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    new JScrollPane(areaTexto), // JScrollPane para poder desplazarte.
                    "Escribe el nuevo contenido",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            // Si el usuario presiona OK, se escribe el contenido en el archivo.
            if (opcion == JOptionPane.OK_OPTION) {
                // Escritura del contenido en el archivo.
                try (FileWriter escritor = new FileWriter(archivo)) {
                    escritor.write(areaTexto.getText());
                    JOptionPane.showMessageDialog(this, "¡Archivo creado correctamente!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    // Método para modificar un archivo existente.
    private void modificarArchivo() {
        // Mostrar la ventana para seleccionar el archivo a modificar.
        if (archivoChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Declaración del archivo seleccionado.
            File archivo = archivoChooser.getSelectedFile();
            StringBuilder contenidoActual = new StringBuilder(); // StringBuilder para almacenar el contenido actual del archivo.
            // Lectura del contenido actual del archivo.
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    contenidoActual.append(linea).append("\n"); // Añadir cada línea al StringBuilder.
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer: " + ex.getMessage());
                return;
            }
            // Área de texto para mostrar y editar el contenido actual del archivo.
            JTextArea areaTexto = new JTextArea(contenidoActual.toString(), 12, 30);
            // Mostrar un panel para editar el contenido del archivo.
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    new JScrollPane(areaTexto), // JScrollPane para poder desplazarte.
                    "Edita el contenido del archivo",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            // Si el usuario presiona OK, se escribe el contenido en el archivo.
            if (opcion == JOptionPane.OK_OPTION) {
                // Escritura del contenido en el archivo.
                try (FileWriter escritor = new FileWriter(archivo)) {
                    escritor.write(areaTexto.getText());
                    JOptionPane.showMessageDialog(this, "¡Archivo editado correctamente!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        // Inicializa el Scanner.
        Scanner sc = new Scanner(System.in);
        // Da la bienvenida personalizada por consola.
        System.out.print("¡Bienvenido al gestor de archivos!, podría indicar su nombre: ");
        String nombre = sc.next();
        System.out.println(nombre + ", se está ejecutando la interfaz gráfica.");

        // Hace la ventana visible.
        SwingUtilities.invokeLater(() -> new CreaYModificaArchivo().setVisible(true));
    }
}
