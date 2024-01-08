package models;

import implement.AdminImplementa;
import models.Usuario;

import java.time.LocalDate;

public class Admin extends Usuario implements AdminImplementa {
    private static Integer adminId = 1;
    public Admin(String nome, LocalDate dataDeNascimento, String email) {
        super(nome, dataDeNascimento, email);
        setId(adminId );
        adminId++;
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
        return "Admin: " +
                "Id: " + getId() +
                " - Nome: " + getNome() +
                " - Data de Nascimento: " + getDataDeNascimento() +
                " - Email: " + getEmail() + "\'";
    }


}
