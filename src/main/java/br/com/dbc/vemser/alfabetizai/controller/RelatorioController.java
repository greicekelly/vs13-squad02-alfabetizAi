package br.com.dbc.vemser.alfabetizai.controller;


import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.ModuloService;
import br.com.dbc.vemser.alfabetizai.services.UsuarioService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/relatorio")
@Slf4j
public class RelatorioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioDTO> buscarUsuariosPaginados(@RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina) {
        return usuarioService.buscarUsuarios(paginaSolicitada, tamanhoPagina);
    }

    @GetMapping("/nome")
    public Page<UsuarioDTO> buscarUsuariosPorNomePaginado(@RequestParam String nome, @RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina) {
        return usuarioService.buscarUsuariosPorNome(paginaSolicitada, tamanhoPagina, nome);
    }

    @GetMapping("/ativo")
    public Page<UsuarioDTO> buscarUsuariosAtivoPaginado(@RequestParam(defaultValue = "S") char ativo , @RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina) {
        return usuarioService.buscarUsuariosAtivo(paginaSolicitada, tamanhoPagina, ativo);
    }

}
