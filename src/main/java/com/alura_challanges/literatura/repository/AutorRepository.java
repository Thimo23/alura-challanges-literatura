package com.alura_challanges.literatura.repository;

import com.alura_challanges.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository  extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento <= :anio AND a.anoFallecimiento > :anio")
    List<Autor> autoresVivosEnDeterminadoAño(int anio);

}
