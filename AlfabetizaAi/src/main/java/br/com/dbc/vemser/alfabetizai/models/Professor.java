package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.implement.ProfessorImplementa;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Professor extends Usuario implements ProfessorImplementa {

    private Integer idProfessor;

    private String descricao;

    public Professor(int idUsuario, String nome, String sobrenome, String telefone, String email, LocalDate dataDeNascimento, String ativo, String sexo, String senha, String cpf, Integer idProfessor, String descricao) {
        super(idUsuario, nome, sobrenome, telefone, email, dataDeNascimento, ativo, sexo, senha, cpf);
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

}

