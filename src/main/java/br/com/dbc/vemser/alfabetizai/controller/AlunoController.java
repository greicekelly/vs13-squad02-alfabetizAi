package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IAlunoController;

import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.services.AlunoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
@Slf4j
public class AlunoController implements IAlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listar() throws Exception {
        return new ResponseEntity<>(alunoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> BuscarPorIdAluno(@PathVariable("idAluno") Integer idAluno) throws Exception {
        return new ResponseEntity<>(alunoService.buscarAlunoPorId(idAluno), HttpStatus.OK);
    }

    @GetMapping("/responsavel/{idResponsavel}")
    public ResponseEntity<List<AlunoDTO>> BuscarAlunosPorIdReponsavel(@PathVariable("idResponsavel") Integer id) throws Exception {
        return new ResponseEntity<>(alunoService.buscarAlunosPorIdResponsavel(id), HttpStatus.OK);
    }

    @GetMapping("/{idAluno}/desafios")
    public ResponseEntity<List<DesafioDTO>> listarDesafiosConcluidos(@PathVariable("idAluno") Integer idAluno) throws Exception {
       return new ResponseEntity<>(alunoService.listarDesafiosConcluidos(idAluno), HttpStatus.OK);
    }

    @GetMapping("/{idAluno}/modulos")
    public ResponseEntity<List<ModuloDTO>> listarModulosConcluidos(@PathVariable("idAluno") Integer idAluno) throws Exception {
        return new ResponseEntity<>(alunoService.listarModulosConcluidos(idAluno), HttpStatus.OK);
    }

    @PostMapping("/{idResponsavel}")
    public ResponseEntity<AlunoDTO> criar(@PathVariable("idResponsavel") Integer id,@Valid @RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception {
        log.info("Criando aluno");
        AlunoDTO alunoDTO = alunoService.criar(id, alunoCreateDTO);
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

    @DeleteMapping("/delete-fisico/{idAluno}")
    public ResponseEntity<Void> deleteFisico(@PathVariable("idAluno") Integer id) throws Exception {
        log.info("Deletando aluno");
        alunoService.removerFisicamente(id);
        log.info("Aluno deletado");
        return ResponseEntity.ok().build();
    }
}
