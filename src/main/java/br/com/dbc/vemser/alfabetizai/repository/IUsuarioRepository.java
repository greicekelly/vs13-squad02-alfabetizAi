package br.com.dbc.vemser.alfabetizai.repository;


import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

//    @Query(value = "        SELECT u.ID_USUARIO , u.NOME , u.ATIVO , u.CPF  FROM USUARIO u " +
//            "        JOIN ADMIN a ON u.ID_USUARIO = a.ID_USUARIO " +
//            "        UNION ALL " +
//            "        SELECT u.ID_USUARIO ,u.NOME, u.ATIVO , u.CPF , u.DATA_NASCIMENTO FROM USUARIO u " +
//            "        JOIN PROFESSOR p ON u.ID_USUARIO = p.ID_USUARIO " +
//            "        UNION ALL " +
//            "        SELECT u.ID_USUARIO ,u.NOME, u.ATIVO , u.CPF , u.DATA_NASCIMENTO FROM USUARIO u        " +
//            "        JOIN RESPONSAVEL r ON u.ID_USUARIO = r.ID_USUARIO ", nativeQuery = true)
//    public List<Usuario> buscarUsuarios();

//    @Query(
//            value = "SELECT * FROM USUARIO ORDER BY id",
//            countQuery = "SELECT count(*) FROM Users",
//            nativeQuery = true)
//    Page<Usuario> findAllUsersWithPagination(Pageable pageable);

    @Query(value = "select * " +
            "         from USUARIO",

            countQuery = "select count(*) " +
                    "         from USUARIO "
                          , nativeQuery = true)
    Page<Usuario> getPorQualquerNomePaginadoNativo(String nome, Pageable pageable);

//    @Query(value = "select * from USUARIO u where upper(u.nome) like upper(:nome)")
//    public Page<Usuario> getPorQualquerNomeJPQL(String nome, Pageable pageable);

    @Query(
            """
                    SELECT new br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO(
                        p.idUsuario,  p.nome,  p.ativo)
                    FROM USUARIO p
                    """
    )
    Page<UsuarioDTO> procurarUsuariosDTO(Pageable pageable);
}
