package br.com.dbc.vemser.alfabetizai;

import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.repository.IAdminRepository;
import br.com.dbc.vemser.alfabetizai.services.AdminService;
import br.com.dbc.vemser.alfabetizai.services.EmailService;
import br.com.dbc.vemser.alfabetizai.services.ModuloService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    @InjectMocks
    private AdminService adminService;

    @Test
    @DisplayName("Deveria criar um novo usuário Admin com sucesso")
    void criarUsuarioAdminComSucesso() throws Exception {
        // ARRANGE - GIVEN
        AdminCreateDTO adminCreateDTOMock = retornarAdminCreateDTO();
        Admin adminEntityMock = retornarAdminEntity();
        AdminDTO adminDTOMock = retornarAdminDTO();

        // ACT - WHEN
        when(objectMapper.convertValue(adminCreateDTOMock, Admin.class)).thenReturn(adminEntityMock);
        when(adminRepository.save(any())).thenReturn(adminEntityMock);
        when(objectMapper.convertValue(adminEntityMock, AdminDTO.class)).thenReturn(adminDTOMock);

        AdminDTO adminDTOCriado =  adminService.criar(adminCreateDTOMock);

        // ASSERT / THEN
        assertNotNull(adminDTOCriado);
        assertEquals(adminDTOCriado, adminDTOMock);
    }

//    @Test
//    @DisplayName("Deveria criar um novo usuário Admin com sucesso")
//    void buscarUsuarioAdminPorCpfEmail() throws Exception {
//        // ARRANGE - GIVEN
//        Admin adminEntityMock = retornarAdminEntity();
//
//        // ACT - WHEN
//        when(adminRepository.findAllByCpfOrEmail(anyString(), anyString())).thenReturn(adminEntityMock);
//
//        Admin adminRetornado =  adminService.ad(adminCreateDTOMock);
//
//        // ASSERT / THEN
//        assertNotNull(adminDTOCriado);
//        assertEquals(adminDTOCriado, adminDTOMock);
//    }

    @Test
    @DisplayName("Deveria retornar a lista de todo os usuários Admin com sucesso")
    void listarTodosUsuariosAdminComSucesso() throws Exception {

        // ARRANGE - GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        Page<Admin> adminsMock = criarPageAdminsMock(pageable);
        Page<AdminDTO> adminsDTOMock = criarPageAdminsDTOMock(pageable);

        // ACT - WHEN
        when(adminRepository.findAll(pageable)).thenReturn(adminsMock);
        when(objectMapper.convertValue(any(Admin.class), eq(AdminDTO.class)))
                .thenReturn();
        Page<AdminDTO> adminDTOPageRetornado = adminService.listar(pageable);


        // ASSERT / THEN
        assertNotNull(adminDTOPageRetornado);
        assertEquals(adminsDTOMock, adminDTOPageRetornado);

    }



    public static Admin retornarAdminEntity(){
//        Admin admin = new Admin();
//        admin.setIdUsuario(1);
//        admin.setNome("Greice");
//        admin.setSobrenome("Rosa");
//        admin.setTelefone("51987654321");
//        admin.setEmail("greice@email.com");
//        admin.setDataDeNascimento(LocalDate.of(1990,05,05));
//        admin.setSexo("F");
//        admin.setSenha("1234");
//        admin.setCpf("12345678922");
//        admin.setDescricao("Administrador de Sistema");
//        admin.setAtivo("S");

        Admin admin = new Admin();
        admin.setIdUsuario(1);
        admin.setNome("Tiago");
        admin.setSobrenome("Raupp");
        admin.setTelefone("48912345678");
        admin.setEmail("tiago@email.com");
        admin.setDataDeNascimento(LocalDate.parse("1990-10-10"));
        admin.setSexo("M");
        admin.setSenha("1234");
        admin.setCpf("12345678911");
        admin.setDescricao("Administrador de Sistema");
        admin.setAtivo("S");

        return admin;
    }

    public static Admin retornarAdminEntitySegundo(){
        Admin admin = new Admin();
        admin.setIdUsuario(2);
        admin.setNome("Greice");
        admin.setSobrenome("Rosa");
        admin.setTelefone("51987654321");
        admin.setEmail("greice@email.com");
        admin.setDataDeNascimento(LocalDate.parse("1990-10-10"));
        admin.setSexo("F");
        admin.setSenha("1234");
        admin.setCpf("12345678922");
        admin.setDescricao("Administrador de Sistema");
        admin.setAtivo("S");

        return admin;
    }

    public static AdminCreateDTO retornarAdminCreateDTO(){
        AdminCreateDTO adminCreateDTO = new AdminCreateDTO("Tiago", "Raupp", "48912345678", "tiago@email.com", LocalDate.parse("1990-10-10"), "M","1234", "12345678911", "Admistrador de Sistema");

        return adminCreateDTO;
    }

    public static AdminDTO retornarAdminDTO(){
//        AdminDTO adminDTO = new AdminDTO(2, "Shaienne", "Oliveira", "11932165487", "shaienne@email.com", LocalDate.of(1990, 01,01), "S", "F", "12345678933", "Admintrador de Dados");

        AdminDTO adminDTO = new AdminDTO(1, "Tiago", "Raupp", "48912345678", "tiago@email.com", LocalDate.parse("1990-10-10"), "S", "M", "12345678911", "Admintrador de Sistema");

        return adminDTO;
    }

    public static AdminDTO retornarAdminDTOSegundo(){
        AdminDTO adminDTO = new AdminDTO(2, "Greice", "Rosa", "51987654321", "greice@email.com", LocalDate.parse("1990-10-10"), "S", "F", "12345678922", "Admintrador de Sistema");

        return adminDTO;
    }

    public static Page<Admin> criarPageAdminsMock(Pageable pageable) {

        List<Admin> admins = new ArrayList<>();
        admins.add(retornarAdminEntity());
        admins.add(retornarAdminEntitySegundo());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), admins.size());
        return new PageImpl<>(admins.subList(start, end), pageable, admins.size());
    }

    public static Page<AdminDTO> criarPageAdminsDTOMock(Pageable pageable) {

        List<AdminDTO> admins = new ArrayList<>();
        admins.add(retornarAdminDTO());
        admins.add(retornarAdminDTOSegundo());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), admins.size());
        return new PageImpl<>(admins.subList(start, end), pageable, admins.size());
    }
}