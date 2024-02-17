package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IAlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static br.com.dbc.vemser.alfabetizai.services.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AlunoService - Teste")
class AlunoServiceTest {
    @Mock
    private DesafioService desafioService;
    @Mock
    private ModuloService moduloService;
    @Mock
    private ResponsavelService responsavelService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private IAlunoRepository alunoRepository;
    @Spy
    @InjectMocks
    private AlunoService alunoService;

    @Test
    @DisplayName("Deveria criar aluno com sucesso")
    public void deveriaCriarALunoComSucesso() throws Exception {
        AlunoCreateDTO alunoCreateDTO = retornarAlunoCreateDTO();
        Aluno aluno = retornarAluno();
        AlunoDTO alunoDTO = retornarAlunoDTO();

        when(objectMapper.convertValue(alunoCreateDTO, Aluno.class)).thenReturn(aluno);
        when(responsavelService.buscarResponsavelPorId(anyInt())).thenReturn(retornarResponsavel());
        when(objectMapper.convertValue(aluno, AlunoDTO.class)).thenReturn(alunoDTO);

        AlunoDTO alunoRetornado = alunoService.criar(1, alunoCreateDTO);

        assertNotNull(alunoRetornado);
        assertEquals(alunoRetornado, alunoDTO);
    }

    @Test
    @DisplayName("Deveria listar todos alunos com sucesso")
    public void deveriaListarTodosAlunos() throws RegraDeNegocioException {
        List<Aluno> listaMock = List.of(retornarAluno(), retornarAluno(), retornarAluno());
        when(alunoRepository.findAll()).thenReturn(listaMock);
        List<AlunoDTO> listaRetornoAlunoDTO = alunoService.listar();

        assertNotNull(listaRetornoAlunoDTO);
        assertEquals(listaMock.size(), listaRetornoAlunoDTO.size());
    }

    @Test
    @DisplayName("Deveria retornar aluno por Id com sucesso")
    public void deveriaRetornarAlunoPorId() throws ObjetoNaoEncontradoException {
        Integer idAleatorio = new Random().nextInt();
        AlunoDTO alunoDTO = retornarAlunoDTO();
        Optional<Aluno> alunoOptional = Optional.of(retornarAluno());

        when(alunoRepository.findById(anyInt())).thenReturn(alunoOptional);
        when(objectMapper.convertValue(alunoOptional.get(), AlunoDTO.class)).thenReturn(alunoDTO);

        AlunoDTO alunoRetornado = alunoService.buscarAlunoPorId(idAleatorio);
        assertNotNull(alunoRetornado);
        assertEquals(alunoRetornado, alunoDTO);
    }

    @Test
    @DisplayName("Deveria retornar ObjetoNaoEncontradoExceotion ao nao encontrar aluno")
    public void deveriaRetornarExceptionAoNaoEncontrarAluno() {
        Integer idAleatorio = new Random().nextInt();
        Optional<Aluno> alunoOptional = Optional.empty();

        when(alunoRepository.findById(anyInt())).thenReturn(alunoOptional);

        assertThrows(ObjetoNaoEncontradoException.class, () -> alunoService.buscarAlunoPorId(idAleatorio));
    }

    @Test
    @DisplayName("Deveria retornar alunos por IdResponsavel com sucesso")
    public void deveriaRetornarAlunosPorIdResponsavel() throws Exception {
        Integer idAleatorio = new Random().nextInt();
        AlunoDTO alunoDTO = retornarAlunoDTO();
        List<Aluno> alunoList = List.of(retornarAluno());
        Optional<List<Aluno>> alunoOptionalList = Optional.of(alunoList);
        Responsavel responsavel = retornarResponsavel();

        when(responsavelService.buscarResponsavelPorId(idAleatorio)).thenReturn(responsavel);
        when(alunoRepository.findAllByResponsavel(responsavel)).thenReturn(alunoOptionalList);
        when(objectMapper.convertValue(any(Aluno.class), any(Class.class))).thenReturn(alunoDTO);

        List<AlunoDTO> alunosRetornados = alunoService.buscarAlunosPorIdResponsavel(idAleatorio);
        assertNotNull(alunosRetornados);
        assertEquals(alunosRetornados.size(), alunoList.size());
    }

    @Test
    @DisplayName("Deveria retornar ObjetoNaoEncontradoExceotion ao nao encontrar aluno por idResponsavel")
    public void deveriaRetornarExceptionAoNaoEncontrarAlunoPorIdResponsavel() throws Exception {
        Integer idAleatorio = new Random().nextInt();
        Optional<List<Aluno>> alunoOptionalList = Optional.empty();
        Responsavel responsavel = retornarResponsavel();

        when(responsavelService.buscarResponsavelPorId(idAleatorio)).thenReturn(responsavel);
        when(alunoRepository.findAllByResponsavel(responsavel)).thenReturn(alunoOptionalList);

        assertThrows(ObjetoNaoEncontradoException.class, () -> alunoService.buscarAlunosPorIdResponsavel(idAleatorio));
    }

