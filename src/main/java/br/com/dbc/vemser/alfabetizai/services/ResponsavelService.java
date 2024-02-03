package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.ResponsavelCreateDTO;

import br.com.dbc.vemser.alfabetizai.dto.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IResponsavelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResponsavelService {
    private final IResponsavelRepository responsavelRepository;

    private final ObjectMapper objectMapper;

    private final EmailService emailService;

    public ResponsavelDTO criar(ResponsavelCreateDTO responsavelCreateDTO) throws Exception {
        Responsavel responsavelEntity = objectMapper.convertValue(responsavelCreateDTO, Responsavel.class);

        responsavelEntity = responsavelRepository.save(responsavelEntity);

        ResponsavelDTO responsavelDTO = objectMapper.convertValue(responsavelEntity, ResponsavelDTO.class);

        emailService.sendEmailResponsavel(responsavelDTO, "Cadastro efetuado, ", "create");

        return responsavelDTO;
    }

    public List<ResponsavelDTO> listar() throws RegraDeNegocioException {
        List<Responsavel> responsavels = responsavelRepository.findAll();

        return responsavels.stream().map(responsavel -> objectMapper.convertValue(responsavel, ResponsavelDTO.class)).toList();
    }

    public ResponsavelDTO buscarResponsavelPorId(Integer idUsuario) {
        Responsavel responsavelEntity = responsavelRepository.getById(idUsuario);

        return objectMapper.convertValue(responsavelEntity, ResponsavelDTO.class);
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
            throw new ObjetoNaoEncontradoException("Objeto com o ID " + id + " não encontrado informe um id valido");
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
            throw new ObjetoNaoEncontradoException("Objeto com o ID " + id + " não encontrado informe um id valido");
        }
    }


}
