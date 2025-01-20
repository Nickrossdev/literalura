package com.challenge.literalura.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id") // Esto indica cómo se mapea la relación con la tabla de autor
    private Autor autor;

    private String idioma;
    private Integer descargas;

    public Libro(){
        
    }

    public Libro(DataLibro dataLibro){
        this.titulo = dataLibro.titulo();
        this.idioma = dataLibro.idioma().get(0);
        this.descargas = dataLibro.descargas();
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

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public String obtenerDatosPrimarios(){
        return titulo + " (" + idioma + ")" ;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                TITULO: %s
                AUTOR: %s
                IDIOMA: %s
                DESCARGAS: %s
                """,id,titulo,autor.obtenerDatosPrimarios(),idioma,descargas);
    }

}
