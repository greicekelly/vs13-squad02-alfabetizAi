import interfaces.AdminImplementa;

public class Admin implements AdminImplementa {
    String nome;
    int idade;
    String email;
    @Override
    public boolean aprovarModulo(Modulo conteudo) {
        System.out.println("\nConteudo aprovado com sucesso");
        return true;
    }

    @Override
    public boolean deletarModulo(Modulo conteudo) {
        System.out.println("/nConteudo deletado com sucesso");
        return true;
    }
}
