package OnlineLibrarySystem;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    //Construtor
    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true; //Livro é disponivel por padrão a ser criado
    }

    //Getters
    public String getTitulo() {

        return titulo;
    }

    public String getAutor(){

        return autor;
    }

    public String getIsbn(){

        return isbn;
    }

    public boolean isDisponivel(){

        return disponivel;
    }

    //Setter para o status de disponibilidade
    public void setDisponivel(boolean disponivel) {

        this.disponivel = disponivel;
    }

    //Método para exibir detalhes do livro
    public void exibirDetalhes() {
        System.out.println("\n--- Detalhes do Livro ---");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Disponível: " +  (disponivel ? "Sim" : "Não"));
    }
}
