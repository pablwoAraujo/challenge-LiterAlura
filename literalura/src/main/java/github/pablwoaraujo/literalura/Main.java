package github.pablwoaraujo.literalura;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import github.pablwoaraujo.literalura.model.Author;
import github.pablwoaraujo.literalura.model.Book;
import github.pablwoaraujo.literalura.model.BookDataResponseDTO;
import github.pablwoaraujo.literalura.model.BookResponseDTO;
import github.pablwoaraujo.literalura.model.PersonResponseDTO;
import github.pablwoaraujo.literalura.repository.AuthorRepository;
import github.pablwoaraujo.literalura.repository.BookRepository;
import github.pablwoaraujo.literalura.service.GutendexService;

@Component
public class Main {
	private Scanner scanner = new Scanner(System.in);

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final GutendexService gutendexService;

	// private final String URL = "https://gutendex.com/books";

	public Main(BookRepository bookRepository, AuthorRepository authorRepository, GutendexService gutendexService) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.gutendexService = gutendexService;
	}

	public void menu() {
		var option = -1;

		while (option != 0) {

			var menu = """
					Escolha o número de sua opção:

					1 - Buscar livro pelo título
					2 - Listar livros registrados
					3 - Listar autores registrados
					4 - Listar autores vivos em um determinado ano
					5 - Listar livros em um determinado idioma
					0 - Sair

					""";

			System.out.println(menu);
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {

			case 1:
				System.out.println("1 - Buscar livro pelo título");
				buscarLivropeloTitulo();
				break;
			case 2:
				System.out.println("2 - Listar livros registrados");
				break;
			case 3:
				System.out.println("3 - Listar autores registrados");
				break;
			case 4:
				System.out.println("4 - Listar autores vivos em um determinado ano");
				break;
			case 5:
				System.out.println("5 - Listar livros em um determinado idioma");
				break;
			case 0:
				System.out.println("0 - Sair");
				break;
			default:
				System.out.println("[!] - Opção inválida!");
			}
		}
		scanner.close();
	}

	private void buscarLivropeloTitulo() {
		System.out.println("\nBUSCA POR TÍTULO ********************************************");
		System.out.print("Digite o título do livro: ");
		var titulo = scanner.nextLine();

		System.out.println("\nPesquisando...\n");

		BookResponseDTO dto = gutendexService.search(titulo);
		for (BookDataResponseDTO livro : dto.livros()) {
			PersonResponseDTO autorDTO = livro.autores().getFirst();
			Author author = authorRepository.findByNome(autorDTO.nome())
					.orElseGet(() -> authorRepository.save(Author.convertDTO(autorDTO)));

			Optional<Book> existingBook = bookRepository.findByTitulo(livro.titulo());
			if (existingBook.isEmpty()) {
				Book book = bookRepository.save(Book.convertDTO(livro, author));
				System.out.println(book);
			} else {
				System.out.println("Livro já existe: " + existingBook.get());
			}
		}

	}

}
