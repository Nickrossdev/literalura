package com.challenge.literalura.principal;

import java.util.List;

import com.challenge.literalura.modelos.Autor;
import com.challenge.literalura.modelos.DataLibro;
import com.challenge.literalura.modelos.Libro;
import com.challenge.literalura.repository.AutorRepository;
import com.challenge.literalura.repository.LibroRepository;
import com.challenge.literalura.services.Conexion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    private static AutorRepository autorRepository;
    private static LibroRepository libroRepository;

    public void iniciar(AutorRepository autorRepository, LibroRepository libroRepository) {
        App.autorRepository = autorRepository;
        App.libroRepository = libroRepository;

        mostrarsaludo();
        mostrarMenuPrincipal();
    }

    private void mostrarsaludo() {
        Utilidades.imprimirMensaje("BIENVENIDO A LITERALURA");
    }

    private void mostrarMenuPrincipal() {
        while (true) {
            Utilidades.imprimirMensaje("""
                    1. AÑADIR UN NUEVO LIBRO A SU BIBLIOTECA
                    2. MOSTRAR LIBROS GUARDADOS
                    3. MOSTRAR AUTORES GUARDADOS
                    4. MOSTRAR LIBROS POR IDIOMA
                    5. MOSTRAR AUTORES VIVOS EN DETERMINADO AÑO""");

            Integer opcion = Utilidades.leerEntradaNumerica();
            Boolean esValido = Utilidades.validarEntradaNumerica(opcion);
            if (!esValido)
                return;

            switch (opcion) {
                case 1:
                    guardarNuevoLibro();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarLibrosPorIdioma();
                    break;
                case 5:
                    mostrarAutoresVivosEnDetermianoAnio();
                    break;
                default:
                    break;
            }
        }
    }

    public void guardarNuevoLibro() {
        Utilidades.imprimirMensaje("Inserte nombre del libro");
        String nombreLibro = Utilidades.leerEntrada();

        String nombreLibroFormateado = nombreLibro.replace(" ", "%20");
        String url = "https://gutendex.com/books/?search=" + nombreLibroFormateado;

        Conexion conexion = new Conexion();
        String json = conexion.conectar(url);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode nodo = mapper.readTree(json);
            JsonNode libros = nodo.get("results");

            List<DataLibro> librosEncontrados = mapper.readValue(libros.toString(),
                    mapper.getTypeFactory().constructCollectionType(List.class, DataLibro.class));

            Libro libro = new Libro(librosEncontrados.get(0));
            Autor autor = new Autor(librosEncontrados.get(0).autores().get(0));

            Autor autorGuardado = guardarAutor(autor);

            libro.setAutor(autorGuardado);

            Libro libroGuardado = guardarLibro(libro);

            Utilidades.imprimirMensaje("LIBRO GUARDADO CORRECTAMENTE CON EL ID" + libroGuardado.getId());
            Utilidades.imprimirMensaje(libroGuardado.toString());

        } catch (Exception e) {
            Utilidades.imprimirMensaje("ERROR AL OBTENER RESPUESTA DE LA API");;
        }

    }

    private Libro guardarLibro(Libro libro) {

        Libro libroExistente = libroRepository.findByTitulo(libro.getTitulo());
        if (libroExistente != null) {
            Utilidades.imprimirMensaje("EL LIBRO YA ESTA REGISTRADO -> " + libroExistente.getTitulo());
            return libroExistente;
        } else {

            Libro libroGuardado = libroRepository.save(libro);
            if (libroGuardado != null) {
                Utilidades.imprimirMensaje("NUEVO LIBRO REGISTRADO CORRECTAMENTE -> " + libroGuardado.getId());
            } else {
                Utilidades.imprimirMensaje("ERROR AL GUARDAR LIBRO");
            }
            return libroGuardado;
        }
    }

    private Autor guardarAutor(Autor autor) {
        // Verificar si el autor ya existe en la base de datos
        Autor autorExistente = autorRepository.findByNombre(autor.getNombre());

        if (autorExistente != null) {
            // Si el autor ya existe, devuelve el autor existente
            Utilidades.imprimirMensaje("EL AUTOR YA ESTA REGISTRADO ->" + autorExistente.getNombre());
            return autorExistente;
        } else {
            // Si el autor no existe, lo guarda
            Autor autorGuardado = autorRepository.save(autor);
            if (autorGuardado != null) {
                Utilidades.imprimirMensaje("NUEVO AUTOR REGISTRADO CORRECTAMENTE" + autorGuardado.getId());
            } else {
                Utilidades.imprimirMensaje("ERROR AL GUARDAR AUTOR");
            }
            return autorGuardado;
        }
    }

    public void mostrarLibrosRegistrados() {
        List<Libro> librosRegistrados = libroRepository.findAll();
        if (librosRegistrados.isEmpty()) {
            Utilidades.imprimirMensaje("AUN NO HAY LIBROS REGISTRADOS");
        } else {
            Utilidades.imprimirLista(librosRegistrados);
        }
    }

    public void mostrarAutoresRegistrados() {
        List<Autor> autoresRegistrados = autorRepository.findAll();
        if (autoresRegistrados.isEmpty()) {
            Utilidades.imprimirMensaje("AUN NO HAY AUTORES REGISTRADOS");
        } else {
            Utilidades.imprimirLista(autoresRegistrados);
        }
    }

    public void mostrarLibrosPorIdioma() {

        Utilidades.imprimirMensaje("""
                SELECCIONE EL IDIOMA

                1. ESPAÑOL
                2. INGLES
                3. FRANCES""");

        Integer opcion = Utilidades.leerEntradaNumerica();
        Boolean esValido = Utilidades.validarEntradaNumerica(opcion);
        if (!esValido)
            return;

        String idioma;

        switch (opcion) {
            case 1:
                Utilidades.imprimirMensaje(" BUSCANDO LIBROS -> Español (es)");
                idioma = "es";
                break;
            case 2:
                Utilidades.imprimirMensaje(" BUSCANDO LIBROS -> Ingles (en)");
                idioma = "en";
                break;
            case 3:
                Utilidades.imprimirMensaje(" BUSCANDO LIBROS -> Frances (fr)");
                idioma = "fr";
            default:
                Utilidades.imprimirMensaje("""
                        *OPCIÓN NO VALIDA*
                        BUSCANDO POR IDIOMA POR DEFECTO -> Español (es)""");
                idioma = "es";
                break;
        }
        List<Libro> librosObtenidos = libroRepository.findByIdioma(idioma);

        if (librosObtenidos.isEmpty()) {
            Utilidades.imprimirMensaje("AÚN NO TIENE LIBROS EN -> " + idioma);
        } else {
            Utilidades.imprimirLista(librosObtenidos);
        }
    }

    public void mostrarAutoresVivosEnDetermianoAnio() {
        Utilidades.imprimirMensaje("INGRESE AÑO PARA BUSCAR A LOS AUTORES VIVOS EN ESE MOMENTO");
        Integer anio = Utilidades.leerEntradaNumerica();
        Boolean esValido = Utilidades.validarEntradaNumerica(anio);
        if (!esValido)
            return;
        List<Autor> autoresVivos = autorRepository.findVivosEnAnio(anio);

        if (autoresVivos.isEmpty()) {
            Utilidades.imprimirMensaje("AUN NO TIENE AUTORES VIVOS EN EL AÑO -> " + anio);
        } else {
            Utilidades.imprimirLista(autoresVivos);
        }
    }

}
