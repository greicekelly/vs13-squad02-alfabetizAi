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
    private int idProfessor;
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
            switch (Character.toUpperCase(foiAprovado)) {
                case 'S':
                    this.foiAprovado = 'S';
                    break;
                case 'N':
                    this.foiAprovado = 'N';
                    break;
                default:
                    this.foiAprovado = null;
                    break;
            }
        }

    }



