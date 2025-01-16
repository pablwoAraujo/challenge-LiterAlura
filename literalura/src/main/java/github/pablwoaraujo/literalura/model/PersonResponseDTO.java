package github.pablwoaraujo.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonResponseDTO(@JsonAlias("birth_year") Integer anoNascimento,
		@JsonAlias("death_year") Integer anoMorte, @JsonAlias("name") String nome) {
}
