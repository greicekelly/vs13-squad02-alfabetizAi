
package br.com.dbc.vemser.alfabetizai.dto.desafio;

import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DesafioDTO {
    private Integer id;
    private String titulo;
    private String conteudo;
    private TipoDesafio tipo;
    private String instrucao;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String alternativaCorreta;
    private Integer pontos;
    private ModuloDTO modulo;
    private String ativo;


}