package br.com.dbc.vemser.alfabetizai.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity(name = "ALUNO")
public class Aluno extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALUNO_SEQ")
    @SequenceGenerator(name = "ALUNO_SEQ", sequenceName = "seq_aluno", allocationSize = 1)
    @Column(name = "id_aluno")
    private Integer idAluno;
    @Column(name = "nome_aluno")
    private String nomeAluno;
    @Column(name = "sobrenome_aluno")
    private String sobrenomeAluno;
    @Column(name = "data_nascimento_aluno")
    private LocalDate dataNascimentoAluno;
    @Column(name = "sexo_aluno")
    private String sexoAluno;
    @Column(name = "pontuacao")
    private int pontuacao;
}
