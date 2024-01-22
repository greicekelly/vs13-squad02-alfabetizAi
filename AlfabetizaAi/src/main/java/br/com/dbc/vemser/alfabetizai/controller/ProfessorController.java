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

    public ResponseEntity<List<ProfessorDTO>> list() throws Exception {
        List<ProfessorDTO> professores = professorService.visualizarTodos();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable("idProfessor") Integer idProfessor) {
        Professor professor = professorService.buscarProfessorPorId(idProfessor);
        return Optional.ofNullable(professor)
                .map(p -> ResponseEntity.ok(ProfessorDTO.fromProfessor(p)))
                .orElse(ResponseEntity.notFound().build());
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
                .map(p -> ResponseEntity.ok(ProfessorDTO.fromProfessor(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{idProfessor}")
    public ResponseEntity<ProfessorDTO> delete(@PathVariable("idProfessor") Integer id) {
        try {
            Professor professorRemovido = professorService.remover(id);
            return ResponseEntity.ok(ProfessorDTO.fromProfessor(professorRemovido));
        } catch (BancoDeDadosException e) {
            log.error("Erro ao tentar remover o professor | ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<ProfessorDTO> login(@RequestParam String email, @RequestParam String senha) {
//        Optional<Professor> optionalProfessor = professorService.loginProfessor(email, senha);
//        return optionalProfessor
//                .map(p -> ResponseEntity.ok(ProfessorDTO.fromProfessor(p)))
//                .orElse(ResponseEntity.notFound().build());
//    }
}