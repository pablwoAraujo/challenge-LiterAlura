# challenge-LiterAlura

Neste desafio de programação do curso **Praticando Spring Boot: Challenge LiterAlura**, da formação **Java e Spring Framework G7 - ONE**, construímos um catálogo de livros que se conecta à API **Gutendex** para consultar e manipular informações sobre livros e autores. O sistema foi desenvolvido utilizando **Spring Boot** e **PostgreSQL** (em um container Docker).

### Funcionalidades

- **Buscar livro pelo título**: Permite ao usuário pesquisar livros registrados no sistema pelo título. O sistema busca o livro na API Gutendex e registra o livro e seu autor (caso encontrado) no banco de dados. O título e informações sobre o autor são exibidos ao usuário.

- **Listar livros registrados**: Exibe uma lista de todos os livros registrados no sistema. Para cada livro, uma descrição curta é apresentada, e o número total de livros registrados também é mostrado.

- **Listar autores registrados**: Exibe todos os autores registrados no sistema. Para cada autor, o nome e informações são exibidos, junto com o número total de autores registrados.

- **Listar autores vivos em um determinado ano**: Permite ao usuário informar um ano e, com isso, listar os autores que estavam vivos naquele ano específico. O sistema exibe todos os autores encontrados e o total de autores vivos naquele ano.

- **Listar livros em um determinado idioma**: Exibe uma lista de livros registrados que estão em um idioma específico escolhido pelo usuário. O sistema apresenta todos os idiomas disponíveis, e o usuário pode escolher um para ver os livros disponíveis. Caso não haja livros no idioma escolhido, o sistema informa ao usuário. O total de livros registrados no idioma também é mostrado.


Para rodar o PostgreSQL em um container Docker, execute o seguinte comando no seu terminal:
```bash
docker run -p 5432:5432 --name literalura -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -e POSTGRES_DB=literalura -d postgres
```
