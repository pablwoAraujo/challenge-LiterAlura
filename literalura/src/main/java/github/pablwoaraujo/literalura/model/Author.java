package github.pablwoaraujo.literalura.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private Integer anoNascimento;
	private Integer anoFalecimento;

	@OneToMany(mappedBy = "autor")
	private Set<Book> livros;

	public Author() {
		// TODO Auto-generated constructor stub
	}

	public static Author convertDTO(PersonResponseDTO personResponseDT) {
		Author autor = new Author();
		autor.nome = personResponseDT.nome();
		autor.anoFalecimento = personResponseDT.anoMorte();
		autor.anoNascimento = personResponseDT.anoNascimento();

		return autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(Integer anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public Integer getAnoFalecimento() {
		return anoFalecimento;
	}

	public void setAnoFalecimento(Integer anoFalecimento) {
		this.anoFalecimento = anoFalecimento;
	}

	public Set<Book> getLivros() {
		return livros;
	}

	public void setLivros(Set<Book> livros) {
		this.livros = livros;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.nome);

		if (anoNascimento != null && anoFalecimento != null) {
			sb.append(" (" + anoNascimento + "-" + anoFalecimento + ")");
		} else if (anoNascimento != null) {
			sb.append(" (" + anoNascimento + "-)");
		} else if (anoFalecimento != null) {
			sb.append(" (-" + anoFalecimento + ")");
		}

		return sb.toString();
	}

}
