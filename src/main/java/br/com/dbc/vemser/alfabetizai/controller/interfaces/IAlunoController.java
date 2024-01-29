package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.AlunoCreateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Aluno", description = "Endpoint de Aluno")
public interface IAlunoController {

        @Operation(summary = "Listar todos os Alunos", description = "Lista todos os alunos cadastrados no banco de Dados.")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna a lista de alunos cadastrados."),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @GetMapping()
        public ResponseEntity<List<AlunoDTO>> listar() throws Exception;
        @Operation(summary = "Buscar aluno por id.", description = "Retorna o resultado correspondente ao id do aluno informado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna o resultado correspondente ao id do aluno informado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @GetMapping("/{idAluno}")
        public ResponseEntity<AlunoDTO> listarPorIdAluno(@PathVariable("idAluno") Integer idAluno) throws Exception;

        @Operation(summary = "Criar aluno", description = "Cria um aluno com os dados repassados no body")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna os dados do aluno cadastrado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @PostMapping
        public ResponseEntity<AlunoDTO> criar(@Valid @RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception;
        @Operation(summary = "Editar aluno", description = "Edita os dados de um aluno com os novos dados repassados no body")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna os dados do aluno atualizado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @PutMapping("/{idAluno}")
        public ResponseEntity<AlunoDTO> atualizar(@PathVariable("idAluno") Integer id,
                                                  @Valid @RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception;
        @Operation(summary = "Deletar aluno", description = "Apaga o aluno com o id informado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Aluno apagado com sucesso"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @DeleteMapping("/{idAluno}")
        public ResponseEntity<Void> deletar(@PathVariable("idAluno") Integer id) throws Exception;
    }
