package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModuloCreateDTO {

    @Schema(description = "Titulo do Modulo", required = true, example = "Aprenda as Letras.")
    @NotBlank
    private String titulo;

    @Schema(description = "Demonstra o conteudo do Modulo.", required = true, example = " Aprenda as Vogais.")
    @NotBlank
    private String conteudo;

    @Schema(description = "Id do Professor", required = true, example = "1")
    @NotNull
    private Integer idProfessor;

    @Schema(description = "Demonstra o conteudo do Modulo foi aprovado.", required = true, example = "S")
    @NotBlank
    private String foiAprovado;

    @Schema(description = "Classificação em INICIANTE, INTERMEDIARIO e AVANÇADO", required = true, example = "INICIANTE")
    @NotNull
    private ClassificacaoModulo classificacao;
}
