package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity(name = "modulo")
public class Modulo {

    private int id;
    private int idProfessor;
    private String titulo;
    private String conteudo;

    private String foiAprovado;
    private Admin adminAprova;
    private ClassificacaoModulo classificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor", referencedColumnName = "id_professor")
    private Professor autor;

}



