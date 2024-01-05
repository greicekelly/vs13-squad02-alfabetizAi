import java.time.LocalDate;

public class Admin extends Usuario implements AdminImplementa {

    public Admin(String nome, LocalDate dataDeNascimento, String email) {
        super(nome, dataDeNascimento, email);
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

    @Override
    public String toString() {
        return "Nome: "+getNome() +" - Idade: "+getIdade()+" - Email: "+getEmail();
    }
}
