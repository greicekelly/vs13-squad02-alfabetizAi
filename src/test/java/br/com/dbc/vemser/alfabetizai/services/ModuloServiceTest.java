package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloAdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.IModuloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.*;

import static br.com.dbc.vemser.alfabetizai.services.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Classe Modulo - Teste")
class ModuloServiceTest {
    @Mock
    private IModuloRepository moduloRepository;
    @Mock
    private ProfessorService professorService;
    @Mock
    private ObjectMapper objectMapper;

    @Spy
    @InjectMocks
    private ModuloService moduloService;


    @Test
    @DisplayName("Deveria converter de ModuloCreateDTO para Modulo com sucesso")
    void converterModuloCreateDTOParaModulo(){
        // ARRANGE - GIVEN
        ModuloCreateDTO moduloCreateDTO = retornarModuloCreateDTO();
        Modulo modulo = retornarModulo();

        // ACT - WHEN
        when(objectMapper.convertValue(moduloCreateDTO, Modulo.class)).thenReturn(modulo);

        Modulo moduloRetornado = moduloService.converterDTO(moduloCreateDTO);

        // ASSERT / THEN
        assertNotNull(moduloRetornado);
        assertEquals(moduloRetornado, modulo);
    }

    @Test
    @DisplayName("Deveria converter de Modulo para ModuloDTO com sucesso")
    void converterModuloParaModuloDTO(){
        // ARRANGE - GIVEN
        ModuloDTO moduloDTO = retornarModuloDTO();
        Modulo modulo = retornarModulo();

        // ACT - WHEN
        when(objectMapper.convertValue(modulo, ModuloDTO.class)).thenReturn(moduloDTO);

        ModuloDTO moduloDTORetornado = moduloService.retornarDTO(modulo);

        // ASSERT / THEN
        assertNotNull(moduloDTORetornado);
        assertEquals(moduloDTORetornado, moduloDTO);
    }


    @Test
    @DisplayName("Deveria criar um novo módulo com sucesso")
    void criarModuloComSucesso() throws Exception {
        // ARRANGE - GIVEN
        ProfessorDTO professorDTO = retornarProfessorDTO();
        Professor professor = retornarProfessor();
        ModuloCreateDTO moduloCreateDTO = retornarModuloCreateDTO();
        ModuloDTO moduloDTO = retornarModuloDTO();
        Modulo modulo = retornarModulo();

        // ACT - WHEN
        when(professorService.buscarProfessorPorId(moduloCreateDTO.getIdProfessor())).thenReturn(professorDTO);
        when(moduloService.converterDTO(moduloCreateDTO)).thenReturn(modulo);
        modulo.setProfessor(professor);

        when(moduloService.retornarDTO(moduloRepository.save(modulo))).thenReturn(moduloDTO);

        ModuloDTO moduloDTOCriado = moduloService.criar(moduloCreateDTO);


        // ASSERT / THEN
        assertNotNull(moduloDTOCriado);
        assertEquals(moduloDTOCriado, moduloDTO);
    }

    @Test
    @DisplayName("Deveria retornar a lista de todo os módulos com sucesso")
    void listarTodosModulosComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        Page<Modulo> modulosMock = criarPageModulosMock(pageable);
        Page<ModuloDTO> modulosDTOMock = criarPageModulosDTOMock(pageable);

        // ACT - WHEN
        when(moduloRepository.findAll(pageable)).thenReturn(modulosMock);

        Page<ModuloDTO> moduloDTOPageRetornado = moduloService.listar(pageable);

