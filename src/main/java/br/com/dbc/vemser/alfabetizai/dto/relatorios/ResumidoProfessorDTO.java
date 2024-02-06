package br.com.dbc.vemser.alfabetizai.dto.relatorios;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ResumidoProfessorDTO {

    private Integer idUsuario;

    private String nome;

    private String sobrenome;

    private String email;
}
