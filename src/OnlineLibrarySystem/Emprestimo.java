package OnlineLibrarySystem;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Membro membro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Membro membro) {
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public Membro getMembro() {
        return membro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void exibirDetalhes() {
        System.out.println("\n--- Detalhes do Empréstimo ---");
        System.out.println("Livro: " + livro.getTitulo() + " (ISBN: " + livro.getIsbn() + ")");
        System.out.println("Membro: " + membro.getNome() + " (ID: " + membro.getIdMembro() + ")");
        System.out.println("Data de Empréstimo: " + dataEmprestimo);
        System.out.println("Data de Devolução: " + (dataDevolucao != null ? dataDevolucao : "Pendente"));
    }
}
