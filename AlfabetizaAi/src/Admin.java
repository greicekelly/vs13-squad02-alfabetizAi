public class Admin extends Usuario implements AdminImplementa {

    public Admin(String nome, int idade, String email) {
        super(nome, idade, email);
    }

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
