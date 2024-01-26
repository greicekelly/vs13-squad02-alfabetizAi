package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
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
public class DesafioCreateDTO {

    @NotNull(message = "Id não pode ser nulo.")
    @Schema(description = "Numero do id Desafio", required = true, example = "3")
    private int id;

    @NotNull(message = "Id do Modulo não pode ser nulo.")
    @Schema(description = "Numero do id do Modulo", required = true, example = "3")
    private int idModulo;

    @NotBlank(message = "O titulo não pode estar em branco ou ser Nulo!")
    @Schema(description = "Nome do titulo do desafio", required = true, example = "Animais com a letra A")
    private String titulo;

    @NotBlank(message = "O conteudo não pode estar em branco ou ser Nulo!")
    @Schema(description = "Descrição do conteudo do desafio", required = true, example = "Insira nome de animais com a letra A")
    private String conteudo;

    @NotNull(message = "O Tipo de desafio não pode ser Nulo!")
    @Schema(description = "Tipo de Desafio", required = true, example = "QUIZ")
    private TipoDesafio tipoDesafio;
}