package OnlineLibrarySystem;

public class MainBiblioteca {
    public static void main(String[] args) {
        Biblioteca minhaBiblioteca = new Biblioteca();

        // 1. Adicionar Livros
        System.out.println("\n=== Adicionando Livros ===");
        Livro livro1 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", "978-85-7827-069-3");
        Livro livro2 = new Livro("1984", "George Orwell", "978-85-221-0611-1");
        Livro livro3 = new Livro("Dom Casmurro", "Machado de Assis", "978-85-08-11677-3");

        minhaBiblioteca.adicionarLivro(livro1);
        minhaBiblioteca.adicionarLivro(livro2);
        minhaBiblioteca.adicionarLivro(livro3);
        minhaBiblioteca.adicionarLivro(new Livro("1984", "George Orwell", "978-85-221-0611-1")); // Tentando adicionar duplicado

        // 2. Adicionar Membros
        System.out.println("\n=== Adicionando Membros ===");
        Membro membro1 = new Membro("Giovanna Oliveira", "M001");
        Membro membro2 = new Membro("Cauê Bueno", "M002");

        minhaBiblioteca.adicionarMembro(membro1);
        minhaBiblioteca.adicionarMembro(membro2);
        minhaBiblioteca.adicionarMembro(new Membro("Ana Silva", "M001")); // Tentando adicionar duplicado

        // 3. Listar Livros Disponíveis e Membros
        minhaBiblioteca.listarLivrosDisponiveis();
        minhaBiblioteca.listarMembros();

        // 4. Realizar Empréstimos
        System.out.println("\n=== Realizando Empréstimos ===");
        minhaBiblioteca.realizarEmprestimo(livro1.getIsbn(), membro1.getIdMembro());
        minhaBiblioteca.realizarEmprestimo(livro2.getIsbn(), membro2.getIdMembro());
        minhaBiblioteca.realizarEmprestimo(livro1.getIsbn(), membro1.getIdMembro()); // Livro já emprestado
        minhaBiblioteca.realizarEmprestimo("999-99-9999-999-9", membro1.getIdMembro()); // Livro não existe

        // 5. Listar Empréstimos Ativos e Livros Disponíveis (após empréstimos)
        minhaBiblioteca.listarEmprestimosAtivos();
        minhaBiblioteca.listarLivrosDisponiveis();
        membro1.exibirDetalhes();
        membro2.exibirDetalhes();

        // 6. Realizar Devoluções
        System.out.println("\n=== Realizando Devoluções ===");
        minhaBiblioteca.realizarDevolucao(livro1.getIsbn(), membro1.getIdMembro());
        minhaBiblioteca.realizarDevolucao(livro3.getIsbn(), membro1.getIdMembro()); // Livro não emprestado por este membro

        // 7. Listar Empréstimos Ativos e Livros Disponíveis (após devoluções)
        minhaBiblioteca.listarEmprestimosAtivos();
        minhaBiblioteca.listarLivrosDisponiveis();
        membro1.exibirDetalhes();

        // 8. Tentando remover livro emprestado
        System.out.println("\n=== Tentando Remover Livro Emprestado ===");
        minhaBiblioteca.removerLivro(livro2.getIsbn());

        // 9. Remover membro com livros emprestados
        System.out.println("\n=== Tentando Remover Membro com Livros Emprestados ===");
        minhaBiblioteca.removerMembro(membro2.getIdMembro());

        // 10. Devolver o último livro
        System.out.println("\n=== Devolvendo Último Livro ===");
        minhaBiblioteca.realizarDevolucao(livro2.getIsbn(), membro2.getIdMembro());

        // 11. Remover livro e membro após devolução
        System.out.println("\n=== Removendo Livro e Membro Após Devolução ===");
        minhaBiblioteca.removerLivro(livro2.getIsbn());
        minhaBiblioteca.removerMembro(membro2.getIdMembro());

        minhaBiblioteca.listarLivrosDisponiveis();
        minhaBiblioteca.listarMembros();
    }
}

