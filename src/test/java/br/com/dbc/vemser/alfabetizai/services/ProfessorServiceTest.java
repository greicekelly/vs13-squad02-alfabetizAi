package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.professor.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.IProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProfessorService - Teste")
class ProfessorServiceTest {

    @Mock
    private IProfessorRepository professorRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private  EmailService emailService;

    @InjectMocks
    private ProfessorService professorService;

    @Test
    @DisplayName("Deveria listar todos professores com sucesso")
    public void deveriaListarTodosProfessores() {
        List<Professor> listaMock = List.of(retornarProfessor(), retornarProfessor(), retornarProfessor());
        when(professorRepository.findAll()).thenReturn(listaMock);
        List<ProfessorDTO> listaRetornoProfessorDTO = professorService.listar();

        assertNotNull(listaRetornoProfessorDTO);
        assertEquals(listaMock.size(), listaRetornoProfessorDTO.size());
    }
    @Test
    @DisplayName("Deveria criar professor com sucesso")
    public void deveriaCriarProfessorComSucesso()throws Exception{

        ProfessorCreateDTO professorCreateDTOMock = retornarProfessorCreateDTO();
        Professor professorMock = retornarProfessor();
        ProfessorDTO professorDTOMock = retornarProfessorDTO();

        when(objectMapper.convertValue(professorCreateDTOMock, Professor.class)).thenReturn(professorMock);
        when(professorRepository.save(any())).thenReturn(professorMock);
        when(objectMapper.convertValue(professorMock, ProfessorDTO.class)).thenReturn(professorDTOMock);

        ProfessorDTO professorDtoCriado = professorService.criar(professorCreateDTOMock);
        
        if (!Boolean.parseBoolean(System.getProperty("test.ignore.emails"))) {
            emailService.sendEmailProfessor(professorDTOMock, "Cadastro efetuado, ", "create");
        }
        assertNotNull(professorDtoCriado);
        assertEquals(professorDtoCriado, professorDTOMock);
    }

    private static Professor retornarProfessor() {
        Professor professor = new Professor();
        professor.setIdUsuario(1);
        professor.setNome("Jake");
        professor.setSobrenome("Oliveira");
        professor.setTelefone("997239878");
        professor.setEmail("jake@email.com");
        professor.setDataDeNascimento(LocalDate.parse("2022-02-01"));
        professor.setAtivo("S");
        professor.setSexo("M");
        professor.setSenha("123");
        professor.setCpf("57665284000");
        professor.setDescricao("Licenciatura em Letras");

        return professor;

    }

    private static ProfessorDTO retornarProfessorDTO() {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setIdUsuario(1);
        professorDTO.setNome("Jake");
        professorDTO.setSobrenome("Oliveira");
        professorDTO.setTelefone("997239878");
        professorDTO.setEmail("jake@email.com");
        professorDTO.setAtivo("S");
        professorDTO.setDescricao("Licenciatura em Letras");

        return professorDTO;

    }

    private static ProfessorCreateDTO retornarProfessorCreateDTO(){
    ProfessorCreateDTO professorCreateDTO = new ProfessorCreateDTO();
        professorCreateDTO.setNome("Jake");
        professorCreateDTO.setSobrenome("Oliveira");
        professorCreateDTO.setTelefone("997239878");
        professorCreateDTO.setEmail("jake@email.com");
        professorCreateDTO.setDataDeNascimento(LocalDate.parse("2022-02-01"));
        professorCreateDTO.setSexo("M");
        professorCreateDTO.setSenha("123");
        professorCreateDTO.setCpf("57665284000");
        professorCreateDTO.setDescricao("Licenciatura em Letras");

        return professorCreateDTO;
    }
}