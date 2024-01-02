import java.util.ArrayList;
public class Aluno extends Usuario
{
    private ArrayList<Modulo> modulosConcluidos;

    public Aluno(String nome, int idade, String email, ArrayList<Modulo> modulosConcluidos) {
        super(nome, idade, email);
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
