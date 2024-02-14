package br.com.dbc.vemser.alfabetizai.dto.aluno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AlunoDTO {

    private Integer idAluno;

    private String nomeAluno;

    private String sobrenomeAluno;

    private LocalDate dataNascimentoAluno;

    private String sexoAluno;

    private int pontuacao;
}
