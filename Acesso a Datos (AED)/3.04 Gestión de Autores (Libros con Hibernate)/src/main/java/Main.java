/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entidades.Autor;
import entidades.Libro;

import java.util.List;

/**
 *
 * @author byAlexLR
 */
public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        // Configura Hibernate.
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            System.out.println("\nIniciando aplicación de Hibernate...");
        } catch (Throwable ex) {
            System.err.println("\nERROR AL CREAR LA SESSIONFACTORY: " + ex + "\n");
            throw new ExceptionInInitializerError(ex);
        }

        // Inserta los datos.
        insertarDatos();

        // Consulta y muestra los datos.
        consultarDatos();

        // Cierra la SessionFactory.
        if (sessionFactory != null) {
            System.out.println("\nCerrando aplicación de Hibernate...\n");
            sessionFactory.close();
        }
    }

    private static void insertarDatos() {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Insercción de autores.
            System.out.println("\nSe están insertando los autores.");
            Autor autorCollins = new Autor();
            autorCollins.setNombre("Suzanne Collins");
            autorCollins.setNacionalidad("Estadounidense");

            Autor autorRoth = new Autor();
            autorRoth.setNombre("Veronica Roth");
            autorRoth.setNacionalidad("Estadounidense");

            session.save(autorCollins);
            session.save(autorRoth);

            // Insercción de libros con referencias de autoría.
            System.out.println("Se están insertando los libros.");
            Libro libro1 = new Libro();
            libro1.setTitulo("Los Juegos del Hambre");
            libro1.setIsbn("978-8427202122");
            libro1.setAnioPublicacion(2008);
            libro1.setAutor(autorCollins);

            Libro libro2 = new Libro();
            libro2.setTitulo("En Llamas");
            libro2.setIsbn("978-8427202139");
            libro2.setAnioPublicacion(2009);
            libro2.setAutor(autorCollins);

            Libro libro3 = new Libro();
            libro3.setTitulo("Sinsajo");
            libro3.setIsbn("978-8427202146");
            libro3.setAnioPublicacion(2010);
            libro3.setAutor(autorCollins);

            Libro libro4 = new Libro();
            libro4.setTitulo("Balada de pájaros cantores y serpientes");
            libro4.setIsbn("978-8427219489");
            libro4.setAnioPublicacion(2020);
            libro4.setAutor(autorCollins);

            Libro libro5 = new Libro();
            libro5.setTitulo("Divergente");
            libro5.setIsbn("978-8427203181");
            libro5.setAnioPublicacion(2011);
            libro5.setAutor(autorRoth);

            Libro libro6 = new Libro();
            libro6.setTitulo("Insurgente");
            libro6.setIsbn("978-8427203198");
            libro6.setAnioPublicacion(2012);
            libro6.setAutor(autorRoth);

            Libro libro7 = new Libro();
            libro7.setTitulo("Leal");
            libro7.setIsbn("978-8427204171");
            libro7.setAnioPublicacion(2013);
            libro7.setAutor(autorRoth);

            Libro libro8 = new Libro();
            libro8.setTitulo("Cuatro (Una historia de Divergente)");
            libro8.setIsbn("978-8427208193");
            libro8.setAnioPublicacion(2014);
            libro8.setAutor(autorRoth);

            session.save(libro1);
            session.save(libro2);
            session.save(libro3);
            session.save(libro4);
            session.save(libro5);
            session.save(libro6);
            session.save(libro7);
            session.save(libro8);

            tx.commit();
            System.out.println("\nLos autores y los libros se han insertado correctamente.");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Realiza un rollback si hay un error.
            }
            System.err.println("\nERROR AL INSERTAR LOS DATOS: " + e.getMessage() + "\n");
        } finally {
            if (session != null) {
                session.close(); // Cierra la sesión.
            }
        }
    }

    private static void consultarDatos() {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Muestra los libros.
            String hql = "FROM Libro";

            Query<Libro> query = session.createQuery(hql, Libro.class);
            List<Libro> libros = query.list();

            System.out.println("\n--- LISTADO DE LIBROS Y AUTORES ---");
            if (libros.isEmpty()) {
                System.out.println("No se han encontrado libros en la base de datos.");
            } else {
                for (Libro libro : libros) {
                    System.out.println(libro.toString());
                }
            }

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Realiza un rollback si hay un error.
            }
            System.err.println("\nERROR AL CONSULTAR LOS DATOS: " + e.getMessage() + "\n");
        } finally {
            if (session != null) {
                session.close(); // Cierra la sesión.
            }
        }
    }
}
