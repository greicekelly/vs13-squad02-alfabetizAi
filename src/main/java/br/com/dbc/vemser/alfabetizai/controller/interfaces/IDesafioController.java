package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Desafio", description = "Endpoint de Desafio")
public interface IDesafioController {

    @Operation(summary = "Listar desafios", description = "Lista todos os desafios do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de desafios"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<DesafioDTO>> listar() throws RegraDeNegocioException;

    @Operation(summary = "Buscar desafio por id do Módulo", description = "Retorna o desafio correspondente ao id do módulo informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o desafio correspondente ao id do módulo informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idModulo}")
    public ResponseEntity<List<DesafioDTO>> listarPorIdModulo(@PathVariable("idModulo") Integer idModulo) throws RegraDeNegocioException ;

    @Operation(summary = "Criar desafio", description = "Cria um desafio com os dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do desafio cadastrado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<DesafioDTO> criar(@Valid @RequestBody DesafioCreateDTO desafioCreateDTO) throws Exception;


    @Operation(summary = "Editar desafio", description = "Edita os dados de um desafio com os novos dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do desafio atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idDesafio}")
    public ResponseEntity<DesafioDTO> editar(@PathVariable("idDesafio") Integer id,@Valid @RequestBody DesafioCreateDTO desafioCreateDTO) throws Exception ;


    @Operation(summary = "Deletar desafio", description = "Apaga o desafio com o id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Desafio apagado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idDesafio}")
    public ResponseEntity<Void> remover(@PathVariable("idDesafio") Integer id) throws Exception;
}
