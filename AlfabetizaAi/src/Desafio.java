import java.util.ArrayList;
import java.util.Scanner;

public class Desafio {

    private String titulo;
    private char tipo; //(v - video, e - escrito)
    private Professor autor;
/*
    public Desafio() {}

    public Desafio(String titulo, char tipo, Professor autor) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.autor = autor;
    }

    // CREATE

    Professor luis = new Professor("Luis", 22, "klsdjnckj");
    Professor filipe = new Professor("Filipe", 32, "ppodomspm");

    ArrayList<Professor> professores = new ArrayList<>();
*/
    public void criarDesafio() {

        System.out.println("\nDigite o título do desafio: ");
        Scanner input = new Scanner(System.in);
        this.titulo = input.nextLine();

        System.out.println("Digite o tipo do desafio: (V - Vídeo ou E - Escrito)");
        this.tipo = input.next().charAt(0);
        input.nextLine();

        professores.add(luis);
        professores.add(filipe);

        System.out.println("Selecione um entre os seguintes professores: ");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println((i + 1) + " - " + professores.get(i).getNome());
        }
        int prof = input.nextInt();
        this.autor = professores.get(prof-1);

    }


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

    public Professor getAutor() {
        return autor;
    }

    public void setAutor(Professor autor) {
        this.autor = autor;
    }

    public static void main(String[] args) {
        Desafio um = new Desafio();
        um.criarDesafio();
    }

}
