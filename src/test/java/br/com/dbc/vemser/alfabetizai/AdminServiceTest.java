package br.com.dbc.vemser.alfabetizai;

import br.com.dbc.vemser.alfabetizai.repository.IAdminRepository;
import br.com.dbc.vemser.alfabetizai.services.EmailService;
import br.com.dbc.vemser.alfabetizai.services.ModuloService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

@DisplayName("Classe Admin - Teste")
class AdminServiceTest {
@Mock
    private IAdminRepository adminRepository;
@Mock
    private ObjectMapper objectMapper;
@Mock
    private EmailService emailService;
@Mock
    private ModuloService moduloService;
@Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("")
    void criarUsuarioAdminComSucesso() {

    }
}