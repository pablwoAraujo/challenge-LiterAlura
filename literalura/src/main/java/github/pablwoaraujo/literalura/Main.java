package github.pablwoaraujo.literalura;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import github.pablwoaraujo.literalura.model.Author;
import github.pablwoaraujo.literalura.model.Book;
import github.pablwoaraujo.literalura.model.BookDataResponseDTO;
import github.pablwoaraujo.literalura.model.BookResponseDTO;
import github.pablwoaraujo.literalura.model.PersonResponseDTO;
import github.pablwoaraujo.literalura.service.AuthorService;
import github.pablwoaraujo.literalura.service.BookService;
import github.pablwoaraujo.literalura.service.GutendexService;

@Component
public class Main {
	private Scanner scanner = new Scanner(System.in);

	@Autowired
	private GutendexService gutendexService;

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	public Main() {

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
				searchBookByTitle();
				break;
			case 2:
				System.out.println("2 - Listar livros registrados");
				listRegisteredBooks();
				break;
			case 3:
				System.out.println("3 - Listar autores registrados");
				listRegisteredAuthors();
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

	private void searchBookByTitle() {
		System.out.println("\nBUSCA POR TÍTULO ********************************************");
		System.out.print("Digite o título do livro: ");
		var titulo = scanner.nextLine();

		System.out.println("\nPesquisando...\n");

		BookResponseDTO dto = gutendexService.search(titulo);
		for (BookDataResponseDTO livro : dto.livros()) {
			PersonResponseDTO autorDTO = livro.autores().getFirst();
			Author author = authorService.register(autorDTO);
			Book book = bookService.register(livro, author);

			System.out.println(book);
		}
	}

	private void listRegisteredBooks() {
		System.out.println("\nLISTAGEM DE LIVROS ********************************************");

		List<Book> books = bookService.listRegisteredBooks();

		int count = 0;
		for (Book book : books) {
			count++;
			System.out.println(count + ". " + book.shortDescription());
		}

		System.out.println("\nTotal de livros registrados: " + count);
	}

	private void listRegisteredAuthors() {
		System.out.println("\nLISTAGEM DE AUTORES ********************************************");

		List<Author> authors = authorService.listRegisteredAuthors();

		int count = 0;
		for (Author author : authors) {
			count++;
			System.out.println(count + ". " + author.toString());
		}

		System.out.println("\nTotal de autores registrados: " + count);
	}

}
