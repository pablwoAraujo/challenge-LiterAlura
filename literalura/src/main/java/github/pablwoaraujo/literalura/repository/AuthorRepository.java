package github.pablwoaraujo.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import github.pablwoaraujo.literalura.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByNome(String nome);

	List<Author> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(
			Integer anoNascimento, Integer anoFalecimento);

}
