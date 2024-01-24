package br.com.dbc.vemser.alfabetizai.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {

    private Integer idProfessor;

    private Integer idUsuario;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String email;

    private String descricao;
}
