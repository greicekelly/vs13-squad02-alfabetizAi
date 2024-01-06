import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class AdminService {

    private ArrayList<Admin> lista;

    public AdminService() {
        this.lista = new ArrayList<>();
    }

    public void adicionar(Admin admin) {
        lista.add(admin);
    }


    public void visualizarTodos() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Id: " + i+" - "+lista.get(i));
        }
        System.out.println("--------------------------------");
    }

    public void consultar(int index){
        Admin admin = lista.get(index);
        System.out.printf("""
                Id: %d
                Nome: %s
                Idade: %d
                Email: %s
                """, index, admin.getNome(), admin.getDataDeNascimento(), admin.getEmail());
        System.out.println("--------------------------------");

    }

    public Admin consultarAdminEmail(String email) {
        for (Admin admin : lista) {
            if(admin.getEmail().equals(email)){
                return admin;
            }
        }
        return null;
    }

    public void editar(Admin adminASerEditado, Admin cadastroEditado) {
        adminASerEditado.setNome(cadastroEditado.getNome());
        adminASerEditado.setDataDeNascimento(cadastroEditado.getDataDeNascimento());
        adminASerEditado.setEmail(cadastroEditado.getEmail());
        System.out.println("Cadastro atualizado com sucesso.");
        System.out.println("--------------------------------");
    }

    public void remover(int index) {
        lista.remove(index);
        System.out.println("--------------------------------");
    }

    public Admin loginAdmin(String email, LocalDate dataNascimento) {
        for (Admin admin : lista) {
            if (admin.getEmail().equals(email) && admin.getDataDeNascimento().equals(dataNascimento)) {
                return admin;
            }
        }
        return null;
    }
}
