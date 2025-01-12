package github.pablwoaraujo.literalura;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
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
}
