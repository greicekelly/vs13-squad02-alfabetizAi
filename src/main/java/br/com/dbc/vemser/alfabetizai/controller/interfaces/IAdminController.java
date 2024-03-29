package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Tag(name = "Admin", description = "Endpoint de Admin")
public interface IAdminController {

    @Operation(summary = "Listar admins", description = "Lista todos os usuários admin do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários admin"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<Page<AdminDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) throws Exception;


    @Operation(summary = "Buscar admin por id", description = "Retorna o usuário admin correspondente ao id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário admin correspondente ao id informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idUsuario}")
    public ResponseEntity<AdminDTO> BuscarAdminPorId(@PathVariable("idUsuario") Integer idUsuario) throws Exception;


    @Operation(summary = "Editar admin", description = "Edita os dados de um usuário admin com os novos dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuário admin atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<AdminDTO> atualizar(@PathVariable("idUsuario") Integer id,
                                              @Valid @RequestBody AdminCreateDTO adminCreateDTO) throws Exception;


    @Operation(summary = "Delatar admin", description = "Inativa o usuário admin com o id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Admin deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletar(@PathVariable("idUsuario") Integer id) throws Exception;


    @Operation(summary = "Analisar Aprovação/Reprovação de Módulo", description = "Edita o módulo para aparecer o status de aprovado ou reprovado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do módulo atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/analisarmodulo/{idModulo}")
    public ResponseEntity<ModuloDTO> aprovacaoModulo(@PathVariable("idModulo") Integer idModulo,
                                                     @Valid @RequestParam Integer idAdmin, String aprovacao) throws Exception;




    @Operation(summary = "Delatar admin fisicamente", description = "deleta o usuário admin com o id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Admin deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/delete-fisico/{idAdmin}")
    public ResponseEntity<Void> deleteFisico(@PathVariable("idAdmin") Integer id) throws Exception;

}
