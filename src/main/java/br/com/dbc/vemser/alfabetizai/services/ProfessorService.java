package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.models.Professor;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class ProfessorService {
//    @Autowired
//    private final ProfessorRepository professorRepository;
//    private final ObjectMapper objectMapper;
//    private final EmailService emailService;
//
//    public ProfessorDTO adicionar(ProfessorCreateDTO professorCreateDTO) throws Exception {
//        Professor professorEntity = objectMapper.convertValue(professorCreateDTO, Professor.class);
//        professorEntity = professorRepository.adicionar(professorEntity);
//
//        ProfessorDTO professorDTO = objectMapper.convertValue(professorEntity, ProfessorDTO.class);
//
//        emailService.sendEmailProfessor(professorDTO, "Cadastro efetuado, ", "create");
//
//        return professorDTO;
//    }
//
//    public List<ProfessorDTO> visualizarTodos() throws Exception {
//        List<Professor> professores = professorRepository.listar();
//
//        return professores.stream()
//                .map(professor -> objectMapper.convertValue(professor, ProfessorDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    public ProfessorDTO buscarProfessorPorIdUsuario(Integer idUsuario) throws Exception {
//        Professor professor = professorRepository.buscarProfessorPorIdUsuario(idUsuario);
//
//        return objectMapper.convertValue(professor, ProfessorDTO.class);
//    }
//
//    public ProfessorDTO buscarProfessorPorId(Integer id) throws Exception {
//        Professor professor = professorRepository.buscarProfessorPorId(id);
//
//        return objectMapper.convertValue(professor, ProfessorDTO.class);
//    }
//
//    public ProfessorDTO editar(Integer id, ProfessorCreateDTO professorCreateDTO) throws Exception {
//        Professor professorEntity = objectMapper.convertValue(professorCreateDTO, Professor.class);
//
//        professorEntity = professorRepository.editar(id, professorEntity);
//
//        ProfessorDTO professorDTO = objectMapper.convertValue(professorEntity, ProfessorDTO.class);
//
//        emailService.sendEmailProfessor(professorDTO, "Cadastro atualizado, ","update");
//        return professorDTO;
//    }
//
//    public void remover(Integer id) throws Exception {
//        professorRepository.remover(id);
//        ProfessorDTO professorDTO = buscarProfessorPorIdUsuario(id);
//        emailService.sendEmailProfessor(professorDTO, "Cadastro excluido, ","delete");
//    }
}
