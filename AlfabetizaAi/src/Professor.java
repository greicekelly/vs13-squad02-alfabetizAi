public class Professor extends Usuario implements ProfessorImplementa {

    public Professor(String nome, int idade, String email) {
        super(nome, idade, email);
    }

    @Override
    public Modulo criarModulo(){
        System.out.println("\nConteudo criado com sucesso");
        return new Modulo();
    }
    @Override
    public void modificarModulo(Modulo conteudo) {
        System.out.println("\nConteudo Modificado com sucesso");
    }

    @Override
    public String toString() {
        return "Professor \nNome: " + this.getNome() + "\nIdade: " + this.getIdade() + "\nE-mail: " + this.getEmail();
    }
}

