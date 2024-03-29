package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Responsavel", description = "Endpoint de Responsavel")
public interface IResponsavelController {

    @Operation(summary = "Listar Responsaveis", description = "Lista todos os usuários responsaveis do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários respinsaveis"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> listarResponsaveis() throws Exception;

    @Operation(summary = "Listar Responsaveis paginado", description = "Lista todos os usuários responsaveis do banco paginado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários respinsaveis paginado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/paginado")
    public ResponseEntity<Page<ResponsavelDTO>> listarResponsaveisPaginado(@PageableDefault(page = 0, size = 9, sort = "nome") Pageable pageable) throws RegraDeNegocioException;

    @Operation(summary = "Listar Responsaveis ativos", description = "Lista todos os usuários responsaveis ativos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários respinsaveis ativos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<List<ResponsavelDTO>> listarResponsaveisAtivos(@PathVariable("ativo") char ativo);


    @Operation(summary = "Buscar responsavel por id", description = "Retorna o usuário responsavel correspondente ao id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário responsavel correspondente ao id informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelDTO> buscarResponsavelPorId(@PathVariable("idResponsavel") Integer idResponsavel) throws Exception;

    @Operation(summary = "Buscar responsavel logado", description = "Retorna o usuário responsavel logado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário responsavel logado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/logado")
    public ResponseEntity<ResponsavelDTO> buscarResponsavelLogado() throws Exception;


    @Operation(summary = "Editar responsavel", description = "Edita os dados de um usuário responsavel com os novos dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuário responsavel atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelDTO> atualizar(@PathVariable("idResponsavel") Integer id,
                                                    @Valid @RequestBody ResponsavelCreateDTO responsavelCreateDTO) throws Exception;


    @Operation(summary = "Delatar responsavel", description = "Inativa o usuário responsavel com o id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Responsavel deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idResponsavel}")
    public ResponseEntity<Void> deleteLogico(@PathVariable("idResponsavel") Integer id) throws Exception;
}
