package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/relatorio")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

//    @GetMapping
//    public ResponseEntity<List<Usuario>> listar() throws Exception {
//        return new ResponseEntity<>(usuarioService.buscarUsuarios(), HttpStatus.OK);
//    }

//    @GetMapping("/nome-paginado")
//    public Page<Usuario> listPaginadaComFiltro(@RequestParam String nome, @RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina) {
//        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina);
//        return usuarioService.getPorQualquerNomeJPQL(paginaSolicitada, tamanhoPagina, nome);
//    }

    @GetMapping("/nome-paginado")
    public Page<UsuarioDTO> listPaginadaComFiltro(@RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina) {
        return usuarioService.listar(paginaSolicitada, tamanhoPagina);
    }
}
