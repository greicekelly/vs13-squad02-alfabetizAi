package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.models.DesafioAlternativas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DesafioDTO {
    private int id;
    private int idModulo;
    private String titulo;
    private String conteudo;
    private TipoDesafio tipoDesafio;
    private String instrucao;
    private DesafioAlternativas desafioAlternativas;
    private int pontos;
}

//CODIGOS ALTERADOS
//    private List<String> alternativas;
//    private String alternativaCorreta;