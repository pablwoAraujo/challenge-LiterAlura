package github.pablwoaraujo.literalura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.pablwoaraujo.literalura.model.Author;
import github.pablwoaraujo.literalura.model.PersonResponseDTO;
import github.pablwoaraujo.literalura.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public AuthorService() {

	}

	public Author register(PersonResponseDTO dto) {
		Author author = authorRepository.findByNome(dto.nome())
				.orElseGet(() -> authorRepository.save(Author.convertDTO(dto)));

		return author;

		// Author author = Author.convertDTO(dto);
		// return authorRepository.save(author);
	}

	public List<Author> listRegisteredAuthors() {
		return authorRepository.findAll();
	}

	public List<Author> listLivingAuthorsInAGivenYear(Integer year) {
		return authorRepository
				.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(year, year);
	}

}
