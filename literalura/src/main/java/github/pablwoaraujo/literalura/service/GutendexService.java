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

	public BookResponseDTO search(String text) {
		String json = ApiConsumer
				.getData(baseUrl + "?search=" + URLEncoder.encode(text.trim().toLowerCase(), StandardCharsets.UTF_8));

		try {
			return objectMapper.readValue(json, BookResponseDTO.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
