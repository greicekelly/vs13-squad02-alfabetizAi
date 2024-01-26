package br.com.dbc.vemser.alfabetizai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AutorDTO {
    private Integer idProfessor;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String descricao;
}
