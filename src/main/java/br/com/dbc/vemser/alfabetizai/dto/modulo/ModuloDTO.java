package br.com.dbc.vemser.alfabetizai.dto.modulo;

import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminModuloDTO;
import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
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
    private String foiAprovado;
    private AdminModuloDTO admin;
    private ProfessorDTO professor;
    private ClassificacaoModulo classificacao;
}
