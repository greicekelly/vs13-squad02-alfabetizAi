package br.com.dbc.vemser.alfabetizai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "DESAFIO_ALTERNATIVAS")
public class DesafioAlternativas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALTERNATIVAS_SEQ")
    @SequenceGenerator(name = "ALTERNATIVAS_SEQ", sequenceName = "seq_alternativas", allocationSize = 1)
    @Column(name = "id_desafio_alternativas")
    private int idAlternativas;

    @Column(name = "id_desafio", updatable = false, insertable = false)
    private int idDesafio;

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

    @Column(name = "correta")
    private String alternativaCorreta;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_desafio", referencedColumnName = "id_desafio")
    private Desafio desafio;

    public boolean isEmpty() {
        return false;
    }
}
