package br.com.dbc.vemser.alfabetizai.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Aluno extends Usuario {

    private Integer idAluno;

    private String nomeAluno;

    private String sobrenomeAluno;

    private LocalDate dataNascimentoAluno;

    private String sexoAluno;
}
