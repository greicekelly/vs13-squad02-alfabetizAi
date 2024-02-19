package br.com.dbc.vemser.alfabetizai.dto.responsavel;

import br.com.dbc.vemser.alfabetizai.dto.cargos.CargosDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ResponsavelCreateDTO {

    @NotBlank
    @Schema(description = "Nome do Responsável", required = true, example = "Rafael")
    private String nome;

    @NotBlank
    @Schema(description = "Sobrenome do Responsável", required = true, example = "Silveira")
    private String sobrenome;

    @NotBlank
    @Size(min = 9)
    @Schema(description = "Número do telefone do Responsável", required = true, example = "(51) 99136-4015")
    private String telefone;

    @Email
    @Schema(description = "Email do Responsável", required = true, example = "rafael@email")
    private String email;

    @NotNull
    @Past
    @Schema(description = "Data de nascimento do Responsável", required = true, example = "1990-01-26")
    private LocalDate dataDeNascimento;

    @NotBlank
    @Schema(description = "Sexo do Responsável - M=Masculino | F=Feminino | O=Outros", required = true, example = "M")
    private String sexo;

    @NotBlank
    @Schema(description = "Senha do Responsável", required = true, example = "1111")
    private String senha;

    @CPF
    @NotBlank
    @Size(max = 11, min = 11)
    @Schema(description = "CPF do Responsável - 11 digitos", required = true, example = "05474124015")
    private String cpf;

}