        // ASSERT / THEN
        assertNotNull(moduloDTOPageRetornado);
        assertEquals(2, moduloDTOPageRetornado.getContent().size());
    }

    @Test
    @DisplayName("Deveria retornar um móduloDTO pelo Id com sucesso")
    void retornarUmModuloDTOPeloIdComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Modulo modulo = retornarModulo();
        Optional<Modulo> moduloOptional = Optional.of(retornarModulo());
        ModuloDTO moduloDTO = retornarModuloDTO();
        Integer id = 1;

        // ACT - WHEN
        when(moduloRepository.findById(anyInt())).thenReturn(moduloOptional);
        when(objectMapper.convertValue(moduloOptional.get(), ModuloDTO.class)).thenReturn(moduloDTO);

        ModuloDTO moduloDTORetornado = moduloService.listarPorIdModulo(id);

        // ASSERT / THEN
        assertNotNull(moduloDTORetornado);
        assertEquals(moduloDTORetornado, moduloDTO);

    }

    @Test
    @DisplayName("Deveria retornar um módulo pelo Id com sucesso")
    void retornarUmModuloPeloIdComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Optional<Modulo> moduloOptional = Optional.of(retornarModulo());

        // ACT - WHEN
        when(moduloRepository.findById(anyInt())).thenReturn(moduloOptional);

        Modulo moduloRetornado = moduloService.buscarModuloPorId(new Random().nextInt());

        // ASSERT / THEN
        assertNotNull(moduloRetornado);
        assertEquals(moduloRetornado, moduloOptional.get());
    }

    @Test
    @DisplayName("Deveria retornar Excepition ao buscar um módulo pelo Id incorreto")
    void retornarExcepitionBuscarModuloPeloIdIncorreto() throws Exception {
        // ARRANGE - GIVEN
        Integer idModulo = new Random().nextInt();

        // ACT - WHEN
        when(moduloRepository.findById(idModulo)).thenReturn(Optional.empty());

        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> moduloService.buscarModuloPorId(idModulo));
    }

    @Test
    @DisplayName("Deveria retornar a lista de todo os módulos pelo Id do professor com sucesso")
    void listarTodosModulosPeloIdProfessorComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        Page<Modulo> modulosMock = criarPageModulosMock(pageable);
        Page<ModuloDTO> modulosDTOMock = criarPageModulosDTOMock(pageable);
        Integer idProfessor = 1;

        // ACT - WHEN
        when(moduloRepository.findAllByIdProfessor(idProfessor, pageable)).thenReturn(modulosMock);

        Page<ModuloDTO> moduloDTOPageRetornado = moduloService.pagePorIdProfessor(idProfessor, pageable);

        // ASSERT / THEN
        assertNotNull(moduloDTOPageRetornado);
        assertEquals(2, moduloDTOPageRetornado.getContent().size());
    }

    @Test
    @DisplayName("Deveria retornar a lista de todo os módulos (ModuloProfessorDTO) pelo Id do professor com sucesso")
    void listarModulosProfessorDTOPeloIdProfessorComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        Page<Modulo> modulosMock = criarPageModulosMock(pageable);
        Integer idProfessor = 1;

        // ACT - WHEN
        when(moduloRepository.findAllByIdProfessor(idProfessor, pageable)).thenReturn(modulosMock);

        Page<ModuloProfessorDTO> moduloDTOPageRetornadoSemAnalise = moduloService.pagePorProfessor(idProfessor, "Sem Analise", pageable);
        Page<ModuloProfessorDTO> moduloDTOPageRetornadoAprovado = moduloService.pagePorProfessor(idProfessor, "Aprovado", pageable);
        Page<ModuloProfessorDTO> moduloDTOPageRetornadoReprovado = moduloService.pagePorProfessor(idProfessor, "Reprovado", pageable);

        // ASSERT / THEN
        assertNotNull(moduloDTOPageRetornadoSemAnalise);
        assertNotNull(moduloDTOPageRetornadoAprovado);
        assertNotNull(moduloDTOPageRetornadoReprovado);
        assertEquals(2, moduloDTOPageRetornadoSemAnalise.getContent().size());
    }

    @Test
    @DisplayName("Deveria retornar a lista de todo os módulos pelo Id do Admin aprovador com sucesso")
    void listarModulosPeloIdAdminAprovadorComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        Page<Modulo> modulosMock = criarPageModulosMock(pageable);
        Integer idAdmin = 1;

        // ACT - WHEN
        when(moduloRepository.findAllByAdmin(idAdmin, pageable)).thenReturn(modulosMock);

        Page<ModuloAdminDTO> moduloDTOPageRetornado = moduloService.pagePorIdAdmin(idAdmin, pageable);

        // ASSERT / THEN
        assertNotNull(moduloDTOPageRetornado);
        assertEquals(2, moduloDTOPageRetornado.getContent().size());
    }

    @Test
    @DisplayName("Deveria atualizar o módulo com sucesso.")
    void atualizarModuloComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Integer idModulo = 1;
        ModuloCreateDTO moduloCreateDTO = retornarModuloCreateDTO();
        ModuloDTO moduloDTO = retornarModuloDTO();
        Optional<Modulo> moduloOptional = Optional.of(retornarModulo());

        // ACT - WHEN
        when(moduloRepository.findById(idModulo)).thenReturn(moduloOptional);
        when(moduloService.converterDTO(moduloCreateDTO)).thenReturn(moduloOptional.get());

        moduloOptional.get().setTitulo(moduloCreateDTO.getTitulo());
        moduloOptional.get().setConteudo(moduloCreateDTO.getConteudo());
        moduloOptional.get().setClassificacao(moduloCreateDTO.getClassificacao());
        moduloOptional.get().setFoiAprovado("S");
        moduloOptional.get().setIdProfessor(moduloCreateDTO.getIdProfessor());

        when(moduloService.retornarDTO(moduloRepository.save(moduloOptional.get()))).thenReturn(moduloDTO);

        ModuloDTO moduloDTOCriado = moduloService.atualizar(idModulo,moduloCreateDTO);

        // ASSERT / THEN
        assertNotNull(moduloDTOCriado);
        assertEquals(moduloDTOCriado, moduloDTO);
    }

    @Test
    @DisplayName("Deveria retornar Excepition ao tentar atualizar o módulo com id incorreto.")
    void atualizarModuloComIdIncorreto() throws Exception {
        // ARRANGE - GIVEN
        Integer idModulo = 1;
        ModuloCreateDTO moduloCreateDTO = retornarModuloCreateDTO();

        // ACT - WHEN
        when(moduloRepository.findById(idModulo)).thenReturn(Optional.empty());

        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> moduloService.atualizar(idModulo,moduloCreateDTO));
    }

    @Test
    @DisplayName("Deveria remover fisicamente o Módulo com sucesso.")
    void removerFisicoModuloComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Optional<Modulo> moduloOptional = Optional.of(retornarModulo());
        Integer id = 1;

        // ACT - WHEN
        when(moduloRepository.findById(id)).thenReturn(moduloOptional);

        moduloService.remover(id);

        // ASSERT / THEN
        verify(moduloRepository, times(1)).delete(moduloOptional.get());
    }

    @Test
    @DisplayName("Deveria retornar Exception ao tentar remover fisicamente o Módulo com Desafios ou Alunos associados.")
    void retornarExceptionAoRemoverFisicoModuloComDesafioAlunoAssociado() throws Exception {
        // ARRANGE - GIVEN
        Optional<Modulo> moduloOptional = Optional.of(retornarModulo());
        moduloOptional.get().setDesafios(retornarDesafios());
        Integer id = 1;

        // ACT - WHEN
        when(moduloRepository.findById(id)).thenReturn(moduloOptional);

        // ASSERT / THEN
        assertThrows(RegraDeNegocioException.class, () -> moduloService.remover(id));
    }

    @Test
    @DisplayName("Deveria retornar Exception ao tentar remover fisicamente o Módulo com Id Incorreto.")
    void retornarExceptionAoRemoverFisicoModuloComIdIncorreto() throws Exception {
        // ARRANGE - GIVEN
        Integer id = 1;

        // ACT - WHEN
        when(moduloRepository.findById(id)).thenReturn(Optional.empty());

        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> moduloService.remover(id));
    }

    @Test
    @DisplayName("Deveria remover logicamente o Módulo com sucesso.")
    void removerLogicoModuloComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Optional<Modulo> moduloOptional = Optional.of(retornarModulo());
        Integer id = 1;

        // ACT - WHEN
        when(moduloRepository.findById(id)).thenReturn(moduloOptional);

        moduloService.removerLogico(id);

        // ASSERT / THEN
        verify(moduloRepository, times(1)).save(moduloOptional.get());
    }

    @Test
    @DisplayName("Deveria retornar Excepition ao tentar remover logicamente o Módulo com id incorreto.")
    void excepitionRemoverLogicoModuloComIdIncorreto() throws Exception {
        // ARRANGE - GIVEN
        Integer id = 1;

        // ACT - WHEN
        when(moduloRepository.findById(id)).thenReturn(Optional.empty());

        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> moduloService.removerLogico(id));
    }


    @Test
    @DisplayName("Deveria retornar lista com os Módulos por aprovação.")
    void retornarListaModulosPorAprovacao() throws Exception {
        // ARRANGE - GIVEN
        List<Modulo> modulos = new ArrayList<>();
        modulos.add(retornarModulo());
        List<ModuloDTO> modulosDTO = new ArrayList<>();
        modulosDTO.add(retornarModuloDTO());
        String aprovacao = "Sem Analise";
        String filtro = aprovacao.equals("Sem Analise") ? "N" : "S";

        // ACT - WHEN
        when(moduloRepository.findAllByFoiAprovado(filtro)).thenReturn(modulos);

        List<ModuloDTO> moduloDTORetornadoSemAnalise = moduloService.listarPorAprovacao("Sem Analise");
        List<ModuloDTO> moduloDTORetornadoAprovado = moduloService.listarPorAprovacao("Aprovado");
        List<ModuloDTO> moduloDTORetornado = moduloService.listarPorAprovacao("N");

        // ASSERT / THEN
        assertNotNull(moduloDTORetornadoSemAnalise);
        assertNotNull(moduloDTORetornadoAprovado);
        assertNotNull(moduloDTORetornado);

    }

    @Test
    @DisplayName("Deveria retornar lista com os Módulos conluidos pelo aluno.")
    void retornarListaModulosConcluidosPorAluno() throws Exception {
        // ARRANGE - GIVEN
        List<Modulo> modulos = new ArrayList<>();
        modulos.add(retornarModulo());
        List<ModuloDTO> modulosDTO = new ArrayList<>();
        modulosDTO.add(retornarModuloDTO());

        // ACT - WHEN
        when(moduloRepository.listarModulosConcluidos(anyInt())).thenReturn(modulos);

        List<ModuloDTO> moduloDTORetornado = moduloService.listarModulosConcluidos(anyInt());

        // ASSERT / THEN
        assertNotNull(moduloDTORetornado);
    }

    @Test
    @DisplayName("Deveria retornar Excepition com listar Módulos conluidos pelo id aluno incorreto.")
    void retornarExcepitionAoListarModulosConcluidosPorIdAlunoIncorreto() throws Exception {
        // ARRANGE - GIVEN
        List<Modulo> modulos = new ArrayList<>();

        // ACT - WHEN
        lenient().when(moduloRepository.listarModulosConcluidos(anyInt())).thenReturn(null);

        // ASSERT / THEN
        assertThrows(Exception.class, () -> moduloService.listarModulosConcluidos(anyInt()));
    }

    @Test
    @DisplayName("Deveria salvar módulo com sucesso.")
    void salvarModuloComSucesso() {
        // ARRANGE - GIVEN
        ModuloDTO moduloDTO = retornarModuloDTO();
        Modulo modulo = retornarModulo();

        // ACT - WHEN
        when(objectMapper.convertValue(moduloDTO, Modulo.class)).thenReturn(modulo);

        moduloService.save(moduloDTO);

        // ASSERT / THEN
        verify(moduloRepository, times(1)).save(modulo);
    }


    public static Page<Modulo> criarPageModulosMock(Pageable pageable) {

        List<Modulo> modulos = new ArrayList<>();
        modulos.add(retornarModulo());
        modulos.add(retornarModulo());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), modulos.size());
        return new PageImpl<>(modulos.subList(start, end), pageable, modulos.size());
    }

    public static Page<ModuloDTO> criarPageModulosDTOMock(Pageable pageable) {

        List<ModuloDTO> modulos = new ArrayList<>();
        modulos.add(retornarModuloDTO());
        modulos.add(retornarModuloDTO());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), modulos.size());
        return new PageImpl<>(modulos.subList(start, end), pageable, modulos.size());
    }

    public static Page<ModuloProfessorDTO> criarPageModulosProfessorDTOMock(Pageable pageable) {

        List<ModuloProfessorDTO> modulos = new ArrayList<>();
        modulos.add(retornarModuloProfessorDTO());
        modulos.add(retornarModuloProfessorDTO());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), modulos.size());
        return new PageImpl<>(modulos.subList(start, end), pageable, modulos.size());
    }

    public static Page<ModuloAdminDTO> criarPageModulosAdminDTOMock(Pageable pageable) {

        List<ModuloAdminDTO> modulos = new ArrayList<>();
        modulos.add(retornarModuloAdminDTO());
        modulos.add(retornarModuloAdminDTO());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), modulos.size());
        return new PageImpl<>(modulos.subList(start, end), pageable, modulos.size());
    }


}