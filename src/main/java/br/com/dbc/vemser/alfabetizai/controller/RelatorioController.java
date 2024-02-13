package br.com.dbc.vemser.alfabetizai.controller;


import br.com.dbc.vemser.alfabetizai.controller.interfaces.IRelatorioController;
import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/relatorio")
@Slf4j
public class RelatorioController implements IRelatorioController {

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
