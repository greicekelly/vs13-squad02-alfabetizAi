public class Desafio {

    private String titulo;
    private TipoDesafio tipoDesafio;
    private Professor autor;

    public Desafio() {}

    public Desafio(String titulo, TipoDesafio tipo, Professor autor) {
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

    public TipoDesafio getTipoDesafio() {
        return tipoDesafio;
    }

    public void setTipoDesafio(TipoDesafio tipo) {
        this.tipoDesafio = tipo;
    }

    public Professor getAutor() {
        return autor;
    }

    public void setAutor(Professor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Titulo: " + this.getTitulo() + "\nTipo: " + tipoDesafio.name() + "\nAutor: " + this.getAutor().getNome();
    }

}
