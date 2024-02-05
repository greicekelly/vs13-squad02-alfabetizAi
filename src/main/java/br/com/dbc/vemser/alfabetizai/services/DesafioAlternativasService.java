package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.DesafioAlternativas;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioAlternativasRepository;
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
public class DesafioAlternativasService {
    private final IDesafioAlternativasRepository desafioAlternativasRepository;
    private final ObjectMapper objectMapper;

    private final DesafioService desafioService;

    public List<DesafioAlternativasDTO> listarAlternativas() throws RegraDeNegocioException {
        return desafioAlternativasRepository.findAll().stream()
                .map(this::retornarAlternativasDTO)
                .collect(Collectors.toList());
    }
    public DesafioAlternativasDTO create(DesafioAlternativasCreateDTO desafioAlternativas) throws RegraDeNegocioException {
        DesafioAlternativas desafioAlt = converterAlternativasDTO(desafioAlternativas);
        Desafio desafio = objectMapper.convertValue(desafioService.buscarDesafioPorId(desafioAlternativas.getIdDesafio()), Desafio.class);

        desafioAlt.setDesafio(desafio);

        desafioAlt = desafioAlternativasRepository.save(desafioAlt);

        return retornarAlternativasDTO(desafioAlt);
    }

    public DesafioAlternativasDTO atualizar(
            Integer id, DesafioAlternativasCreateDTO desafioAlternativasCreateDTO) throws Exception {
        Optional<DesafioAlternativas> objetoOptional = desafioAlternativasRepository.findById(id);
        if (objetoOptional.isPresent()) {
            DesafioAlternativas desafioAlternativas = objetoOptional.get();
            DesafioAlternativas desafioAtualizacao = converterAlternativasDTO(desafioAlternativasCreateDTO);
            //desafioAlternativas.setIdDesafio(desafioAtualizacao.getIdDesafio());
            desafioAlternativas.setA(desafioAtualizacao.getA());
            desafioAlternativas.setB(desafioAtualizacao.getB());
            desafioAlternativas.setC(desafioAtualizacao.getC());
            desafioAlternativas.setD(desafioAtualizacao.getD());
            desafioAlternativas.setE(desafioAtualizacao.getE());
            desafioAlternativas.setAlternativaCorreta(desafioAtualizacao.getAlternativaCorreta());

            desafioAlternativas = desafioAlternativasRepository.save(desafioAlternativas);

            DesafioAlternativasDTO desafioAlternativasDTO = retornarAlternativasDTO(desafioAlternativas);

            return desafioAlternativasDTO;
        }else {
            throw new ObjetoNaoEncontradoException("Desafio Alternativas  com o ID " + id + " não encontrado informe um id valido");
    }}

    public void remover(int id) throws Exception {
        Optional<DesafioAlternativas> objetoOptional = desafioAlternativasRepository.findById(id);
        if (objetoOptional.isPresent()) {
            DesafioAlternativas desafioAlternativas = objetoOptional.get();

            if (!desafioAlternativas.getDesafio().isEmpty()) {
                throw new Exception("Não é possível excluir o desafio pois ele está associado a outras classes.");
            }
            desafioAlternativasRepository.delete(desafioAlternativas);

            DesafioAlternativasDTO desafioAlternativasDTO = retornarAlternativasDTO(desafioAlternativas);

        } else {
            throw new ObjetoNaoEncontradoException("Desafio Alternativas com o ID " + id + " não encontrado informe um id valido");
        }
    }
        public DesafioAlternativas converterAlternativasDTO (DesafioAlternativasCreateDTO dto){
            return objectMapper.convertValue(dto, DesafioAlternativas.class);
        }
        public DesafioAlternativasDTO retornarAlternativasDTO (DesafioAlternativas entity){
            return objectMapper.convertValue(entity, DesafioAlternativasDTO.class);
        }
    }

