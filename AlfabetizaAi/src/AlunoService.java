import java.util.ArrayList;

public class AlunoService {
    private ArrayList<Aluno> lista;

    public AlunoService() {
        this.lista = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        lista.add(aluno);
    }

    public void visualizarTodosAlunos() {
        if (lista.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("Lista de Alunos:");
        for (int i = 0; i < lista.size(); i++) {
            Aluno aluno = lista.get(i);
            System.out.println("Id: " + i + ", Nome: " + aluno.getNome() + ", Idade: " + aluno.getIdade() + ", Email: " + aluno.getEmail());
        }
    }


    public void consultarAluno(int index) {
        Aluno aluno = lista.get(index);
        System.out.println("Nome: " + aluno.getNome() + ", Idade: " + aluno.getIdade() + ", Email: " + aluno.getEmail());
    }

    public void editarAluno(int index, Aluno alunoEditado) {
        Aluno aluno = lista.get(index);
        aluno.setNome((alunoEditado.getNome()));
        aluno.setEmail((alunoEditado.getEmail()));
        aluno.setIdade((alunoEditado.getIdade()));
    }

    public void removerAluno(int index) {
        lista.remove(index);
        System.out.println("Aluno removido com sucesso" );
    }
}
