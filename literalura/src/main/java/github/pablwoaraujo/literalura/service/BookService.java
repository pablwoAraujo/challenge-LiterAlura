package github.pablwoaraujo.literalura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.pablwoaraujo.literalura.model.Author;
import github.pablwoaraujo.literalura.model.Book;
import github.pablwoaraujo.literalura.model.BookDataResponseDTO;
import github.pablwoaraujo.literalura.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public BookService() {

	}

	public List<Book> listRegisteredBooks() {
		return bookRepository.findAll();
	}

	public Book register(BookDataResponseDTO dto, Author author) {
		Optional<Book> existingBook = bookRepository.findByTitulo(dto.titulo());

		if (existingBook.isEmpty()) {
			return bookRepository.save(Book.convertDTO(dto, author));
		}
		return existingBook.get();
	}

	public List<String> listRegisteredLanguages() {
		return bookRepository.findDistinctLanguages();
	}

	public List<Book> listBooksByLanguage(String language) {
		return bookRepository.findByIdioma(language);
	}
}
