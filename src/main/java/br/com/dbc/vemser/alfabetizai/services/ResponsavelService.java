package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelComAlunosDTO;
import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelCreateDTO;

import br.com.dbc.vemser.alfabetizai.dto.responsavel.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Cargo;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IResponsavelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResponsavelService {
    private final IResponsavelRepository responsavelRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ResponsavelDTO criar(ResponsavelCreateDTO responsavelCreateDTO) throws Exception {
        Responsavel responsavelEntity = objectMapper.convertValue(responsavelCreateDTO, Responsavel.class);

        responsavelPorCpfEmail(responsavelCreateDTO.getCpf(), responsavelCreateDTO.getEmail());

        responsavelEntity.setAtivo("S");

        String senha = passwordEncoder.encode(responsavelEntity.getPassword());
        responsavelEntity.setSenha(senha);

        Cargo cargo = new Cargo();
        cargo.setIdCargo(3);
        cargo.setNome("ROLE_RESPONSAVEL");

        responsavelEntity.setCargos(List.of(cargo));

        responsavelEntity = responsavelRepository.save(responsavelEntity);

        ResponsavelDTO responsavelDTO = objectMapper.convertValue(responsavelEntity, ResponsavelDTO.class);

        emailService.sendEmailResponsavel(responsavelDTO, "Cadastro efetuado, ", "create");

        return responsavelDTO;
    }

    private Responsavel responsavelPorCpfEmail(String cpf, String email) throws Exception {
        Responsavel responsavel = responsavelRepository.findAllByCpfOrEmail(cpf, email);
        if (responsavel != null) {
            throw new RegraDeNegocioException("Cpf ou Email já estão em uso.");
        } else {
            return responsavel;
        }
    }

    public List<ResponsavelDTO> listar() throws RegraDeNegocioException {
        List<Responsavel> responsavels = responsavelRepository.findAll();

        return responsavels.stream().map(responsavel -> objectMapper.convertValue(responsavel, ResponsavelDTO.class)).toList();
    }

    public Page<ResponsavelDTO> listar(Pageable pageable) throws RegraDeNegocioException {
        Page<Responsavel> responsaveis = responsavelRepository.findAll(pageable);

        return responsaveis.map(responsavel -> objectMapper.convertValue(responsavel, ResponsavelDTO.class));
    }

    public List<ResponsavelDTO> listarAtivos(char ativo) {
        List<Responsavel> responsavels = responsavelRepository.findAllByAtivo(ativo);

        return responsavels.stream().map(responsavel -> objectMapper.convertValue(responsavel, ResponsavelDTO.class)).toList();
    }

    public ResponsavelComAlunosDTO buscarResponsavelPorIdComAlunos(Integer id) throws ObjetoNaoEncontradoException {
        Optional<Responsavel> objetoOptional = responsavelRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Responsavel responsavel = objetoOptional.get();
            return objectMapper.convertValue(responsavel, ResponsavelComAlunosDTO.class);

        } else {
            throw new ObjetoNaoEncontradoException("Responsavel com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public Responsavel buscarResponsavelPorId(Integer id) throws ObjetoNaoEncontradoException {
        Optional<Responsavel> objetoOptional = responsavelRepository.findById(id);
        if (objetoOptional.isPresent()) {
            return objetoOptional.get();
        } else {
            throw new ObjetoNaoEncontradoException("Responsavel com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public ResponsavelDTO atualizar(Integer id, ResponsavelCreateDTO responsavelCreateDTO) throws Exception {
        Optional<Responsavel> objetoOptional = responsavelRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Responsavel responsavel = objetoOptional.get();
            Responsavel responsavelAtualizacoes = objectMapper.convertValue(responsavelCreateDTO, Responsavel.class);
            responsavel.setNome(responsavelAtualizacoes.getNome());
            responsavel.setSobrenome(responsavelAtualizacoes.getSobrenome());
            responsavel.setEmail(responsavelAtualizacoes.getEmail());
            responsavel.setSexo(responsavelAtualizacoes.getSexo());
            responsavel.setSenha(responsavelAtualizacoes.getSenha());
            responsavel.setCpf(responsavelAtualizacoes.getCpf());
            responsavel.setTelefone(responsavelAtualizacoes.getTelefone());
            responsavel.setDataDeNascimento(responsavelAtualizacoes.getDataDeNascimento());

            responsavel = responsavelRepository.save(responsavel);

            ResponsavelDTO responsavelDTO = objectMapper.convertValue(responsavel, ResponsavelDTO.class);

            emailService.sendEmailResponsavel(responsavelDTO, "Cadastro atualizado, ", "update");

            return responsavelDTO;

        } else {
            throw new ObjetoNaoEncontradoException("Responsavel com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void remover(int id) throws Exception {
        Optional<Responsavel> objetoOptional = responsavelRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Responsavel responsavel = objetoOptional.get();

            responsavel.setAtivo("N");

            responsavel = responsavelRepository.save(responsavel);

            ResponsavelDTO responsavelDTO = objectMapper.convertValue(responsavel, ResponsavelDTO.class);

            emailService.sendEmailResponsavel(responsavelDTO, "Cadastro excluido, ","delete");
        } else {
            throw new ObjetoNaoEncontradoException("Responsavel com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void removerFisicamente(int id) throws Exception {
        Optional<Responsavel> objetoOptional = responsavelRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Responsavel responsavel = objetoOptional.get();

            responsavel.setCargos(null);

            responsavelRepository.delete(responsavel);

            ResponsavelDTO responsavelDTO = objectMapper.convertValue(responsavel, ResponsavelDTO.class);

            emailService.sendEmailResponsavel(responsavelDTO, "Cadastro excluido, ","delete");
        } else {
            throw new ObjetoNaoEncontradoException("Responsavel com o ID " + id + " não encontrado informe um id valido");
        }
    }
}
