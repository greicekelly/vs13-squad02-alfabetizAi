package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioRepository;
import br.com.dbc.vemser.alfabetizai.repository.IModuloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static br.com.dbc.vemser.alfabetizai.services.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@DisplayName("DesafioService - Teste")
class DesafioServiceTest {
//    @Mock
//    private IDesafioRepository desafioRepository;
//    @Mock
//    private IModuloRepository moduloRepository;
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @InjectMocks
//    private DesafioService desafioService;
//
//    @Test
//    @DisplayName("Deveria listar todos desafios com sucesso")
//    public void deveriaListarTodosDesafios(){
//        List<Desafio>listaMock = List.of(retornarDesafio(), retornarDesafio(), retornarDesafio());
//        when(desafioRepository.findAll()).thenReturn(listaMock);
//        List<DesafioDTO> listaRetornoDesafioDTO = desafioService.listarDesafios();
//
//        assertNotNull(listaRetornoDesafioDTO);
//        assertEquals(listaMock.size(), listaRetornoDesafioDTO.size());
//    }
//
//    @Test
//    @DisplayName("Deveria criar desafios com sucesso")
//    public void deveriaCriarDesafioComSucesso()throws Exception{
//        Modulo moduloMock = new Modulo();
//        Mockito.when(moduloRepository.findById(1)).thenReturn(Optional.of(moduloMock));
//
//        DesafioCreateDTO desafioCreateDTOMock = retornarDesafioCreateDTO();
//        Desafio desafioMock = retornarDesafio();
//        DesafioDTO desafioDTOMock = retornarDesafioDTO();
//
//        when(objectMapper.convertValue(desafioCreateDTOMock, Desafio.class)).thenReturn(desafioMock);
//        when(desafioRepository.save(any())).thenReturn(desafioMock);
//        when(objectMapper.convertValue(desafioMock, DesafioDTO.class)).thenReturn(desafioDTOMock);
//
//        DesafioDTO desafioDtoCriado = desafioService.create(desafioCreateDTOMock);
//
//        assertNotNull(desafioDtoCriado);
//        assertEquals(desafioDtoCriado, desafioDTOMock);
//    }
//
//    @Test
//    @DisplayName("Deveria listar desafio por Id com sucesso")
//    public void deveriaRetornarDesafioPorId() throws ObjetoNaoEncontradoException {
//        Integer idAleatorio = new Random().nextInt();
//
//        Optional<Desafio> desafioEntityMock = Optional.of(retornarDesafio());
//
//        when(desafioRepository.findById(anyInt())).thenReturn(desafioEntityMock);
//
//        Desafio desafioRetornado = desafioService.desafioPorId(idAleatorio);
//        assertNotNull(desafioRetornado);
//        assertEquals(desafioRetornado, desafioEntityMock.get());
//    }
//
//    @Test
//    @DisplayName("Deveria atualizar desafio com sucesso")
//    public void deveriaAtualizaDesafioPorId() throws RegraDeNegocioException {
//        Desafio desafioMock = new Desafio();
//        desafioMock.setTitulo("Escolha a letra final");
//        desafioMock.setConteudo("Aprenda as consoantes");
//        desafioMock.setTipo(TipoDesafio.valueOf("QUIZ"));
//        desafioMock.setInstrucao("Marque a letra, que corresponde a ultima letra da palavra Banana.");
//        desafioMock.setA("A");
//        desafioMock.setB("T");
//        desafioMock.setC("B");
//        desafioMock.setD("S");
//        desafioMock.setE("R");
//        desafioMock.setAlternativaCorreta("A");
//        desafioMock.setPontos(10);
//        desafioMock.setAtivo("S");
//
//        Desafio desafioEntityAntigo = new Desafio();
//        BeanUtils.copyProperties(desafioMock, desafioEntityAntigo);
//
//        DesafioCreateDTO desafioCreateDTOMock = retornarDesafioCreateDTO();
//        Desafio desafioEntityAlterado = retornarDesafio();
//        DesafioDTO desafioDTOMock = retornarDesafioDTO();
//
//        when(desafioRepository.findById(anyInt())).thenReturn(Optional.of(desafioMock));
//        when(desafioRepository.save(anyObject())).thenReturn(desafioEntityAlterado);
//        when(objectMapper.convertValue(desafioEntityAlterado, DesafioDTO.class)).thenReturn(desafioDTOMock);
//        when(objectMapper.convertValue(desafioCreateDTOMock, Desafio.class)).thenReturn(desafioEntityAlterado);
//
//        DesafioDTO desafioDTORetornado = desafioService.atualizar(desafioMock.getId(), desafioCreateDTOMock);
//
//        assertNotNull(desafioDTORetornado);
//        assertNotEquals(desafioEntityAntigo, desafioMock);
//        assertNotEquals(desafioEntityAntigo.getTitulo(), desafioDTORetornado.getTitulo());
//    }
//
//    @Test
//    @DisplayName("Deveria retornar lista de desafios por ID do módulo com sucesso")
//    public void deveriaRetornarListaDesafioPorIdModulo() throws RegraDeNegocioException {
//        List<Desafio> desafiosMock = Arrays.asList(retornarDesafio(), retornarDesafio());
//        DesafioDTO desafioDTOMock = retornarDesafioDTO();
//        int idModulo = 1;
//
//        when(desafioRepository.findByModuloId(idModulo)).thenReturn(desafiosMock);
//        when(objectMapper.convertValue(any(Desafio.class), any(Class.class))).thenReturn(desafioDTOMock);
//
//        List<DesafioDTO> desafiosDTORetornados = desafioService.listarPorIdModulo(idModulo);
//
//        assertNotNull(desafiosDTORetornados);
//        assertFalse(desafiosDTORetornados.isEmpty());
//        assertEquals(desafiosDTORetornados.size(), desafiosMock.size());
//        assertEquals(desafiosDTORetornados.get(0), desafioDTOMock);
//    }
//    @Test
//    @DisplayName("Deveria remover desafio logicamente")
//    public void deveriaRemoverLogico() throws RegraDeNegocioException {
//        int id = 1;
//        Optional<Desafio> desafioOptional = Optional.of(new Desafio());
//        Desafio desafio = desafioOptional.get();
//        desafio.setId(id);
//        when(desafioRepository.findById(id)).thenReturn(desafioOptional);
//
//        desafioService.removerLogico(id);
//
//        verify(desafioRepository).findById(id);
//        verify(desafioRepository).save(desafio);
//        assertEquals("N", desafio.getAtivo());
//    }
//
//        @Test
//        @DisplayName("Deveria retornar erro quando não encontrar desafio para remover Logicamente pelo ID do desafio")
//        public void deveriaLancarErroQuandoNaoEncontrarIdDesafioParaRemover() {
//            int idRemover = 1;
//            assertThrows(RegraDeNegocioException.class, () -> desafioService.removerLogico(idRemover));
//        }
//
//        @Test
//        @DisplayName("Deveria retornar Exception para id não encontrado")
//        public void deveriaRetornarExceptionAoReceberIdNaoExistente() {
//            Integer idNaoExistente = new Random().nextInt();
//
//            assertThrows(ObjetoNaoEncontradoException.class, () -> desafioService.desafioPorId(idNaoExistente));
//        }
//
//        @Test
//        @DisplayName("Deveria retornar Exception para id não encontrado em Atualizar")
//        public void deveriaRetornarExceptionAoReceberIdAlterarNaoExistente() throws RegraDeNegocioException{
//            DesafioCreateDTO desafioMock = new DesafioCreateDTO();
//            Integer idNaoExistente = 1 ;
//
//            assertThrows(RegraDeNegocioException.class, () -> desafioService.atualizar(idNaoExistente, desafioMock));
//        }
//
//        @Test
//        @DisplayName("Deveria retornar erro quando não encontrar desafio pelo ID do módulo")
//        public void deveriaLancarErroQuandoNaoEncontrarDesafio() {
//            int idModulo = 1;
//            assertThrows(RegraDeNegocioException.class, () -> desafioService.listarPorIdModulo(idModulo));
//        }

}