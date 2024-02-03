package br.com.dbc.vemser.alfabetizai.models;


import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Aluno")
public class Aluno  {

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
    private int pontuacao;

}
