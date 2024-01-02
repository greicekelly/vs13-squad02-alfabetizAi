public class Professor implements ProfessorImplementa {
    private String nome;
    private int idade;
    private String email;

    @Override
    public Modulo criarModulo(){
        System.out.println("\nConteudo criado com sucesso");
        return new Modulo();
    }
    @Override
    public void modificarModulo(Modulo conteudo) {
        System.out.println("\nConteudo Modificado com sucesso");
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
}

