package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.services.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
@AllArgsConstructor
@Validated
@Slf4j
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping("/list")
    public ResponseEntity<List<ProfessorDTO>> list() {
        List<ProfessorDTO> professores = professorService.visualizarTodos();
        return new ResponseEntity<>(professores, HttpStatus.OK);
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable("idProfessor") Integer idProfessor) {
        Professor professor = professorService.buscarProfessorPorId(idProfessor);
        return ResponseEntity.ok(ProfessorDTO.fromProfessor(professor));
    }

    @PostMapping("/create")
    public ResponseEntity<ProfessorDTO> create(@Valid @RequestBody Professor professor) throws RegraDeNegocioException {
        Professor professorAdicionado = professorService.adicionar(professor);
        return ResponseEntity.ok(ProfessorDTO.fromProfessor(professorAdicionado));
    }

    @PutMapping("/editar/{idProfessor}")
    public ResponseEntity<ProfessorDTO> update(@Valid @PathVariable("idProfessor") Integer id,
                                               @RequestBody Professor professorAtualiza) {
        Professor professorAtualizado = professorService.editar(id, professorAtualiza);
        return Optional.ofNullable(professorAtualizado)
                .map(professor -> ResponseEntity.ok(ProfessorDTO.fromProfessor(professor)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{idProfessor}")
    public ResponseEntity<ProfessorDTO> delete(@PathVariable("idProfessor") Integer id) throws BancoDeDadosException {
        Professor professorRemovido = professorService.remover(id, null);
        return ResponseEntity.ok(ProfessorDTO.fromProfessor(professorRemovido));
    }

    @PostMapping("/login")
    public ResponseEntity<ProfessorDTO> login(@RequestParam String email, @RequestParam String senha) {
        Optional<Professor> optionalProfessor = professorService.loginProfessor(email, senha);
        return optionalProfessor
                .map(professor -> ResponseEntity.ok(ProfessorDTO.fromProfessor(professor)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
