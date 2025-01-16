package github.pablwoaraujo.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	private String autor;

	public Book() {
		// TODO Auto-generated constructor stub
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", titulo=" + titulo + ", idioma=" + idioma + ", downloads=" + downloads + ", autor="
				+ autor + "]";
	}

}
