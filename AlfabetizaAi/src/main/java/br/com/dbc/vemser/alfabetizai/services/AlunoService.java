package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.repository.AlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

    private final ObjectMapper objectMapper;

    private final EmailService emailService;


    public AlunoDTO criar(AlunoCreateDTO alunoCreateDTO) throws Exception {
        try {
            Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);

            alunoEntity = alunoRepository.adicionar(alunoEntity);

            AlunoDTO alunoDTO = objectMapper.convertValue(alunoEntity, AlunoDTO.class);

            emailService.sendEmailAluno(alunoDTO, "Cadastro efetuado, ", "create");

            return alunoDTO;
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao adicionar aluno, revise os dados" + e.getMessage());
        }
    }

    public List<AlunoDTO> listar() throws RegraDeNegocioException {
        try {
            List<Aluno> alunos = alunoRepository.listar();

            return alunos.stream().map(aluno -> objectMapper.convertValue(aluno, AlunoDTO.class)).toList();
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao listar alunos" + e.getMessage());
        }
    }

    public AlunoDTO buscarAlunoPorId(Integer idUsuario) throws Exception {
        try {
            Aluno alunoEntity = alunoRepository.buscarAlunoPorId(idUsuario);

            return objectMapper.convertValue(alunoEntity, AlunoDTO.class);
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao buscar aluno" + e.getMessage());
        }
    }

    public AlunoDTO atualizar(Integer id, AlunoCreateDTO alunoCreateDTO) throws Exception {
        try {
            Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);

            AlunoDTO alunoDTO = objectMapper.convertValue(alunoRepository.editar(id, alunoEntity), AlunoDTO.class);

            emailService.sendEmailAluno(alunoDTO, "Cadastro atualizado, ", "update");

            return alunoDTO;

        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao atualizar aluno" + e.getMessage());
        }
    }

    public void remover(int id) throws Exception {
        try {
            alunoRepository.remover(id);
            AlunoDTO alunoDTO = buscarAlunoPorId(id);
            emailService.sendEmailAluno(alunoDTO, "Cadastro excluido, ","delete");
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao deletar aluno" + e.getMessage());
        }
    }
}
