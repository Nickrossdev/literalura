# Literalura - Biblioteca de Libros

**Literalura** es una aplicación que permite gestionar una biblioteca de libros. Utiliza la API de Gutendex para buscar libros y luego los guarda en una base de datos PostgreSQL. La aplicación ofrece varias funcionalidades para añadir libros, mostrar información de libros y autores, y filtrar libros según idioma o autores vivos en un año determinado.

## Funcionalidades

1. **Añadir un nuevo libro a su biblioteca:**
   Permite buscar libros en la API de Gutendex mediante el nombre del libro y guardar la información en la base de datos.

2. **Mostrar libros guardados:**
   Muestra todos los libros registrados en la base de datos.

3. **Mostrar autores guardados:**
   Muestra todos los autores registrados en la base de datos.

4. **Mostrar libros por idioma:**
   Permite filtrar los libros por idioma (español, inglés, francés, etc.) y mostrar los resultados.

5. **Mostrar autores vivos en determinado año:**
   Muestra los autores que estaban vivos en un año específico.

## Requisitos

- **Java 17 o superior**
- **Spring Boot** para la implementación del backend
- **PostgreSQL** para almacenar los datos
- **API de Gutendex** para la búsqueda de libros

##Uso
La aplicación te presenta un menú con las siguientes opciones:

- **Añadir un nuevo libro a la biblioteca: Ingresa el nombre de un libro para buscarlo en la API de Gutendex y guardarlo en la base de datos.
- **Mostrar libros guardados: Visualiza todos los libros registrados en la base de datos.
- **Mostrar autores guardados: Visualiza todos los autores registrados.
- **Mostrar libros por idioma: Filtra y muestra los libros por idioma.
- **Mostrar autores vivos en determinado año: Consulta los autores vivos en un año específico.

##Tecnologías
- **Spring Boot: Framework para la creación de aplicaciones Java.
- **PostgreSQL: Base de datos relacional para almacenar los libros y autores.
- **Gutendex API: API para obtener información sobre libros de dominio público
