package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.ModuloCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.services.ModuloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/modulo")
@RequiredArgsConstructor
@Slf4j
public class ModuloController  {

    private final ModuloService moduloService;
    @PostMapping
    public ResponseEntity<ModuloDTO> criar(@Valid @RequestBody ModuloCreateDTO moduloCreateDTO) throws Exception {
        log.info("Criando modulo");
        ModuloDTO moduloDTO = moduloService.criar(moduloCreateDTO);
        log.info("Modulo criado");
        return new ResponseEntity<>(moduloDTO, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ModuloDTO>> listar() throws Exception {
        log.info("Listando Modulos!");
        List<ModuloDTO> modulosListados = moduloService.listar();
        log.info("Modulos Listados!");
        return new ResponseEntity<>(modulosListados, HttpStatus.OK);
    }
    @GetMapping("professor/{idProfessor}")
    public ResponseEntity<List<ModuloDTO>>listarPorIdProfessor(
                        @PathVariable("idProfessor") Integer idProfessor)throws Exception{
        log.info("Listando modulo por idProfessor");
        List<ModuloDTO> modulosPorId = moduloService.listarPorIdProfessor(idProfessor);
        log.info("Modulos Listados!");
        return new ResponseEntity<>(modulosPorId, HttpStatus.OK);
    }
    @PutMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO> atualizar(@PathVariable("idModulo") Integer id,
                                               @Valid @RequestBody ModuloCreateDTO moduloCreateDTO) throws Exception {
        log.info("Atualizando modulo");
        ModuloDTO moduloAtualizado = moduloService.atualizar(id, moduloCreateDTO);
        log.info("Modulo atualizado");
        return new ResponseEntity<>(moduloAtualizado, HttpStatus.OK);
    }
    @DeleteMapping("/{idModulo}")
    public ResponseEntity<Void> deletar(@PathVariable("idModulo") Integer id) throws Exception {
        log.info("Deletando modulo");
        moduloService.remover(id);
        log.info("Modulo deletado");
        return ResponseEntity.ok().build();
    }
}
