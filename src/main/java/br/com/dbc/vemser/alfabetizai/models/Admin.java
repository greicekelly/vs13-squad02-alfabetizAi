package br.com.dbc.vemser.alfabetizai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {

    @Column(name = "descricao")
    private String descricao;

}
