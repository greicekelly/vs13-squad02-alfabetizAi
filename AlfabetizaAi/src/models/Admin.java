package models;

import implement.AdminImplementa;
import models.Usuario;

import java.time.LocalDate;

public class Admin extends Usuario implements AdminImplementa {

    private Integer idAdmin;

    private String descricao;

    public Admin() {}

    public Admin(int idUsuario, String nome, String sobrenome, String telefone, String email, LocalDate dataDeNascimento, String ativo, String sexo, String senha, String cpf, Integer idAdmin, String descricao) {
        super(idUsuario, nome, sobrenome, telefone, email, dataDeNascimento, ativo, sexo, senha, cpf);
        this.idAdmin = idAdmin;
        this.descricao = descricao;
    }

    public Admin( String nome, String sobrenome, String telefone, String email, LocalDate dataDeNascimento, String ativo, String sexo, String senha, String cpf, String descricao) {
        super( nome, sobrenome, telefone, email, dataDeNascimento, ativo, sexo, senha, cpf);
        this.descricao = descricao;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
