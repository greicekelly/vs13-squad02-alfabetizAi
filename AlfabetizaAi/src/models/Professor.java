package models;

import implement.ProfessorImplementa;
import models.Modulo;
import models.Usuario;

import java.time.LocalDate;

public class Professor extends Usuario implements ProfessorImplementa {

    private Integer idProfessor;

    private String descricao;

    public Professor() {}

    public Professor(int idUsuario, String nome, String sobrenome, String telefone, String email, LocalDate dataDeNascimento, String ativo, String sexo, String senha, Integer idProfessor, String descricao) {
        super(idUsuario, nome, sobrenome, telefone, email, dataDeNascimento, ativo, sexo, senha);
        this.idProfessor = idProfessor;
        this.descricao = descricao;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        return "Professor: " +
                "Id: " + getId() +
                " - Nome: " + getNome() +
                " - Data de Nascimento: " + getDataDeNascimento() +
                " - Email: " + getEmail() + "\'";
    }
}

