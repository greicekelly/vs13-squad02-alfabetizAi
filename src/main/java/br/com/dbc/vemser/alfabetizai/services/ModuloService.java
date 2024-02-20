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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        modulo.setAtivo("S");
        modulo.setFoiAprovado("N");

        Modulo moduloAdicionado = converterDTO(moduloCreateDTO);
        return retornarDTO(moduloRepository.save(modulo));
    }

    public Page<ModuloDTO> listar(Pageable pageable) throws Exception {
        return moduloRepository.findAll(pageable)
                .map(this::retornarDTO);
    }

    public ModuloDTO listarPorIdModulo(Integer idModulo) throws Exception {
        Modulo modulo = buscarModuloPorId(idModulo);

        return objectMapper.convertValue(modulo, ModuloDTO.class);

//        Optional<Modulo> moduloOptional = moduloRepository.findById(idModulo);
//
//        if (moduloOptional.isPresent()) {
//            Modulo modulo = moduloOptional.get();
//            return objectMapper.convertValue(modulo, ModuloDTO.class);
//        } else {
//            throw new RegraDeNegocioException("Módulo não encontrado.");
//        }
    }

    public Modulo buscarModuloPorId(Integer idModulo) throws Exception {
        Optional<Modulo> moduloOptional = moduloRepository.findById(idModulo);

        if (moduloOptional.isPresent()) {
            return moduloOptional.get();
        } else {
            throw new ObjetoNaoEncontradoException("Módulo de " + idModulo +" não encontrado.");
        }

    }

    public Page<ModuloDTO> pagePorIdProfessor(Integer idProfessor, Pageable pageable) {

            Page<Modulo> modulos = moduloRepository.findAllByIdProfessor(idProfessor, pageable);

            return modulos.map(modulo -> objectMapper.convertValue(modulo, ModuloDTO.class));

    }

    public Page<ModuloProfessorDTO> pagePorProfessor(Integer idProfessor, String aprovacao, Pageable pageable) {
        Page<Modulo> modulos;
        String filtro;

        if(aprovacao != null){
            if(aprovacao.equals("Sem Analise")){
                filtro = "N";
            } else if(aprovacao.equals("Aprovado")){
                filtro = "S";
            } else {
                filtro = "R";
            }
            modulos = moduloRepository.findAllProfessorComFiltro(idProfessor, filtro, pageable);
        }

        modulos = moduloRepository.findAllByIdProfessor(idProfessor, pageable);

        return modulos.map(modulo -> objectMapper.convertValue(modulo, ModuloProfessorDTO.class));

    }

    public Page<ModuloAdminDTO> pagePorIdAdmin(Integer idAdmin, Pageable pageable) {

        Page<Modulo> modulos = moduloRepository.findAllByAdmin(idAdmin, pageable);

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
                throw new RegraDeNegocioException("Não é possível excluir o módulo pois ele está associado a outras classes.");
            }
            moduloRepository.delete(modulo);

        } else {
            throw new ObjetoNaoEncontradoException("Modulo com o ID " + id + " não encontrado informe um id valido");
        }
    }
    public void removerLogico(int id) throws Exception {
        Optional<Modulo> objetoOptional = moduloRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Modulo modulo = objetoOptional.get();

            modulo.setAtivo("N");

            modulo = moduloRepository.save(modulo);

        } else {
            throw new ObjetoNaoEncontradoException("Modulo com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public List<ModuloDTO> listarPorAprovacao(String aprovacao) throws Exception {
        String filtro;
        if(aprovacao.equals("Sem Analise")){
            filtro = "N";
        } else if(aprovacao.equals("Aprovado")){
            filtro = "S";
        } else {
            filtro = "R";
        }

        List<Modulo> modulo = moduloRepository.findAllByFoiAprovado(filtro);

        return modulo.stream().map(this::retornarDTO).collect(Collectors.toList());
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

    public ModuloDTO save(ModuloDTO moduloDTO){
        Modulo modulo = objectMapper.convertValue(moduloDTO, Modulo.class);

        return retornarDTO(moduloRepository.save(modulo));
    }
}