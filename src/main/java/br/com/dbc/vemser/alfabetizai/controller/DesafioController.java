package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IDesafioController;
import br.com.dbc.vemser.alfabetizai.dto.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.DesafioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/desafio")
@Slf4j
public class DesafioController implements IDesafioController {
    private final DesafioService desafioService;
    @GetMapping
    public ResponseEntity<List<DesafioDTO>> listar() throws RegraDeNegocioException {
        log.info("Listando Desafios.");
        List<DesafioDTO> desafiosListados = desafioService.listarDesafios();
        log.info("Desafios Listados");
        return new ResponseEntity<>(desafiosListados, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DesafioDTO> criar(@Valid @RequestBody DesafioCreateDTO desafioCreateDTO) throws Exception {
        log.info("Criando desafio");
        DesafioDTO desafioDTO = desafioService.create(desafioCreateDTO);
        log.info("Desafio criado");
        return new ResponseEntity<>(desafioDTO, HttpStatus.OK);
    }
    @PutMapping("/{idDesafio}")
    public ResponseEntity<DesafioDTO> editar(@PathVariable("idDesafio") Integer id,
                                                @Valid @RequestBody DesafioCreateDTO desafioCreateDTO) throws Exception {
        log.info("Atualizando desafio");
        DesafioDTO desafioAtualizado = desafioService.atualizar(id, desafioCreateDTO);
        log.info("Desafio atualizado");
        return new ResponseEntity<>(desafioAtualizado, HttpStatus.OK);
    }
    @DeleteMapping("/{idDesafio}")
    public ResponseEntity<Void> remover(@PathVariable("idDesafio") Integer id) throws Exception {
        log.info("Deletando desafio");
        desafioService.remover(id);
        log.info("Desafio deletado");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{idModulo}")
    public ResponseEntity<List<DesafioDTO>> listarPorIdModulo(@PathVariable("idModulo") Integer idModulo) throws Exception {
        log.info("Listando Módulo por Id.");
        List<DesafioDTO> moduloListado = desafioService.listarPorIdModulo(idModulo);
        log.info("Módulo Listado por Id");
        return new ResponseEntity<>(moduloListado, HttpStatus.OK);
    }
}
