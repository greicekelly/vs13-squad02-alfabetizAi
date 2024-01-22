package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.models.Professor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {

    private Integer idProfessor;

    @NotNull
    private Integer idUsuario;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    private String telefone;

    @NotBlank
    private String email;

    private String descricao;

    public static ProfessorDTO fromProfessor(Professor professor) {
        return new ProfessorDTO(
                professor.getIdProfessor(),
                professor.getIdUsuario(),
                professor.getNome(),
                professor.getSobrenome(),
                professor.getTelefone(),
                professor.getEmail(),
                professor.getDescricao()
        );
    }
}
