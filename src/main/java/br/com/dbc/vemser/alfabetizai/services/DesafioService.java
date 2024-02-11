
package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class DesafioService {
    private final IDesafioRepository desafioRepository;
    private final ObjectMapper objectMapper;
    private final ModuloService moduloService;

    public List<DesafioDTO>listarDesafios() throws RegraDeNegocioException{
        return desafioRepository.findAll().stream()
                .map(this :: retornarDTO)
                .collect(Collectors.toList());
    }

    public DesafioDTO create(DesafioCreateDTO desafio)throws Exception  {
        log.info("camada service criação desafio ");
        Modulo modulo = objectMapper.convertValue(moduloService.listarPorIdModulo(desafio.getIdModulo()), Modulo.class);

        Desafio desafioEntity = converterDTO(desafio);
        desafioEntity.setModulo(modulo);

        desafioEntity = desafioRepository.save(desafioEntity);
        log.info("desafio Criado na camada Service!");
        return retornarDTO(desafioEntity);
    }

    public DesafioDTO buscarDesafioPorId(Integer idDesafio) throws RegraDeNegocioException {
        Desafio desafioEntity = desafioRepository.getById(idDesafio);

        if (desafioEntity != null) {
            return retornarDTO(desafioEntity);
        } else {
            log.error("Erro ao buscar desafio por ID");
            throw new RegraDeNegocioException("Nenhum desafio encontrado para o ID " + idDesafio);
        }
    }

    public Desafio desafioPorId(Integer idDesafio) throws ObjetoNaoEncontradoException {
        Optional<Desafio> objetoOptional = desafioRepository.findById(idDesafio);
        if (objetoOptional.isPresent()) {
            return objetoOptional.get();
        } else {
            throw new ObjetoNaoEncontradoException("Desafio com o ID " + idDesafio + " não encontrado informe um id valido");
        }
    }

    public DesafioDTO atualizar(
            Integer id, DesafioCreateDTO desafioCreateDTO) throws  RegraDeNegocioException {
        Optional<Desafio> objetoOptional = desafioRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Desafio desafio = objetoOptional.get();
            Desafio desafioAtualizacao = converterDTO(desafioCreateDTO);
            desafio.setTitulo(desafioAtualizacao.getTitulo());
            desafio.setConteudo(desafioAtualizacao.getConteudo());
            desafio.setTipo(desafioAtualizacao.getTipo());
            desafio.setInstrucao(desafioAtualizacao.getInstrucao());
            desafio.setA(desafioAtualizacao.getA());
            desafio.setB(desafioAtualizacao.getB());
            desafio.setC(desafioAtualizacao.getC());
            desafio.setD(desafioAtualizacao.getD());
            desafio.setE(desafioAtualizacao.getE());
            desafio.setAlternativaCorreta(desafioAtualizacao.getAlternativaCorreta());
            desafio.setPontos(desafioAtualizacao.getPontos());

            desafio = desafioRepository.save(desafio);

            return retornarDTO(desafio);
        }else {
            throw new  RegraDeNegocioException("Desafio com o ID " + id + " não encontrado informe um id valido");
        }
    }
    public List<DesafioDTO> listarPorIdModulo(int idModuloEscolhido) throws  RegraDeNegocioException {
        Optional<Desafio> desafios = desafioRepository.findById(idModuloEscolhido);
        if (desafios.isPresent()){
            return desafios.stream()
                    .map(this::retornarDTO)
                    .collect(Collectors.toList());
        } else {
            log.error("Erro ao listar desafios por módulo");
            throw new  RegraDeNegocioException("Desafio com o ID " + idModuloEscolhido + " não encontrado informe um id valido");
        }
    }
    public List<DesafioDTO> listardesafiosConcluidos(Integer idAluno) throws RegraDeNegocioException {
        try {
            List<Desafio> desafios = desafioRepository.listardesafiosConcluidos(idAluno);
            return desafios.stream()
                    .map(this::retornarDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Erro ao listar desafios concluidos", e);
            throw new RegraDeNegocioException("Erro ao listar desafios concluidos: " + e.getMessage());
        }
    }
    public void remover(int id) throws RegraDeNegocioException{
        Optional<Desafio> objetoOptional = desafioRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Desafio desafio = objetoOptional.get();

            if (!desafio.getModulo().isEmpty()) {
                throw new RegraDeNegocioException("Não é possível excluir o desafio pois ele está associado a outras classes.");
            }

            desafioRepository.delete(desafio);

        } else {
            throw new RegraDeNegocioException("Desafio com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void removerLogico(int id) throws RegraDeNegocioException{
        Optional<Desafio> objetoOptional = desafioRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Desafio desafio = objetoOptional.get();

            desafio.setAtivo("N");

            desafio = desafioRepository.save(desafio);

            DesafioDTO desafioDTO = retornarDTO(desafio);

        } else {
            throw new  RegraDeNegocioException("Desafio com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public Desafio converterDTO(DesafioCreateDTO dto) {
        return objectMapper.convertValue(dto, Desafio.class);
    }
    public DesafioDTO retornarDTO(Desafio entity) {
        return objectMapper.convertValue(entity, DesafioDTO.class);
    }
}