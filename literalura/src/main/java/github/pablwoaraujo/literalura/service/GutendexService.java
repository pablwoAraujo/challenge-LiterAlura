package github.pablwoaraujo.literalura.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
//import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.pablwoaraujo.literalura.model.BookResponseDTO;

@Service
public class GutendexService {
	private final String baseUrl = "https://gutendex.com/books/";
    private final ObjectMapper objectMapper = new ObjectMapper();

	public void search(String text) {
		String json = ApiConsumer
				.getData(baseUrl + "?search=" + URLEncoder.encode(text.trim().toLowerCase(), StandardCharsets.UTF_8));

		System.out.println(json);
		try {
			var teste = objectMapper.readValue(json, BookResponseDTO.class);
			System.out.println(teste);

			System.out.println("Quantidade de livros encontrados: " + teste.quantidade());
			for (var livro : teste.livros()) {
			    System.out.println("Título: " + livro.titulo());
			    System.out.println("Autores:");
			    for (var autor : livro.autores()) {
			        System.out.println(" - " + autor.nome() + " (Nascido em " + autor.anoNascimento() + ", falecido em " + autor.anoMorte() + ")");
			    }
			    System.out.println("Idiomas: " + livro.idiomas());
			    System.out.println("Downloads: " + livro.downloads());
			    System.out.println("--------------------------------");
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return new DataConverter().convert(json, BookApiResponse.class).livros();
	}
}
