package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ModuloDTO {

    private Integer id;
    private Integer idProfessor;
    private String titulo;
    private String conteudo;
    private Character foiAprovado;
    private AutorDTO autor;
    private ClassificacaoModulo classificacao;
}
