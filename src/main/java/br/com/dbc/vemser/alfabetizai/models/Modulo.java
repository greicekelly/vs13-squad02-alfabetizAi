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

    @Column(name = "ativo",columnDefinition = "CHAR(1) DEFAULT 'S'")
    private String ativo;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id_usuario")
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
    @ManyToMany(mappedBy = "modulos")
    private Set<Aluno> alunos;


    public boolean isEmpty() {
        return false;
    }
}
