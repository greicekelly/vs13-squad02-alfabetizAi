import java.time.LocalDate;
import java.util.ArrayList;
public class Aluno extends Usuario
{
    private ArrayList<Modulo> modulosConcluidos;

    public Aluno(String nome, LocalDate dataDeNascimento, String email, ArrayList<Modulo> modulosConcluidos) {
        super(nome, dataDeNascimento, email);
        this.modulosConcluidos = modulosConcluidos;
    }

    public boolean concluirModulo(Modulo modulo){
        System.out.println("O módulo " + modulo.getTitulo() + " foi concluído.");
        this.modulosConcluidos.add(modulo);
        return true;
    }

    public ArrayList<Modulo> getModulosConcluidos() {
        return modulosConcluidos;
    }

    public void setModulosConcluidos(ArrayList<Modulo> modulosConcluidos) {
        this.modulosConcluidos = modulosConcluidos;
    }

}
