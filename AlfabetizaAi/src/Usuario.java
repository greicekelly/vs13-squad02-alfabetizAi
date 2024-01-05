import java.time.LocalDate;

public abstract class Usuario {

    private String nome;
    private LocalDate dataDeNascimento;
    private String email;

    public Usuario(String nome, LocalDate dataDeNascimento, String email) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return dataDeNascimento;
    }

    public void setIdade(int idade) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
