package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.services.ProfessorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@Validated
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Professor>> list() {
        return new ResponseEntity<>(professorService.visualizarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{idProfessor}")
    public Professor getById(@PathVariable("idProfessor") Integer idProfessor) {
        return professorService.buscarProfessorPorId(idProfessor);
    }

    @PostMapping("/create")
    public ResponseEntity<Professor> create(@Valid @RequestBody Professor professor) throws Exception {
        return new ResponseEntity<>(professorService.adicionar(professor), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{idProfessor}")
    public ResponseEntity<Professor> update(@Valid @PathVariable("idProfessor") Integer id,
                                                @RequestBody Professor professorAtualiza) {
        try {
            Professor professorAtualizado = professorService.editar(id, professorAtualiza);
            return new ResponseEntity<>(professorAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idProfessor}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("idProfessor") @NotBlank Integer id) throws Exception {
        professorService.remover(id, null);
        return ResponseEntity.ok().build();
    }
}
