package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<List<Aluno>> findAllByResponsavel(Responsavel responsavel);

//    @Query(value = "SELECT * FROM ALUNO a " +
//            "JOIN RESPONSAVEL r ON r.ID_USUARIO = a.ID_USUARIO " +
//            "WHERE a.ID_USUARIO = :idUsuario", nativeQuery = true)
//    public Set<A> buscarPessoaCompletaPorId(@Param("idUsuario") Integer id);
}
