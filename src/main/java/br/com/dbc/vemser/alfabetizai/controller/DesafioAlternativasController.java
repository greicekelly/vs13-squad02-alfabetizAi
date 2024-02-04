package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.DesafioAlternativasService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/alternativas")
@Slf4j
public class DesafioAlternativasController {

    private DesafioAlternativasService desafioAlternativasService;

    @GetMapping
    public ResponseEntity<List<DesafioAlternativasDTO>> listar() throws RegraDeNegocioException {
        log.info("Listando Alternativa Desafios.");
        List<DesafioAlternativasDTO> desafiosAltListados = desafioAlternativasService.listarAlternativas();
        log.info("Alternativas Listadas");
        return new ResponseEntity<>(desafiosAltListados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DesafioAlternativasDTO> criar(@Valid @RequestBody DesafioAlternativasCreateDTO desafioAlternativasCreateDTO) throws Exception {
        log.info("Criando Alternativas desafio");
        DesafioAlternativasDTO desafioDTO = desafioAlternativasService.create(desafioAlternativasCreateDTO);
        log.info("Alternativas Desafio criado");
        return new ResponseEntity<>(desafioDTO, HttpStatus.OK);
    }


}