package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResponsavelRepository extends JpaRepository<Responsavel, Integer> {

    List<Responsavel> findAllByAtivo(String ativo);
    
}
