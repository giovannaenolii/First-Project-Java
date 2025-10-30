package OnlineLibrarySystem;

import java.util.List;

public interface OperacoesBiblioteca {

    void adicionarLivro(Livro livro);
    boolean removerLivro(String isbn);
    void adicionarMembro(Membro mesmbro);
    boolean removerMembro(String idMembro);
    Emprestimo realizarEmeprestimo(String isbnLivro, String idMmebro);
    boolean realizarDevolucao(String isbnLivro, String idMembro);
    void listarLivrosDisponiveis();
    void listarMmebros();
    void listarEmprestimosAtivos();
    Livro buscarLivroPorIsbn(String isbn);
    Membro buscarMmebroPorId(String idMmebro);
}
