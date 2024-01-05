import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        ModuloService lista = new ModuloService();
        Professor professor = new Professor("maria", 20, "teste@email");
        Desafio desafio = new Desafio("Desafio 1", 'V', professor);
        Modulo modulo = new Modulo("teste", professor , new ArrayList<>(), 1);
        modulo.adicionarDesafio(desafio);
        lista.adicionar(modulo);

    }
}