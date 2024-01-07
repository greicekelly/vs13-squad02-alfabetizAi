package models;

import enums.ClassificacaoModulo;
import models.Desafio;

import java.util.ArrayList;

public class Modulo {

    private String titulo;
    private String conteudo;
    private Professor autor;
    private boolean foiAprovado;
    private ArrayList<Desafio> desafios;
    private ClassificacaoModulo classificacao; //(1- iniciante, 2- intermediário, 3- avancado)

    public Modulo(){}

    public Modulo(String titulo, Professor autor, ArrayList<Desafio> desafios, ClassificacaoModulo classificacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.foiAprovado = false;
        this.desafios = desafios;
        this.classificacao = classificacao;
    }

    public void visualizarDesafios() {
        for (int i = 0; i < desafios.size(); i++) {
            System.out.printf("""
                __________Desafio %d___________
                Titulo: %s
                Autor: %s
                """, i, desafios.get(i).getTitulo(), desafios.get(i).getAutor().getNome());
            System.out.println("--------------------------------");
        }
    }

    @Override
    public String toString() {
        return "Titulo: "+getTitulo() +" - Autor: "+getAutor()+" - Classificação: "+getClassificacao();
    }

    public void ExibirConteudo(){
        System.out.println("Exibindo conteúdo do módulo.");
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

    public Professor getAutor() {
        return autor;
    }

    public void setAutor(Professor autor) {
        this.autor = autor;
    }

    public boolean isFoiAprovado() {
        return foiAprovado;
    }

    public void setFoiAprovado(boolean foiAprovado) {
        this.foiAprovado = foiAprovado;
    }

    public ArrayList<Desafio> getDesafios() {
        return desafios;
    }

    public void setDesafios(ArrayList<Desafio> desafios) {
        this.desafios = desafios;
    }

    public ClassificacaoModulo getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoModulo classificacao) {
        this.classificacao = classificacao;
    }
}
