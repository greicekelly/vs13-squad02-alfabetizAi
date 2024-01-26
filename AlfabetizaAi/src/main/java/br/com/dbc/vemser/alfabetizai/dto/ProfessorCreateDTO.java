package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.models.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorCreateDTO {

    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    @Schema(description = "Nome do professor", required = true, example = "Rafael")
    private String nome;

    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    @Schema(description = "Sobrenome do professor", required = true, example = "Santos")
    private String sobrenome;

    @NotNull
    @NotBlank
    @Size(min=9, max=12)
    @Schema(description = "Número do telefone do professor", required = true, example = "(11) 99124-1515")
    private String telefone;

    @NotNull
    @NotBlank
    @Email
    @Size(min=1, max=255)
    @Schema(description = "Email do professor", required = true, example = "rafael@email")
    private String email;

    @NotNull
    @Schema(description = "Data de nascimento do professor", required = true, example = "01/01/1990")
    private LocalDate dataDeNascimento;

    private String ativo;

    @NotNull
    @Schema(description = "Sexo do professor - M=Masculino | F=Feminino | O=Outros", required = true, example = "M")
    private String sexo;

    @NotNull
    @Schema(description = "Senha do professor", required = true, example = "1111")
    private String senha;

    @NotNull
    @CPF
    @Schema(description = "CPF do professor", required = true, example = "95443427024")
    private String cpf;

    @NotNull
    @NotBlank
    @Schema(description = "Formação do professor", required = true, example = "Licenciatura em Letras")
    private String descricao;
}
