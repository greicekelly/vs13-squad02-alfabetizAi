package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.log.LogCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.aluno.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoCreateDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IAlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlunoService {
    private final IAlunoRepository alunoRepository;

    private final ObjectMapper objectMapper;

    private final DesafioService desafioService;

    private final ModuloService moduloService;

    private final ResponsavelService responsavelService;

    private final LogService logService;

    private final NotificacaoService notificacaoService;

    public AlunoDTO criar(Integer idResponsavel, AlunoCreateDTO alunoCreateDTO) throws Exception {
        Aluno alunoEntity = objectMapper.convertValue(alunoCreateDTO, Aluno.class);

        Responsavel responsavel = responsavelService.buscarResponsavelPorId(idResponsavel);
        alunoEntity.setResponsavel(responsavel);

        alunoEntity.setAtivo("S");
        alunoRepository.save(alunoEntity);

        logService.registerLog(new LogCreateDTO(TipoLog.ALUNO, "ALUNO CADASTRADO", LocalDate.now().toString()));

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

    public List<AlunoDTO> buscarAlunosPorIdResponsavel(Integer id) throws Exception {
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

            logService.registerLog(new LogCreateDTO(TipoLog.ALUNO, "ALUNO ATUALIZADO", LocalDate.now().toString()));

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

            logService.registerLog(new LogCreateDTO(TipoLog.ALUNO, "ALUNO REMOVIDO", LocalDate.now().toString()));

        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public List<DesafioDTO> listarDesafiosConcluidos(Integer idAluno) throws Exception {
        Optional<Aluno> objetoOptional = alunoRepository.findById(idAluno);
        if (objetoOptional.isPresent()) {
            Aluno aluno = objetoOptional.get();

            if(aluno.getDesafios().isEmpty()) {
                throw new RegraDeNegocioException("Aluno não possui desafio concluido");
            }

            return aluno.getDesafios().stream()
                    .map(this::retornarDesafioDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + idAluno + " não encontrado informe um id valido");
        }
    }

    public List<ModuloDTO> listarModulosConcluidos(Integer idAluno) throws Exception {
        Optional<Aluno> objetoOptional = alunoRepository.findById(idAluno);
        if (objetoOptional.isPresent()) {
            Aluno aluno = objetoOptional.get();

            if(aluno.getModulos().isEmpty()) {
                throw new RegraDeNegocioException("Aluno não possui modulo concluido");
            }

            return aluno.getModulos().stream()
                    .map(this::retornarModuloDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + idAluno + " não encontrado informe um id valido");
        }
    }

    public void fazerModulo(Integer idALuno, Integer idModulo) throws Exception {
       Modulo modulo = moduloService.buscarModuloPorId(idModulo);
        Optional<Aluno> objetoOptional = alunoRepository.findById(idALuno);
        if (objetoOptional.isPresent()) {
            Aluno aluno = objetoOptional.get();
            aluno.getModulos().add(modulo);
            alunoRepository.save(aluno);

            notificacaoService.registerEvent(new NotificacaoCreateDTO(TipoStatus.CONCLUIDO, "MODULO CONCLUIDO - "+modulo.getTitulo(), aluno.getNomeAluno(), aluno.getResponsavel().getNome(), aluno.getResponsavel().getTelefone(), LocalDate.now().toString()));
        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + idALuno + " não encontrado informe um id valido");
        }
    }

    public void fazerDesafio(Integer idALuno, Integer idDesafio) throws Exception {
        Desafio desafio = desafioService.desafioPorId(idDesafio);
        Optional<Aluno> objetoOptional = alunoRepository.findById(idALuno);
        if (objetoOptional.isPresent()) {
            Aluno aluno = objetoOptional.get();

            if(jaFezDesafio(aluno, desafio)) {
                throw new RegraDeNegocioException("Aluno ja concluiu este desafio anteriormente");
            }
            notificacaoService.registerEvent(new NotificacaoCreateDTO(TipoStatus.INICIADO, "DESAFIO INICIADO - "+desafio.getTitulo(), aluno.getNomeAluno(), aluno.getResponsavel().getNome(), aluno.getResponsavel().getTelefone(), LocalDate.now().toString()));
            aluno.getDesafios().add(desafio);
            aluno.adicionarPontuacao(desafio.getPontos());
            alunoRepository.save(aluno);
            concluirModulo(aluno,desafio);

        } else {
            throw new ObjetoNaoEncontradoException("Aluno com o ID " + idALuno + " não encontrado informe um id valido");
        }
    }

    public void concluirModulo(Aluno aluno, Desafio desafio) throws Exception {
       Modulo modulo = moduloService.buscarModuloPorId(desafio.getModulo().getId());
       if (aluno.getDesafios().containsAll(modulo.getDesafios())) {
           fazerModulo(aluno.getIdAluno(), desafio.getModulo().getId());
           notificacaoService.registerEvent(new NotificacaoCreateDTO(TipoStatus.EM_ANDAMENTO, "MODULO EM ANDAMENTO - "+modulo.getTitulo(), aluno.getNomeAluno(), aluno.getResponsavel().getNome(), aluno.getResponsavel().getTelefone(), LocalDate.now().toString()));
       }
    }

    public boolean jaFezDesafio(Aluno aluno, Desafio desafio) {
        return aluno.getDesafios().contains(desafio);
    }

    private DesafioDTO retornarDesafioDTO(Desafio entity) {
        return objectMapper.convertValue(entity, DesafioDTO.class);
    }

    private ModuloDTO retornarModuloDTO(Modulo entity) {
        return objectMapper.convertValue(entity, ModuloDTO.class);
    }
}
