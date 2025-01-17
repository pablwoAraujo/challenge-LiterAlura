package github.pablwoaraujo.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import github.pablwoaraujo.literalura.repository.AuthorRepository;
import github.pablwoaraujo.literalura.repository.BookRepository;
import github.pablwoaraujo.literalura.service.GutendexService;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookRepository, authorRepository, gutendexService);
		main.menu();
	}

}
