import java.time.LocalDate;
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

    public Professor loginProfessor(String email, LocalDate dataNascimento) {
        for (Professor professor : professores) {
            professor.getDataDeNascimento();
            if (professor.getEmail().equals(email) && professor.getDataDeNascimento().equals(dataNascimento)) {
                return professor;
            }
        }
        return null;
    }

    public Professor consultarProfessorEmail(String email) {
        for (Professor professor : professores) {
            if(professor.getEmail().equals(email)){
                return professor;
            }
        }
        return null;
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

    public void editar(Professor professor, Professor professorEditado) {
        professor.setNome(professorEditado.getNome());
        professor.setDataDeNascimento(professorEditado.getDataDeNascimento());
        professor.setEmail(professorEditado.getEmail());
    }

    public void remover(int i) {
        professores.remove(i);
    }
}
