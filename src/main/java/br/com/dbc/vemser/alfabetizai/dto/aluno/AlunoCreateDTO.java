package br.com.dbc.vemser.alfabetizai.dto.aluno;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoCreateDTO {

    @NotBlank
    @Schema(description = "Nome do Aluno", required = true, example = "Jake")
    private String nomeAluno;

    @NotBlank
    @Schema(description = "Sobrenome do Aluno", required = true, example = "Santos")
    private String sobrenomeAluno;

    @NotNull
    @Past
    @Schema(description = "Data de nascimento do Aluno", required = true, example = "1998-02-21")
    private LocalDate dataNascimentoAluno;

    @NotBlank
    @Schema(description = "Sexo do Aluno - M=Masculino | F=Feminino | O=Outros", required = true, example = "M")
    private String sexoAluno;
}
