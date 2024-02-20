package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IAuthController;
import br.com.dbc.vemser.alfabetizai.dto.Log.LogCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.login.LoginDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.security.TokenService;
import br.com.dbc.vemser.alfabetizai.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
@Slf4j
public class AuthController implements IAuthController {

    public final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final ProfessorService professorService;
    private final AdminService adminService;
    private final ResponsavelService responsavelService;
    private final UsuarioService usuarioService;
    private final LogService logService;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDTO loginDTO) throws Exception {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getSenha()
                    );

            Authentication authentication =
                    authenticationManager.authenticate(
                            usernamePasswordAuthenticationToken);

            Usuario usuarioValidado = (Usuario) authentication.getPrincipal();

            log.info("Token Gerado.");

            TipoLog tipo = usuarioValidado.getCargos().stream()
                    .map(cargo -> cargo.getNome())
                    .findFirst()
                    .map(TipoLog::fromCargo)
                    .orElseThrow(() -> new RegraDeNegocioException("Usuário sem cargo definido"));

            LogCreateDTO logCreateDTO = new LogCreateDTO();
            logCreateDTO.setTipoLog(tipo);
            logCreateDTO.setDescricao("Efetuado Login");
            logCreateDTO.setData(LocalDate.now().toString());

            logService.registerLog(logCreateDTO);

            return new ResponseEntity<>(tokenService.generateToken(usuarioValidado), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new RegraDeNegocioException("Usuário ou senha incorretos");
        }
    }

    @PostMapping("/cadastrar/professor")
    public ResponseEntity<ProfessorDTO> cadastrarProfessor(@Valid @RequestBody ProfessorCreateDTO professorCreateDTO) throws Exception {
        ProfessorDTO professorAdicionado = professorService.criar(professorCreateDTO);
        return ResponseEntity.ok(professorAdicionado);
    }

    @PostMapping("/cadastrar/admin")
    public ResponseEntity<AdminDTO> cadastrarAdmin(@Valid @RequestBody AdminCreateDTO adminCreateDTO) throws Exception {
        AdminDTO adminAdicionado = adminService.criar(adminCreateDTO);
        return ResponseEntity.ok(adminAdicionado);
    }

    @PostMapping("/cadastrar/responsavel")
    public ResponseEntity<ResponsavelDTO> cadastrarResponsavel(@Valid @RequestBody ResponsavelCreateDTO responsavelCreateDTO) throws Exception {
        ResponsavelDTO responsavelAdicionado = responsavelService.criar(responsavelCreateDTO);
        return ResponseEntity.ok(responsavelAdicionado);
    }

    @PutMapping("/alterar_senha")
    public String alterarSenha( @Valid @RequestParam String senhaAtual, String novaSenha, String confirmacaoSenha) throws Exception {
        log.info("Atualizando senha");

        String response = usuarioService.alterarSenha(senhaAtual, novaSenha, confirmacaoSenha);
        log.info("Senha atualizado");
        return response;
    }
}
