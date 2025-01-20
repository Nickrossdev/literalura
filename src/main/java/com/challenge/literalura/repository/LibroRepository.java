package com.challenge.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.literalura.modelos.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByIdioma(String idioma);

    Libro findByTitulo(String titulo);
}
