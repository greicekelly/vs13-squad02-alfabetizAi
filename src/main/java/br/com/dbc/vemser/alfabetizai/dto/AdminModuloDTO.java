package br.com.dbc.vemser.alfabetizai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminModuloDTO {
    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String ativo;
    private String descricao;
}
