package br.com.dbc.vemser.alfabetizai.dto;

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
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Size(min = 9)
    private String telefone;

    @Email
    private String email;

    @NotNull
    @Past
    private LocalDate dataDeNascimento;

    @NotBlank
    private String ativo;

    @NotBlank
    private String sexo;

    @NotBlank
    private String senha;

    @NotBlank
    @Size(max = 11, min = 11)
    private String cpf;

    @NotBlank
    private String nomeAluno;

    @NotBlank
    private String sobrenomeAluno;

    @NotNull
    @Past
    private LocalDate dataNascimentoAluno;

    @NotBlank
    private String sexoAluno;
}
