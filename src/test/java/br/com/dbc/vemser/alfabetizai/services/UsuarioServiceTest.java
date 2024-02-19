package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.repository.IUsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Random;

import static br.com.dbc.vemser.alfabetizai.services.Mock.retornarUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UsuarioService - Teste")
class UsuarioServiceTest {
    @Mock
    private IUsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deveria logar com sucesso")
    public void deverialogarComSucesso() {
        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
        String ativo = "S";

        when(usuarioRepository.findByEmailAndAtivo(usuarioOptional.get().getEmail(), ativo)).thenReturn(usuarioOptional);

        Optional<Usuario> usuarioRetornado = usuarioService.login(usuarioOptional.get().getEmail());

        assertEquals(usuarioRetornado.get(), usuarioOptional.get());
    }

    @Test
    @DisplayName("Deveria buscar por id com sucesso")
    public void deveriaBuscarPorIdComSucesso() {
        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
        int idAleatorio = new Random().nextInt();

        when(usuarioRepository.findById(idAleatorio)).thenReturn(usuarioOptional);

        Optional<Usuario> usuarioRetornado = usuarioService.findById(idAleatorio);

        assertEquals(usuarioRetornado.get(), usuarioOptional.get());
    }

    @Test
    @DisplayName("Deveria alterar senha com sucesso")
    public void deveriaAlterarSenhaComSucesso() throws Exception {
        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());

        String mensagem = "Senha Alterada com sucesso!";
        String senha = "teste";

        doReturn(usuarioOptional).when(usuarioService).getLoggedUser();
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn(senha);

        String mensagemRetornada = usuarioService.alterarSenha(senha, senha, senha);

        assertEquals(mensagemRetornada,mensagem);
    }

    @Test
    @DisplayName("Deveria retornar exception ao errar a senha")
    public void deveriaRetornarExceptionAoErrarASenha() throws Exception {
        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
        String senha = "teste";

        doReturn(usuarioOptional).when(usuarioService).getLoggedUser();
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.alterarSenha(senha,"teste2", senha));
    }

    @Test
    @DisplayName("Deveria retornar exception ao errar a confirmacao de senha")
    public void deveriaRetornarExceptionAoErrarAConfirmacaoDeSenha() throws Exception {
        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
        String senha = "teste";

        doReturn(usuarioOptional).when(usuarioService).getLoggedUser();
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.alterarSenha(senha,"teste2", "1234"));
    }

}