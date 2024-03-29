package br.com.dbc.vemser.alfabetizai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

  private Integer idUsuario;

  private String nome;

  private String sobrenome;

  private String telefone;

  private String email;

  private LocalDate dataDeNascimento;

  private String ativo;

  private String sexo;

  private String cpf;

  private String descricao;

}