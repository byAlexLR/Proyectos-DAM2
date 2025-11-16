/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.gestion;

// Importa las clases necesarias
import com.dam.modelo.Autor;
import com.dam.modelo.Ejemplar;
import com.dam.modelo.Ejemplar.EstadoEjemplar;
import com.dam.modelo.Libro;
import com.dam.util.HibernateUtil;

// Importa las librerías necesarias
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author byAlexLR
 */
public class GestionBiblioteca {

    // Diferentes métodos CRUD (CREATE, READ, UPDATE, DELETE)
    public void crearAutorConLibros() {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Crea el Autor
            Autor autor = new Autor("Javier", "Marías", "Española", LocalDate.of(1951, 9, 20));

            // Crea el primer libro
            Libro libro1 = new Libro("Corazón tan blanco", "978-84-204-2954-3", LocalDate.of(1992, 3, 1), 368);

            // Añade ejemplares al libro
            Ejemplar ej1 = new Ejemplar("EJ-009-2024", EstadoEjemplar.DISPONIBLE, "Estantería E-10");
            Ejemplar ej2 = new Ejemplar("EJ-010-2024", EstadoEjemplar.DISPONIBLE, "Estantería E-10");
            libro1.addEjemplar(ej1);
            libro1.addEjemplar(ej2);

            // Crea el segundo libro
            Libro libro2 = new Libro("Mañana en la batalla piensa en mí", "978-84-204-2955-0", LocalDate.of(1994, 5, 5), 464);

            // Añade un ejemplar al libro
            Ejemplar ej3 = new Ejemplar("EJ-011-2024", EstadoEjemplar.DISPONIBLE, "Estantería E-11");
            libro2.addEjemplar(ej3);

            // Añade los libros al autor
            autor.addLibro(libro1);
            autor.addLibro(libro2);

            // Guarda el autor
            session.persist(autor);

            // Hace los cambios permanentes y visibles
            tx.commit();
            System.out.println("El autor ha sido creado exitosamente con " + autor.getLibros().size() + " libros.");
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR AL CREAR EL AUTOR: " + ex.getMessage());
        }
    }

    public void agregarEjemplarALibroExistente(String isbn, String codigoEjemplar) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca el libro por el ISBN
            String hql = "FROM Libro WHERE isbn = :isbn";
            Query<Libro> query = session.createQuery(hql, Libro.class);
            query.setParameter("isbn", isbn); // Añade el isbn al parámetro
            Libro libro = query.uniqueResult(); // Obtiene el resultado de la consulta

            // Comprueba que el libro no sea null
            if (libro != null) {
                // Crea y añade un nuevo ejemplar
                Ejemplar nuevoEjemplar = new Ejemplar(codigoEjemplar, EstadoEjemplar.DISPONIBLE, "Almacén"); // Añade el codigo del Ejemplar, junto al estado a la ubicación
                libro.addEjemplar(nuevoEjemplar); // Lo añade al libro

                // Actualiza el libro existente
                session.merge(libro);
                // Hace los cambios permanentes y visibles
                tx.commit();
                System.out.println("El ejemplar se ha añadido al libro: " + libro.getTitulo());
            } else {
                // Si el libro es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado ningún libro con el ISBN: " + isbn);
                tx.rollback();
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR AL AGREGAR EL EJEMPLAR: " + ex.getMessage());
        }
    }

    public void listarTodosLosAutores() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Lista todos los autores
            String hql = "FROM Autor";
            Query<Autor> query = session.createQuery(hql, Autor.class);
            List<Autor> autores = query.list(); // Obtiene el resultado de todos los autores

            // Itinera y muestra todos los autores por pantalla
            for (Autor autor : autores) {
                System.out.println("Autor[ID: " + autor.getIdAutor() + ", Nombre: " + autor.getNombre() + " " + autor.getApellidos() + ", Nacionalidad: " + autor.getNacionalidad() + ", Nº de Libros: " + autor.getLibros().size() + "]");
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            System.err.println("ERROR AL MOSTRAR LOS AUTORES: " + ex.getMessage());
        }
    }

    public void buscarLibroPorId(Integer idLibro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Obtiene el libro que concuerde con la id añadida
            Libro libro = session.get(Libro.class, idLibro);

            // Comprueba que el libro no sea null
            if (libro != null) {
                System.out.println("\n--- INFORMACIÓN DEL LIBRO ---");
                // Muestra la información del libro y autor
                System.out.println("Libro[Título: " + libro.getTitulo() + ", ISBN: " + libro.getIsbn() + ", Fecha de Publicación: " + libro.getFechaPublicacion() + ", Nº de Páginas: " + libro.getNumeroPaginas() + "\n- Autor[ID: " + libro.getAutor().getIdAutor() + ", Nombre: " + libro.getAutor().getNombre() + " " + libro.getAutor().getApellidos() + ", Nacionalidad: " + libro.getAutor().getNacionalidad() + ", Nº de Libros: " + libro.getAutor().getLibros().size() + "]" + "\n- Número de Ejemplares: ");
                // Itinera y muestra todos los ejemplares
                for (Ejemplar e : libro.getEjemplares()) {
                    System.out.println("  - Ejemplar[ID: " + e.getIdEjemplar() + ", Código: " + e.getCodigoEjemplar() + ", Estado: " + e.getEstado() + ", Ubicación: " + e.getUbicacion() + "]");
                }
            } else {
                // Si el libro es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado ningún libro con el ID: " + idLibro);
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            System.err.println("ERROR AL BUSCAR EL LIBRO: " + ex.getMessage());
        }
    }

    public void buscarEjemplaresPorEstado(EstadoEjemplar estado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Busca los ejemplares por estado
            String hql = "FROM Ejemplar e WHERE e.estado = :estado";
            Query<Ejemplar> query = session.createQuery(hql, Ejemplar.class);
            query.setParameter("estado", estado); // Añade el estado al parámetro
            List<Ejemplar> ejemplares = query.list(); // Obtiene el resultado de la consulta

            System.out.println("\n--- EJEMPLARES CON ESTADO: " + estado + " ---");
            // Itinera y muestra todos los ejemplares con el estado seleccionado por pantalla
            for (Ejemplar e : ejemplares) {
                System.out.println("Ejemplar[Código: " + e.getCodigoEjemplar() + ", Estado: " + e.getEstado() + ", Ubicación: " + e.getUbicacion() + ", Título del Libro: " + e.getLibro().getTitulo() + "]");
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            System.err.println("ERROR AL BUSCAR LOS EJEMPLARES: " + ex.getMessage());
        }
    }

    public void obtenerEstadisticasBiblioteca() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Obtiene el total de autores únicos registrados en la BBDD
            Long totalAutores = session.createQuery("SELECT COUNT(a) FROM Autor a", Long.class).uniqueResult();

            // Obtiene el total de libros únicos registrados en la BBDD
            Long totalLibros = session.createQuery("SELECT COUNT(l) FROM Libro l", Long.class).uniqueResult();

            // Obtiene el total de ejemplares únicos registrados en la BBDD
            Long totalEjemplares = session.createQuery("SELECT COUNT(e) FROM Ejemplar e", Long.class).uniqueResult();

            // Muestra el total de cada consulta por pantalla
            System.out.println("Totales[Autores: " + totalAutores + " - Libros: " + totalLibros + " - Ejemplares: " + totalEjemplares + "]");
            // Muestra el total de ejemplares por estado
            System.out.println("\nEjemplares por estado:");
            for (EstadoEjemplar estado : EstadoEjemplar.values()) { // Itinera cada estado
                String hql = "SELECT COUNT(e) FROM Ejemplar e WHERE e.estado = :estado"; // Consulta
                Long count = session.createQuery(hql, Long.class).setParameter("estado", estado).uniqueResult(); // Añade el estado al parámetro e indica que deben ser valores únicos
                System.out.println(" - " + estado + ": " + count); // Muestra el resultado
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            System.err.println("ERROR AL OBTENER LAS ESTADÍSTICAS: " + ex.getMessage());
        }
    }

    public void actualizarEstadoEjemplar(Integer idEjemplar, EstadoEjemplar nuevoEstado) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca el ejemplar por el ID
            Ejemplar ejemplar = session.get(Ejemplar.class, idEjemplar);

            // Comprueba que el ejemplar no sea null
            if (ejemplar != null) {
                // Añade a una variable el estado actual a la hora de la consulta
                EstadoEjemplar estadoAnterior = ejemplar.getEstado();
                ejemplar.setEstado(nuevoEstado); // Le asigna al ejemplar el nuevo estado

                // Si el nuevo estado es PRESTADO cambia la ubicación
                if (nuevoEstado == EstadoEjemplar.PRESTADO) {
                    ejemplar.setUbicacion("En préstamo");
                }

                // Actualiza el ejemplar existente
                session.merge(ejemplar);
                // Hace los cambios permanentes y visibles
                tx.commit();

                // Muestra una confirmación del cambio de estado
                System.out.println("El estado  del ejemplar " + idEjemplar + " ha sido actualizado de " + estadoAnterior + " a " + nuevoEstado);
            } else {
                // Si el ejemplar es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado el ejemplar con el ID: " + idEjemplar);
                tx.rollback();
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR AL ACTUALIZAR EL ESTADO: " + ex.getMessage());
        }
    }

    public void actualizarDatosLibro(Integer idLibro, String nuevoTitulo, Integer nuevasPaginas) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca el libro por el ID
            Libro libro = session.get(Libro.class, idLibro);

            // Comprueba que el libro no sea null
            if (libro != null) {
                // Muestra un mensaje con los datos anteriores
                System.out.println("- Datos Anteriores[Título: " + libro.getTitulo() + " - Nº de Páginas: " + libro.getNumeroPaginas() + "]");

                // Asigna el nuevo título y número de páginas al libro
                libro.setTitulo(nuevoTitulo);
                libro.setNumeroPaginas(nuevasPaginas);

                // Actualiza el libro
                session.merge(libro);
                // Hace los cambios permanentes y visibles
                tx.commit();

                // Muestra un mensaje con los nuevos datos
                System.out.println("- Datos Actualizados[Título: " + nuevoTitulo + " - Nº de Páginas: " + nuevasPaginas + "]");
            } else {
                // Si el libro es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado el libro con el ID: " + idLibro);
                tx.rollback();
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR AL ACTUALIZAR EL LIBRO: " + ex.getMessage());
        }
    }

    public void transferirLibrosEntreAutores(Integer idAutorOrigen, Integer idAutorDestino) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca el autor de Origen y de Destino por el ID
            Autor autorOrigen = session.get(Autor.class, idAutorOrigen);
            Autor autorDestino = session.get(Autor.class, idAutorDestino);

            // Comprueba que los autores no sean null
            if (autorOrigen != null && autorDestino != null) {
                // Copia los libros a transferir
                List<Libro> librosATransferir = new ArrayList<>(autorOrigen.getLibros());
                int cantidadLibros = librosATransferir.size(); // Obtiene la cantidad de libros

                // Itinera cada libro, cambiando el autor de libro
                for (Libro libro : librosATransferir) {
                    libro.setAutor(autorDestino);
                    session.merge(libro); // Actualiza el libro
                }

                // Hace los cambios permanentes y visibles
                tx.commit();

                // Muestra la cantidad de libros transferidos de un autor a otro
                System.out.println("Se han transferido " + cantidadLibros + " libros del autor " + autorOrigen.getNombre() + " " + autorOrigen.getApellidos() + " al autor " + autorDestino.getNombre() + " " + autorDestino.getApellidos());
            } else {
                // Si el autor es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado a uno o ambos autores.");
                tx.rollback();
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR AL TRANSFERIR LOS LIBROS: " + ex.getMessage());
        }
    }

    public void eliminarEjemplar(Integer idEjemplar) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca el ejemplar por el ID
            Ejemplar ejemplar = session.get(Ejemplar.class, idEjemplar);

            // Comprueba que el ejemplar no sea null
            if (ejemplar != null) {
                // Obtiene el código de ejemplar y la información del libro
                String codigo = ejemplar.getCodigoEjemplar();
                Libro libro = ejemplar.getLibro();

                // Mantiene la consistencia bidireccional, utilizando el método de la clase libro
                libro.removeEjemplar(ejemplar);

                // Elimina el ejemplar existente
                session.remove(ejemplar);
                // Hace los cambios permanentes y visibles
                tx.commit();

                // Muestra un mensaje de confirmación del borrado
                System.out.println("El ejemplar ha sido eliminado: " + codigo);
            } else {
                // Si el ejemplar es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado el ejemplar con el ID: " + idEjemplar);
                tx.rollback();
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR AL ELIMINAR EL EJEMPLAR: " + ex.getMessage());
        }
    }

    public void eliminarLibro(Integer idLibro) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca el libro por el ID
            Libro libro = session.get(Libro.class, idLibro);

            // Comprueba que el libro no sea null
            if (libro != null) {
                // Obtiene el título del libro, número de ejemplares y el autor
                String titulo = libro.getTitulo();
                int numEjemplares = libro.getEjemplares().size();
                Autor autor = libro.getAutor();

                // Mantiene la consistencia bidireccional, utilizando el método de la clase autor
                autor.removeLibro(libro);

                // Elimina el libro existente
                session.remove(libro);
                // Hace los cambios permanentes y visibles
                tx.commit();

                System.out.println("El libro con el título '" + titulo + "' ha sido eliminado, junto a los " + numEjemplares + " ejemplares.");
            } else {
                // Si el libro es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado el libro con el ID: " + idLibro);
                tx.rollback();
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar libro: " + ex.getMessage());
        }
    }

    public void eliminarAutorConCascada(Integer idAutor) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca el autor por el ID
            Autor autor = session.get(Autor.class, idAutor);

            // Comprueba que el autor no sea null
            if (autor != null) {
                // Obtiene el nombre y apellidos junto al número de libros del autor
                String nombreCompleto = autor.getNombre() + " " + autor.getApellidos();
                int numLibros = autor.getLibros().size();

                // Cuenta el número de ejemplares
                int numEjemplares = 0;
                for (Libro libro : autor.getLibros()) {
                    numEjemplares += libro.getEjemplares().size();
                }

                // Elimina el autor existente
                session.remove(autor);
                // Hace los cambios permanentes y visibles
                tx.commit();

                // Muestra un mensaje de confirmación del borrado
                System.out.println("Se ha eliminado el autor " + nombreCompleto + " con " + numLibros + " libros y " + numEjemplares + " ejemplares.");
            } else {
                // Si el autor es null, muestra un mensaje de error y hace rollback
                System.out.println("No se ha encontrado el autor con ID: " + idAutor);
                tx.rollback();
            }
            // Captura las posibles excepciones
        } catch (Exception ex) {
            // En caso de que la transacción sea diferente a null, realiza un rollback
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR AL ELIMINAR EL AUTOR: " + ex.getMessage());
        }
    }
}
