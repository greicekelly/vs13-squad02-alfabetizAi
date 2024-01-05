import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ModuloService lista = new ModuloService();
        Professor professor = new Professor("maria", 20, "teste@email");
        Desafio desafio = new Desafio("Desafio 1", 'V', professor);
        Modulo modulo = new Modulo("teste", professor , new ArrayList<>(), 1);
        modulo.adicionarDesafio(desafio);
        lista.adicionar(modulo);
        
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

        ProfessorService listaProfessor = new ProfessorService();
        listaProfessor.adicionar(new Professor("Bruno",34,"bruno@email.com"));
        listaProfessor.adicionar(new Professor("Gabriel",23, "gabriel@email.com"));
        listaProfessor.adicionar(new Professor("Vitoria",32, "vitoria@email.teste"));

        listaProfessor.editar(2, new Professor("Vitoria", 21, "vitoria@email.teste"));
        listaProfessor.consultarProfessor(2);
        listaProfessor.remover(2);
        listaProfessor.visualizar();

    }
}