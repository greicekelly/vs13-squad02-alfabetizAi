package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public Page<UsuarioDTO> buscarUsuariosPorNome(Integer paginaSolicitada, Integer tamanhoPagina, String nome) {
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina);
        return usuarioRepository.buscarUsuariosPorNomeDTO(pageable, nome);
    }

    public Page<UsuarioDTO> buscarUsuarios(Integer paginaSolicitada, Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina);
        return usuarioRepository.buscarUsuarios(pageable);
    }

    public Page<UsuarioDTO> buscarUsuariosAtivo(Integer paginaSolicitada, Integer tamanhoPagina, char ativo) {
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina);
        return usuarioRepository.buscarUsuariosAtivosDTO(pageable, String.valueOf(ativo));
    }

    public Optional<Usuario> login(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }
}
