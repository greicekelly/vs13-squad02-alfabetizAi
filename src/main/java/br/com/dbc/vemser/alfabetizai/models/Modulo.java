package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Modulo {

    private int id;
    private int idProfessor;
    private String titulo;
    private String conteudo;
    private Professor autor;
    private String foiAprovado;
    private Admin adminAprova;
    private ClassificacaoModulo classificacao;


}



