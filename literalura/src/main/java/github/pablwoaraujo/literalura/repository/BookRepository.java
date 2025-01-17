package github.pablwoaraujo.literalura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import github.pablwoaraujo.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByTitulo(String titulo);

}
