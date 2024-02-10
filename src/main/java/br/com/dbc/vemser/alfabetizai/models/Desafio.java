package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DESAFIO")
public class Desafio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESAFIO_SEQ")
    @SequenceGenerator(name = "DESAFIO_SEQ", sequenceName = "seq_desafio", allocationSize = 1)
    @Column(name = "id_desafio")
    private int id;

//    @Column(name = "id_modulo", updatable = false, insertable = false)
//    private int idModulo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "tipo_desafio")
    private TipoDesafio tipo;

    @Column(name = "instrucao")
    private String instrucao;

    @Column(name = "pontos")
    private int pontos;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_desafio_alternativas", referencedColumnName = "id_desafio_alternativas")
    private DesafioAlternativas desafioAlternativas;


    @ManyToOne()
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    @JsonIgnore
    @ManyToMany(mappedBy = "desafios")
    private Set<Aluno> alunos;

    public boolean isEmpty() {
        return false;
    }
}
