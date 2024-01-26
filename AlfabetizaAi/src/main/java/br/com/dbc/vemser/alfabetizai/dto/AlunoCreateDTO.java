package br.com.dbc.vemser.alfabetizai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
public class AlunoCreateDTO {

    @NotBlank
    @Schema(description = "Nome do Usuario", required = true, example = "Rafael")
    private String nome;

    @NotBlank
    @Schema(description = "Sobrenome do Usuario", required = true, example = "Silveira")
    private String sobrenome;

    @NotBlank
    @Size(min = 9)
    @Schema(description = "Número do telefone do Usuario", required = true, example = "(51) 99136-4015")
    private String telefone;

    @Email
    @Schema(description = "Email do Usuario", required = true, example = "rafael@email")
    private String email;

    @NotNull
    @Past
    @Schema(description = "Data de nascimento do Usuario", required = true, example = "1990-01-26")
    private LocalDate dataDeNascimento;

    @NotBlank
    @Schema(description = "Informa se o cadastro do usuario esta ativo", example = "Sim")
    private String ativo;

    @NotBlank
    @Schema(description = "Sexo do Usuario - M=Masculino | F=Feminino | O=Outros", required = true, example = "M")
    private String sexo;

    @NotBlank
    @Schema(description = "Senha do Usuario", required = true, example = "1111")
    private String senha;

    @NotBlank
    @Size(max = 11, min = 11)
    @Schema(description = "CPF do Usuario - 11 digitos", required = true, example = "05474124015")
    private String cpf;

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
