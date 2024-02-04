package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.repository.IProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ProfessorService {

    private final IProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public ProfessorDTO criar(ProfessorCreateDTO professorCreateDTO) throws Exception {
        Professor professorEntity = objectMapper.convertValue(professorCreateDTO, Professor.class);

        professorEntity.setAtivo("S");
        professorEntity = professorRepository.save(professorEntity);

        ProfessorDTO professorDTO = objectMapper.convertValue(professorEntity, ProfessorDTO.class);

        emailService.sendEmailProfessor(professorDTO, "Cadastro efetuado, ", "create");

        return professorDTO;
    }

    public List<ProfessorDTO> listar() {
        List<Professor> professors = professorRepository.findAll();

        return professors.stream().map(professor -> objectMapper.convertValue(professor, ProfessorDTO.class)).toList();
    }

    public List<ProfessorDTO> listarAtivos(char ativo) {
        List<Professor> professors = professorRepository.findAllByAtivo(ativo);

        return professors.stream().map(professor -> objectMapper.convertValue(professor, ProfessorDTO.class)).toList();
    }

    public ProfessorDTO buscarProfessorPorId(Integer id) throws ObjetoNaoEncontradoException {
        Optional<Professor> objetoOptional = professorRepository.findById(id);
        if (objetoOptional.isPresent()) {
            return objectMapper.convertValue(objetoOptional.get(), ProfessorDTO.class);

        } else {
            throw new ObjetoNaoEncontradoException("Professor com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public ProfessorDTO atualizar(Integer id, ProfessorCreateDTO professorCreateDTO) throws Exception {
        Optional<Professor> objetoOptional = professorRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Professor professor = objetoOptional.get();
            Professor professorAtualizacoes = objectMapper.convertValue(professorCreateDTO, Professor.class);
            professor.setNome(professorAtualizacoes.getNome());
            professor.setSobrenome(professorAtualizacoes.getSobrenome());
            professor.setEmail(professorAtualizacoes.getEmail());
            professor.setSexo(professorAtualizacoes.getSexo());
            professor.setSenha(professorAtualizacoes.getSenha());
            professor.setCpf(professorAtualizacoes.getCpf());
            professor.setTelefone(professorAtualizacoes.getTelefone());
            professor.setDataDeNascimento(professorAtualizacoes.getDataDeNascimento());
            professor.setDescricao(professorAtualizacoes.getDescricao());

            professor = professorRepository.save(professor);

            ProfessorDTO professorDTO = objectMapper.convertValue(professor, ProfessorDTO.class);

            emailService.sendEmailProfessor(professorDTO, "Cadastro atualizado, ", "update");

            return professorDTO;

        } else {
            throw new ObjetoNaoEncontradoException("Professor com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void remover(int id) throws Exception {
        Optional<Professor> objetoOptional = professorRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Professor professor = objetoOptional.get();

            professor.setAtivo("N");

            professor = professorRepository.save(professor);

            ProfessorDTO professorDTO = objectMapper.convertValue(professor, ProfessorDTO.class);

            emailService.sendEmailProfessor(professorDTO, "Cadastro excluido, ","delete");
        } else {
            throw new ObjetoNaoEncontradoException("Professor com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void removerFisicamente(int id) throws Exception {
        Optional<Professor> objetoOptional = professorRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Professor professor = objetoOptional.get();

            professorRepository.delete(professor);

            ProfessorDTO professorDTO = objectMapper.convertValue(professor, ProfessorDTO.class);

            emailService.sendEmailProfessor(professorDTO, "Cadastro excluido, ","delete");
        } else {
            throw new ObjetoNaoEncontradoException("Professor com o ID " + id + " não encontrado informe um id valido");
        }
    }
}
