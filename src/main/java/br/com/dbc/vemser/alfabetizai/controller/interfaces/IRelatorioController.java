package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Relatorio", description = "Endpoint de relatorio")
public interface IRelatorioController {
    @Operation(summary = "Listar todos os usuarios paginados", description = "Lista todos os usuarios cadastrados no banco de Dados paginado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuarios cadastrados paginado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public Page<UsuarioDTO> buscarUsuariosPaginados(@RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina);

    @Operation(summary = "Listar todos os usuarios paginados por nome", description = "Lista todos os usuarios cadastrados no banco de Dados paginado por nome.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuarios cadastrados por nome paginado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/nome")
    public Page<UsuarioDTO> buscarUsuariosPorNomePaginado(@RequestParam String nome, @RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina);

    @Operation(summary = "Listar todos os usuarios paginados por ativo", description = "Lista todos os usuarios cadastrados no banco de Dados paginado por ativo.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuarios cadastrados por ativo paginado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/ativo")
    public Page<UsuarioDTO> buscarUsuariosAtivoPaginado(@RequestParam(defaultValue = "S") char ativo , @RequestParam Integer paginaSolicitada, @RequestParam Integer tamanhoPagina);
}








