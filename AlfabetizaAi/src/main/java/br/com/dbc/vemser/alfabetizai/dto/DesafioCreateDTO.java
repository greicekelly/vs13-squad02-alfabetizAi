package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class DesafioCreateDTO {

    @NotNull(message = "Id não pode ser nulo.")
    private int id;

    @NotNull(message = "Id do Modulo não pode ser nulo.")
    private int idModulo;

    @NotBlank(message = "O titulo não pode estar em branco ou ser Nulo!")
    private String titulo;

    @NotBlank(message = "O conteudo não pode estar em branco ou ser Nulo!")
    private String conteudo;

    @NotNull(message = "O Tipo de desafio não pode ser Nulo!")
    private TipoDesafio tipoDesafio;

}
