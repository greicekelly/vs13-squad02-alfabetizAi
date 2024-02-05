package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.models.DesafioAlternativas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DesafioDTO {
    private Integer id;
    private ModuloDTO modulo;
    private String titulo;
    private String conteudo;
    private TipoDesafio tipo;
    private String instrucao;
    private DesafioAlternativas desafioAlternativas;
    private Integer pontos;
}
