import java.util.ArrayList;

public class ModuloService extends Modulo{

    private ArrayList<Modulo> lista;

    public ModuloService() {
        this.lista = new ArrayList<>();
    }

    public void adicionar(Modulo modulo) {
        lista.add(modulo);
    }

    public void visualizarTodos() {
        for (int i = 0; i < lista.size(); i++) {
            visualizarModulo(lista.get(i), i);
        }
    }

    public void visualizarModulo(Modulo modulo, int index) {
        System.out.printf("""
                _______________Modulo %d______________
                Titulo: %s
                Autor: %s
                Classificação: %s
                """, index, modulo.getTitulo(), modulo.getAutor().getNome(), modulo.getClassificacao());
        modulo.visualizarDesafios();
    }

    public void consultar(int index){
        Modulo modulo = lista.get(index);
        visualizarModulo(modulo, index);
        System.out.println("--------------------------------");
    }

    public void editar(int index, Modulo cadastroEditado) {
        Modulo modulo = lista.get(index);
        modulo.setAutor(cadastroEditado.getAutor());
        modulo.setTitulo(cadastroEditado.getTitulo());
        modulo.setClassificacao(cadastroEditado.getClassificacao());
        modulo.setFoiAprovado(cadastroEditado.isFoiAprovado());
        System.out.println("Modulo atualizado com sucesso.");
        System.out.println("--------------------------------");
    }

    public void remover(int index) {
        lista.remove(index);
    }

    @Override
    public String toString() {
        return "Titulo: "+getTitulo() +" - Autor: "+getAutor()+" - Classificação: "+getClassificacao();
    }
}
