package br.com.dbc.vemser.alfabetizai.services;


import br.com.dbc.vemser.alfabetizai.dto.ModuloCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.ModuloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ModuloService {
    private final ModuloRepository moduloRepository;
    private final ProfessorService professorService;
    private final ObjectMapper objectMapper;


    public ModuloDTO criar(ModuloCreateDTO moduloCreateDTO) throws Exception {
        ProfessorDTO professorDTO = professorService.buscarProfessorPorIdUsuario(moduloCreateDTO.getIdProfessor());
        Modulo modulo = objectMapper.convertValue(moduloCreateDTO, Modulo.class);

        Professor professor = objectMapper.convertValue(professorDTO, Professor.class);
        modulo.setAutor(professor);
        Modulo moduloAdicionado = moduloRepository.adicionar(modulo);
        return objectMapper.convertValue(moduloAdicionado, ModuloDTO.class);
    }

    public void remover(Integer id) throws Exception {
        boolean conseguiuRemover = moduloRepository.remover(id);
    }

    public ModuloDTO atualizar(Integer id, ModuloCreateDTO moduloCreateDTO) throws Exception {
        ProfessorDTO professorDTO = professorService.buscarProfessorPorIdUsuario(moduloCreateDTO.getIdProfessor());
        Professor professor = objectMapper.convertValue(professorDTO, Professor.class);
        Modulo moduloEntity = objectMapper.convertValue(moduloCreateDTO, Modulo.class);
        moduloEntity.setAutor(professor);
        moduloEntity = moduloRepository.editar(id, moduloEntity);

        return objectMapper.convertValue(moduloEntity, ModuloDTO.class);
    }

    public void editarAprovacaoPorAdmin(Integer idAdmin, Integer idModulo, String aprovacaoModulo) {
        try {
            boolean conseguiuEditar = moduloRepository.editarAprovacaoPorAdmin(idAdmin, idModulo, aprovacaoModulo);
            log.info("editado? " + conseguiuEditar + "| com id=" + idModulo);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public List<ModuloDTO> listar() throws Exception {
        List<Modulo> listaModuloBanco = moduloRepository.listar();

        return listaModuloBanco.stream()
                .map(modulo -> objectMapper.convertValue(modulo, ModuloDTO.class))
                .collect(Collectors.toList());
    }

    public List<ModuloDTO> listarPorIdProfessor(Integer idProfessor)throws Exception{
        List<Modulo> listaPorId = moduloRepository.listarPorIdProfessor(idProfessor);

        return listaPorId.stream()
                .map(modulo -> objectMapper.convertValue(modulo, ModuloDTO.class))
                .collect(Collectors.toList());
    }

    public void listarSemAprovacao() {
        try {
            moduloRepository.listarSemAprovacao().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarAprovados() {
        try {
            moduloRepository.listarAprovados().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarReprovados() {
        try {
            moduloRepository.listarReprovados().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public ModuloDTO buscarModuloPorId(Integer idUsuario) throws Exception {
       Modulo modulo = moduloRepository.buscarModuloPorId(idUsuario);

        return objectMapper.convertValue(modulo, ModuloDTO.class);
    }

    public List<ModuloDTO> listarModulosConcluidos(Integer idAluno) throws RegraDeNegocioException {
        try {
            List<Modulo> modulos = moduloRepository.listarModulosConcluidos(idAluno);
            return modulos.stream()
                    .map(modulo -> objectMapper.convertValue(modulo, ModuloDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Erro ao listar modulos concluidos", e);
            throw new RegraDeNegocioException("Erro ao listar modulos concluidos: " + e.getMessage());
        }
    }
}