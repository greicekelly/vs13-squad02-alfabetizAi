import java.time.LocalDate;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ModuloService lista = new ModuloService();
        Professor professor = new Professor("maria", LocalDate.parse("12-18-1987"), "teste@email");
        Desafio desafio = new Desafio("Desafio 1", TipoDesafio.JOGO, professor);
        Modulo modulo = new Modulo("teste", professor , new ArrayList<>(), 1);
        modulo.adicionarDesafio(desafio);
        lista.adicionar(modulo);
        
        AdminService listaAdmin = new AdminService();
        listaAdmin.adicionar(new Admin("Maria Antonia", LocalDate.parse("12-18-1987"), "teste@email"));
        listaAdmin.adicionar(new Admin("Joao da Silva", LocalDate.parse("12-18-1987"), "teste@email"));
        listaAdmin.adicionar(new Admin("Pedro", LocalDate.parse("12-18-1987"), "teste@email"));

        listaAdmin.visualizarTodos();
        listaAdmin.consultar(1);
        listaAdmin.editar(2, new Admin("Pedro Santos",LocalDate.parse("01-06-2018"), "pedro.santos@email"));
        listaAdmin.visualizarTodos();
        listaAdmin.remover(0);
        listaAdmin.visualizarTodos();

        AlunoService listaAluno = new AlunoService();
        listaAluno.adicionarAluno(new Aluno("Lucas", LocalDate.parse("01-07-2018"), "lucass@mail.com", new ArrayList<>()));
        listaAluno.adicionarAluno(new Aluno("Renan", LocalDate.parse("01-08-2018"), "lucas@mail.com", new ArrayList<>()));
        listaAluno.adicionarAluno(new Aluno("Arthur", LocalDate.parse("01-09-2018"), "arthura@mail.com", new ArrayList<>()));

        listaAluno.editarAluno(1, new Aluno("Lucas Vinicius", LocalDate.parse("01-06-2015"), "lucas@gmail.com", new ArrayList<>()));
        listaAluno.visualizarTodosAlunos();
        listaAluno.consultarAluno(0);
        listaAluno.removerAluno(1);

        ProfessorService listaProfessor = new ProfessorService();
        listaProfessor.adicionar(new Professor("Bruno",LocalDate.parse("01-06-2013"),"bruno@email.com"));
        listaProfessor.adicionar(new Professor("Gabriel",LocalDate.parse("01-06-2014"), "gabriel@email.com"));
        listaProfessor.adicionar(new Professor("Vitoria",LocalDate.parse("01-06-2019"), "vitoria@email.teste"));

        listaProfessor.editar(2, new Professor("Vitoria", LocalDate.parse("01-06-2015"), "vitoria@email.teste"));
        listaProfessor.consultarProfessor(2);
        listaProfessor.remover(2);
        listaProfessor.visualizar();

    }
}