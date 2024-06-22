package com.alura_challanges.literatura.model;

import java.util.List;

public class Libro {
    private String titulo;
    private List<DatosAutor> autor;
    private String idioma;
    private Long cantDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor();
        this.idioma = datosLibro.idioma().get(0);
        this.cantDescargas = datosLibro.cantDescargas();
    }

    // Getters y setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getCantDescargas() {
        return cantDescargas;
    }

    public void setCantDescargas(Long cantDescargas) {
        this.cantDescargas = cantDescargas;
    }

    @Override
    public String toString() {
        return "Libro: " +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", cantDescargas=" + cantDescargas;
    }
}
