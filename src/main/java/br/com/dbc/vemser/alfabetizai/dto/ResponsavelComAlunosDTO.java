package br.com.dbc.vemser.alfabetizai.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelComAlunosDTO extends ResponsavelDTO {
    private Set<AlunoDTO> alunos;
}
