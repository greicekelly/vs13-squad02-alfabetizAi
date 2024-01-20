package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProfessorService {
    private ProfessorRepository professorRepository;

    public ProfessorService() {
        this.professorRepository = new ProfessorRepository();
    }

    public Professor adicionar(Professor professor) {
        try {
            if (professor.getCpf().length() != 11) {
                throw new Exception("CPF Inválido!");
            }

            Professor professorAdicionado = professorRepository.adicionar(professor);

            System.out.println("Professor adicionado com sucesso! " + professorAdicionado);
            return professorAdicionado;
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }

        // Se ocorrer algum erro, retorna null
        return null;
    }


    public List<Professor> visualizarTodos() {
        try {
            List<Professor> listar = professorRepository.listar();
            listar.forEach(System.out::println);
            return listar;
        } catch (BancoDeDadosException e) {

            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public Professor buscarProfessorPorId(Integer idUsuario){
        try {
            return professorRepository.buscarProfessorPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Professor editar(Integer id, Professor professorEditado) {
        try {
            boolean conseguiuEditar = professorRepository.editar(id, professorEditado);
            System.out.println("Professor editado com sucesso? " + conseguiuEditar + " | com id=" + id);

            if (conseguiuEditar) {
                return professorEditado;
            } else {
                return null;
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
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

    public Professor loginProfessor(String email, String senha) {
        try {
            Professor professor = professorRepository.loginProfessor(email, senha);
            return professor;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }
}
