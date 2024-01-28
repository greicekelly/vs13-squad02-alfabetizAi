package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Desafio {

    private int id;

    private int idModulo;

    private String titulo;

    private String conteudo;

    private TipoDesafio tipoDesafio;

    private String instrucao;

    private List<String> alternativas;

    private String alternativaCorreta;

    private int pontos;
}
