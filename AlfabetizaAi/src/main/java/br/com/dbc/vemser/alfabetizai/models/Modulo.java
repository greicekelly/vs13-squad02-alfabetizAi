package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import lombok.*;

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

    public void ExibirConteudo(){
        System.out.println("Exibindo conteúdo do módulo.");
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
