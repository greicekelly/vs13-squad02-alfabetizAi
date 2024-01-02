import java.util.ArrayList;

public class Aluno {

    private String nome;
    private int idade;
    private String email;
    private ArrayList<Modulo> modulosConcluidos = new ArrayList<Modulo>();

    public boolean concluirModulo(Modulo modulo){
        System.out.println("O módulo " + modulo.getTitulo() + " foi concluído.");
        this.modulosConcluidos.add(modulo);
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Modulo> getModulosConcluidos() {
        return modulosConcluidos;
    }

    public void setModulosConcluidos(ArrayList<Modulo> modulosConcluidos) {
        this.modulosConcluidos = modulosConcluidos;
    }
}
