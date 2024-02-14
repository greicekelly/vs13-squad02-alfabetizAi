package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.login.LoginDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Tag(name = "Auth", description = "Endpoint de Auth")
public interface IAuthController {

    @Operation(summary = "Fazer Login", description = "Faz Login do usuario")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o Token"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDTO loginDTO) throws Exception;

    @Operation(summary = "Criar professor", description = "Cria um usuario professor com os dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do professor cadastrado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping()
    public ResponseEntity<ProfessorDTO> cadastrarProfessor(@Valid @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception;

    @Operation(summary = "Criar admin", description = "Cria um usuário admin com dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuário admin criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<AdminDTO> cadastrarAdmin(@Valid @RequestBody AdminCreateDTO adminCreateDTO) throws Exception;

    @Operation(summary = "Criar responsavel", description = "Cria um usuário responsavel com dados repassados no body")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuário responsavel criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ResponsavelDTO> cadastrarResponsavel(@Valid @RequestBody ResponsavelCreateDTO responsavelCreateDTO) throws Exception;

    @Operation(summary = "Recuperar Dados por Token de usuario logado", description = "Recupera os dados de um usuario logado com o token.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os dados do usuário logado por token"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario-logado")
    public ResponseEntity<Optional<Usuario>>usuarioLogado()throws RegraDeNegocioException;

    @Operation(summary = "Alteração de Senha", description = "Alteração de senha do usuário logado.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/alterar_senha")
    public String alterarSenha(@Valid @RequestParam String senhaAtual, String novaSenha, String confirmacaoSenha) throws Exception;
}
