package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoCreateDTO;

import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
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
        public ResponseEntity<AlunoDTO> BuscarPorIdAluno(@PathVariable("idAluno") Integer idAluno) throws Exception;

        @Operation(summary = "Criar aluno", description = "Cria um aluno com os dados repassados no body")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna os dados do aluno cadastrado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @PostMapping("/{idResponsavel}")
        public ResponseEntity<AlunoDTO> criar(@PathVariable("idResponsavel") Integer id,@Valid @RequestBody AlunoCreateDTO alunoCreateDTO) throws Exception;
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

        @Operation(summary = "Deletar aluno fisicamente", description = "Apaga o aluno com o id informado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Aluno apagado com sucesso"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @DeleteMapping("/delete-fisico/{idAluno}")
        public ResponseEntity<Void> deleteFisico(@PathVariable("idAluno") Integer id) throws Exception;


        @Operation(summary = "Buscar alunos por id responsavel.", description = "Retorna o resultado correspondente ao id do responsavel informado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna o resultado correspondente ao id do responsavel informado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @GetMapping("/responsavel/{idResponsavel}")
        public ResponseEntity<List<AlunoDTO>> BuscarAlunosPorIdReponsavel(@PathVariable("idResponsavel") Integer id) throws Exception;

        @Operation(summary = "Buscar desafios concluidos por id aluno.", description = "Retorna o resultado correspondente ao id do aluno informado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna o resultado correspondente ao id do aluno informado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @GetMapping("/{idAluno}/desafios")
        public ResponseEntity<List<DesafioDTO>> listarDesafiosConcluidos(@PathVariable("idAluno") Integer idAluno) throws Exception;

        @Operation(summary = "Buscar modulos concluidos por id aluno.", description = "Retorna o resultado correspondente ao id do aluno informado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna o resultado correspondente ao id do aluno informado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @GetMapping("/{idAluno}/modulos")
        public ResponseEntity<List<ModuloDTO>> listarModulosConcluidos(@PathVariable("idAluno") Integer idAluno) throws Exception;

        @Operation(summary = "Fazer desafio", description = "Edita os dados de um aluno e adiciona o desafio concluido")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna os dados do aluno atualizado"),
                        @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                        @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
                }
        )
        @PutMapping("/{idAluno}/{idDesafio}")
        public ResponseEntity<Void> fazerDesafio(@PathVariable("idAluno") Integer idAluno,
                                                  @PathVariable("idDesafio") Integer idDesafio) throws Exception;

}
