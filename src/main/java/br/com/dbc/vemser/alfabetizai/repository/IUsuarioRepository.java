package br.com.dbc.vemser.alfabetizai.repository;


import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    @Query(
            """
                    SELECT new br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO(
                        p.idUsuario,  p.nome, p.sobrenome, p.telefone, p.email, p.dataDeNascimento, p.ativo, p.sexo, p.cpf, p.descricao)
                    FROM Usuario p
                    WHERE p.nome like %:nome%
                    """
    )
    Page<UsuarioDTO> buscarUsuariosPorNomeDTO(Pageable pageable, String nome);

    @Query(
            """
                    SELECT new br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO(
                        p.idUsuario,  p.nome, p.sobrenome, p.telefone, p.email, p.dataDeNascimento, p.ativo, p.sexo, p.cpf, p.descricao)
                    FROM Usuario p
                    """
    )
    Page<UsuarioDTO> buscarUsuarios(Pageable pageable);

    @Query(
            """
                    SELECT new br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO(
                        p.idUsuario,  p.nome, p.sobrenome, p.telefone, p.email, p.dataDeNascimento, p.ativo, p.sexo, p.cpf, p.descricao)
                    FROM Usuario p
                    WHERE p.ativo = :ativo
                    """
    )
    Page<UsuarioDTO> buscarUsuariosAtivosDTO(Pageable pageable, String ativo);

    Optional<Usuario> findById(Integer idUsuario);
}

