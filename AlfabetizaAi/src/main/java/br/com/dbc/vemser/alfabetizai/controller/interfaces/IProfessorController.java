package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Professor", description = "Endpoint de Professor")
public interface IProfessorController {
    @Operation(summary = "Listar todos os Professores", description = "Lista todos os professores cadastrados no banco de Dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de professores cadastrados."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping()
    public ResponseEntity<List<ProfessorDTO>> list() throws Exception;
    @Operation(summary = "Buscar professor por id do usuario.", description = "Retorna o professor correspondente ao id do usuario informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o professor correspondente ao id do usuario informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idUsuario}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable("idUsuario") Integer idUsuario) throws Exception;
    @Operation(summary = "Buscar professor por id.", description = "Retorna o professor correspondente ao id do professor informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o professor correspondente ao id do professor informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/id/{idProfessor}")
    public ResponseEntity<ProfessorDTO> getByIdProfessor(@PathVariable("idProfessor") Integer idProfessor) throws Exception;

    @Operation(summary = "Criar professor", description = "Cria um professor com os dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do professor cadastrado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping()
    public ResponseEntity<ProfessorDTO> create(@Valid @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception;
    @Operation(summary = "Editar professor", description = "Edita os dados de um professor com os novos dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do professor atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> update(@Valid @PathVariable("idProfessor") Integer id,
                                               @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception ;
    @Operation(summary = "Deletar professor", description = "Apaga o professor com o id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Professor apagado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable("idProfessor") Integer id) throws Exception;
}








