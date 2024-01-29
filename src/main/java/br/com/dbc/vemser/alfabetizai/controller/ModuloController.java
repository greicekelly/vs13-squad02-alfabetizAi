package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IModuloController;
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

public class ModuloController implements IModuloController {

    private final ModuloService moduloService;

    @GetMapping
    public ResponseEntity<List<ModuloDTO>> listar() throws Exception {
        return new ResponseEntity<>(moduloService.listar(), HttpStatus.OK);
    }
    @GetMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO> listarPorIdModulo(@PathVariable("idModulo") Integer idModulo) throws Exception {
        return new ResponseEntity<>(moduloService.buscarModuloPorId(idModulo), HttpStatus.OK);
    }
    @GetMapping("professor/{idProfessor}")
    public ResponseEntity<List<ModuloDTO>>listarPorIdProfessor(
                        @PathVariable("idProfessor") Integer idProfessor)throws Exception{
        log.info("Listando modulo por idProfessor");
        return new ResponseEntity<>(moduloService.listarPorIdProfessor(idProfessor), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ModuloDTO> criar(@Valid @RequestBody ModuloCreateDTO moduloCreateDTO) throws Exception {
        log.info("Criando modulo");
        ModuloDTO moduloDTO = moduloService.criar(moduloCreateDTO);
        log.info("Modulo criado");
        return new ResponseEntity<>(moduloDTO, HttpStatus.OK);
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
