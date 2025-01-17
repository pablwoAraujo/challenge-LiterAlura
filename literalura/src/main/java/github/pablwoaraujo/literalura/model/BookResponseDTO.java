package github.pablwoaraujo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResponseDTO(@JsonAlias("results") List<BookDataResponseDTO> livros) {
}
