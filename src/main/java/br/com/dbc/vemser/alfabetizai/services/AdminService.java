package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.ResponsavelComAlunosDTO;
import br.com.dbc.vemser.alfabetizai.dto.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.models.Admin;

import br.com.dbc.vemser.alfabetizai.models.Responsavel;
import br.com.dbc.vemser.alfabetizai.repository.IAdminRepository;
import br.com.dbc.vemser.alfabetizai.repository.IResponsavelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminService {
    private final IAdminRepository adminRepository;

    private final ObjectMapper objectMapper;

    private final EmailService emailService;

    public AdminDTO criar(AdminCreateDTO adminCreateDTO) throws Exception {
        Admin adminEntity = objectMapper.convertValue(adminCreateDTO, Admin.class);

        adminEntity = adminRepository.save(adminEntity);

        AdminlDTO adminDTO = objectMapper.convertValue(adminEntity, AdminDTO.class);

        emailService.sendEmailAdmin(adminDTO, "Cadastro efetuado, ", "create");

        return adminDTO;
    }

    public List<ResponsavelDTO> listar() throws RegraDeNegocioException {
        List<Responsavel> responsavels = responsavelRepository.findAll();

        return responsavels.stream().map(responsavel -> objectMapper.convertValue(responsavel, ResponsavelDTO.class)).toList();
    }

    public List<ResponsavelDTO> listarAtivos() {
        List<Responsavel> responsavels = responsavelRepository.findAllByAtivo("N");

        return responsavels.stream().map(responsavel -> objectMapper.convertValue(responsavel, ResponsavelDTO.class)).toList();
    }

    public ResponsavelComAlunosDTO buscarResponsavelPorIdComAlunos(Integer id) throws ObjetoNaoEncontradoException {
        Optional<Responsavel> objetoOptional = responsavelRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Responsavel responsavel = objetoOptional.get();
            //   List<Aluno> listaAlunos = alunoService.buscarAlunosPorIdResponsavel(responsavel.getIdUsuario());
            //        responsavel.setAlunos(listaAlunos);
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

    public Responsavel salvar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
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

            responsavelRepository.delete(responsavel);

            ResponsavelDTO responsavelDTO = objectMapper.convertValue(responsavel, ResponsavelDTO.class);

            emailService.sendEmailResponsavel(responsavelDTO, "Cadastro excluido, ","delete");
        } else {
            throw new ObjetoNaoEncontradoException("Responsavel com o ID " + id + " não encontrado informe um id valido");
        }
    }
}
