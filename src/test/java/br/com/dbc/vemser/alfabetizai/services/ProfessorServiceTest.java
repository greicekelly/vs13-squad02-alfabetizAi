package br.com.dbc.vemser.alfabetizai.services;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.IProfessorRepository;
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

//@ExtendWith(MockitoExtension.class)
//@DisplayName("ProfessorService - Teste")
class ProfessorServiceTest {
//
//    @Mock
//    private IProfessorRepository professorRepository;
//    @Mock
//    private ObjectMapper objectMapper;
//    @Mock
//    private  EmailService emailService;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @InjectMocks
//    private ProfessorService professorService;
//
//    @Test
//    @DisplayName("Deveria listar todos professores com sucesso")
//    public void deveriaListarTodosProfessores() {
//        List<Professor> listaMock = List.of(retornarProfessor(), retornarProfessor(), retornarProfessor());
//        when(professorRepository.findAll()).thenReturn(listaMock);
//        List<ProfessorDTO> listaRetornoProfessorDTO = professorService.listar();
//
//        assertNotNull(listaRetornoProfessorDTO);
//        assertEquals(listaMock.size(), listaRetornoProfessorDTO.size());
//    }
//    @Test
//    @DisplayName("Deveria criar professor com sucesso")
//    public void deveriaCriarProfessorComSucesso()throws Exception{
//
//        ProfessorCreateDTO professorCreateDTOMock = retornarProfessorCreateDTO();
//        Professor professorMock = retornarProfessor();
//        ProfessorDTO professorDTOMock = retornarProfessorDTO();
//
//        when(objectMapper.convertValue(professorCreateDTOMock, Professor.class)).thenReturn(professorMock);
//        when(professorRepository.save(any())).thenReturn(professorMock);
//        when(objectMapper.convertValue(professorMock, ProfessorDTO.class)).thenReturn(professorDTOMock);
//
//        ProfessorDTO professorDtoCriado = professorService.criar(professorCreateDTOMock);
//
//        if (!Boolean.parseBoolean(System.getProperty("test.ignore.emails"))) {
//            emailService.sendEmailProfessor(professorDTOMock, "Cadastro efetuado, ", "create");
//        }
//        assertNotNull(professorDtoCriado);
//        assertEquals(professorDtoCriado, professorDTOMock);
//    }
//
//    @Test
//    @DisplayName("Deveria atualizar professor com sucesso")
//    public void deveriaAtualizaProfessorPorId() throws Exception {
//
//        Professor professorMock = new Professor();
//        professorMock.setNome("James");
//        professorMock.setSobrenome("de Oliveira");
//        professorMock.setEmail("james@email.com");
//        professorMock.setSexo("M");
//        professorMock.setSenha("123");
//        professorMock.setCpf("50293697086");
//        professorMock.setTelefone("998768902");
//        professorMock.setDataDeNascimento(LocalDate.parse("2022-02-01"));
//        professorMock.setDescricao("Letras");
//
//        professorMock.setIdUsuario(1);
//
//        Professor professorEntityAntigo = new Professor();
//        BeanUtils.copyProperties(professorMock, professorEntityAntigo);
//
//        ProfessorCreateDTO professorCreateDTOMock = retornarProfessorCreateDTO();
//        Professor professorEntityAlterado = retornarProfessor();
//        ProfessorDTO professorDTOMock = retornarProfessorDTO();
//
//        when(professorRepository.findById(anyInt())).thenReturn(Optional.of(professorMock));
//        when(professorRepository.save(anyObject())).thenReturn(professorEntityAlterado);
//        when(objectMapper.convertValue(professorEntityAlterado, ProfessorDTO.class)).thenReturn(professorDTOMock);
//        when(objectMapper.convertValue(professorCreateDTOMock, Professor.class)).thenReturn(professorEntityAlterado);
//
//        ProfessorDTO professorDTORetornado = professorService.atualizar(professorMock.getIdUsuario(), professorCreateDTOMock);
//
//        assertNotNull(professorDTORetornado);
//        assertNotEquals(professorEntityAntigo, professorMock);
//        assertNotEquals(professorEntityAntigo.getNome(), professorDTORetornado.getNome());
//    }
//    @Test
//    @DisplayName("Deveria listar professor por Id com sucesso")
//    public void deveriaRetornarProfessorPorId() throws ObjetoNaoEncontradoException {
//        int id = 1;
//        Optional<Professor> professorEntityMock = Optional.of(retornarProfessor());
//
//        when(professorRepository.findById(1)).thenReturn(professorEntityMock);
//
//        ProfessorDTO professorRetornado = professorService.buscarProfessorPorId(id);
//
//        ProfessorDTO professorEntityDTO = objectMapper.convertValue(professorEntityMock.get(), ProfessorDTO.class);
//        assertEquals(professorRetornado, professorEntityDTO);
//    }
//    @Test
//    @DisplayName("Deveria remover professor logicamente")
//    public void deveriaRemoverProfessorLogicamente() throws Exception {
//        int id = 1;
//        Optional<Professor> professorOptional = Optional.of(new Professor());
//        Professor professor = professorOptional.get();
//        professor.setIdUsuario(id);
//        when(professorRepository.findById(id)).thenReturn(professorOptional);
//
//        professorService.remover(id);
//
//        verify(professorRepository).findById(id);
//        verify(professorRepository).save(professor);
//        assertEquals("N", professor.getAtivo());
//    }
//
//
//    @Test
//    @DisplayName("Deveria lançar exceção com mensagem correta ao remover professor não encontrado")
//    public void deveriaLancarExcecaoComMensagemAoRemoverNaoEncontrado() throws Exception {
//        int idNaoEncontrado = 1;
//
//        ObjetoNaoEncontradoException objetoNaoEncontradoException = assertThrows(
//                ObjetoNaoEncontradoException.class, () -> professorService.remover(idNaoEncontrado));
//
//        assertEquals("Professor com o ID " + idNaoEncontrado + " não encontrado informe um id valido",
//                objetoNaoEncontradoException.getMessage());
//    }
//    @Test
//    @DisplayName("Deveria lançar exceção ao atualizar professor com Id não encontrado")
//    public void deveriaLancarExcecaoComMensagemAoAtualizarComIdNaoEncontrado() throws Exception {
//        ProfessorCreateDTO professorMock = new ProfessorCreateDTO();
//        int idNaoEncontrado = 1;
//
//        ObjetoNaoEncontradoException objetoNaoEncontradoException = assertThrows(
//                ObjetoNaoEncontradoException.class, () -> professorService.atualizar(idNaoEncontrado, professorMock));
//
//        assertEquals("Professor com o ID " + idNaoEncontrado + " não encontrado informe um id valido",
//                objetoNaoEncontradoException.getMessage());
//    }
//    @Test
//    @DisplayName("Deveria lançar exceção com mensagem correta ao listar professor por id não encontrado")
//    public void deveriaLancarExcecaoComMensagemIdNaoEncontrado() throws Exception {
//        int idNaoEncontrado = 1;
//
//        ObjetoNaoEncontradoException objetoNaoEncontradoException = assertThrows(
//                ObjetoNaoEncontradoException.class, () -> professorService.buscarProfessorPorId(idNaoEncontrado));
//
//        assertEquals("Professor com o ID " + idNaoEncontrado + " não encontrado informe um id valido",
//                objetoNaoEncontradoException.getMessage());
//    }
//    @Test
//    @DisplayName("Deveria lançar exceção quando CPF ou Email já estão em uso")
//    public void deveriaLancarExcecaoQuandoCpfOuEmailJaEmUso() throws Exception {
//        String cpfAleatorio = "12345678900";
//        String emailAleatorio = "jaexistente@email.com";
//
//        when(professorRepository.findAllByCpfOrEmail(anyString(), anyString())).thenReturn(new Professor());
//
//        assertThrows(RegraDeNegocioException.class, () -> professorService.professorPorCpfEmail(cpfAleatorio, emailAleatorio));
//    }
//
//    @Test
//    @DisplayName("Deveria retornar Exception para id não encontrado")
//    public void deveriaRetornarExceptionAoReceberIdNaoExistente() {
//        int idNaoExistente = new Random().nextInt();
//
//        assertThrows(ObjetoNaoEncontradoException.class, () -> professorService.remover(idNaoExistente));
//    }

}