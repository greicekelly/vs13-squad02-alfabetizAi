package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Modulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Repository
public interface IModuloRepository extends JpaRepository< Modulo, Integer> {

    @Query(value = "SELECT m FROM MODULO m WHERE UPPER(m.foiAprovado) = :aprovacao")
    List<Modulo> findAllByFoiAprovado(String aprovacao);

    @Query(value = """
        SELECT * FROM MODULO m JOIN m.alunos a WHERE a.id = :idAluno AND m.foiAprovado = 'S'
        """, nativeQuery = true)
    List<Modulo> listarModulosConcluidos(@Param("idAluno") Integer idAluno);

    Page<Modulo> findAllByIdProfessor(Integer idProfessor, Pageable pageable);

    @Query(value = "SELECT m FROM MODULO m WHERE m.idProfessor = :idProfessor AND UPPER(m.foiAprovado) = :aprovacao")
    Page<Modulo> findAllProfessorComFiltro(Integer idProfessor, String aprovacao, Pageable pageable);

    @Query(value = "SELECT * FROM MODULO m INNER JOIN USUARIO u ON u.ID_USUARIO = m.ADMIN_ID WHERE u.ID_USUARIO = :idAdmin", nativeQuery = true)
    Page<Modulo> findAllByAdmin(Integer idAdmin, Pageable pageable);
}
