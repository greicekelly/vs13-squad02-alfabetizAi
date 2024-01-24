package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Desafio {

    private int id;

    private int idModulo;

    private String titulo;

    private String conteudo;

    private TipoDesafio tipoDesafio;

    public Desafio() {

        this.id = id;
        this.idModulo = idModulo;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.tipoDesafio = tipoDesafio;

    }
}
