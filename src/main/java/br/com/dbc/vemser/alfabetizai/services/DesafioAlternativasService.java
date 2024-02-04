package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.DesafioAlternativas;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioAlternativasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
@Slf4j
public class DesafioAlternativasService {
private final IDesafioAlternativasRepository desafioAlternativasRepository;
private final ObjectMapper objectMapper;

    public List<DesafioAlternativasDTO>listarAlternativas() throws RegraDeNegocioException{
        return desafioAlternativasRepository.findAll().stream()
                .map(this::retornarAlternativasDTO)
                .collect(Collectors.toList());
    }

     public DesafioAlternativasDTO create(DesafioAlternativasCreateDTO desafioAlternativas) throws RegraDeNegocioException {
    DesafioAlternativas desafioAlt = converterAlternativasDTO(desafioAlternativas);
    return retornarAlternativasDTO(desafioAlternativasRepository.save(desafioAlt));
     }






    public DesafioAlternativas converterAlternativasDTO(DesafioAlternativasCreateDTO dto) {
        return objectMapper.convertValue(dto, DesafioAlternativas.class);
    }
    public DesafioAlternativasDTO retornarAlternativasDTO(DesafioAlternativas entity) {
        return objectMapper.convertValue(entity, DesafioAlternativasDTO.class);
    }
}

