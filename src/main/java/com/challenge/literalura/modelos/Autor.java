package com.challenge.literalura.modelos;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer deceso;

    @OneToMany(mappedBy = "autor")
    List<Libro> libros;

    public Autor() {

    }

    public Autor(DataAutor dataAutor) {
        this.nombre = dataAutor.nombre();
        this.nacimiento = dataAutor.nacimiento();
        this.deceso = dataAutor.deceso();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getDeceso() {
        return deceso;
    }

    public void setDeceso(Integer deceso) {
        this.deceso = deceso;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String obtenerDatosPrimarios() {
        return nombre + "(" + nacimiento + " - " + deceso + ")";
    }

    @Override
    public String toString() {

            String detallesLibros = libros.isEmpty() ? "Sin libros" :
            libros.stream()
                .map(libro -> String.format(" * %s (%s)", libro.getTitulo(), libro.getIdioma()))  // Aquí agregas más propiedades, como el idioma
                .collect(Collectors.joining("\n"));

        return String.format("""
                ID: %s
                NOMBRE: %s
                AÑO DE NACIMIENTO: %s
                AÑO DE DECESO: %s
                LIBROS:
                %s""", id, nombre, nacimiento, deceso, detallesLibros);
    }

}
