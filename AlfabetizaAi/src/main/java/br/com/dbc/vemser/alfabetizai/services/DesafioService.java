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

    public List<DesafioDTO> getListaDesafios()throws RegraDeNegocioException {
        try {
            List<Desafio> desafios = desafioRepository.listar();
            return desafios.stream()
                    .map(desafio -> objectMapper.convertValue(desafio, DesafioDTO.class))
                    .collect(Collectors.toList());
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao listar desafios, revise os dados" + e.getMessage());
        }
    }
    public List<DesafioDTO> listarModuloporId(int idModuloEscolhido) throws RegraDeNegocioException {
        try {
            List<Desafio> desafioListado = desafioRepository.listarModuloporId(idModuloEscolhido);

            if (desafioListado.isEmpty()) {
                throw new RegraDeNegocioException("Módulo não encontrado.");
            }

            List<DesafioDTO> desafioDTOListado = desafioListado.stream()
                    .map(desafio -> objectMapper.convertValue(desafio, DesafioDTO.class))
                    .collect(Collectors.toList());

            return desafioDTOListado;
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Erro ao listar desafios por módulo: " + e.getMessage());
        }
    }

public DesafioDTO adicionar(DesafioCreateDTO desafioCreateDTO) throws RegraDeNegocioException{
    try {
        Desafio desafioEntity = objectMapper.convertValue(desafioCreateDTO, Desafio.class);
        desafioEntity = desafioRepository.adicionar(desafioEntity);
        DesafioDTO desafioDTO = objectMapper.convertValue(desafioEntity, DesafioDTO.class);

        return desafioDTO;
    } catch (BancoDeDadosException e) {
        e.printStackTrace();
        throw new RegraDeNegocioException("Erro ao adicionar desafio no banco de dados: " + e.getMessage());
    }}


    public DesafioDTO editar(Integer id, DesafioCreateDTO atualizarDesafio)throws RegraDeNegocioException {
        try {
            Desafio desafioEntity = objectMapper.convertValue(atualizarDesafio, Desafio.class);
            desafioEntity = desafioRepository.editar(id, desafioEntity);

            return objectMapper.convertValue(desafioEntity, DesafioDTO.class);

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            throw new RegraDeNegocioException("Erro ao editar desafio no banco de dados: " + e.getMessage());
        }
    }

    public void remover(Integer id)throws RegraDeNegocioException {
        try {
            boolean removeuDesafio = desafioRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            throw new RegraDeNegocioException("Erro ao editar desafio no banco de dados: " + e.getMessage());
        }
    }}




