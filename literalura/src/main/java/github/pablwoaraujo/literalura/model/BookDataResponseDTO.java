package github.pablwoaraujo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDataResponseDTO(Integer id, @JsonAlias("title") String titulo,
		@JsonAlias("authors") List<PersonResponseDTO> autores, @JsonAlias("languages") List<String> idiomas,
		@JsonAlias("download_count") Integer downloads) {

}