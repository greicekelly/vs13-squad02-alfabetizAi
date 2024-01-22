package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.repository.AlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    private final ObjectMapper objectMapper;

    public AlunoService(AlunoRepository alunoRepository, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO criar(AlunoCreateDTO alunoCreateDTO) throws RegraDeNegocioException {
        try {
            Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);

            alunoEntity = alunoRepository.adicionar(alunoEntity);

            return objectMapper.convertValue(alunoEntity, AlunoDTO.class);
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

    public AlunoDTO BuscarAlunoPorId(Integer idUsuario) throws RegraDeNegocioException {
        try {
            Aluno alunoEntity = alunoRepository.BuscarAlunoPorId(idUsuario);

            return objectMapper.convertValue(alunoEntity, AlunoDTO.class);
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao buscar aluno" + e.getMessage());
        }
    }

    public boolean atualizar(Integer id, AlunoCreateDTO alunoCreateDTO) throws RegraDeNegocioException {
        try {
            Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);
           return alunoRepository.editar(id, alunoEntity);
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao atualizar aluno" + e.getMessage());
        }
    }

    public void remover(int id) throws RegraDeNegocioException {
        try {

            alunoRepository.remover(id);
        } catch (BancoDeDadosException e) {
            throw new RegraDeNegocioException("Algum problema ocorreu ao deletar aluno" + e.getMessage());
        }
    }
}
