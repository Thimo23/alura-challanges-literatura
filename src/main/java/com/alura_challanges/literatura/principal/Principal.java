package com.alura_challanges.literatura.principal;

import com.alura_challanges.literatura.model.Autor;
import com.alura_challanges.literatura.model.DatosLibro;
import com.alura_challanges.literatura.model.Libro;
import com.alura_challanges.literatura.service.ConsumoAPI;
import com.alura_challanges.literatura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/3/";

    public void mostrarMenu(){
            var opcion = -1;
            while (opcion != 0) {
                var menu = """
                    1 - Buscar libro por titulo                                      
                    0 - Salir
                    """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }


    }
    private DatosLibro getDatosLibro() {
       // System.out.println("Escribe el nombre del libro que deseas buscar");
      //  var nombreLibro = teclado.nextInt();
        var json = consumoApi.obtenerDatos(URL_BASE);
        System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    private void buscarLibro() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        Autor autor = new Autor(
                libro.getAutor().get(0).nombre(),
                libro.getAutor().get(0).anoNacimiento(),
                libro.getAutor().get(0).anoFallecimiento());

        System.out.println("\n"+libro);
        System.out.println(autor.getNombre());
    }
}
