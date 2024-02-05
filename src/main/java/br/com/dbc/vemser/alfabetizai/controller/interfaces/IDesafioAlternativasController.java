package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.DesafioAlternativasDTO;

import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Desafio Alternativas", description = "Endpoint de Alternativas de Desafio")
public interface IDesafioAlternativasController {

        @Operation(summary = "Listar Alternativas desafios", description = "Lista todos as alternativas para desafios do banco")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna a lista de Alternativas desafios"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @GetMapping
        public ResponseEntity<List<DesafioAlternativasDTO>> listar() throws RegraDeNegocioException;



        @Operation(summary = "Criar Alternativas para desafio", description = "Cria alternativas para desafio com os dados repassados no body")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna as alternativas cadastradas"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @PostMapping
        public ResponseEntity<DesafioAlternativasDTO> criar(@Valid @RequestBody DesafioAlternativasCreateDTO desafioAlternativasCreateDTO) throws Exception ;


        @Operation(summary = "Editar Alternativas desafio", description = "Edita as alternativas de um desafio com os novos dados repassados no body")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna as alternativass do desafio atualizado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @PutMapping("/{idDesafioAlternativas}")
        public ResponseEntity<DesafioAlternativasDTO>atualizar(@PathVariable("idDesafioAlternativas") Integer id,
                                                               @Valid @RequestBody DesafioAlternativasCreateDTO desafioAlternativasCreateDTO)throws Exception  ;


        @Operation(summary = "Deletar Alternativas de desafio", description = "Apaga as alternativas cadastradas para o desafio com o id informado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Alternativas apagadas com sucesso"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @DeleteMapping("/{idDesafioAlternativas}")
        public ResponseEntity<Void> remover(@PathVariable("idDesafioAlternativas")Integer id)throws Exception;
    }