    @Test
    @DisplayName("Deveria atualizar aluno com sucesso")
    public void deveriaAtualizaAlunoPorId() throws Exception {
        Aluno aluno = retornarAluno();
        aluno.setNomeAluno("Pedro");
        Aluno alunoAntigo = retornarAluno();
        BeanUtils.copyProperties(aluno, alunoAntigo);

        AlunoCreateDTO alunoCreateDTO = retornarAlunoCreateDTO();
        Aluno alunoAlterado = retornarAluno();
        AlunoDTO alunoDTO = retornarAlunoDTO();

        when(alunoRepository.findById(anyInt())).thenReturn(Optional.of(aluno));
        when(objectMapper.convertValue(alunoCreateDTO, Aluno.class)).thenReturn(aluno);
        when(alunoRepository.save(anyObject())).thenReturn(alunoAlterado);
        when(objectMapper.convertValue(alunoAlterado, AlunoDTO.class)).thenReturn(alunoDTO);


        AlunoDTO alunoDTORetornado = alunoService.atualizar(aluno.getIdAluno(), alunoCreateDTO);

        assertNotNull(alunoDTORetornado);
        assertNotEquals(alunoDTORetornado, aluno);
        assertNotEquals(alunoAntigo.getNomeAluno(), alunoDTORetornado.getNomeAluno());
    }

    @Test
    @DisplayName("Deveria retornar exception ao atualizar aluno")
    public void deveriaRetornarExceptionAoAtualizaAlunoPorId() throws Exception {
        AlunoCreateDTO alunoCreateDTO = retornarAlunoCreateDTO();
        Integer idAleatorio = new Random().nextInt();

        when(alunoRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ObjetoNaoEncontradoException.class, () -> alunoService.atualizar(idAleatorio, alunoCreateDTO));
    }

    @Test
    @DisplayName("Deveria remover aluno logicamente")
    public void deveriaRemoverLogico() throws Exception {
        int id = 1;
        Optional<Aluno> alunoOptional = Optional.of(new Aluno());
        Aluno aluno = alunoOptional.get();
        aluno.setIdAluno(id);
        when(alunoRepository.findById(id)).thenReturn(alunoOptional);

        alunoService.remover(id);

        verify(alunoRepository).findById(id);
        verify(alunoRepository).save(aluno);
        assertEquals("N", aluno.getAtivo());
    }

    @Test
    @DisplayName("Deveria lançar exceção ao remover aluno não encontrado")
    public void deveriaLancarExcecaoAoRemoverNaoEncontrado() {
        int id = 1;
        Optional<Aluno> alunoOptional = Optional.empty();

        when(alunoRepository.findById(id)).thenReturn(alunoOptional);

        assertThrows(ObjetoNaoEncontradoException.class, () -> alunoService.remover(id));
    }

    @Test
    @DisplayName("Deveria listar corretamente os desafios concluidos")
    public void deveriaListarCorretamenteOsDesafiosConcluidos() throws Exception {
        Integer idAleatorio = new Random().nextInt();
        Aluno aluno = retornarAluno();
        aluno.setDesafios(Set.of(retornarDesafio()));
        Optional<Aluno> alunoOptional = Optional.of(aluno);
        DesafioDTO desafioDTO = retornarDesafioDTO();

        when(alunoRepository.findById(idAleatorio)).thenReturn(alunoOptional);
        when(objectMapper.convertValue(any(Desafio.class), any(Class.class))).thenReturn(desafioDTO);

        List<DesafioDTO> listaRetornada = alunoService.listarDesafiosConcluidos(idAleatorio);

        assertNotNull(listaRetornada);
    }

    @Test
    @DisplayName("Deveria lancar exception ao buscar desafios concluidos")
    public void deveriaLancarExceptionAoBuscarDesafiosConcluidos() {
        Integer idAleatorio = new Random().nextInt();
        Optional<Aluno> alunoOptional = Optional.empty();

        when(alunoRepository.findById(idAleatorio)).thenReturn(alunoOptional);

        assertThrows(ObjetoNaoEncontradoException.class, () -> alunoService.listarDesafiosConcluidos(idAleatorio));
    }

    @Test
    @DisplayName("Deveria listar corretamente os modulos concluidos")
    public void deveriaListarCorretamenteOsModulosConcluidos() throws Exception {
        Integer idAleatorio = new Random().nextInt();
        Aluno aluno = retornarAluno();
        aluno.setModulos(Set.of(retornarModulo()));
        Optional<Aluno> alunoOptional = Optional.of(aluno);
        ModuloDTO desafioDTO = retornarModuloDTO();

        when(alunoRepository.findById(idAleatorio)).thenReturn(alunoOptional);
        when(objectMapper.convertValue(any(Modulo.class), any(Class.class))).thenReturn(desafioDTO);

        List<ModuloDTO> listaRetornada = alunoService.listarModulosConcluidos(idAleatorio);

        assertNotNull(listaRetornada);
    }

    @Test
    @DisplayName("Deveria lancar exception ao buscar modulos concluidos")
    public void deveriaLancarExceptionAoBuscarModulosConcluidos() {
        Integer idAleatorio = new Random().nextInt();
        Optional<Aluno> alunoOptional = Optional.empty();

        when(alunoRepository.findById(idAleatorio)).thenReturn(alunoOptional);

        assertThrows(ObjetoNaoEncontradoException.class, () -> alunoService.listarModulosConcluidos(idAleatorio));
    }


//    @Test
//    @DisplayName("Deveria fazer desafios com sucesso")
//    public void deveriaFazerDesafioComSUcesso() throws Exception {
//        Desafio desafio = new Desafio();
//        desafio.setId(1);
//        Aluno aluno = new Aluno();
//        Set<Desafio>lista =  Set.of(retornarDesafio());
//        aluno.setDesafios(listaMock) ;
//        Optional<Aluno> alunoOptional = Optional.of(aluno);
//
//        when(desafioService.desafioPorId(1)).thenReturn(desafio);
//        when(alunoRepository.findById(anyInt())).thenReturn(alunoOptional);
//
//        alunoService.fazerDesafio(1,1);
//
//        verify(alunoService, times(0)).concluirModulo(aluno, desafio);
//    }

}