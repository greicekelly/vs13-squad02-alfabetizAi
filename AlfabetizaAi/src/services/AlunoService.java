package services;

import models.Admin;
import models.Aluno;
import models.Professor;

import java.time.LocalDate;
import java.util.ArrayList;

public class AlunoService {
    private ArrayList<Aluno> lista;

    public AlunoService() {
        this.lista = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        if(aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new IllegalArgumentException("Por favor insira um nome.");
        }
        lista.add(aluno);
    }

    public void visualizarTodosAlunos() {
        if (lista.isEmpty()) {
            throw new IllegalStateException("Nenhum aluno cadastrado.");
        } else{
            for (Aluno aluno : lista) {
                System.out.println(aluno);
            }
            System.out.println("--------------------------------");
        }

    }

    public void consultar(int id){
        for (Aluno aluno : lista) {
            if(aluno.getId() == id){
                System.out.printf("""
                Id: %d
                Nome: %s
                Data de nascimento: %s
                Email: %s
                """, aluno.getId(), aluno.getNome(),aluno.getDataDeNascimento(), aluno.getEmail());
                System.out.println("--------------------------------");
                return;
            }
        }
        System.out.println("Nenhum aluno com o ID informado.");
        System.out.println("--------------------------------");
    }

    public Aluno consultarAlunoEmail(String email) {
        for (Aluno aluno : lista) {
            if(aluno.getEmail().equals(email)){
               return aluno;
            }
        }
        return null;
    }

    public Aluno loginAluno(String email, LocalDate dataNascimento) {
        for (Aluno aluno : lista) {
            aluno.getDataDeNascimento();
            if (aluno.getEmail().equals(email) && aluno.getDataDeNascimento().equals(dataNascimento)) {
                return aluno;
            }
        }
        return null;
    }


    public void editarAluno(Aluno aluno, Aluno alunoEditado) {
        try{
            aluno.setNome((alunoEditado.getNome()));
            aluno.setEmail((alunoEditado.getEmail()));
            aluno.setDataDeNascimento((alunoEditado.getDataDeNascimento()));
        } catch (IndexOutOfBoundsException e){
            System.err.println("erro ao editar  aluno.");
        }

    }

    public void remover(int id) {
        for (Aluno aluno :lista) {
            if(aluno.getId() == id){
                lista.remove(aluno);
                return;
            }
        }
        System.out.println("Nenhum aluno com o ID informado.");
        System.out.println("--------------------------------");
    }

}
