//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AdminService listaAdmin = new AdminService();
        listaAdmin.adicionar(new Admin("Maria Antonia", 36, "teste@email"));
        listaAdmin.adicionar(new Admin("Joao da Silva", 36, "teste@email"));
        listaAdmin.adicionar(new Admin("Pedro", 36, "teste@email"));

        listaAdmin.visualizarTodos();
        listaAdmin.consultar(1);
        listaAdmin.editar(2, new Admin("Pedro Santos",34, "pedro.santos@email"));
        listaAdmin.visualizarTodos();
        listaAdmin.remover(0);
        listaAdmin.visualizarTodos();
    }
}