package com.challenge.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challenge.literalura.modelos.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNombre(String nombre);

    @EntityGraph(attributePaths = { "libros" })
    List<Autor> findAll();

    @EntityGraph(attributePaths = { "libros" })
    @Query("SELECT a FROM Autor a WHERE a.deceso >= :fecha AND a.nacimiento <= :fecha")
    List<Autor> findVivosEnAnio(Integer fecha);

}
