package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.implement.AdminImplementa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario implements AdminImplementa {

    private String descricao;

    public Admin() {}

    @Override
    public boolean aprovarModulo(Modulo conteudo) {
        return false;
    }

    @Override
    public boolean deletarModulo(Modulo conteudo) {
        return false;
    }
}
