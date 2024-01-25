package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.ProfessorCreateDTO;
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

    @GetMapping()
    public ResponseEntity<List<ProfessorDTO>> list() throws Exception {
        List<ProfessorDTO> professores = professorService.visualizarTodos();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable("idProfessor") Integer idProfessor) throws Exception {
        ProfessorDTO professor = professorService.buscarProfessorPorId(idProfessor);
        return ResponseEntity.ok(professor);
    }

    @PostMapping()
    public ResponseEntity<ProfessorDTO> create(@Valid @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception {
        ProfessorDTO professorAdicionado = professorService.adicionar(professorCreateDTO);
        return ResponseEntity.ok(professorAdicionado);
    }

    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> update(@Valid @PathVariable("idProfessor") Integer id,
                                               @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception {
        ProfessorDTO professorAtualizado = professorService.editar(id, professorCreateDTO);
        return ResponseEntity.ok(professorAtualizado);
    }

    @DeleteMapping("/delete/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable("idProfessor") Integer id) throws Exception {
        professorService.remover(id);
        return ResponseEntity.ok().build();
    }
}