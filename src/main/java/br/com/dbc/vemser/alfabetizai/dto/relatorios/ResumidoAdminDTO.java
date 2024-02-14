package br.com.dbc.vemser.alfabetizai.dto.relatorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ResumidoAdminDTO {

    private Integer idUsuario;

    private String nome;

    private String sobrenome;

    private String email;
}
