package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IDesafioAlternativasController;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioAlternativasCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioAlternativasDTO;
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
public class DesafioAlternativasController implements IDesafioAlternativasController {

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
    @PutMapping("/{idDesafioAlternativas}")
    public ResponseEntity<DesafioAlternativasDTO>atualizar(@PathVariable("idDesafioAlternativas") Integer id,
            @Valid @RequestBody DesafioAlternativasCreateDTO desafioAlternativasCreateDTO)throws Exception {
        log.info("Criando Desafio com Alternativas!");
        DesafioAlternativasDTO desafioAlternativasAtualizado = desafioAlternativasService.atualizar(id, desafioAlternativasCreateDTO);
        log.info(" Desafio com Alternativas Criadas!");
        return new ResponseEntity<>(desafioAlternativasAtualizado, HttpStatus.OK);
    }
    @DeleteMapping("/{idDesafioAlternativas}")
    public ResponseEntity<Void> remover(@PathVariable("idDesafioAlternativas")Integer id)throws Exception{
        log.info("Excluindo desafio alternativas.");
        desafioAlternativasService.remover(id);
        log.info("Desafio Alternativas Excluido!");
        return ResponseEntity.ok().build();
    }
    }