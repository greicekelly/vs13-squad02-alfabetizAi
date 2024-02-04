package br.com.dbc.vemser.alfabetizai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DesafioAlternativasCreateDTO {

    @NotNull
    private int idAlternativas;

    @NotNull
    private int idDesafio;

    @NotBlank
    private String a;

    @NotBlank
    private String b;

    @NotBlank
    private String c;

    @NotBlank
    private String d;

    @NotBlank
    private String e;

    @NotBlank
    private String alternativaCorreta;
}

