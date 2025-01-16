package github.pablwoaraujo.literalura;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import github.pablwoaraujo.literalura.service.GutendexService;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	private Scanner scanner = new Scanner(System.in);
	private GutendexService gutendexService = new GutendexService();

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
		gutendexService.search(titulo);
	}
}
