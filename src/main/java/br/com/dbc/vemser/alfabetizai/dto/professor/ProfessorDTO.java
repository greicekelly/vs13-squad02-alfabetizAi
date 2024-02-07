package br.com.dbc.vemser.alfabetizai.dto.professor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {

    private Integer idUsuario;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String email;

    private String descricao;

    private String ativo;
}
