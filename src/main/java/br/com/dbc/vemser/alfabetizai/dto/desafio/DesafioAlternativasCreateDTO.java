package br.com.dbc.vemser.alfabetizai.dto.desafio;

import io.swagger.v3.oas.annotations.media.Schema;
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


    @Schema(description = "Id do Desafio", required = true, example = "1")
    private int idDesafio;

    @Schema(description = "Alternativa A", required = true, example = "Inicia Letra B")
    @NotBlank
    private String a;

    @Schema(description = "Alternativa B", required = true, example = "Inicia Letra B")
    @NotBlank
    private String b;

    @Schema(description = "Alternativa C", required = true, example = "Inicia Letra C")
    @NotBlank
    private String c;

    @Schema(description = "Alternativa D", required = true, example = "Inicia Letra D")
    @NotBlank
    private String d;

    @Schema(description = "Alternativa E", required = true, example = "Inicia Letra E")
    @NotBlank
    private String e;

    @Schema(description = "Mostra a  Alternativa Correta", required = true, example = "A")
    @NotBlank
    private String alternativaCorreta;
}

