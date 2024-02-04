package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity(name = "modulo")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODULO_SEQ")
    @SequenceGenerator(name = "MODULO_SEQ", sequenceName = "SEQ_MODULO", allocationSize = 1)
    private int id;
    private int idProfessor;
    private String titulo;
    private String conteudo;

    private String foiAprovado;
    //private Admin adminAprova;
    private ClassificacaoModulo classificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Professor professor;

}



