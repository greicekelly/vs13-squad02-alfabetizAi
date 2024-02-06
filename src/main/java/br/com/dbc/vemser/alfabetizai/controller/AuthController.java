package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.login.LoginDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.security.TokenService;
import br.com.dbc.vemser.alfabetizai.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;

    private final TokenService tokenService;

    @PostMapping
    public String login(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        Optional<Usuario> login = usuarioService.login(loginDTO.getEmail(), loginDTO.getSenha());
        if (login.isPresent()) {
            return tokenService.getToken(login.get());
        } else {
            throw new RegraDeNegocioException("Usuário ou senha inválidos");
        }
    }

}
