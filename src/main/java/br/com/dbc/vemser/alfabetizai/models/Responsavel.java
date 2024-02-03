package br.com.dbc.vemser.alfabetizai.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("RESPONSAVEL")
public class Responsavel extends Usuario{

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos;

}
