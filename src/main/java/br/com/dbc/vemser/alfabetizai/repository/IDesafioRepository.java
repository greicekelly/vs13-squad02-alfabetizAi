package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Desafio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDesafioRepository extends JpaRepository<Desafio, Integer> {
    @Query(value = """
                SELECT * FROM VS_13_EQUIPE_2.MODULO d WHERE d.aluno.id = :idAluno AND d.desafioConcluido = 'S'
                """, nativeQuery = true)
    List<Desafio> listardesafiosConcluidos(@Param("idAluno") Integer idAluno);

    List<Desafio> findByModuloId(int idModuloEscolhido);
}
