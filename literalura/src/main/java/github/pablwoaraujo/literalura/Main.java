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
		String option = "";

		while (option != "0") {

			String menu = """
					Escolha o número de sua opção:

					1 - Buscar livro pelo título
					2 - Listar livros registrados
					3 - Listar autores registrados
					4 - Listar autores vivos em um determinado ano
					5 - Listar livros em um determinado idioma
					0 - Sair

					""";

			System.out.println(menu);
			option = scanner.nextLine().trim();

			switch (option) {

			case "1":
				System.out.println("1 - Buscar livro pelo título");
				searchBookByTitle();
				break;
			case "2":
				System.out.println("2 - Listar livros registrados");
				listRegisteredBooks();
				break;
			case "3":
				System.out.println("3 - Listar autores registrados");
				listRegisteredAuthors();
				break;
			case "4":
				System.out.println("4 - Listar autores vivos em um determinado ano");
				listLivingAuthorsInAGivenYear();
				break;
			case "5":
				System.out.println("5 - Listar livros em um determinado idioma");
				listBooksInASpecificLanguage();
				break;
			case "0":
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
	        PersonResponseDTO autorDTO = null;
	        if (!livro.autores().isEmpty()) {
	            autorDTO = livro.autores().get(0);
	        }

	        Author author = null;
	        if (autorDTO != null) {
	            author = authorService.register(autorDTO);
	        } 
	        
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

	private void listLivingAuthorsInAGivenYear() {
		System.out.println("\nLISTAR AUTORES VIVOS EM UM DETERMINADO ANO ********************************************");
		System.out.print("Digite o ano desejado: ");
		String year = scanner.nextLine().trim();

		List<Author> authors = authorService.listLivingAuthorsInAGivenYear(Integer.parseInt(year));

		if (authors.isEmpty()) {
			System.out.println("******************************************** Nenhum autor encontrado\n");
		} else {
			int count = 0;
			for (Author author : authors) {
				count++;
				System.out.println(count + ". " + author.toString());
			}
			System.out.println("\nTotal de autores vivos no ano " + year + ": " + count);
		}
	}

	private void listBooksInASpecificLanguage() {
		System.out.println("\nLISTAR LIVROS DE UM DETERMINADO IDIOMA ********************************************");

		List<String> languages = bookService.listRegisteredLanguages();
		if (languages.isEmpty()) {
			System.out.println(
					"******************************************** Nenhum idioma foi registrado, tente pesquisar um livro\n");
			return;
		}

		boolean isValidLanguage = false;
		while (!isValidLanguage) {
			System.out.println("Idiomas disponíveis:");
			for (String language : languages) {
				System.out.println("- " + language);
			}

			System.out.println("\nDigite o idioma desejado (ou 'sair' para cancelar): ");
			String inputLanguage = scanner.nextLine().trim();

			if ("sair".equalsIgnoreCase(inputLanguage)) {
				System.out.println("Você saiu da listagem de livros.");
				break;
			}

			if (!languages.contains(inputLanguage.toLowerCase())) {
				System.out.println("Idioma não encontrado, tente novamente.\n");
				continue;
			}

			List<Book> books = bookService.listBooksByLanguage(inputLanguage.toLowerCase());

			if (books.isEmpty()) {
				System.out.println(
						"******************************************** Nenhum livro encontrado neste idioma, tente novamente\n");
			} else {
				System.out.println("\nLivros encontrados no idioma \"" + inputLanguage + "\":");
				int count = 0;

				for (Book book : books) {
					count++;
					System.out.println(count + ". " + book.shortDescription());
				}

				System.out.println("\nTotal de livros registrados nesse idioma: " + count);
				isValidLanguage = true;
			}
		}
	}

}
