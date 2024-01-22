package br.com.dbc.vemser.alfabetizai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AlunoDTO {

    private String nome;

    private String sobrenome;

    private String telefone;

    private String email;

    private LocalDate dataDeNascimento;

    private String ativo;

    private String sexo;

    private String cpf;

    private String nomeAluno;

    private String sobrenomeAluno;

    private LocalDate dataNascimentoAluno;

    private String sexoAluno;
}
