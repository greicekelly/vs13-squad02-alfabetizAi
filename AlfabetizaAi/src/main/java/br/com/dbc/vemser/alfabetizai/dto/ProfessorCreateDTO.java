package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.models.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorCreateDTO {


    @NotBlank(message = "O Nome não pode estar em branco ou ser Nulo!")
    @Size(min=2, max=255, message = "O nome deve conter entre 2 e 255 caracteres.")
    @Schema(description = "Nome do professor", required = true, example = "Rafael")
    private String nome;


    @NotBlank(message = "O Sobrenome não pode estar em branco ou ser Nulo!")
    @Size(min=2, max=255, message = "O Sobrenome deve conter entre 2 e 255 caracteres.")
    @Schema(description = "Sobrenome do professor", required = true, example = "Santos")
    private String sobrenome;

    @NotBlank(message = "O Telefone não pode estar em branco ou ser Nulo!")
    @Size(min=9, max=12, message = "O Telefone deve conter entre 9 e 12 digitos.")
    @Schema(description = "Número do telefone do professor", required = true, example = "(11) 99124-1515")
    private String telefone;

    @NotBlank(message = "O Email não pode estar em branco ou ser Nulo!")
    @Email(message = "O email deve ser valido, contendo @.")
    @Size(min=1, max=255)
    @Schema(description = "Email do professor", required = true, example = "rafael@email")
    private String email;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de Nascimento deve ser no passado!")
    @Schema(description = "Data de nascimento do professor", required = true, example = "1990-01-26")
    private LocalDate dataDeNascimento;

    @Schema(description = "Informa se o cadastro esta ativo", example = "Sim")
    private String ativo;

    @NotBlank(message = "O Sexo não pode estar em branco ou ser Nulo!")
    @Schema(description = "Sexo do professor - M=Masculino | F=Feminino | O=Outros", required = true, example = "M")
    private String sexo;

    @NotBlank(message = "O campo senha não pode estar em branco ou ser Nulo!")
    @Size(min=4, max=8, message = "O campo senha deve conter entre 4 e 8 digitos.")
    @Schema(description = "Senha do professor", required = true, example = "1111")
    private String senha;

    @CPF(message = "O campo CPF não pode estar em branco ou ser Nulo! Por favor, forneça um CPF válido.")
    @Schema(description = "CPF do professor", required = true, example = "95443427024")
    private String cpf;

    @NotBlank(message = "O campo Descrição não pode estar em branco ou ser Nulo!")
    @Schema(description = "Formação do professor", required = true, example = "Licenciatura em Letras")
    private String descricao;
}
