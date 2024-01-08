package models;

import implement.ProfessorImplementa;
import models.Modulo;
import models.Usuario;

import java.time.LocalDate;

public class Professor extends Usuario implements ProfessorImplementa {
    private static Integer professorId = 1;
    public Professor(String nome, LocalDate dataDeNascimento, String email) {
        super(nome, dataDeNascimento, email);
        setId(professorId);
        professorId++;
    }

    @Override
    public Modulo criarModulo(){
        System.out.println("\nConteudo criado com sucesso");
        return new Modulo();
    }
    @Override
    public void modificarModulo(Modulo conteudo) {
        System.out.println("\nConteudo Modificado com sucesso");
    }


    @Override
    public String toString() {
        return "Professor: " +
                "Id: " + getId() +
                " - Nome: " + getNome() +
                " - Data de Nascimento: " + getDataDeNascimento() +
                " - Email: " + getEmail() + "\'";
    }
}

