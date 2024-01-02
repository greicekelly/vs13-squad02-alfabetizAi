import interfaces.ProfessorImplementa;

public class Professor implements ProfessorImplementa {
    String nome;
    int idade;
    String email;
    Modulo[] listaModulo;

    @Override
    public Modulo criarModulo(){
        System.out.println("\nConteudo criado com sucesso");
        return new Conteudo();
    }
    @Override
    public void modificarModulo(Modulo conteudo) {
        System.out.println("\nConteudo Modificado com sucesso");
    }
}
