package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.services.ResponsavelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/responsavel")
@Slf4j

public class ResponsavelController {

    private final ResponsavelService responsavelService;

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> listar() throws Exception {
        return new ResponseEntity<>(responsavelService.listar(), HttpStatus.OK);
    }
//
//    @GetMapping("/{idResponsavel}")
//    public ResponseEntity<ResponsavelDTO> buscarPorIdResponsavel(@PathVariable("idResponsavel") Integer idResponsavel) throws Exception {
//        return new ResponseEntity<>(responsavelService.buscarResponsavelPorId(idResponsavel), HttpStatus.OK);
//    }
//
//    @GetMapping("/{idResponsavel}/desafios")
//    public ResponseEntity<List<DesafioDTO>> listarDesafiosConcluidos(@PathVariable("idResponsavel") Integer idResponsavel) throws Exception {
//        return new ResponseEntity<>(responsavelService.listarDesafiosConcluidos(idResponsavel), HttpStatus.OK);
//    }
//
//    @GetMapping("/{idResponsavel}/modulos")
//    public ResponseEntity<List<ModuloDTO>> listarModulosConcluidos(@PathVariable("idResponsavel") Integer idResponsavel) throws Exception {
//        return new ResponseEntity<>(responsavelService.listarModulosConcluidos(idResponsavel), HttpStatus.OK);
//    }
//
    @PostMapping
    public ResponseEntity<ResponsavelDTO> criar(@Valid @RequestBody ResponsavelCreateDTO responsavelCreateDTO) throws Exception {
        log.info("Criando responsavel");
        ResponsavelDTO responsavelDTO = responsavelService.criar(responsavelCreateDTO);
        log.info("Responsavel criado");
        return new ResponseEntity<>(responsavelDTO, HttpStatus.OK);
    }

    @PutMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelDTO> atualizar(@PathVariable("idResponsavel") Integer id,
                                                @Valid @RequestBody ResponsavelCreateDTO responsavelCreateDTO) throws Exception {
        log.info("Atualizando responsavel");
        ResponsavelDTO responsavelAtualizado = responsavelService.atualizar(id, responsavelCreateDTO);
        log.info("Responsavel atualizado");
        return new ResponseEntity<>(responsavelAtualizado, HttpStatus.OK);
    }
//
//    @DeleteMapping("/{idResponsavel}")
//    public ResponseEntity<Void> deletar(@PathVariable("idResponsavel") Integer id) throws Exception {
//        log.info("Deletando responsavel");
//        responsavelService.remover(id);
//        log.info("Responsavel deletado");
//        return ResponseEntity.ok().build();
//    }
//
//
//    @PostMapping("/adicionar/{idUsuario}")
//    public ResponseEntity<ResponsavelDTO> adicionarResponsavel(@PathVariable("idUsuario")Integer id, @Valid @RequestBody ResponsavelAdicionarCreateDTO responsavelAdicionarCreateDTO) throws Exception {
//        log.info("Criando responsavel");
//        ResponsavelDTO responsavelDTO = responsavelService.adicionarResponsavel(id, responsavelAdicionarCreateDTO);
//        log.info("Responsavel criado");
//        return new ResponseEntity<>(responsavelDTO, HttpStatus.OK);
//    }
//


}
