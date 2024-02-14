package br.com.dbc.vemser.alfabetizai.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminCreateDTO {

    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    @Schema(description = "Nome do usuário Admin", required = true, example = "João")
    private String nome;
    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    @Schema(description = "Sobrenome do usuário Admin", required = true, example = "da Silva")
    private String sobrenome;
    @NotNull
    @NotBlank
    @Size(min=9, max=12)
    @Schema(description = "Número do telefone do usuário Admin", required = true, example = "11912345678")
    private String telefone;
    @NotNull
    @NotBlank
    @Email
    @Size(min=1, max=255)
    @Schema(description = "Email do usuário Admin", required = true, example = "email@email.com")
    private String email;
    @NotNull
    @Schema(description = "Data de nascimento do usuário Admin", required = true, example = "01/01/1990")
    private LocalDate dataDeNascimento;
    @NotNull
    @Schema(description = "Sexo do usuário Admin - M=Masculino | F=Feminino | O=Outros", required = true, example = "M")
    private String sexo;
    @NotNull
    @Schema(description = "Senha do usuário Admin", required = true, example = "xxxxxxxxx")
    private String senha;
    @NotNull
    @CPF
    @Schema(description = "CPF do usuário Admin", required = true, example = "12345678900")
    private String cpf;
    @NotNull
    @NotBlank
    @Schema(description = "Cargo do usuário Admin", required = true, example = "Gerente de conteúdo")
    private String descricao;
}
