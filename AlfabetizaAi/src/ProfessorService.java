import java.util.ArrayList;
import java.util.List;

public class ProfessorService {
    private ArrayList<Professor> professores;

    public ProfessorService() {
        this.professores = new ArrayList<>();
    }

    public void adicionar(Professor professor) {
        professores.add(professor);
    }

    public ArrayList<Professor> listarTodos() {
        return professores;
    }

    public void visualizar() {
        for(int i = 0; i < professores.size(); i++) {
            System.out.println("Id do Professor: " + i + "\n" + professores.get(i) + "\n--------");
        }
    }

    public void consultarProfessor(int i) {
        Professor professor = professores.get(i);
        System.out.println("Nome: " + professor.getNome() + " - Idade: " + professor.getDataDeNascimento() + " - E-mail: " + professor.getEmail());
    }

    public void editar(int i, Professor professorEditado) {
        Professor professor = professores.get(i);
        professor.setNome(professorEditado.getNome());
        professor.setDataDeNascimento(professorEditado.getDataDeNascimento());
        professor.setEmail(professorEditado.getEmail());
        System.out.println("Edição realizada com sucesso");
    }

    public void remover(int i) {
        professores.remove(i);
    }
}
