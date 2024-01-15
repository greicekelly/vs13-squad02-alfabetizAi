package models;

import enums.ClassificacaoModulo;

import java.util.ArrayList;

public class Modulo {

    private int id;
    private String titulo;
    private String conteudo;
    private Professor autor;
    private Character foiAprovado;
    private Admin adminAprova;
    private ClassificacaoModulo classificacao;

    public Modulo(){}

    public Modulo(int id, String titulo, String conteudo, Professor autor, ClassificacaoModulo classificacao) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
        this.foiAprovado = 'N';
        this.classificacao = classificacao;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Character isFoiAprovado() {
        return foiAprovado;
    }

    public void setFoiAprovado(Character foiAprovado) {

        switch (foiAprovado){
            case 'S':
                this.foiAprovado = foiAprovado;
                break;
            case 's':
                this.foiAprovado = foiAprovado;
                break;
            case 'N':
                this.foiAprovado = foiAprovado;
                break;
            case 'n':
                this.foiAprovado = foiAprovado;
                break;
            default:
                this.foiAprovado = null;
                break;
        }
    }

    public Admin getAdminAprova() {
        return adminAprova;
    }

    public void setAdminAprova(Admin adminAprova) {
        this.adminAprova = adminAprova;
    }

    public ClassificacaoModulo getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoModulo classificacao) {
        this.classificacao = classificacao;
    }

//    public void visualizarDesafios() {
//        for (int i = 0; i < desafios.size(); i++) {
//            System.out.printf("""
//                __________Desafio %d___________
//                Titulo: %s
//                Autor: %s
//                Tipo: %s
//                """, i + 1, desafios.get(i).getTitulo(), desafios.get(i).getAutor().getNome(), desafios.get(i).getTipoDesafio().getNome());
//            System.out.println("--------------------------------");
//        }
//    }

    @Override
    public String toString() {
        return "Id: "+getId()+" - Titulo: "+getTitulo() +" - Classificação: "+getClassificacao();
    }

}
