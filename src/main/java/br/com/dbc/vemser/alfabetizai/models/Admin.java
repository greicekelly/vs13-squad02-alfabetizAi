package br.com.dbc.vemser.alfabetizai.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;
import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {

    @Column(name = "descricao")
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Modulo> modulos;

}
