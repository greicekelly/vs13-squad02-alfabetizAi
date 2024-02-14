package br.com.dbc.vemser.alfabetizai.dto.responsavel;

import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelComAlunosDTO extends ResponsavelDTO {
    private Set<AlunoDTO> alunos;
}
