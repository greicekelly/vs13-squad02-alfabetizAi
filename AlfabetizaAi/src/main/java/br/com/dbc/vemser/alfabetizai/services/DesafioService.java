package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.repository.DesafioRepository;
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
    private final DesafioRepository desafioRepository;
    private final ObjectMapper objectMapper;
    public List<DesafioDTO> listarDesafios() throws RegraDeNegocioException {
        try {
            List<Desafio> desafios = desafioRepository.listar();
            return desafios.stream()
                    .map(desafio -> objectMapper.convertValue(desafio, DesafioDTO.class))
                    .collect(Collectors.toList());
        } catch (BancoDeDadosException e) {
            log.error("Erro ao listar desafios", e);
            throw new RegraDeNegocioException("Algum problema ocorreu ao listar desafios, revise os dados");
        }
    }
    public List<DesafioDTO> listarModuloPorId(int idModuloEscolhido) throws RegraDeNegocioException {
        try {
            List<Desafio> desafios = desafioRepository.listarModuloporId(idModuloEscolhido);
            if (desafios.isEmpty()) {
                throw new RegraDeNegocioException("Nenhum desafio encontrado para o módulo com ID: " + idModuloEscolhido);
            }
            return desafios.stream()
                    .map(desafio -> objectMapper.convertValue(desafio, DesafioDTO.class))
                    .collect(Collectors.toList());
        } catch (BancoDeDadosException e) {
            log.error("Erro ao listar desafios por módulo", e);
            throw new RegraDeNegocioException("Erro ao listar desafios por módulo: " + e.getMessage());
        }
    }
    public DesafioDTO adicionar(DesafioCreateDTO desafioCreateDTO) throws RegraDeNegocioException {
        try {
            Desafio desafioEntity = objectMapper.convertValue(desafioCreateDTO, Desafio.class);
            desafioEntity = desafioRepository.adicionar(desafioEntity);
            return objectMapper.convertValue(desafioEntity, DesafioDTO.class);
        } catch (BancoDeDadosException e) {
            log.error("Erro ao adicionar desafio", e);
            throw new RegraDeNegocioException("Erro ao adicionar desafio no banco de dados: " + e.getMessage());
        }
    }
    public DesafioDTO editar(Integer id, DesafioCreateDTO atualizarDesafio) throws RegraDeNegocioException {
        try {
            Desafio desafioEntity = objectMapper.convertValue(atualizarDesafio, Desafio.class);
            desafioEntity = desafioRepository.editar(id, desafioEntity);
            return objectMapper.convertValue(desafioEntity, DesafioDTO.class);
        } catch (BancoDeDadosException e) {
            log.error("Erro ao editar desafio", e);
            throw new RegraDeNegocioException("Erro ao editar desafio no banco de dados: " + e.getMessage());
        }
    }
    public void remover(Integer id) throws RegraDeNegocioException {
        try {
            desafioRepository.remover(id);
        } catch (BancoDeDadosException e) {
            log.error("Erro ao remover desafio", e);
            throw new RegraDeNegocioException("Erro ao remover desafio no banco de dados: " + e);
        }}
}
