package br.com.dbc.vemser.alfabetizai.security;

import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioService.login(username);
        return usuarioOptional
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inválido"));
    }
}
