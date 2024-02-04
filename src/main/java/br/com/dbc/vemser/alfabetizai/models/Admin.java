package br.com.dbc.vemser.alfabetizai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import javax.persistence.*;



@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "adminAprova", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Modulo> modulos;

}
