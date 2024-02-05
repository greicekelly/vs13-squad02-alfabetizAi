package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, Integer> {
    List<Professor> findAllByAtivo(char ativo);

    Professor findAllByCpfOrEmail(String cpf, String email);
}
