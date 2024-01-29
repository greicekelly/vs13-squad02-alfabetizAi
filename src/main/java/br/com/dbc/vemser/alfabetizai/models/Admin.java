package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.implement.AdminImplementa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Admin extends Usuario implements AdminImplementa {

    private Integer idAdmin;

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
