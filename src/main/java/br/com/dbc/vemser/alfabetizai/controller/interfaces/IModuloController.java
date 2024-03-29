package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloAdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.relatorios.ModuloProfessorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<ModuloDTO>> listar(
            @Parameter(in = ParameterIn.QUERY, description = "Ordenar por: ", schema = @Schema(allowableValues = {"id", "titulo", "conteudo", "admin.idUsuario", "admin.nome", "professor.idUsuario", "professor.nome", "foiAprovado", "classificacao"}))
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception;

    @Operation(summary = "Buscar modulo por id do Módulo", description = "Retorna o modulo correspondente ao id do módulo informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o modulo correspondente ao id do módulo informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO>listarPorIdModulo(@PathVariable("idModulo") Integer idModulo) throws Exception;

    @Operation(summary = "Buscar modulos por id do Professor", description = "Retorna os modulos correspondente ao id do professor informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os módulos correspondente ao id do professor informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/professor/{idProfessor}")
    public ResponseEntity<Page<ModuloDTO>> listarPorIdProfessor(
            @PathVariable("idProfessor") Integer idProfessor,
            @Parameter(in = ParameterIn.QUERY, description = "Ordenar por: ", schema = @Schema(allowableValues = {"id", "titulo", "conteudo", "foiAprovado", "classificacao"}))
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception;


    @Operation(summary = "Buscar modulos por Professor Logado", description = "Retorna os modulos correspondente ao professor logado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os módulos correspondente ao professor logado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/professor/professor_id")
    public ResponseEntity<Page<ModuloProfessorDTO>> listarPorProfessor(
            @Parameter(in = ParameterIn.QUERY, description = "Filtrar por: ", schema = @Schema(allowableValues = {"Aprovado", "Sem Analise", "Reprovado"}))
            @RequestParam(required = false) String aprovacao,
            @Parameter(in = ParameterIn.QUERY, description = "Ordenar por: ", schema = @Schema(allowableValues = {"id", "titulo", "conteudo", "foiAprovado", "classificacao"}))
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception ;


    @Operation(summary = "Buscar modulos por aprovação", description = "Retorna os modulos correspondente a aprovação")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os módulos correspondente a aprovação"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/aprovacao")
    public ResponseEntity<List<ModuloDTO>> listarPorAprovacao(
            @Parameter(in = ParameterIn.QUERY, description = "Filtrar por: ", schema = @Schema(allowableValues = {"Sem Analise", "Aprovado", "Reprovado"}))
            @RequestParam String aprovacao) throws Exception;

    @Operation(summary = "Buscar modulos por id do Admin", description = "Retorna os módulos correspondente ao id do padmin informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os modulos correspondente ao id do admin informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/admin/{idAdmin}")
    public ResponseEntity<Page<ModuloAdminDTO>> listarPorIdAdmin(
            @PathVariable("idProfessor") Integer idAdmin,
            @Parameter(in = ParameterIn.QUERY, description = "Ordenar por: ", schema = @Schema(allowableValues = {"id", "titulo", "conteudo", "foiAprovado", "classificacao"}))
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) throws Exception;


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

    @Operation(summary = "Deletar modulo de forma Logica", description = "Apaga o modulo com o id informado de forma lógica.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Modulo removido de forma lógica com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/delete-logico/{idModulo}")
    public ResponseEntity<Void> removerLogico(@PathVariable("idModulo") Integer id) throws Exception;
}
