package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private AlunoRepository alunoRepository;

    public AlunoService() {
        alunoRepository = new AlunoRepository();
    }

    public void adicionar(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new IllegalArgumentException("Por favor insira um nome.");
        }

        try {
            alunoRepository.adicionar(aluno);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> visualizarTodosAlunos() {
        try {
            List<Aluno> alunos = alunoRepository.listar();
            if (alunos.isEmpty()) {
                throw new IllegalStateException("Nenhum aluno cadastrado.");
            } else {
                return alunos;
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void BuscarAlunoPorId(Integer idUsuario){
        try {
            alunoRepository.BuscarAlunoPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void consultar(int id) {
        try {
            List<Aluno> alunos = alunoRepository.BuscarAlunoPorId(id);
            if (!alunos.isEmpty()) {
                Aluno aluno = alunos.get(0);
                System.out.printf("""
                        Id: %d
                        Nome: %s
                        Data de nascimento: %s
                        Email: %s
                        """, aluno.getIdUsuario(), aluno.getNome(), aluno.getDataDeNascimento(), aluno.getEmail());
                System.out.println("--------------------------------");
            } else {
                System.out.println("Nenhum aluno com o ID informado.");
                System.out.println("--------------------------------");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public Aluno loginAluno(String email, String senha) {
        try {
            Aluno aluno = alunoRepository.loginAluno(email, senha);
            return aluno;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void editar(Aluno aluno, Aluno alunoEditado) {
        try {
            alunoRepository.editar(aluno.getIdUsuario(), alunoEditado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try {
            alunoRepository.remover(id, new Aluno());
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
