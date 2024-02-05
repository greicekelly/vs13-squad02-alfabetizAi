package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.implement.ProfessorImplementa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity()
@DiscriminatorValue("PROFESSOR")
public class Professor extends Usuario implements ProfessorImplementa {

    @Column(name = "descricao")
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Modulo> modulos;

    @Override
    public Modulo criarModulo(){
        System.out.println("\nConteudo criado com sucesso");
        return new Modulo();
    }
    @Override
    public void modificarModulo(Modulo conteudo) {
        System.out.println("\nConteudo Modificado com sucesso");
    }

}

