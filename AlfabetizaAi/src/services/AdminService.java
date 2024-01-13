package services;

import exceptions.BancoDeDadosException;
import models.Admin;
import models.Professor;
import repository.AdminRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        if (lista.isEmpty()) {
            throw new IllegalStateException("Nenhum administrador cadastrado.");
        } else {
            for (Admin admin : lista) {
                System.out.println(admin);
            }
            System.out.println("--------------------------------");
        }
    }

    public void BuscarAdminPorId(Integer idUsuario){
        try {
            AdminRepository adminRepository = new AdminRepository();
            adminRepository.BuscarAdminPorId(2);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        System.out.println("Nenhum administrador com o ID informado.");
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

    public void remover(int id) {
        for (Admin admin : lista) {
            if(admin.getId() == id){
                lista.remove(admin);
                return;
            }
        }
        System.out.println("Nenhum administrador com o ID informado.");
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
