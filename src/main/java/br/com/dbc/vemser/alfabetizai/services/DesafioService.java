
package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
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
@Slf4j
@Service
public class DesafioService {
    private final IDesafioRepository desafioRepository;
    private final IModuloRepository moduloRepository;
    private final ObjectMapper objectMapper;

    public List<DesafioDTO>listarDesafios(){
        return desafioRepository.findAll().stream()
                .map(this :: retornarDTO)
                .collect(Collectors.toList());
    }

    public DesafioDTO create(DesafioCreateDTO desafio) throws Exception {
        log.info("camada service criação desafio ");

        Modulo modulo = moduloRepository.findById(desafio.getIdModulo())
                .orElseThrow(() -> new RegraDeNegocioException("Módulo não encontrado com o ID: " + desafio.getIdModulo()));

        Desafio desafioEntity = converterDTO(desafio);
        desafioEntity.setModulo(modulo);
        desafioEntity = desafioRepository.save(desafioEntity);

        log.info("Desafio criado na camada Service!");
        return retornarDTO(desafioEntity);
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

    public List<DesafioDTO> listarPorIdModulo(int idModuloEscolhido) throws RegraDeNegocioException {
        List<Desafio> desafios = desafioRepository.findByModuloId(idModuloEscolhido);

        if (!desafios.isEmpty()) {
            return desafios.stream()
                    .map(this::retornarDTO)
                    .collect(Collectors.toList());
        } else {
            log.error("Erro ao listar desafios por módulo");
            throw new RegraDeNegocioException("Não foram encontrados desafios para o módulo com o ID " + idModuloEscolhido);
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