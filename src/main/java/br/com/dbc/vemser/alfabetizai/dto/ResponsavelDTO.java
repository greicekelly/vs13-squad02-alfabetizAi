package br.com.dbc.vemser.alfabetizai.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelDTO {
    private Integer idUsuario;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String email;

    private LocalDate dataDeNascimento;

    private String ativo;

    private String sexo;

    private String cpf;

}
