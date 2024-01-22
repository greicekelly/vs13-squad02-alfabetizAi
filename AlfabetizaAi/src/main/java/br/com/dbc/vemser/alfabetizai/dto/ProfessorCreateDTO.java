package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.models.Professor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorCreateDTO {

    @NotNull
    private Integer idUsuario;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    private String telefone;

    @NotBlank
    private String email;

    @NotBlank
    private String descricao;

    public static ProfessorCreateDTO fromProfessor(Professor professor) {
        return new ProfessorCreateDTO(
                professor.getIdUsuario(),
                professor.getNome(),
                professor.getSobrenome(),
                professor.getTelefone(),
                professor.getEmail(),
                professor.getDescricao()
        );
    }
}
