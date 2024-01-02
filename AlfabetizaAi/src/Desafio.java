public class Desafio {

    private String titulo;
    private char tipo; //(v - video, e - escrito)
    private String autor;
    private boolean foiAprovado;

    public boolean exibirDesafio(){
        System.out.println("Exibindo desafio.");
        return true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isFoiAprovado() {
        return foiAprovado;
    }

    public void setFoiAprovado(boolean foiAprovado) {
        this.foiAprovado = foiAprovado;
    }
}
