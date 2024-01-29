package br.com.dbc.vemser.alfabetizai.dto;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ModuloCreateDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String conteudo;
    @NotNull
    private Integer idProfessor;
    @NotNull
    private ClassificacaoModulo classificacao;
}
