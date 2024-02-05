package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "MODULO")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODULO_SEQ")
    @SequenceGenerator(name = "MODULO_SEQ", sequenceName = "SEQ_MODULO", allocationSize = 1)
    @Column(name = "id_modulo" )
    private Integer id;

    @Column(name = "id_professor" )
    private Integer idProfessor;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "modulo_aprovado")
    private String foiAprovado;
    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "classificacao")
    @Enumerated(EnumType.ORDINAL)
    private ClassificacaoModulo classificacao;

    @ManyToOne()
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Professor professor;

    @JsonIgnore
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL)
    private Set<Desafio> desafios;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "aluno_modulo",
            joinColumns = @JoinColumn(name = "id_modulo"),
            inverseJoinColumns = @JoinColumn(name = "id_aluno")
    )
    private Set<Aluno> alunos;

    public boolean isEmpty() {
        return false;
    }
}
