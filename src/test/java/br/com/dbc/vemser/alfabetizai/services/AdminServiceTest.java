package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.repository.IAdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;
import java.util.Random;

import static br.com.dbc.vemser.alfabetizai.services.Mock.*;
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
    @Mock
    private LogService logService;

    @InjectMocks
    private AdminService adminService;

    @Test
    @DisplayName("Deveria criar um novo usuário Admin com sucesso")
    void criarUsuarioAdminComSucesso() throws Exception {
        // ARRANGE - GIVEN
        AdminCreateDTO adminCreateDTO = retornarAdminCreateDTO();
        Admin admin = retornarAdmin();
        AdminDTO adminDTO = retornarAdminDTO();

        // ACT - WHEN
        when(objectMapper.convertValue(adminCreateDTO, Admin.class)).thenReturn(admin);
        when(adminRepository.findAllByCpfOrEmail(anyString(), anyString())).thenReturn(null);
        when(adminRepository.save(any())).thenReturn(admin);
        when(objectMapper.convertValue(admin, AdminDTO.class)).thenReturn(adminDTO);

        AdminDTO adminDTOCriado =  adminService.criar(adminCreateDTO);


        // ASSERT / THEN
        assertNotNull(adminDTOCriado);
        assertEquals(adminDTOCriado, adminDTO);
    }

    @Test
    @DisplayName("Deveria retornar RegraDeNegocioException: Cpf ou Email já estão em uso")
    void criarUsuarioAdminComException() {
        // ARRANGE - GIVEN
        AdminCreateDTO adminCreateDTO = retornarAdminCreateDTO();
        Admin admin = retornarAdmin();

        // ACT - WHEN
        when(objectMapper.convertValue(adminCreateDTO, Admin.class)).thenReturn(admin);
        when(adminRepository.findAllByCpfOrEmail(adminCreateDTO.getCpf(), adminCreateDTO.getEmail())).thenReturn(admin);


        // ASSERT / THEN
        assertThrows(RegraDeNegocioException.class, () -> adminService.criar(adminCreateDTO));
    }


    @Test
    @DisplayName("Deveria retornar a lista de todo os usuários Admin com sucesso")
    void listarTodosUsuariosAdminComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        Page<Admin> adminsMock = criarPageAdminsMock(pageable);
        Page<AdminDTO> adminsDTOMock = criarPageAdminsDTOMock(pageable);

        // ACT - WHEN
        when(adminRepository.findAll(pageable)).thenReturn(adminsMock);

        Page<AdminDTO> adminDTOPageRetornado = adminService.listar(pageable);

        // ASSERT / THEN
        assertNotNull(adminDTOPageRetornado);
        assertEquals(2, adminDTOPageRetornado.getContent().size());
    }

    @Test
    @DisplayName("Deveria retornar uma lista de usuário Admin ativo com sucesso")
    void retornarUmaListaUsuarioAdminAtivoComSucesso(){
        // ARRANGE - GIVEN
        List<Admin> admins = List.of(retornarAdmin());
        AdminDTO adminDTO = retornarAdminDTO();

        // ACT - WHEN
        when(adminRepository.findAllByAtivo("S")).thenReturn(admins);
        for (Admin admin : admins) {
            when(objectMapper.convertValue(eq(admin), eq(AdminDTO.class)))
                    .thenReturn(adminDTO);
        }

        List<AdminDTO> adminDTOList = adminService.listarAtivos();

        // ASSERT / THEN
        assertNotNull(adminDTOList);
    }

    @Test
    @DisplayName("Deveria retornar um usuário Admin pelo Id com sucesso")
    void retornarUmUsuarioAdminPeloIdComSucesso() throws ObjetoNaoEncontradoException {
        // ARRANGE - GIVEN
        Optional<Admin> adminOptional = Optional.of(retornarAdmin());
        AdminDTO adminDTO = retornarAdminDTO();

        // ACT - WHEN
        when(adminRepository.findById(any())).thenReturn(adminOptional);
        when(objectMapper.convertValue(adminOptional.get(), AdminDTO.class)).thenReturn(adminDTO);

        AdminDTO adminDTOCriado =  adminService.buscarAdminPorId(new Random().nextInt());

        // ASSERT / THEN
        assertNotNull(adminDTOCriado);
        assertEquals(adminDTOCriado, adminDTO);
    }

    @Test
    @DisplayName("Deveria retornar ObjetoNaoEncontradoException para Id de Admin inexistente")
    void retornarObjetoNaoEncontradoExceptionIdAdminInexistente() throws ObjetoNaoEncontradoException {
        // ARRANGE - GIVEN
        Optional<Admin> adminOptional = Optional.of(retornarAdmin());
        AdminDTO adminDTO = retornarAdminDTO();

        // ACT - WHEN
        when(adminRepository.findById(any())).thenReturn(Optional.empty());


        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> adminService.buscarAdminPorId(new Random().nextInt()));
    }

    @Test
    @DisplayName("Deveria retornar Exception Id do Admin incorreto")
    void retornarExceptionIdAdminIncorreto() throws ObjetoNaoEncontradoException {
        // ARRANGE - GIVEN
        Optional<Admin> adminOptional = Optional.empty();

        // ACT - WHEN
        when(adminRepository.findById(anyInt())).thenReturn(adminOptional);

        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> adminService.buscarAdminPorId(new Random().nextInt()));
    }

    @Test
    @DisplayName("Deveria atualizar o Usuário Admin com sucesso.")
    void atualizarUsuarioAdminComSucesso() throws Exception {
        // ARRANGE - GIVEN
        AdminCreateDTO adminCreateDTOMock = retornarAdminCreateDTO();
        Admin adminAntigo = retornarAdminSegundo();
        Admin adminNovo = retornarAdmin();
        AdminDTO adminDTOMock = retornarAdminDTO();

        // ACT - WHEN
        when(adminRepository.findById(anyInt())).thenReturn(Optional.of(adminAntigo));
        when(objectMapper.convertValue(adminCreateDTOMock, Admin.class)).thenReturn(adminNovo);
        when(adminRepository.save(anyObject())).thenReturn(adminNovo);
        when(objectMapper.convertValue(adminNovo, AdminDTO.class)).thenReturn(adminDTOMock);

        AdminDTO adminDTORetornado = adminService.atualizar(adminNovo.getIdUsuario(), adminCreateDTOMock);

        // ASSERT / THEN
        assertNotNull(adminDTORetornado);
        assertNotEquals(adminDTORetornado, adminAntigo);
        assertNotEquals(adminAntigo.getNome(), adminDTORetornado.getNome());
    }

    @Test
    @DisplayName("Deveria retornar Exception ao atualizar Admin com Id inexistente.")
    void retornarExceptionAtualizarAdminComIdInexistente() throws Exception {
        // ARRANGE - GIVEN
        AdminCreateDTO adminCreateDTOMock = retornarAdminCreateDTO();
        Admin adminNovo = retornarAdmin();

        // ACT - WHEN
        when(adminRepository.findById(anyInt())).thenReturn(Optional.empty());

        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> adminService.atualizar(adminNovo.getIdUsuario(), adminCreateDTOMock));
    }

    @Test
    @DisplayName("Deveria salvar Usuário Admin com sucesso.")
    void salvarUsuarioAdminComSucesso() {
        // ARRANGE - GIVEN
        Admin admin = retornarAdmin();

        // ACT - WHEN
        when(adminRepository.save(admin)).thenReturn(admin);
        Admin adminRetornado = adminService.salvar(admin);

        // ASSERT / THEN
        assertNotNull(adminRetornado);
        assertEquals(adminRetornado, admin);
    }

    @Test
    @DisplayName("Deveria remover o Usuário Admin com sucesso.")
    void removerLogicoUsuarioAdminComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Optional<Admin> adminOptional = Optional.of(retornarAdmin());
        AdminDTO adminDTOMock = retornarAdminDTO();
        Integer id = new Random().nextInt();

        // ACT - WHEN
        when(adminRepository.findById(id)).thenReturn(adminOptional);
        when(objectMapper.convertValue(adminOptional.get(), AdminDTO.class)).thenReturn(adminDTOMock);
        when(adminRepository.save(adminOptional.get())).thenReturn(adminOptional.get());

        adminService.remover(id);

        // ASSERT / THEN
        verify(adminRepository, times(1)).save(adminOptional.get());
    }

    @Test
    @DisplayName("Deveria retornar Exception ao remover Admin com id inexistente.")
    void retornarExceptionComIdAdminInexistenteLogico() throws Exception {
        // ARRANGE - GIVEN
        Integer id = new Random().nextInt();

        // ACT - WHEN
        when(adminRepository.findById(id)).thenReturn(Optional.empty());


        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> adminService.remover(id));
    }

    @Test
    @DisplayName("Deveria remover fisicamente o Usuário Admin com sucesso.")
    void removerFisicoUsuarioAdminComSucesso() throws Exception {
        // ARRANGE - GIVEN
        Optional<Admin> adminOptional = Optional.of(retornarAdmin());
        AdminDTO adminDTOMock = retornarAdminDTO();
        Integer id = new Random().nextInt();

        // ACT - WHEN
        when(adminRepository.findById(id)).thenReturn(adminOptional);
        when(objectMapper.convertValue(adminOptional.get(), AdminDTO.class)).thenReturn(adminDTOMock);

        adminService.removerFisicamente(id);

        // ASSERT / THEN
        verify(adminRepository, times(1)).delete(adminOptional.get());
    }

    @Test
    @DisplayName("Deveria retornar Exception ao remover fisicamente Admin com id inexistente.")
    void retornarExceptionComIdAdminInexistenteFisico() throws Exception {
        // ARRANGE - GIVEN
        Integer id = new Random().nextInt();

        // ACT - WHEN
        when(adminRepository.findById(id)).thenReturn(Optional.empty());

        // ASSERT / THEN
        assertThrows(ObjetoNaoEncontradoException.class, () -> adminService.removerFisicamente(id));
    }

    @Test
    @DisplayName("Deveria retornar modulo analisado com sucesso.")
    void analisarModulos() throws Exception {
        // ARRANGE - GIVEN
        ModuloDTO moduloDTO = retornarModuloDTO();
        Optional<Admin> adminOptional = Optional.of(retornarAdmin());
        AdminDTO adminDTO = retornarAdminDTO();
        AdminModuloDTO adminModuloDTO = retornarAdminModuloDTO();

        // ACT - WHEN
        when(adminRepository.findById(any())).thenReturn(adminOptional);
        when(adminService.buscarAdminPorId(anyInt())).thenReturn(adminDTO);
        when(moduloService.listarPorIdModulo(anyInt())).thenReturn(moduloDTO);
        when(objectMapper.convertValue(adminService.buscarAdminPorId(anyInt()), AdminModuloDTO.class)).thenReturn(adminModuloDTO);
        when(moduloService.save(moduloDTO)).thenReturn(moduloDTO);

        ModuloDTO moduloDTORetornado = adminService.modudoAnalisado(moduloDTO.getId(),"S", anyInt());

        // ASSERT / THEN
        assertNotNull(moduloDTORetornado);
    }


    public static Page<Admin> criarPageAdminsMock(Pageable pageable) {

        List<Admin> admins = new ArrayList<>();
        admins.add(retornarAdmin());
        admins.add(retornarAdmin());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), admins.size());
        return new PageImpl<>(admins.subList(start, end), pageable, admins.size());
    }

    public static Page<AdminDTO> criarPageAdminsDTOMock(Pageable pageable) {

        List<AdminDTO> admins = new ArrayList<>();
        admins.add(retornarAdminDTO());
        admins.add(retornarAdminDTO());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), admins.size());
        return new PageImpl<>(admins.subList(start, end), pageable, admins.size());
    }


}