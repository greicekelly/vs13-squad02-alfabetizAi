package br.com.dbc.vemser.alfabetizai.services;


import br.com.dbc.vemser.alfabetizai.dto.*;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloAdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Professor;

import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IModuloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ModuloService {
    private final IModuloRepository moduloRepository;
    private final ProfessorService professorService;
    private final ObjectMapper objectMapper;

    public ModuloDTO criar(ModuloCreateDTO moduloCreateDTO) throws Exception {
        ProfessorDTO professorDTO = professorService.buscarProfessorPorId(moduloCreateDTO.getIdProfessor());

        Modulo modulo = converterDTO(moduloCreateDTO);
        Professor professor = objectMapper.convertValue(professorDTO, Professor.class);

        modulo.setProfessor(professor);

        Modulo moduloAdicionado = converterDTO(moduloCreateDTO);
        return retornarDTO(moduloRepository.save(modulo));
    }

    public Page<ModuloDTO> listar(Pageable pageable) throws Exception {
        return moduloRepository.findAll(pageable)
                .map(this::retornarDTO);
    }

    public ModuloDTO listarPorIdModulo(Integer idModulo) throws Exception {
        Optional<Modulo> moduloOptional = moduloRepository.findById(idModulo);

        if (moduloOptional.isPresent()) {
            Modulo modulo = moduloOptional.get();
            return objectMapper.convertValue(retornarDTO(modulo), ModuloDTO.class);
        } else {
            throw new RegraDeNegocioException("Módulo não encontrado.");
        }
    }

//    public List<ModuloDTO> listarPorIdProfessor(Integer idProfessor) {
//        List<Modulo> listaPorId = moduloRepository.findAllByIdProfessor(idProfessor);
//        return listaPorId.stream()
//                .map(this::retornarDTO)
//                .collect(Collectors.toList());
//    }

    public Page<ModuloProfessorDTO> pagePorIdProfessor(Integer idProfessor, Pageable pageable) {

            Page<Modulo> modulos = moduloRepository.findAllByIdProfessor(idProfessor, pageable);

            return modulos.map(modulo -> objectMapper.convertValue(modulo, ModuloProfessorDTO.class));

    }

    public Page<ModuloAdminDTO> pagePorIdAdmin(Integer idAdmin, Pageable pageable) {

        Page<Modulo> modulos = moduloRepository.findAllByAdmin(idAdmin, pageable);
        System.out.println(modulos);

        return modulos.map(modulo -> objectMapper.convertValue(modulo, ModuloAdminDTO.class));

    }

    public ModuloDTO atualizar(Integer id, ModuloCreateDTO moduloCreateDTO) throws Exception {
        Optional<Modulo> objetoOptional = moduloRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Modulo modulo = objetoOptional.get();
            Modulo moduloAtualizacao = converterDTO(moduloCreateDTO);

            modulo.setTitulo(moduloAtualizacao.getTitulo());
            modulo.setConteudo(moduloAtualizacao.getConteudo());
            modulo.setClassificacao(moduloAtualizacao.getClassificacao());
            modulo.setFoiAprovado(moduloAtualizacao.getFoiAprovado());
            modulo.setIdProfessor(moduloAtualizacao.getIdProfessor());

            modulo = moduloRepository.save(modulo);

            return retornarDTO(modulo);
        } else {
            throw new ObjetoNaoEncontradoException("Modulo com o ID " + id + " não encontrado. Informe um ID válido");
        }
    }

    public void remover(int id) throws Exception {
        Optional<Modulo> objetoOptional = moduloRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Modulo modulo = objetoOptional.get();

            if (!modulo.getDesafios().isEmpty() || !modulo.getAlunos().isEmpty()) {
                throw new Exception("Não é possível excluir o módulo pois ele está associado a outras classes.");
            }
            moduloRepository.delete(modulo);

        } else {
            throw new ObjetoNaoEncontradoException("Modulo com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public List<Modulo> listarSemAprovacao() throws Exception {
        try {
            return moduloRepository.listarSemAprovacao();
        } catch (Exception e) {
            throw new Exception("Erro ao listar módulos sem aprovação", e);
        }
    }

    public List<Modulo> listarAprovados() throws Exception {
        try {
            return moduloRepository.listarAprovados();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao listar módulos aprovados", e);
        }
    }

    public List<Modulo> listarReprovados() throws Exception {
        try {
            return moduloRepository.listarReprovados();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao listar módulos Reprovados", e);
        }
    }

    public List<ModuloDTO> listarModulosConcluidos(Integer idAluno) throws Exception {
        try {
            List<Modulo> modulos = moduloRepository.listarModulosConcluidos(idAluno);
            return modulos.stream()
                    .map(this::retornarDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Erro ao listar modulos concluidos", e);
            throw new Exception("Erro ao listar modulos concluidos: " + e.getMessage());
        }
    }
    public Modulo converterDTO(ModuloCreateDTO dto) {
        return objectMapper.convertValue(dto, Modulo.class);
    }
    public ModuloDTO retornarDTO(Modulo entity) {
        return objectMapper.convertValue(entity, ModuloDTO.class);
    }

    public ModuloDTO moduloPorId (Integer id){
        return retornarDTO(moduloRepository.getById(id));
    }

    public ModuloDTO save(ModuloDTO moduloDTO){
        Modulo modulo = objectMapper.convertValue(moduloDTO, Modulo.class);

        return retornarDTO(moduloRepository.save(modulo));
    }
}