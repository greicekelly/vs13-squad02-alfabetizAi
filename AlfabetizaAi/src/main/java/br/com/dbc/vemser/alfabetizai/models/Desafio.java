package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;

public class Desafio {

    private int id;
    private int idModulo;
    private String titulo;
    private String conteudo;
    private TipoDesafio tipoDesafio;

    public Desafio() {}

    public Desafio(int id,String titulo,String conteudo, TipoDesafio tipo) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.tipoDesafio = tipo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public TipoDesafio getTipoDesafio() {
        return tipoDesafio;
    }

    public void setTipoDesafio(TipoDesafio tipo) {
        this.tipoDesafio = tipo;
    }

    @Override
    public String toString() {
        return "Titulo: " + this.getTitulo() + "\nTipo: " + tipoDesafio.name() + "\nConteudo: " + this.getConteudo();
    }

}
