package OnlineLibrarySystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Biblioteca implements OperacoesBiblioteca {
    private List<Livro> livros;
    private List<Membro> membros;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.membros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    @Override
    public void adicionarLivro(Livro livro) {
        if (livro != null && buscarLivroPorIsbn(livro.getIsbn()).isEmpty()) {
            livros.add(livro);
            System.out.println("Livro \"" + livro.getTitulo() + "\" adicionado à biblioteca.");
        } else {
            System.out.println("Erro: Livro com ISBN " + (livro != null ? livro.getIsbn() : "") + " já existe ou é inválido.");
        }
    }

    @Override
    public boolean removerLivro(String isbn) {
        Optional<Livro> livroParaRemoverOpt = buscarLivroPorIsbn(isbn);
        if (livroParaRemoverOpt.isPresent()) {
            Livro livroParaRemover = livroParaRemoverOpt.get();
            if (livroParaRemover.isDisponivel()) {
                livros.remove(livroParaRemover);
                System.out.println("Livro \"" + livroParaRemover.getTitulo() + "\" removido da biblioteca.");
                return true;
            } else {
                System.out.println("Erro: Livro \"" + livroParaRemover.getTitulo() + "\" não pode ser removido, pois está emprestado.");
                return false;
            }
        } else {
            System.out.println("Erro: Livro com ISBN " + isbn + " não encontrado.");
            return false;
        }
    }

    @Override
    public void adicionarMembro(Membro membro) {
        if (membro != null && buscarMembroPorId(membro.getIdMembro()).isEmpty()) {
            membros.add(membro);
            System.out.println("Membro \"" + membro.getNome() + "\" adicionado à biblioteca.");
        } else {
            System.out.println("Erro: Membro com ID " + (membro != null ? membro.getIdMembro() : "") + " já existe ou é inválido.");
        }
    }

    @Override
    public boolean removerMembro(String idMembro) {
        Optional<Membro> membroParaRemoverOpt = buscarMembroPorId(idMembro);
        if (membroParaRemoverOpt.isPresent()) {
            Membro membroParaRemover = membroParaRemoverOpt.get();
            if (membroParaRemover.getLivrosEmprestados().isEmpty()) {
                membros.remove(membroParaRemover);
                System.out.println("Membro \"" + membroParaRemover.getNome() + "\" removido da biblioteca.");
                return true;
            } else {
                System.out.println("Erro: Membro \"" + membroParaRemover.getNome() + "\" não pode ser removido, pois possui livros emprestados.");
                return false;
            }
        } else {
            System.out.println("Erro: Membro com ID " + idMembro + " não encontrado.");
            return false;
        }
    }

    @Override
    public Emprestimo realizarEmeprestimo(String isbnLivro, String idMmebro) {
        return null;
    }

    public Emprestimo realizarEmprestimo(String isbnLivro, String idMembro) {
        Optional<Livro> livroOpt = buscarLivroPorIsbn(isbnLivro);
        Optional<Membro> membroOpt = buscarMembroPorId(idMembro);

        if (livroOpt.isEmpty()) {
            System.out.println("Erro: Livro com ISBN " + isbnLivro + " não encontrado.");
            return null;
        }
        if (membroOpt.isEmpty()) {
            System.out.println("Erro: Membro com ID " + idMembro + " não encontrado.");
            return null;
        }

        Livro livro = livroOpt.get();
        Membro membro = membroOpt.get();

        if (!livro.isDisponivel()) {
            System.out.println("Erro: Livro \"" + livro.getTitulo() + "\" não está disponível para empréstimo.");
            return null;
        }

        Emprestimo novoEmprestimo = new Emprestimo(livro, membro);
        emprestimos.add(novoEmprestimo);
        livro.setDisponivel(false);
        membro.adicionarLivroEmprestado(livro);
        System.out.println("Empréstimo realizado com sucesso: \"" + livro.getTitulo() + "\" para " + membro.getNome() + ".");
        return novoEmprestimo;
    }

    @Override
    public boolean realizarDevolucao(String isbnLivro, String idMembro) {
        Optional<Livro> livroOpt = buscarLivroPorIsbn(isbnLivro);
        Optional<Membro> membroOpt = buscarMembroPorId(idMembro);

        if (livroOpt.isEmpty() || membroOpt.isEmpty()) {
            System.out.println("Erro: Livro ou Membro não encontrados para devolução.");
            return false;
        }

        Livro livro = livroOpt.get();
        Membro membro = membroOpt.get();

        Emprestimo emprestimoAtivo = null;
        for (Emprestimo emp : emprestimos) {
            if (emp.getLivro().getIsbn().equals(isbnLivro) && emp.getMembro().getIdMembro().equals(idMembro) && emp.getDataDevolucao() == null) {
                emprestimoAtivo = emp;
                break;
            }
        }

        if (emprestimoAtivo != null) {
            emprestimoAtivo.setDataDevolucao(LocalDate.now());
            livro.setDisponivel(true);
            membro.removerLivroEmprestado(livro);
            System.out.println("Devolução realizada com sucesso: \"" + livro.getTitulo() + "\" de " + membro.getNome() + ".");
            return true;
        } else {
            System.out.println("Erro: Empréstimo ativo para o livro \"" + livro.getTitulo() + "\" e membro " + membro.getNome() + " não encontrado.");
            return false;
        }
    }

    @Override
    public void listarLivrosDisponiveis() {
        System.out.println("\n--- Livros Disponíveis na Biblioteca ---");
        boolean encontrou = false;
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                livro.exibirDetalhes();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro disponível no momento.");
        }
    }

    @Override
    public void listarMmebros() {

    }

    public void listarMembros() {
        System.out.println("\n--- Membros da Biblioteca ---");
        if (membros.isEmpty()) {
            System.out.println("Nenhum membro cadastrado.");
        } else {
            for (Membro membro : membros) {
                membro.exibirDetalhes();
            }
        }
    }

    @Override
    public void listarEmprestimosAtivos() {
        System.out.println("\n--- Empréstimos Ativos ---");
        boolean encontrou = false;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() == null) {
                emprestimo.exibirDetalhes();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        }
    }

    @Override
    public Optional<Livro> buscarLivroPorIsbn(String isbn) {
        return livros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public Membro buscarMmebroPorId(String idMmebro) {
        return null;
    }

    public Optional<Membro> buscarMembroPorId(String idMembro) {
        return membros.stream()
                .filter(m -> m.getIdMembro().equals(idMembro))
                .findFirst();
    }
}

