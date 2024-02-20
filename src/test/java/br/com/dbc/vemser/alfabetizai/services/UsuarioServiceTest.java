package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static br.com.dbc.vemser.alfabetizai.services.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@DisplayName("UsuarioService - Teste")
class UsuarioServiceTest {
//    @Mock
//    private IUsuarioRepository usuarioRepository;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Spy
//    @InjectMocks
//    private UsuarioService usuarioService;
//
//    @Test
//    @DisplayName("Deveria logar com sucesso")
//    public void deverialogarComSucesso() {
//        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
//        String ativo = "S";
//
//        when(usuarioRepository.findByEmailAndAtivo(usuarioOptional.get().getEmail(), ativo)).thenReturn(usuarioOptional);
//
//        Optional<Usuario> usuarioRetornado = usuarioService.login(usuarioOptional.get().getEmail());
//
//        assertEquals(usuarioRetornado.get(), usuarioOptional.get());
//    }
//
//    @Test
//    @DisplayName("Deveria buscar por id com sucesso")
//    public void deveriaBuscarPorIdComSucesso() {
//        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
//        int idAleatorio = new Random().nextInt();
//
//        when(usuarioRepository.findById(idAleatorio)).thenReturn(usuarioOptional);
//
//        Optional<Usuario> usuarioRetornado = usuarioService.findById(idAleatorio);
//
//        assertEquals(usuarioRetornado.get(), usuarioOptional.get());
//    }
//
//    @Test
//    @DisplayName("Deveria alterar senha com sucesso")
//    public void deveriaAlterarSenhaComSucesso() throws Exception {
//        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
//
//        String mensagem = "Senha Alterada com sucesso!";
//        String senha = "teste";
//
//        doReturn(usuarioOptional).when(usuarioService).getLoggedUser();
//        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
//        when(passwordEncoder.encode(anyString())).thenReturn(senha);
//
//        String mensagemRetornada = usuarioService.alterarSenha(senha, senha, senha);
//
//        assertEquals(mensagemRetornada,mensagem);
//    }
//
//    @Test
//    @DisplayName("Deveria buscar usuario logado com sucesso")
//    public void deveriaBuscarUsuarioLogadoComSucesso() {
//        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
//        Authentication auth = mock(Authentication.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//
//        when(securityContext.getAuthentication()).thenReturn((auth));
//        when(auth.getPrincipal()).thenReturn("1");
//        when(usuarioRepository.findById(1)).thenReturn(usuarioOptional);
//
//        Optional<Usuario> usuarioRetornado = usuarioService.getLoggedUser();
//
//        assertNotNull(usuarioRetornado);
//        assertEquals(usuarioRetornado, usuarioOptional);
//    }
//
//    @Test
//    @DisplayName("Deveria retornar exception ao errar a senha")
//    public void deveriaRetornarExceptionAoErrarASenha() throws Exception {
//        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
//        String senha = "teste";
//
//        doReturn(usuarioOptional).when(usuarioService).getLoggedUser();
//        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
//
//        assertThrows(RegraDeNegocioException.class, () -> usuarioService.alterarSenha(senha,"teste2", senha));
//    }
//
//    @Test
//    @DisplayName("Deveria retornar exception ao errar a confirmacao de senha")
//    public void deveriaRetornarExceptionAoErrarAConfirmacaoDeSenha() throws Exception {
//        Optional<Usuario> usuarioOptional = Optional.of(retornarUsuario());
//        String senha = "teste";
//
//        doReturn(usuarioOptional).when(usuarioService).getLoggedUser();
//        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
//
//        assertThrows(RegraDeNegocioException.class, () -> usuarioService.alterarSenha(senha,"teste2", "1234"));
//    }
//
//
//    @Test
//    @DisplayName("Deveria retornar usuario por nome com sucesso")
//    void deveriaBuscarUsuariosPorNomeComSucesso() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<UsuarioDTO> UsuariosMock = criarPageUsuariosMock(pageable);
//
//        when(usuarioRepository.buscarUsuariosPorNomeDTO(pageable,"")).thenReturn(UsuariosMock);
//
//        Page<UsuarioDTO> usuarioDTOPageRetornado = usuarioService.buscarUsuariosPorNome(0,10,"");
//
//        assertNotNull(usuarioDTOPageRetornado);
//        assertEquals(2, usuarioDTOPageRetornado.getContent().size());
//    }
//
//    @Test
//    @DisplayName("Deveria retornar usuario com sucesso")
//    void deveriaBuscarUsuariosComSucesso() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<UsuarioDTO> UsuariosMock = criarPageUsuariosMock(pageable);
//
//        when(usuarioRepository.buscarUsuarios(pageable)).thenReturn(UsuariosMock);
//
//        Page<UsuarioDTO> usuarioDTOPageRetornado = usuarioService.buscarUsuarios(0,10);
//
//        assertNotNull(usuarioDTOPageRetornado);
//        assertEquals(2, usuarioDTOPageRetornado.getContent().size());
//    }
//
//    @Test
//    @DisplayName("Deveria retornar usuarios ativos com sucesso")
//    void deveriaBuscarUsuariosAtivosComSucesso() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<UsuarioDTO> UsuariosMock = criarPageUsuariosMock(pageable);
//
//        when(usuarioRepository.buscarUsuariosAtivosDTO(pageable,"S")).thenReturn(UsuariosMock);
//
//        Page<UsuarioDTO> usuarioDTOPageRetornado = usuarioService.buscarUsuariosAtivo(0,10,'S');
//
//        assertNotNull(usuarioDTOPageRetornado);
//        assertEquals(2, usuarioDTOPageRetornado.getContent().size());
//    }
//
//    public static Page<UsuarioDTO> criarPageUsuariosMock(Pageable pageable) {
//
//        List<UsuarioDTO> Usuarios = new ArrayList<>();
//        Usuarios.add(retornarUsuarioDTO());
//        Usuarios.add(retornarUsuarioDTO());
//
//        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), Usuarios.size());
//        return new PageImpl<>(Usuarios.subList(start, end), pageable, Usuarios.size());
//    }

}