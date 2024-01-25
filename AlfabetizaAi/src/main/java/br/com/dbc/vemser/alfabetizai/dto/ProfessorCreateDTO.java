package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.models.Professor;
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
