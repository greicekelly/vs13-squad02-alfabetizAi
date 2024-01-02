import java.util.ArrayList;

public class Modulo {

    private String titulo;
    private Professor autor;
    private boolean foiAprovado;
    private ArrayList<Desafio> desafios;
    private int classificacao; //(1- iniciante, 2- medio, 3- avancado)

    public void ExibirConteudo(){
        System.out.println("Exibindo conteúdo do módulo.");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
}
