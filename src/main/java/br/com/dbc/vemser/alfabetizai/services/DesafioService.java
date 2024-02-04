package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.*;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioRepository;
import br.com.dbc.vemser.alfabetizai.repository.IModuloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DesafioService {
    private final IDesafioRepository desafioRepository;
    private final IModuloRepository moduloRepository;
    private final ObjectMapper objectMapper;

    public List<DesafioDTO>listarDesafios() throws RegraDeNegocioException{
        return desafioRepository.findAll().stream()
                .map(this :: retornarDTO)
                .collect(Collectors.toList());
    }

    public DesafioDTO create(DesafioCreateDTO desafio)throws Exception  {
        log.error("camada service criação desafio ");
        Desafio desafioEntity = converterDTO(desafio);
        log.error("criando desafio");
        return retornarDTO(desafioRepository.save(desafioEntity));
    }
    public DesafioDTO buscarDesafioPorId(Integer idDesafio) {
        Desafio desafioEntity = desafioRepository.getById(idDesafio);

        return objectMapper.convertValue(desafioEntity, DesafioDTO.class);
    }

    public DesafioDTO atualizar(
            Integer id, DesafioCreateDTO desafioCreateDTO) throws Exception {
        Optional<Desafio> objetoOptional = desafioRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Desafio desafio = objetoOptional.get();
            Desafio desafioAtualizacao = converterDTO(desafioCreateDTO);
            desafio.setIdModulo(desafioAtualizacao.getIdModulo());
            desafio.setTitulo(desafioAtualizacao.getTitulo());
            desafio.setConteudo(desafioAtualizacao.getConteudo());
            desafio.setTipoDesafio(desafioAtualizacao.getTipoDesafio());
            desafio.setInstrucao(desafioAtualizacao.getInstrucao());
            desafio.setPontos(desafioAtualizacao.getPontos());

            desafio = desafioRepository.save(desafio);

            DesafioDTO desafioDTO = retornarDTO(desafio);

            return desafioDTO;
        }else {
            throw new ObjetoNaoEncontradoException("Desafio com o ID " + id + " não encontrado informe um id valido");
        }
    }
    public void remover(int id) throws Exception {
        Optional<Desafio> objetoOptional = desafioRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Desafio desafio = objetoOptional.get();
            desafioRepository.delete(desafio);

            DesafioDTO desafioDTO = retornarDTO(desafio);
        } else {
            throw new ObjetoNaoEncontradoException("Desafio com o ID " + id + " não encontrado informe um id valido");
        }
    }
    public List<DesafioDTO> listarPorIdModulo(int idModuloEscolhido) throws Exception {
        Optional<Desafio> desafios = desafioRepository.findById(idModuloEscolhido);
            if (desafios.isPresent()){
                return desafios.stream()
                    .map(this::retornarDTO)
                    .collect(Collectors.toList());
        } else {
            log.error("Erro ao listar desafios por módulo");
                throw new ObjetoNaoEncontradoException("Desafio com o ID " + idModuloEscolhido + " não encontrado informe um id valido");
        }
    }
// Verificar se iremos utilizar essa service.
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