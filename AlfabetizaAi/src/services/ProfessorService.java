package services;

import models.Admin;
import models.Professor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfessorService {
    private ArrayList<Professor> professores;

    public ProfessorService() {
        this.professores = new ArrayList<>();
    }

    public void adicionar(Professor professor) {
        professores.add(professor);
    }

    public ArrayList<Professor> listarTodos() {
        return professores;
    }

    public Professor loginProfessor(String email, LocalDate dataNascimento) {
        for (Professor professor : professores) {
            professor.getDataDeNascimento();
            if (professor.getEmail().equals(email) && professor.getDataDeNascimento().equals(dataNascimento)) {
                return professor;
            }
        }
        return null;
    }

    public Professor consultarProfessorEmail(String email) {
        for (Professor professor : professores) {
            if(professor.getEmail().equals(email)){
                return professor;
            }
        }
        return null;
    }

    public void visualizarTodos() {
        if (professores.isEmpty()) {
            throw new IllegalStateException("Nenhum professor cadastrado.");
        } else {
            for (Professor professor : professores) {
                System.out.println(professor);
            }
            System.out.println("--------------------------------");
        }
    }

    public void consultar(int id){
        for (Professor professor : professores) {
            if(professor.getId() == id){
                System.out.printf("""
                Id: %d
                Nome: %s
                Data de nascimento: %s
                Email: %s
                """, professor.getId(), professor.getNome(), professor.getDataDeNascimento(), professor.getEmail());
                System.out.println("--------------------------------");
                return;
            }
        }
        System.out.println("Nenhum professor com o ID informado.");
        System.out.println("--------------------------------");
    }

    public void editar(Professor professor, Professor professorEditado) {
        professor.setNome(professorEditado.getNome());
        professor.setDataDeNascimento(professorEditado.getDataDeNascimento());
        professor.setEmail(professorEditado.getEmail());
    }

    public void remover(int id) {
        for (Professor professor : professores) {
            if(professor.getId() == id){
                professores.remove(professor);
                return;
            }
        }
        System.out.println("Nenhum professor com o ID informado.");
        System.out.println("--------------------------------");
    }
}
