package br.com.dbc.vemser.alfabetizai.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("RESPONSAVEL")
public class Responsavel extends Usuario{


    @Column(name = "nome_aluno")
    private String nomeAluno;
    @Column(name = "sobrenome_aluno")
    private String sobrenomeAluno;
    @Column(name = "data_nascimento_aluno")
    private LocalDate dataNascimentoAluno;
    @Column(name = "sexo_aluno")
    private String sexoAluno;
    private int pontuacao;

}
