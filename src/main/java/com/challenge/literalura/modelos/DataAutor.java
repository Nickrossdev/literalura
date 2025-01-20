package com.challenge.literalura.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAutor(
        @JsonProperty("name") String nombre,

        @JsonProperty("birth_year") Integer nacimiento,

        @JsonProperty("death_year") Integer deceso) {

}
