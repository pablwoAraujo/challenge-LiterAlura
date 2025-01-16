package github.pablwoaraujo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResponseDTO(@JsonAlias("count") Integer quantidade, @JsonAlias("previous") String linkPaginaAnterior,
		@JsonAlias("next") String linkPaginaPosterior, @JsonAlias("results") List<BookDataResponseDTO> livros) {
}
