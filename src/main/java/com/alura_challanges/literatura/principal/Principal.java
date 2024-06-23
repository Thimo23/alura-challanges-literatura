package com.alura_challanges.literatura.principal;

import com.alura_challanges.literatura.model.Autor;
import com.alura_challanges.literatura.model.DatosLibro;
import com.alura_challanges.literatura.model.Libro;
import com.alura_challanges.literatura.model.ResultadoBusqueda;
import com.alura_challanges.literatura.repository.AutorRepository;
import com.alura_challanges.literatura.repository.LibroRepository;
import com.alura_challanges.literatura.service.ConsumoAPI;
import com.alura_challanges.literatura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Libro> libros = new ArrayList<>();
    private LibroRepository repositorio;
    private AutorRepository repositorioAutor;

    public Principal(LibroRepository repositorio,AutorRepository repositorioAutor) {
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
    }

    public void mostrarMenu(){
            var opcion = -1;
            while (opcion != 0) {
                var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3-  Listar Autores registrados
                    4-  Listar autores vivos a partir de un determinado año
                    5-  Listar libros por idioma                                     
                    0 - Salir
                    """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        mostrarTodosLosLibrosBuscados();
                        break;
                    case 3:
                        mostrarTodosLosAutoresBuscados();
                        break;
                    case 4:
                        buscarAutorPorAño();
                        break;
                    case 5:
                        buscarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }


    }

    private ResultadoBusqueda getDatosLibro() {
        while (true) {
            System.out.println("Escribe el nombre del libro que deseas buscar");
            String nombreLibro = teclado.nextLine();

            if (!nombreLibro.isEmpty()) {
                String url = URL_BASE + "?search=" + nombreLibro.replace(" ", "%20");
                String json = consumoApi.obtenerDatos(url);

                System.out.println(json);
                ResultadoBusqueda datos = conversor.obtenerDatos(json, ResultadoBusqueda.class);


                if (datos != null && !datos.libros().isEmpty()) {
                    return datos;
                }
                else {
                    System.out.println("No se encontraron libros con ese título. Intenta de nuevo.");
                }

            }
            else {
                System.out.println("Ingrese un título válido.");
            }
        }
    }

    private void buscarLibro() {
        ResultadoBusqueda resultadoBusqueda = getDatosLibro();

        if (resultadoBusqueda != null && !resultadoBusqueda.libros().isEmpty()) {
            DatosLibro datos = resultadoBusqueda.libros().get(0);

            Optional<Libro> libroExistente = repositorio.findByTitulo(datos.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro ya existe en la base de datos: " + libroExistente.get());
            } else {
                Libro nuevoLibro = new Libro(datos, repositorioAutor);
                repositorio.save(nuevoLibro);
                System.out.println("Nuevo libro guardado: " + nuevoLibro);
            }
        } else {
            System.out.println("No se pudo encontrar información válida para un libro.");
        }
    }

    private void mostrarTodosLosLibrosBuscados() {
        libros = repositorio.findAll();
        libros.forEach(System.out::println);
    }

    private void mostrarTodosLosAutoresBuscados() {
        autores = repositorioAutor.findAll();
        autores.forEach(autor -> {
            autor.getLibros().size();
            System.out.println(autor);
        });
    }

    private void buscarAutorPorAño() {
        System.out.println("Ingrese el año que quiere verificar: ");

        while (!teclado.hasNextInt()) {
            System.out.println("Por favor, ingrese un número entero válido: ");
            teclado.next();
        }
        int anio = teclado.nextInt();
        teclado.nextLine();

        autores = repositorioAutor.autoresVivosEnDeterminadoAño(anio);
        if (autores.isEmpty()) {
            System.out.println("No hay registros de autores vivos en ese año");
        } else {
            autores.forEach(System.out::println);
        }
    }


    private void buscarLibrosPorIdioma() {
        System.out.println("Qué idioma desea buscar: \n" +
                "- en\n" +
                "- es\n" +
                "- fr\n" +
                "- pt\n"
        );
        String idiomaElegido = teclado.nextLine().trim().toLowerCase();

        while (!isValidIdioma(idiomaElegido)) {
            System.out.println("Opción inválida. Por favor, ingrese uno de los siguientes idiomas: en, es, fr, pt");
            idiomaElegido = teclado.nextLine().trim().toLowerCase();
        }

        libros = repositorio.findByIdioma(idiomaElegido);
        libros.forEach(System.out::println);
    }
    private boolean isValidIdioma(String idioma) {
        return idioma.equals("en") || idioma.equals("es") || idioma.equals("fr") || idioma.equals("pt");
    }





}



