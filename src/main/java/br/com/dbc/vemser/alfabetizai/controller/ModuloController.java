package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IModuloController;
import br.com.dbc.vemser.alfabetizai.dto.ModuloCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloAdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.ModuloService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/modulo")
@RequiredArgsConstructor
@Slf4j
public class ModuloController implements IModuloController {

    private final ModuloService moduloService;
    @PostMapping
    public ResponseEntity<ModuloDTO> criar(@Valid @RequestBody ModuloCreateDTO moduloCreateDTO) throws Exception {
        log.info("Criando modulo");
        ModuloDTO moduloDTO = moduloService.criar(moduloCreateDTO);
        log.info("Modulo criado");
        return new ResponseEntity<>(moduloDTO, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<ModuloDTO>> listar(
        @Parameter(in = ParameterIn.QUERY, description = "Ordenar por: ", schema = @Schema(allowableValues = {"id", "titulo", "conteudo", "admin.idUsuario", "admin.nome", "professor.idUsuario", "professor.nome", "foiAprovado", "classificacao"}))
        @RequestParam(required = false) String sort,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {

        Sort.Direction direction = Sort.DEFAULT_DIRECTION;

        Pageable pageable = sort != null ? PageRequest.of(page, size, direction, sort) : PageRequest.of(page, size);

        log.info("Listando Modulos!");
        Page<ModuloDTO> modulosListados = moduloService.listar(pageable);
        log.info("Modulos Listados!");
        return new ResponseEntity<>(modulosListados, HttpStatus.OK);
    }

    @GetMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO>listarPorIdModulo(@PathVariable("idModulo") Integer idModulo) throws Exception {
        log.info("Listando Modulos por Id Modulo!");
        ModuloDTO modulosListadosporId = moduloService.listarPorIdModulo(idModulo);
        log.info("Modulos Listados por Id!");
        return new ResponseEntity<>(modulosListadosporId, HttpStatus.OK);
    }

    @GetMapping("/professor/{idProfessor}")
    public ResponseEntity<Page<ModuloProfessorDTO>> listarPorIdProfessor(
            @PathVariable("idProfessor") Integer idProfessor,
            @Parameter(in = ParameterIn.QUERY, description = "Ordenar por: ", schema = @Schema(allowableValues = {"id", "titulo", "conteudo", "foiAprovado", "classificacao"}))
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {

        Sort.Direction direction = Sort.DEFAULT_DIRECTION;

        Pageable pageable = sort != null ? PageRequest.of(page, size, direction, sort) : PageRequest.of(page, size);

        log.info("Listando modulo por idProfessor");
        Page<ModuloProfessorDTO> modulosPorIdProfessor = moduloService.pagePorIdProfessor(idProfessor, pageable);
        log.info("Modulos Listados!");
        return new ResponseEntity<>(modulosPorIdProfessor, HttpStatus.OK);
    }

    @GetMapping("/admin/{idAdmin}")
    public ResponseEntity<Page<ModuloAdminDTO>> listarPorIdAdmin(
            @PathVariable("idAdmin") Integer idAdmin,
            @Parameter(in = ParameterIn.QUERY, description = "Ordenar por: ", schema = @Schema(allowableValues = {"id", "titulo", "conteudo", "foiAprovado", "classificacao"}))
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {

        Sort.Direction direction = Sort.DEFAULT_DIRECTION;

        Pageable pageable = sort != null ? PageRequest.of(page, size, direction, sort) : PageRequest.of(page, size);

        log.info("Listando modulo por Admin");
        Page<ModuloAdminDTO> modulosPorIdAdmin = moduloService.pagePorIdAdmin(idAdmin, pageable);
        log.info("Modulos Listados!");
        return new ResponseEntity<>(modulosPorIdAdmin, HttpStatus.OK);
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
