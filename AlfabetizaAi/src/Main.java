import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AdminService listaAdmin = new AdminService();
        listaAdmin.adicionar(new Admin("Maria Antonia", 36, "teste@email"));
        listaAdmin.adicionar(new Admin("Joao da Silva", 36, "teste@email"));
        listaAdmin.adicionar(new Admin("Pedro", 36, "teste@email"));

        listaAdmin.visualizarTodos();
        listaAdmin.consultar(1);
        listaAdmin.editar(2, new Admin("Pedro Santos",34, "pedro.santos@email"));
        listaAdmin.visualizarTodos();
        listaAdmin.remover(0);
        listaAdmin.visualizarTodos();

        AlunoService listaAluno = new AlunoService();
        listaAluno.adicionarAluno(new Aluno("Lucas", 8, "lucass@mail.com", new ArrayList<>()));
        listaAluno.adicionarAluno(new Aluno("Renan", 6, "lucas@mail.com", new ArrayList<>()));
        listaAluno.adicionarAluno(new Aluno("Arthur", 3, "arthura@mail.com", new ArrayList<>()));

        listaAluno.editarAluno(1, new Aluno("Lucas Vinicius", 12, "lucas@gmail.com", new ArrayList<>()));
        listaAluno.visualizarTodosAlunos();
        listaAluno.consultarAluno(0);
        listaAluno.removerAluno(1);
    }
}