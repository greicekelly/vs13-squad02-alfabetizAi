package br.com.dbc.vemser.alfabetizai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_SEQ")
    @SequenceGenerator(name = "ADMIN_SEQ", sequenceName = "SEQ_ADMIN", allocationSize = 1)
    @Column(name = "id_admin")
    private Integer idAluno;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "descricao")
    private String descricao;


}
