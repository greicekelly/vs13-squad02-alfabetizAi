package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelComAlunosDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IResponsavelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static br.com.dbc.vemser.alfabetizai.services.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ResponsavelService - Teste")
class ResponsavelServiceTest {

    @Mock
    private IResponsavelRepository responsavelRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private  EmailService emailService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ResponsavelService responsavelService;

    @Test
    @DisplayName("Deveria listar todos responsaveis com sucesso")
    public void deveriaListarTodosResponsaveis() throws RegraDeNegocioException {
        List<Responsavel> listaMock = List.of(retornarResponsavel(), retornarResponsavel(), retornarResponsavel());
        when(responsavelRepository.findAll()).thenReturn(listaMock);
        List<ResponsavelDTO> listaRetornoResponsavelDTO = responsavelService.listar();

        assertNotNull(listaRetornoResponsavelDTO);
        assertEquals(listaMock.size(), listaRetornoResponsavelDTO.size());
    }

    @Test
    @DisplayName("Deveria listar todos responsaveis ativos com sucesso")
    public void deveriaListarTodosResponsaveisAtivos()  {
        char ativo = 'S';
        List<Responsavel> listaMock = List.of(retornarResponsavel(), retornarResponsavel(), retornarResponsavel());
        when(responsavelRepository.findAllByAtivo(ativo)).thenReturn(listaMock);
        List<ResponsavelDTO> listaRetornoResponsavelDTO = responsavelService.listarAtivos(ativo);

        assertNotNull(listaRetornoResponsavelDTO);
        assertEquals(listaMock.size(), listaRetornoResponsavelDTO.size());
    }
    @Test
    @DisplayName("Deveria criar responsavel com sucesso")
    public void deveriaCriarResponsavelComSucesso()throws Exception{

        ResponsavelCreateDTO responsavelCreateDTO = retornarResponsavelCreateDTO();
        Responsavel responsavel = retornarResponsavel();
        ResponsavelDTO responsavelDTO = retornarResponsavelDTO();

        when(objectMapper.convertValue(responsavelCreateDTO, Responsavel.class)).thenReturn(responsavel);
        when(passwordEncoder.encode(anyString())).thenReturn("senha");
        when(responsavelRepository.save(any())).thenReturn(responsavel);
        when(objectMapper.convertValue(responsavel, ResponsavelDTO.class)).thenReturn(responsavelDTO);

        ResponsavelDTO responsavelDtoCriado = responsavelService.criar(responsavelCreateDTO);
        
        if (!Boolean.parseBoolean(System.getProperty("test.ignore.emails"))) {
            emailService.sendEmailResponsavel(responsavelDTO, "Cadastro efetuado, ", "create");
        }
        assertNotNull(responsavelDtoCriado);
        assertEquals(responsavelDtoCriado, responsavelDTO);
    }

    @Test
    @DisplayName("Deveria atualizar responsavel com sucesso")
    public void deveriaAtualizaResponsavelPorId() throws Exception {

        Responsavel responsavel = retornarResponsavel();
        responsavel.setNome("Joao");
        responsavel.setIdUsuario(1);
        Responsavel responsavelEntityAntigo = new Responsavel();
        BeanUtils.copyProperties(responsavel, responsavelEntityAntigo);
        ResponsavelCreateDTO responsavelCreateDTO = retornarResponsavelCreateDTO();
        Responsavel responsavelEntityAlterado = retornarResponsavel();
        ResponsavelDTO responsavelDTO = retornarResponsavelDTO();

        when(responsavelRepository.findById(anyInt())).thenReturn(Optional.of(responsavel));
        when(objectMapper.convertValue(responsavelCreateDTO, Responsavel.class)).thenReturn(responsavelEntityAlterado);
        when(responsavelRepository.save(anyObject())).thenReturn(responsavelEntityAlterado);
        when(objectMapper.convertValue(responsavelEntityAlterado, ResponsavelDTO.class)).thenReturn(responsavelDTO);

        ResponsavelDTO responsavelDTORetornado = responsavelService.atualizar(responsavel.getIdUsuario(), responsavelCreateDTO);

        assertNotNull(responsavelDTORetornado);
        assertNotEquals(responsavelEntityAntigo, responsavelDTO);
        assertNotEquals(responsavelEntityAntigo.getNome(), responsavelDTORetornado.getNome());
    }
    @Test
    @DisplayName("Deveria listar responsavel por Id com sucesso")
    public void deveriaRetornarResponsavelPorId() throws Exception {
        int id = 1;
        Optional<Responsavel> responsavel = Optional.of(retornarResponsavel());

        when(responsavelRepository.findById(1)).thenReturn(responsavel);

        Responsavel responsavelRetornado = responsavelService.buscarResponsavelPorId(id);

        assertEquals(responsavelRetornado, responsavel.get());
    }

