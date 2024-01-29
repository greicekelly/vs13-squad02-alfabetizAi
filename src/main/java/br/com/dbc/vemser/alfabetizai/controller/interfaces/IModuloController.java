package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.ModuloCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Modulo", description = "Endpoint de Modulo")
public interface IModuloController {

    @Operation(summary = "Listar modulos", description = "Lista todos os modulos do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de modulos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<ModuloDTO>> listar() throws Exception;

    @Operation(summary = "Buscar modulo por id do Módulo", description = "Retorna o modulo correspondente ao id do módulo informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o modulo correspondente ao id do módulo informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO> listarPorIdModulo(@PathVariable("idModulo") Integer idModulo) throws Exception;

    @Operation(summary = "Buscar modulos por id do Professor", description = "Retorna os modulos correspondente ao id do professor informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os modulos correspondente ao id do professor informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("professor/{idProfessor}")
    public ResponseEntity<List<ModuloDTO>>listarPorIdProfessor(
            @PathVariable("idProfessor") Integer idProfessor)throws Exception;

    @Operation(summary = "Criar modulo", description = "Cria um modulo com os dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do modulo cadastrado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ModuloDTO> criar(@Valid @RequestBody ModuloCreateDTO moduloCreateDTO) throws Exception;


    @Operation(summary = "Editar modulo", description = "Edita os dados de um modulo com os novos dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do modulo atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO> atualizar(@PathVariable("idModulo") Integer id,
                                               @Valid @RequestBody ModuloCreateDTO moduloCreateDTO) throws Exception;


    @Operation(summary = "Deletar modulo", description = "Apaga o modulo com o id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Modulo apagado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idModulo}")
    public ResponseEntity<Void> deletar(@PathVariable("idModulo") Integer id) throws Exception;
}
