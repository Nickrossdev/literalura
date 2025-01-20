package com.challenge.literalura.modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataLibro(

        @JsonProperty("title") String titulo,

        @JsonProperty("authors") List<DataAutor> autores,

        @JsonProperty("languages") List<String> idioma,

        @JsonProperty("download_count") Integer descargas) {

}
