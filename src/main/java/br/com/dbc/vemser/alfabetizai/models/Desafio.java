package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
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

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "tipo_desafio")
    private TipoDesafio tipo;

    @Column(name = "instrucao")
    private String instrucao;

    @Column(name = "A" )
    private String a;

    @Column(name = "B")
    private String b;

    @Column(name = "C")
    private String c;

    @Column(name = "D")
    private String d;

    @Column(name = "E")
    private String e;

    @Column(name = "alternativa_correta")
    private String alternativaCorreta;

    @Column(name = "pontos")
    private int pontos;

    @Column(name = "ativo",columnDefinition = "CHAR(1) DEFAULT 'S'")
    private String ativo;

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
