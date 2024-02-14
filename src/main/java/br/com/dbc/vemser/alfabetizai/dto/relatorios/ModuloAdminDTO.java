package br.com.dbc.vemser.alfabetizai.dto.relatorios;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ModuloAdminDTO {

    private Integer id;
    private String titulo;
    private String conteudo;
    private String foiAprovado;
    private ClassificacaoModulo classificacao;
    private ResumidoProfessorDTO professor;
}
