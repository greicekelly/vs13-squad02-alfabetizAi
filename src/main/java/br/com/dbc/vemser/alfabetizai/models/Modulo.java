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
    private int id;

    @Column(name = "id_professor" )
    private int idProfessor;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "modulo_aprovado")
    private String foiAprovado;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_id")
    private Admin adminAprova;

    @Column(name = "classificacao")
    @Enumerated(EnumType.ORDINAL)
    private ClassificacaoModulo classificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Professor professor;

    @JsonIgnore
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Desafio> desafios;

    @ManyToMany
    @JoinTable(
            name = "aluno_modulo",
            joinColumns = @JoinColumn(name = "modulo_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private Set<Aluno> alunos;
}
