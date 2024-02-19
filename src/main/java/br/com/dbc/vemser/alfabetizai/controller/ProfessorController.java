package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IProfessorController;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.services.ProfessorService;
import br.com.dbc.vemser.alfabetizai.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/professor")
@AllArgsConstructor
@Validated
@Slf4j
public class ProfessorController implements IProfessorController {

    private final ProfessorService professorService;
    private final UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<ProfessorDTO>> list() {
        List<ProfessorDTO> professores = professorService.listar();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        ProfessorDTO professor = professorService.buscarProfessorPorId(idUsuario);
        return ResponseEntity.ok(professor);
    }

    @GetMapping("/logado")
    public ResponseEntity<ProfessorDTO> professorlogado() throws Exception {
        Integer idProfessor = usuarioService.getLoggedUser().get().getIdUsuario();
        ProfessorDTO professor = professorService.buscarProfessorPorId(idProfessor);
        return ResponseEntity.ok(professor);
    }


    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> update(@Valid @PathVariable("idProfessor") Integer id,
                                               @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception {
        ProfessorDTO professorAtualizado = professorService.atualizar(id, professorCreateDTO);
        return ResponseEntity.ok(professorAtualizado);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable("idProfessor") Integer id) throws Exception {
        professorService.remover(id);
        return ResponseEntity.ok().build();
    }

}