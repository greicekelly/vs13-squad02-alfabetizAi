package services;

import exceptions.BancoDeDadosException;
import models.Admin;
import models.Professor;
import repository.ProfessorRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfessorService {
    private ProfessorRepository professorRepository;

    public ProfessorService() {
        this.professorRepository = new ProfessorRepository();
    }

    public void adicionar(Professor professor) {
        try {

            if (professor.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Professor professorAdicionado = professorRepository.adicionar(professor);

            System.out.println("professor adicinado com sucesso! " + professorAdicionado);
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
            List<Professor> listar = professorRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void buscarProfessorPorId(Integer idUsuario){
        try {
            professorRepository.buscarProfessorPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void editar(Integer id, Professor professorEditado) {
        try {
            boolean conseguiuEditar = professorRepository.editar(id, professorEditado);
            System.out.println("professor editado com sucesso? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Integer id, Professor professor) {
        try {
            boolean conseguiuRemover = professorRepository.remover(id, professor);
            System.out.println("pessoa removida? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public boolean loginProfessor(String email, String senha) {
        try {
            return professorRepository.loginProfessor(email, senha);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return false;
        }
    }
}
