package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IAlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {
    private final IAlunoRepository alunoRepository;

    private final ObjectMapper objectMapper;

    private final DesafioService desafioService;

    private final ModuloService moduloService;

    private final ResponsavelService responsavelService;

    public AlunoDTO criar(Integer idResponsavel, AlunoCreateDTO alunoCreateDTO) throws Exception {
        Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);

        Responsavel responsavel = responsavelService.buscarResponsavelPorId(idResponsavel);
        alunoEntity.setResponsavel(responsavel);

        alunoRepository.save(alunoEntity);

        return objectMapper.convertValue(alunoEntity, AlunoDTO.class);
    }

    public List<AlunoDTO> listar() throws RegraDeNegocioException {
        List<Aluno> alunos = alunoRepository.findAll();

        return alunos.stream().map(aluno -> objectMapper.convertValue(aluno, AlunoDTO.class)).toList();
    }

    public AlunoDTO buscarAlunoPorId(Integer id) throws ObjetoNaoEncontradoException {
        Optional<Aluno> objetoOptional = alunoRepository.findById(id);
        if (objetoOptional.isPresent()) {
            return objectMapper.convertValue(objetoOptional.get(), AlunoDTO.class);
        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public List<AlunoDTO> buscarAlunosPorIdResponsavel(Integer id) throws ObjetoNaoEncontradoException {
        Responsavel responsavel = responsavelService.buscarResponsavelPorId(id);
        Optional<List<Aluno>> objetoOptional = alunoRepository.findAllByResponsavel(responsavel);
        if (objetoOptional.isPresent()) {
            return objetoOptional.get().stream().map(aluno -> objectMapper.convertValue(aluno, AlunoDTO.class)).toList();
        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o IDResponsavel " + id + " não encontrado informe um id valido");
        }
    }

    public AlunoDTO atualizar(Integer id, AlunoCreateDTO alunoCreateDTO) throws Exception {
        Optional<Aluno> objetoOptional = alunoRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Aluno aluno = objetoOptional.get();
            Aluno alunoAtualizacoes = objectMapper.convertValue(alunoCreateDTO, Aluno.class);
            aluno.setNomeAluno(alunoAtualizacoes.getNomeAluno());
            aluno.setSobrenomeAluno(alunoAtualizacoes.getSobrenomeAluno());
            aluno.setSexoAluno(alunoAtualizacoes.getSexoAluno());
            aluno.setDataNascimentoAluno(alunoAtualizacoes.getDataNascimentoAluno());

            aluno = alunoRepository.save(aluno);

            AlunoDTO alunoDTO = objectMapper.convertValue(aluno, AlunoDTO.class);

            return alunoDTO;

        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void remover(int id) throws Exception {
        Optional<Aluno> objetoOptional = alunoRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Aluno aluno = objetoOptional.get();

            aluno.setAtivo("N");

            alunoRepository.save(aluno);
        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void removerFisicamente(int id) throws Exception {
        Optional<Aluno> objetoOptional = alunoRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Aluno responsavel = objetoOptional.get();

            alunoRepository.delete(responsavel);

        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + id + " não encontrado informe um id valido");
        }
    }

//    public List<DesafioDTO> listarDesafiosConcluidos(Integer idAluno) throws Exception {
//        return desafioService.listardesafiosConcluidos(idAluno);
//    }
//
//    public List<ModuloDTO> listarModulosConcluidos(Integer idAluno) throws Exception {
//        return moduloService.listarModulosConcluidos(idAluno);
//    }

}
