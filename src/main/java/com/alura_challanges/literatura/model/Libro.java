package com.alura_challanges.literatura.model;

import com.alura_challanges.literatura.repository.AutorRepository;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;
    private String idioma;
    private Long cantDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro, AutorRepository autorRepository) {
        this.titulo = datosLibro.titulo();

        if (!datosLibro.autor().isEmpty()) {
            String nombreAutor = datosLibro.autor().get(0).nombre();


        Optional<Autor> autorExistente = autorRepository.findByNombre(nombreAutor);
        if (autorExistente.isPresent()) {
            this.autor = autorExistente.get();
        }
        else {
            if (!datosLibro.autor().isEmpty()) {   //Algunos libros de la api no tienen ningun autor en sus campos
                Autor nuevoAutor = new Autor(nombreAutor,
                        datosLibro.autor().get(0).anoNacimiento(),
                        datosLibro.autor().get(0).anoFallecimiento());
                autorRepository.save(nuevoAutor);
                this.autor = nuevoAutor;
            } else {

                this.autor = new Autor("Desconocido", 0, 0);
            }

        }
    }
        this.idioma = datosLibro.idioma().get(0);
        this.cantDescargas = datosLibro.cantDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {

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
        return "\n-------Libro------\n" +
                "Titulo: " + titulo +"\n"+
                "Autor: " + autor.getNombre() +"\n" +
                "Idioma: " + idioma + "\n" +
                "Numero de descargas: " + cantDescargas+ "\n" +
                "-----------------";
    }
}
