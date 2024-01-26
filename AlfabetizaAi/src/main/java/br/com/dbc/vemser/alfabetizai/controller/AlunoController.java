package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/aluno")
@Slf4j
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listar() throws Exception {
        return new ResponseEntity<>(alunoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> listarPorIdAluno(@PathVariable("idAluno") Integer idAluno) throws Exception {
        return new ResponseEntity<>(alunoService.buscarAlunoPorId(idAluno), HttpStatus.OK);
    }

    @GetMapping("/{idAluno}/desafios")
    public ResponseEntity<List<DesafioDTO>> listarDesafiosConcluidos(@PathVariable("idAluno") Integer idAluno) throws Exception {
        return new ResponseEntity<>(alunoService.listarDesafiosConcluidos(idAluno), HttpStatus.OK);
    }

    @GetMapping("/{idAluno}/modulos")
    public ResponseEntity<List<ModuloDTO>> listarModulosConcluidos(@PathVariable("idAluno") Integer idAluno) throws Exception {
        return new ResponseEntity<>(alunoService.listarModulosConcluidos(idAluno), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criar(@Valid @RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception {
        log.info("Criando aluno");
        AlunoDTO alunoDTO = alunoService.criar(alunoCreateDTO);
        log.info("Aluno criado");
        return new ResponseEntity<>(alunoDTO, HttpStatus.OK);
    }

    @PutMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable("idAluno") Integer id,
                                                @Valid @RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception {
        log.info("Atualizando aluno");
        AlunoDTO alunoAtualizado = alunoService.atualizar(id, alunoCreateDTO);
        log.info("Aluno atualizado");
        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idAluno}")
    public ResponseEntity<Void> deletar(@PathVariable("idAluno") Integer id) throws Exception {
        log.info("Deletando aluno");
        alunoService.remover(id);
        log.info("Aluno deletado");
        return ResponseEntity.ok().build();
    }

}
