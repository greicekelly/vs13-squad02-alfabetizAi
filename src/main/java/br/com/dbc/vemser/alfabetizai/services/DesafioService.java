package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.*;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.DesafioAlternativas;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioAlternativasRepository;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DesafioService {
    private final IDesafioRepository desafioRepository;
    private final IDesafioAlternativasRepository desafioAlternativasRepository;
    private final ObjectMapper objectMapper;

    public List<DesafioDTO>listarDesafios() throws RegraDeNegocioException{
        return desafioRepository.findAll().stream()
                .map(this :: retornarDTO)
                .collect(Collectors.toList());
    }

    public DesafioDTO create(DesafioCreateDTO desafio)throws RegraDeNegocioException {
        log.error("camada service criação desafio ");
        Desafio desafioEntity = converterDTO(desafio);
        log.error("criando desafio");
        return retornarDTO(desafioRepository.save(desafioEntity));
    }
    public DesafioDTO buscarDesafioPorId(Integer idDesafio) {
        Desafio desafioEntity = desafioRepository.getById(idDesafio);

        return objectMapper.convertValue(desafioEntity, DesafioDTO.class);
    }


//    public DesafioDTO editar(Integer id, DesafioCreateDTO atualizarDesafio) throws RegraDeNegocioException {
//        try {
//            Desafio desafioEntity = objectMapper.convertValue(atualizarDesafio, Desafio.class);
//            desafioEntity = desafioRepository.editar(id, desafioEntity);
//            return objectMapper.convertValue(desafioEntity, DesafioDTO.class);
//        } catch (BancoDeDadosException e) {
//            log.error("Erro ao editar desafio", e);
//            throw new RegraDeNegocioException("Erro ao editar desafio no banco de dados: " + e.getMessage());
//        }
//    }
//    public void remover(Integer id) throws RegraDeNegocioException {
//        try {
//            desafioRepository.remover(id);
//        } catch (BancoDeDadosException e) {
//            log.error("Erro ao remover desafio", e);
//            throw new RegraDeNegocioException("Erro ao remover desafio no banco de dados: " + e);
//        }
//    }
//
//    public DesafioDTO buscarDesafioPorId(int idDesafio) throws Exception {
//        Desafio desafio = desafioRepository.buscarDesafioPorId(idDesafio);
//
//        return objectMapper.convertValue(desafio, DesafioDTO.class);
//    }
//    public List<DesafioDTO> listarModuloPorId(int idModuloEscolhido) throws RegraDeNegocioException {
//        try {
//            List<Desafio> desafios = desafioRepository.listarModuloporId(idModuloEscolhido);
//            return desafios.stream()
//                    .map(desafio -> objectMapper.convertValue(desafio, DesafioDTO.class))
//                    .collect(Collectors.toList());
//        } catch (BancoDeDadosException e) {
//            log.error("Erro ao listar desafios por módulo", e);
//            throw new RegraDeNegocioException("Erro ao listar desafios por módulo: " + e.getMessage());
//        }
//    }
//
//    public List<DesafioDTO> listardesafiosConcluidos(Integer idAluno) throws RegraDeNegocioException {
//        try {
//            List<Desafio> desafios = desafioRepository.listardesafiosConcluidos(idAluno);
//            return desafios.stream()
//                    .map(desafio -> objectMapper.convertValue(desafio, DesafioDTO.class))
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            log.error("Erro ao listar desafios concluidos", e);
//            throw new RegraDeNegocioException("Erro ao listar desafios concluidos: " + e.getMessage());
//        }
//    }

    public Desafio converterDTO(DesafioCreateDTO dto) {
        return objectMapper.convertValue(dto, Desafio.class);
    }
    public DesafioDTO retornarDTO(Desafio entity) {
        return objectMapper.convertValue(entity, DesafioDTO.class);
    }




}

// CODIGOS ANTERIORES JA EDITADOS PARA JPA

//    public List<DesafioDTO> listarDesafios() throws RegraDeNegocioException {
//        try {
//            List<Desafio> desafios = desafioRepository.list();
//            return desafios.stream()
//                    .map(desafio -> objectMapper.convertValue(desafio, DesafioDTO.class))
//                    .collect(Collectors.toList());
//        } catch (BancoDeDadosException e) {
//            log.error("Erro ao listar desafios", e);
//            throw new RegraDeNegocioException("Algum problema ocorreu ao listar desafios, revise os dados");
//        }
//    }