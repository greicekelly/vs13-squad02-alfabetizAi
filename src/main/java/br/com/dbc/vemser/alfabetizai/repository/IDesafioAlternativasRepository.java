package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.DesafioAlternativas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDesafioAlternativasRepository extends JpaRepository<DesafioAlternativas, Integer> {
}
