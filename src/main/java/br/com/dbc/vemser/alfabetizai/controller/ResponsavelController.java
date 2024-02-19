package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IResponsavelController;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.ResponsavelService;
import br.com.dbc.vemser.alfabetizai.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/responsavel")
@Slf4j
public class ResponsavelController implements IResponsavelController {

    private final ResponsavelService responsavelService;
    private final UsuarioService usuarioService;

    @GetMapping("/paginado")
    public ResponseEntity<Page<ResponsavelDTO>> listarResponsaveisPaginado(@PageableDefault(page = 0, size = 9, sort = "nome") Pageable pageable) throws RegraDeNegocioException {
        Page<ResponsavelDTO> listaResponsavel = responsavelService.listar(pageable);
        return new ResponseEntity<>(listaResponsavel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> listarResponsaveis() throws Exception {
        return new ResponseEntity<>(responsavelService.listar(), HttpStatus.OK);
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<List<ResponsavelDTO>> listarResponsaveisAtivos(@PathVariable("ativo") char ativo) {
        return new ResponseEntity<>(responsavelService.listarAtivos(ativo), HttpStatus.OK);
    }

    @GetMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelDTO> buscarResponsavelPorId(@PathVariable("idResponsavel") Integer idResponsavel) throws Exception {
        return new ResponseEntity<>(responsavelService.buscarResponsavelPorIdComAlunos(idResponsavel), HttpStatus.OK);
    }

    @GetMapping("/logado")
    public ResponseEntity<ResponsavelDTO> buscarResponsavelLogado() throws Exception {
        Integer idResponsavel = usuarioService.getLoggedUser().get().getIdUsuario();
        return new ResponseEntity<>(responsavelService.buscarResponsavelPorIdComAlunos(idResponsavel), HttpStatus.OK);
    }


    @PutMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelDTO> atualizar(@PathVariable("idResponsavel") Integer id,
                                                @Valid @RequestBody ResponsavelCreateDTO responsavelCreateDTO) throws Exception {
        log.info("Atualizando responsavel");
        ResponsavelDTO responsavelAtualizado = responsavelService.atualizar(id, responsavelCreateDTO);
        log.info("Responsavel atualizado");
        return new ResponseEntity<>(responsavelAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idResponsavel}")
    public ResponseEntity<Void> deleteLogico(@PathVariable("idResponsavel") Integer id) throws Exception {
        log.info("Deletando responsavel");
        responsavelService.remover(id);
        log.info("Responsavel deletado");
        return ResponseEntity.ok().build();
    }

}
