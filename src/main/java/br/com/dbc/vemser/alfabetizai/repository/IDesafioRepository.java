package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Desafio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDesafioRepository extends JpaRepository<Desafio, Integer> {
}
