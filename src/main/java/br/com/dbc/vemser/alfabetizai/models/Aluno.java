package br.com.dbc.vemser.alfabetizai.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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
    @Column(name = "pontuacao")
    private int pontuacao;
    @Column(name = "ativo")
    private String ativo;

//    @ManyToMany(mappedBy = "alunos")
//    @JsonIgnore
//    private Set<Modulo> modulo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ALUNO_MODULO",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_modulo")
    )
    private Set<Modulo> modulos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ALUNO_DESAFIO",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_desafio")
    )
    private Set<Desafio> desafios;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Responsavel responsavel;

}
