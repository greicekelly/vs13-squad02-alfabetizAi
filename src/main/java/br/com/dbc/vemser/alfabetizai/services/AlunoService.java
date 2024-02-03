package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.*;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;

import br.com.dbc.vemser.alfabetizai.repository.IAlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {
//    private final IAlunoRepository alunoRepository;
//
//    private final ObjectMapper objectMapper;
//
//    private final DesafioService desafioService;
//
//    private final ModuloService moduloService;
//
//    private final EmailService emailService;
//
//    public AlunoDTO criar(AlunoCreateDTO alunoCreateDTO) throws Exception {
//        try {
//            Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);
//
//            alunoEntity = alunoRepository.save(alunoEntity);
//
//            AlunoDTO alunoDTO = objectMapper.convertValue(alunoEntity, AlunoDTO.class);
//
//            emailService.sendEmailAluno(alunoDTO, "Cadastro efetuado, ", "create");
//
//            return alunoDTO;
//        } catch (BancoDeDadosException e) {
//            throw new RegraDeNegocioException("Algum problema ocorreu ao adicionar aluno, revise os dados" + e.getMessage());
//        }
//    }
//
//    public AlunoDTO adicionarAluno(Integer id, AlunoAdicionarCreateDTO alunoAdicionarCreateDTO) {
//        Aluno alunoEntity = objectMapper.convertValue(alunoAdicionarCreateDTO, Aluno.class);
//
//        alunoEntity = alunoRepository.save(alunoEntity);
//
//        AlunoDTO alunoDTO = objectMapper.convertValue(alunoEntity, AlunoDTO.class);
//
//        return alunoDTO;
//    }
//
//    public List<AlunoDTO> listar() throws RegraDeNegocioException {
//        List<Aluno> alunos = alunoRepository.findAll();
//
//        return alunos.stream().map(aluno -> objectMapper.convertValue(aluno, AlunoDTO.class)).toList();
//    }
//
//    public AlunoDTO buscarAlunoPorId(Integer idUsuario) {
//        Aluno alunoEntity = alunoRepository.getById(idUsuario);
//
//        return objectMapper.convertValue(alunoEntity, AlunoDTO.class);
//    }
//
//    public AlunoDTO atualizar(Integer id, AlunoCreateDTO alunoCreateDTO) throws Exception {
//        try {
//            Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);
//
//            alunoEntity = alunoRepository.save(alunoEntity);
//
//            AlunoDTO alunoDTO = objectMapper.convertValue(alunoEntity, AlunoDTO.class);
//
//           emailService.sendEmailAluno(alunoDTO, "Cadastro atualizado, ", "update");
//
//            return alunoDTO;
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            throw new RegraDeNegocioException("Algum problema ocorreu ao atualizar aluno" + e.getMessage());
//        }
//    }
//
//    public void remover(int id) throws Exception {
//        try {
//            Aluno aluno = alunoRepository.getById(id);
//            alunoRepository.delete(aluno);
//            AlunoDTO alunoDTO = buscarAlunoPorId(id);
//            emailService.sendEmailAluno(alunoDTO, "Cadastro excluido, ","delete");
//        } catch (BancoDeDadosException e) {
//            throw new RegraDeNegocioException("Algum problema ocorreu ao deletar aluno" + e.getMessage());
//        }
//    }
//
//    public List<DesafioDTO> listarDesafiosConcluidos(Integer idAluno) throws Exception {
//        return desafioService.listardesafiosConcluidos(idAluno);
//    }
//
//    public List<ModuloDTO> listarModulosConcluidos(Integer idAluno) throws Exception {
//        return moduloService.listarModulosConcluidos(idAluno);
//    }

}
