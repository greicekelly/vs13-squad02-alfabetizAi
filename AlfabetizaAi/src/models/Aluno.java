package models;

import models.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
public class Aluno extends Usuario {

    private static Integer alunoId = 1;
    private ArrayList<Modulo> modulosConcluidos;

    public Aluno(String nome, LocalDate dataDeNascimento, String email, ArrayList<Modulo> modulosConcluidos) {
        super(nome, dataDeNascimento, email);
        this.modulosConcluidos = modulosConcluidos;
        setId(alunoId);
        alunoId++;
    }

    public Aluno(String nome, LocalDate dataDeNascimento, String email) {
        super(nome, dataDeNascimento, email);
    }

    public boolean concluirModulo(Modulo modulo){
        System.out.println("O módulo " + modulo.getTitulo() + " foi concluído.");
        this.modulosConcluidos.add(modulo);
        return true;
    }

    public ArrayList<Modulo> getModulosConcluidos() {
        return modulosConcluidos;
    }

    public void setModulosConcluidos(ArrayList<Modulo> modulosConcluidos) {
        this.modulosConcluidos = modulosConcluidos;
    }

    public String toString() {
        return "Aluno: " +
                "Id: " + getId() +
                " - Nome: " + getNome() +
                " - Data de Nascimento: " + getDataDeNascimento() +
                " - Email: " + getEmail() + "\'";
    }

}
