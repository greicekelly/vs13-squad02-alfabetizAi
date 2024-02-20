package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

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

    public Optional<Usuario> login(String email) {
        return usuarioRepository.findByEmailAndAtivo(email, "S");
    }
    public Optional<Usuario> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<Usuario> getLoggedUser() {
        return findById(getIdLoggedUser());
    }
    public Integer getIdLoggedUser() {
        Integer findUserId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return findUserId;
    }

    public String alterarSenha(String senhaAtual, String novaSenha, String confirmacaoSenha) throws Exception {

        Optional<Usuario> optionalUsuario = getLoggedUser();
        Usuario usuario = optionalUsuario.get();

        if (passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            if(novaSenha.equals(confirmacaoSenha)){
                String novaSenhaCripto = passwordEncoder.encode(novaSenha);
                usuario.setSenha(novaSenhaCripto);
                usuarioRepository.save(usuario);
                return "Senha Alterada com sucesso!";
            }else{
                throw new RegraDeNegocioException("A confirmação de senha não é igual a senha.");
            }
        } else {
            throw new RegraDeNegocioException("Senha atual não confere.");
        }
    }

}