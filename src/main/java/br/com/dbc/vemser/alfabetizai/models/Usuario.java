package br.com.dbc.vemser.alfabetizai.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
@Data
@Entity(name = "USUARIO")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "seq_usuario", allocationSize = 1)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobrenome")
    private String sobrenome;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataDeNascimento;
    @Column(name = "ativo")
    private String ativo;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "senha")
    private String senha;
    @Column(name = "cpf")
    private String cpf;
}
