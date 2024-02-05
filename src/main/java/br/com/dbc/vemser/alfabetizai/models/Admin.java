package br.com.dbc.vemser.alfabetizai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import javax.persistence.*;


@ToString
@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Modulo> modulos;

}
