package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IResponsavelRepository extends JpaRepository<Responsavel, Integer> {

    List<Responsavel> findAllByAtivo(char ativo);

    Responsavel findAllByCpfOrEmail(String cpf, String email);

//    Optional<Responsavel> findByEmailAndSenha(String email, String senha);

}
