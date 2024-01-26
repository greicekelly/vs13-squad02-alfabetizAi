package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Modulo {

    private int id;
    private String titulo;
    private String conteudo;
    private Professor autor;
    private Character foiAprovado;
    private Admin adminAprova;
    private ClassificacaoModulo classificacao;
    private List<Desafio> desafios;

    public void ExibirConteudo(){
        System.out.println("Exibindo conteúdo do módulo.");
    }

    public Character isFoiAprovado() {
        return foiAprovado;
    }

    public void setFoiAprovado(Character foiAprovado) {

        switch (foiAprovado){
            case 'S':
                this.foiAprovado = foiAprovado;
                break;
            case 's':
                this.foiAprovado = foiAprovado;
                break;
            case 'N':
                this.foiAprovado = foiAprovado;
                break;
            case 'n':
                this.foiAprovado = foiAprovado;
                break;
            default:
                this.foiAprovado = null;
                break;
        }
    }
}
