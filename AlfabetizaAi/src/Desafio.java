import java.util.ArrayList;
import java.util.Scanner;

public class Desafio {

    private String titulo;
    private char tipoDesafio; // (q - quiz ou j - jogo)
    private Professor autor;

    public Desafio() {}

    public Desafio(String titulo, char tipo, Professor autor) {
        this.titulo = titulo;
        this.tipoDesafio = tipo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public char getTipoDesafio() {
        return tipoDesafio;
    }

    public void setTipoDesafio(char tipo) {
        this.tipoDesafio = tipo;
    }

    public Professor getAutor() {
        return autor;
    }

    public void setAutor(Professor autor) {
        this.autor = autor;
    }

    String tipo;
    @Override
    public String toString() {
        if (this.tipoDesafio == 'q') {
            tipo = "Quiz";
        } else if (this.tipoDesafio == 'j'){
            tipo = "Jogo";
        }
        return "Titulo: " + this.getTitulo() + "\nTipo: " + tipo + "\nAutor: " + this.getAutor().getNome();
    }

}
