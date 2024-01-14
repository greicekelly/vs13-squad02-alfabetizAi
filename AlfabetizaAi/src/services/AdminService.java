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

    private  AdminRepository adminRepository;

    public AdminService() {
        adminRepository = new AdminRepository();
    }

    public void adicionar(Admin admin) {
        try {

            if (admin.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Admin adminAdicionado = adminRepository.adicionar(admin);

            System.out.println("admin adicinado com sucesso! " + adminAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void visualizarTodos() {
        try {
            List<Admin> listar = adminRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void BuscarAdminPorId(Integer idUsuario){
        try {
            adminRepository.BuscarAdminPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void editar(Integer id, Admin adminEditado) {
        try {
            boolean conseguiuEditar = adminRepository.editar(id, adminEditado);
            System.out.println("admin editado com sucesso? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Integer id, Admin admin) {
        try {
            boolean conseguiuRemover = adminRepository.remover(id, admin);
            System.out.println("pessoa removida? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public boolean loginAdmin(String email, String senha) {
        try {
            return adminRepository.LoginAdmin(email, senha);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        }
    }
}
