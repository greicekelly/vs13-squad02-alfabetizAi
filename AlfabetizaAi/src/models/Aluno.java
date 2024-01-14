package models;

import models.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
public class Aluno extends Usuario {

    private Integer idAluno;

    private String nomeAluno;

    private String sobrenomeAluno;

    private LocalDate dataNascimentoAluno;

    private String sexoAluno;

    public Aluno() {}

    public Aluno(Integer idUsuario, String nome, String sobrenome, String telefone, String email, LocalDate dataDeNascimento, String ativo, String sexo, String senha, Integer idAluno, String nomeAluno, String sobrenomeAluno, LocalDate dataNascimentoAluno, String sexoAluno) {
        super(idUsuario, nome, sobrenome, telefone, email, dataDeNascimento, ativo, sexo, senha);
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.sobrenomeAluno = sobrenomeAluno;
        this.dataNascimentoAluno = dataNascimentoAluno;
        this.sexoAluno = sexoAluno;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getSobrenomeAluno() {
        return sobrenomeAluno;
    }

    public void setSobrenomeAluno(String sobrenomeAluno) {
        this.sobrenomeAluno = sobrenomeAluno;
    }

    public LocalDate getDataNascimentoAluno() {
        return dataNascimentoAluno;
    }

    public void setDataNascimentoAluno(LocalDate dataNascimentoAluno) {
        this.dataNascimentoAluno = dataNascimentoAluno;
    }

    public String getSexoAluno() {
        return sexoAluno;
    }

    public void setSexoAluno(String sexoAluno) {
        this.sexoAluno = sexoAluno;
    }

    public String toString() {
        return "Aluno: " +
                "Id: " + getId() +
                " - Nome: " + getNome() +
                " - Data de Nascimento: " + getDataDeNascimento() +
                " - Email: " + getEmail() + "\'";
    }

    public void setIdAluno(Integer proximoIdAluno) {
    }
}
