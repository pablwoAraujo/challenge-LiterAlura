package github.pablwoaraujo.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import github.pablwoaraujo.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByTitulo(String titulo);

	@Query("SELECT DISTINCT b.idioma FROM Book b")
	List<String> findDistinctLanguages();

	List<Book> findByIdioma(String idioma);
}
