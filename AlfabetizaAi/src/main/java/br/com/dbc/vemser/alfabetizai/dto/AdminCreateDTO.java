package br.com.dbc.vemser.alfabetizai.dto;

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
    private String nome;
    @NotNull
    @NotBlank
    @Size(min=2, max=255)
    private String sobrenome;
    @NotNull
    @NotBlank
    @Size(min=9, max=12)
    private String telefone;
    @NotNull
    @NotBlank
    @Email
    @Size(min=1, max=255)
    private String email;
    @NotNull
    private LocalDate dataDeNascimento;
    private String ativo;
    @NotNull
    private String sexo;
    @NotNull
    private String senha;
    @NotNull
    @CPF
    private String cpf;
    @NotNull
    @NotBlank
    private String descricao;
}
