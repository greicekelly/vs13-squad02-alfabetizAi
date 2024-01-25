package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DesafioDTO {
    private int id;
    private int idModulo;
    private String titulo;
    private String conteudo;
    private TipoDesafio tipoDesafio;
}
