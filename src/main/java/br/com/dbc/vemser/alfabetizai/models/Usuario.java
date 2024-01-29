package br.com.dbc.vemser.alfabetizai.models;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class Usuario {

    private Integer idUsuario;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String email;

    private LocalDate dataDeNascimento;

    private String ativo;

    private String sexo;

    private String senha;

    private String cpf;
}
