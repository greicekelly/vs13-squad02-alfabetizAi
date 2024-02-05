package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.models.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IModuloRepository extends JpaRepository< Modulo, Integer> {

    @Query(value = """
            SELECT * FROM VS_13_EQUIPE_2.MODULO m WHERE UPPER(m.foiAprovado) = 'n'
            """, nativeQuery = true)
            List<Modulo> listarSemAprovacao();

    @Query(value = """
                    SELECT * FROM VS_13_EQUIPE_2.MODULO m WHERE UPPER(m.foiAprovado) = 's'
                    """, nativeQuery = true)
            List<Modulo> listarAprovados();

            @Query(value = """
                    SELECT * FROM VS_13_EQUIPE_2.MODULO m WHERE UPPER(m.foiAprovado) = = 'r'
                    """, nativeQuery = true)
            List<Modulo> listarReprovados();

            @Query(value = """
                    SELECT * FROM VS_13_EQUIPE_2.MODULO m JOIN m.alunos a WHERE a.id = :idAluno AND m.foiAprovado = 'S'
                            """, nativeQuery = true)
                    List<Modulo> listarModulosConcluidos(@Param("idAluno") Integer idAluno);

            @Modifying
            @Query(value = """ 
                  UPDATE VS_13_EQUIPE_2.MODULO m SET m.adminAprova.idUsuario = :idAdmin, m.foiAprovado = :aprovacaoModulo WHERE m.id = :idModulo
                  """, nativeQuery = true)
                  boolean editarAprovacaoPorAdmin(@Param("idAdmin") Integer idAdmin, @Param("idModulo") Integer idModulo, @Param("aprovacaoModulo") String aprovacaoModulo);

            List<Modulo> findAllByIdProfessor(int idProfessor);
}