    @Test
    @DisplayName("Deveria listar responsavel por Id com alunos com sucesso")
    public void deveriaRetornarResponsavelPorIdComALunos() throws Exception {
        int id = 1;
        Optional<Responsavel> responsavel = Optional.of(retornarResponsavel());
        AlunoDTO alunoDTO = retornarAlunoDTO();
        ResponsavelComAlunosDTO responsavelComAlunosDTO = retornarResponsavelComALunosDTO(alunoDTO);

        when(responsavelRepository.findById(1)).thenReturn(responsavel);
        when(objectMapper.convertValue(responsavel.get(), ResponsavelComAlunosDTO.class)).thenReturn(responsavelComAlunosDTO);

        ResponsavelComAlunosDTO responsavelRetornado = responsavelService.buscarResponsavelPorIdComAlunos(id);

        assertEquals(responsavelRetornado, responsavelComAlunosDTO);
    }

    @Test
    @DisplayName("Deveria remover responsavel logicamente")
    public void deveriaRemoverResponsavelLogicamente() throws Exception {
        int id = 1;
        Optional<Responsavel> responsavelOptional = Optional.of(new Responsavel());
        Responsavel responsavel = responsavelOptional.get();
        responsavel.setIdUsuario(id);
        when(responsavelRepository.findById(id)).thenReturn(responsavelOptional);

        responsavelService.remover(id);

        verify(responsavelRepository).findById(id);
        verify(responsavelRepository).save(responsavel);
    }

    @Test
    @DisplayName("Deveria lançar exceção remover responsavel não encontrado")
    public void deveriaLancarExcecaoAoRemoverNaoEncontrado() {
        int idNaoEncontrado = 1;

         assertThrows(ObjetoNaoEncontradoException.class, () -> responsavelService.remover(idNaoEncontrado));
    }
    @Test
    @DisplayName("Deveria lançar exceção ao atualizar responsavel com Id não encontrado")
    public void deveriaLancarExcecaoAoAtualizarComIdNaoEncontrado() {
        ResponsavelCreateDTO responsavelMock = new ResponsavelCreateDTO();
        int idNaoEncontrado = 1;

         assertThrows(ObjetoNaoEncontradoException.class, () -> responsavelService.atualizar(idNaoEncontrado, responsavelMock));
    }
    @Test
    @DisplayName("Deveria lançar exceção ao listar responsavel por id não encontrado")
    public void deveriaLancarExcecaoIdNaoEncontrado() {
        int idNaoEncontrado = 1;

        assertThrows(ObjetoNaoEncontradoException.class, () -> responsavelService.buscarResponsavelPorId(idNaoEncontrado));
    }
    @Test
    @DisplayName("Deveria lançar exceção quando CPF ou Email já estão em uso")
    public void deveriaLancarExcecaoQuandoCpfOuEmailJaEmUso()  {
        String cpfAleatorio = "12345678900";
        String emailAleatorio = "jaexistente@email.com";

        when(responsavelRepository.findAllByCpfOrEmail(anyString(), anyString())).thenReturn(new Responsavel());

        assertThrows(RegraDeNegocioException.class, () -> responsavelService.responsavelPorCpfEmail(cpfAleatorio, emailAleatorio));
    }

    @Test
    @DisplayName("Deveria retornar Exception para id não encontrado")
    public void deveriaRetornarExceptionAoReceberIdNaoExistente() {
        int idNaoExistente = new Random().nextInt();

        assertThrows(ObjetoNaoEncontradoException.class, () -> responsavelService.remover(idNaoExistente));
    }

    @Test
    @DisplayName("Deveria retornar Exception para id não encontrado de responsavel com alunos")
    public void deveriaRetornarExceptionAoReceberIdNaoExistenteDeResponsavelComAlunos() {
        int idNaoExistente = new Random().nextInt();

        assertThrows(ObjetoNaoEncontradoException.class, () -> responsavelService.buscarResponsavelPorIdComAlunos(idNaoExistente));
    }
}