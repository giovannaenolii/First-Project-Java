package OnlineLibrarySystem;

import java.util.ArrayList;
import java.util.List;

public class Membro {
    private String nome;
    private String idMembro;
    private List<Livro> livrosEmprestados;

    public Membro(String nome, String idMembro) {
        this.nome = nome;
        this.idMembro = idMembro;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getIdMembro() {
        return idMembro;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void adicionarLivroEmprestado(Livro livro) {
        if (livro != null && !livrosEmprestados.contains(livro)) {
            livrosEmprestados.add(livro);
            System.out.println(livro.getTitulo() + " adicionado aos livros emprestados de " + nome);
        }
    }

    public void removerLivroEmprestado(Livro livro) {
        if (livro != null && livrosEmprestados.remove(livro)) {
            System.out.println(livro.getTitulo() + " removido dos livros emprestados de " + nome);
        } else {
            System.out.println(livro.getTitulo() + " não encontrado nos livros emprestados de " + nome);
        }
    }

    public void exibirDetalhes() {
        System.out.println("\n--- Detalhes do Membro ---");
        System.out.println("Nome: " + nome);
        System.out.println("ID do Membro: " + idMembro);
        System.out.println("Livros Emprestados (" + livrosEmprestados.size() + "):");
        if (livrosEmprestados.isEmpty()) {
            System.out.println("  Nenhum livro emprestado.");
        } else {
            for (Livro livro : livrosEmprestados) {
                System.out.println("  - " + livro.getTitulo() + " (ISBN: " + livro.getIsbn() + ")");
            }
        }
    }
}
