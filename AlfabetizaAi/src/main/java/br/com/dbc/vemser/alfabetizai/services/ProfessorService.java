package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.ProfessorRepository;
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
    @Autowired
    private final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;

    public ProfessorDTO adicionar(ProfessorCreateDTO professorCreateDTO) throws Exception {
        Professor professorEntity = objectMapper.convertValue(professorCreateDTO, Professor.class);
        professorEntity = professorRepository.adicionar(professorEntity);

        return objectMapper.convertValue(professorEntity, ProfessorDTO.class);
    }

    public List<ProfessorDTO> visualizarTodos() throws Exception {
        List<Professor> professores = professorRepository.listar();

        return professores.stream()
                .map(professor -> objectMapper.convertValue(professor, ProfessorDTO.class))
                .collect(Collectors.toList());
    }

    public ProfessorDTO buscarProfessorPorIdUsuario(Integer idUsuario) throws Exception {
        Professor professor = professorRepository.buscarProfessorPorIdUsuario(idUsuario);

        return objectMapper.convertValue(professor, ProfessorDTO.class);
    }

    public ProfessorDTO buscarProfessorPorId(Integer id) throws Exception {
        Professor professor = professorRepository.buscarProfessorPorId(id);

        return objectMapper.convertValue(professor, ProfessorDTO.class);
    }

    public ProfessorDTO editar(Integer id, ProfessorCreateDTO professorCreateDTO) throws Exception {
        //TODO ADICIONAR VALIDACAO ID
        Professor professorEntity = objectMapper.convertValue(professorCreateDTO, Professor.class);

        professorEntity = professorRepository.editar(id, professorEntity);

        return objectMapper.convertValue(professorEntity, ProfessorDTO.class);
    }

    public void remover(Integer id) throws Exception {
        //TODO ADICIONAR VALIDACAO ID
        professorRepository.remover(id);
    }
}
