package br.com.dbc.vemser.alfabetizai.services;


import br.com.dbc.vemser.alfabetizai.dto.*;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Professor;

import br.com.dbc.vemser.alfabetizai.repository.IModuloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        return  retornarDTO(moduloRepository.save(modulo));
    }
        public List<ModuloDTO> listar() throws Exception {
        return moduloRepository.findAll().stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }
    public List<ModuloDTO> listarPorIdProfessor(Integer idProfessor)throws Exception{
        Optional<Modulo> listaPorId = moduloRepository.findById(idProfessor);
        return listaPorId.stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }
    public ModuloDTO atualizar(Integer id, ModuloCreateDTO moduloCreateDTO) throws Exception {
        Optional<Modulo> objetoOptional = moduloRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Modulo modulo = objetoOptional.get();
            Modulo moduloAtualizacao = converterDTO(moduloCreateDTO);

            moduloAtualizacao.setTitulo(moduloCreateDTO.getTitulo());
            moduloAtualizacao.setConteudo(moduloCreateDTO.getConteudo());
            moduloAtualizacao.setClassificacao(moduloCreateDTO.getClassificacao());
            moduloAtualizacao.setIdProfessor(moduloCreateDTO.getIdProfessor());

            modulo = moduloRepository.save(modulo);

            ModuloDTO moduloDTO = retornarDTO(modulo);

            return moduloDTO;
        } else {
            throw new ObjetoNaoEncontradoException("Modulo com o ID " + id + " não encontrado. Informe um ID válido");
        }
    }
      public void remover(int id) throws Exception {
        Optional<Modulo> objetoOptional = moduloRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Modulo modulo = objetoOptional.get();

            if (!modulo.getDesafios().isEmpty()) {
                throw new Exception("Não é possível excluir o módulo pois ele está associado a outras classes.");
            }
            moduloRepository.delete(modulo);

            ModuloDTO moduloDTO = retornarDTO(modulo);
        } else {
            throw new ObjetoNaoEncontradoException("Modulo com o ID " + id + " não encontrado informe um id valido");
        }
    }


//    public void editarAprovacaoPorAdmin(Integer idAdmin, Integer idModulo, String aprovacaoModulo) {
//        try {
//            boolean conseguiuEditar = moduloRepository.editarAprovacaoPorAdmin(idAdmin, idModulo, aprovacaoModulo);
//            log.info("editado? " + conseguiuEditar + "| com id=" + idModulo);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }

//    public void listarSemAprovacao() {
//        try {
//            moduloRepository.listarSemAprovacao().forEach(System.out::println);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//    public void listarAprovados() {
//        try {
//            moduloRepository.listarAprovados().forEach(System.out::println);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//    public void listarReprovados() {
//        try {
//            moduloRepository.listarReprovados().forEach(System.out::println);
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//    public ModuloDTO buscarModuloPorId(Integer idUsuario) throws Exception {
//       Modulo modulo = moduloRepository.buscarModuloPorId(idUsuario);
//
//        return objectMapper.convertValue(modulo, ModuloDTO.class);
//    }
//
//    public List<ModuloDTO> listarModulosConcluidos(Integer idAluno) throws RegraDeNegocioException {
//        try {
//            List<Modulo> modulos = moduloRepository.listarModulosConcluidos(idAluno);
//            return modulos.stream()
//                    .map(modulo -> objectMapper.convertValue(modulo, ModuloDTO.class))
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            log.error("Erro ao listar modulos concluidos", e);
//            throw new RegraDeNegocioException("Erro ao listar modulos concluidos: " + e.getMessage());
//        }
//    }

    public Modulo converterDTO(ModuloCreateDTO dto) {
        return objectMapper.convertValue(dto, Modulo.class);
    }
    public ModuloDTO retornarDTO(Modulo entity) {
        return objectMapper.convertValue(entity, ModuloDTO.class);
    }
}