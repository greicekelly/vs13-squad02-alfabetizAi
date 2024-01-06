import java.util.ArrayList;

public class AlunoService {
    private ArrayList<Aluno> lista;

    public AlunoService() {
        this.lista = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        if(aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new IllegalArgumentException("Por favor insira um nome.");
        }
        lista.add(aluno);
    }

    public void visualizarTodosAlunos() {
        if (lista.isEmpty()) {
            throw new IllegalStateException("Nenhum aluno cadastrado.");
        }

        System.out.println("Lista de Alunos:");
        for (int i = 0; i < lista.size(); i++) {
            Aluno aluno = lista.get(i);
            System.out.println("Id: " + i + ", Nome: " + aluno.getNome() + ", Idade: " + aluno.getIdade() + ", Email: " + aluno.getEmail());
        }
    }

    public void consultarAluno(int index) {
        try {
            Aluno aluno = lista.get(index);
            System.out.println("Nome: " + aluno.getNome() + ", Idade: " + aluno.getIdade() + ", Email: " + aluno.getEmail());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Aluno não existe.");
        }
    }

    public Aluno consultarAlunoEmail(String email) {
        for (Aluno aluno : lista) {
            if(aluno.getEmail().equals(email)){
               return aluno;
            }
        }
        return null;
    }


    public void editarAluno(Aluno aluno, Aluno alunoEditado) {
        try{
            aluno.setNome((alunoEditado.getNome()));
            aluno.setEmail((alunoEditado.getEmail()));
            aluno.setIdade((alunoEditado.getIdade()));
        } catch (IndexOutOfBoundsException e){
            System.err.println("erro ao editar  aluno.");
        }

    }

    public void removerAluno(int index) {
        try {
            Aluno alunoRemovido = lista.get(index);
            lista.remove(index);
            System.out.println("Aluno " + alunoRemovido.getNome() + " removido com sucesso");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("O aluno selecionado não existe.");
        }
    }
}
