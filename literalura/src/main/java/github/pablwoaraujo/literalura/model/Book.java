package github.pablwoaraujo.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	private String idioma;
	private Integer downloads;

	@ManyToOne
	private Author autor;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public static Book convertDTO(BookDataResponseDTO bookDataResponseDTO, Author author) {
		var livro = new Book();
		livro.downloads = bookDataResponseDTO.downloads();
		livro.titulo = bookDataResponseDTO.titulo();

		if (!bookDataResponseDTO.idiomas().isEmpty()) {
			livro.idioma = bookDataResponseDTO.idiomas().get(0);
		}

		livro.autor = author;

		return livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}

	public Author getAutor() {
		return autor;
	}

	public void setAuthor(Author autor) {
		this.autor = autor;
	}

	public String shortDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("Título: ").append(this.titulo);
		sb.append(" - autores: ").append(this.autor.toString());

		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Título: ").append(this.titulo).append("\n");
		sb.append("Autores:\n").append(this.autor.toString()).append("\n");
		sb.append("Idiomas: ").append(idioma).append("\n");
		sb.append("Número de Downloads: ").append(downloads);

		return sb.toString();
	}

}
