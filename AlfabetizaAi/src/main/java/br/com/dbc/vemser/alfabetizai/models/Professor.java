package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.implement.ProfessorImplementa;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Professor extends Usuario implements ProfessorImplementa {

    @Id
    private Integer idProfessor;

    @NotBlank
    @Size(max = 255)
    private String descricao;

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

