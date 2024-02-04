package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "id_modulo", updatable = false, insertable = false)
    private int idModulo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo")
    private TipoDesafio tipoDesafio;

    @Column(name = "instrucao")
    private String instrucao;

    @Column(name = "pontos")
    private int pontos;

    @OneToOne(mappedBy = "desafio", cascade = CascadeType.ALL, orphanRemoval = true)
    private DesafioAlternativas desafioAlternativas;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;
}
