package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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

    private ProfessorDTO converterParaDTO(Professor professor) {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setIdProfessor(professor.getIdProfessor());
        professorDTO.setIdUsuario(professor.getIdUsuario());
        professorDTO.setNome(professor.getNome());
        professorDTO.setSobrenome(professor.getSobrenome());
        professorDTO.setTelefone(professor.getTelefone());
        professorDTO.setEmail(professor.getEmail());
        professorDTO.setDescricao(professor.getDescricao());

        return professorDTO;
    }

    private void validarCpf(String cpf) throws RegraDeNegocioException {
        if (cpf.length() != 11) {
            throw new RegraDeNegocioException("CPF Inválido!");
        }
    }
    public Professor adicionar(Professor professor) throws RegraDeNegocioException {
        try {
            validarCpf(professor.getCpf());
            Professor professorAdicionado = professorRepository.adicionar(professor);
            log.info("Professor adicionado com sucesso! {}", professorAdicionado);
            return professorAdicionado;
        } catch (RegraDeNegocioException e) {
            log.error("Erro na regra de negócio: {}", e.getMessage(), e);
            throw e;
        } catch (BancoDeDadosException e) {
            log.error("Erro no banco de dados: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao adicionar professor no banco de dados", e);
        }
    }

    public List<ProfessorDTO> visualizarTodos() throws Exception {
        List<Professor> professores = professorRepository.listar();

        List<ProfessorDTO> professorDTOList = professores.stream()
                .map(professor -> objectMapper.convertValue(professor, ProfessorDTO.class))
                .collect(Collectors.toList());

        return professorDTOList;
    }

    public Professor buscarProfessorPorId(Integer idUsuario){
        try {
            return professorRepository.buscarProfessorPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Professor editar(Integer id, Professor professorEditado) {
//        try {
//            boolean conseguiuEditar = professorRepository.editar(id, professorEditado);
//            log.info("Professor editado com sucesso? {} | com id={}", conseguiuEditar, id);
//            return conseguiuEditar ? professorEditado : null;
//        } catch (BancoDeDadosException e) {
//            log.error("Erro ao editar professor: {}", e.getMessage(), e);
//            throw new RuntimeException("Erro ao editar professor no banco de dados", e);
//        }
        return professorEditado;
    }


    public Professor remover(Integer id) throws BancoDeDadosException {
        try {
            boolean conseguiuRemover = professorRepository.remover(id);

//            if (conseguiuRemover) {
//                log.info("Professor removido com sucesso! | ID: {}", id);
//                return professorRepository.buscarProfessorPorId(id);
//            } else {
//                log.info("Não foi possível remover o professor | ID: {}", id);
//                return null;
//            }
        } catch (BancoDeDadosException e) {
            log.error("Erro ao tentar remover o professor | ID: {}", id, e);
            throw e;
        }
        return null;
    }

//    public Optional<Professor> loginProfessor(String email, String senha) {
//        try {
//            Professor professor = professorRepository.loginProfessor(email, senha);
//            return Optional.ofNullable(professor);
//        } catch (BancoDeDadosException e) {
//            log.error("Erro ao fazer login do professor: {}", e.getMessage(), e);
//            return Optional.empty();
//        }
//    }
}
