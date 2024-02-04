package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.ResponsavelComAlunosDTO;
import br.com.dbc.vemser.alfabetizai.dto.ResponsavelCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.ResponsavelDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
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

        adminEntity.setAtivo("S");
        adminEntity = adminRepository.save(adminEntity);

        AdminDTO adminDTO = objectMapper.convertValue(adminEntity, AdminDTO.class);

        emailService.sendEmailAdmin(adminDTO, "Cadastro efetuado, ", "create");

        return adminDTO;
    }

    public List<AdminDTO> listar() throws RegraDeNegocioException {
        List<Admin> admins = adminRepository.findAll();

        return admins.stream().map(admin -> objectMapper.convertValue(admin, AdminDTO.class)).toList();
    }

    public List<AdminDTO> listarAtivos() {
        List<Admin> admins = adminRepository.findAllByAtivo("S");

        return admins.stream().map(admin -> objectMapper.convertValue(admin, AdminDTO.class)).toList();
    }


    public AdminDTO buscarAdminPorId(Integer id) throws ObjetoNaoEncontradoException {
        Optional<Admin> objetoOptional = adminRepository.findById(id);
        if (objetoOptional.isPresent()) {
            return objectMapper.convertValue(objetoOptional.get(), AdminDTO.class);
        } else {
            throw new ObjetoNaoEncontradoException("Admin com o ID " + id + " não encontrado informe um id válido");
        }
    }

    public AdminDTO atualizar(Integer id, AdminCreateDTO adminCreateDTO) throws Exception {
        Optional<Admin> objetoOptional = adminRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Admin admin = objetoOptional.get();
            Admin adminAtualizacao = objectMapper.convertValue(adminCreateDTO, Admin.class);
            adminAtualizacao.setIdUsuario(admin.getIdUsuario());

            adminAtualizacao = adminRepository.save(admin);

            AdminDTO adminDTO = objectMapper.convertValue(adminAtualizacao,AdminDTO.class);

            emailService.sendEmailAdmin(adminDTO, "Cadastro atualizado, ", "update");

            return adminDTO;

        } else {
            throw new ObjetoNaoEncontradoException("Admin com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public Admin salvar(Admin admin) {
        return adminRepository.save(admin);
    }

    public void remover(int id) throws Exception {
        Optional<Admin> objetoOptional = adminRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Admin admin = objetoOptional.get();

            admin.setAtivo("N");

            admin = adminRepository.save(admin);

            AdminDTO adminDTO = objectMapper.convertValue(admin, AdminDTO.class);

            emailService.sendEmailAdmin(adminDTO, "Cadastro excluido, ","delete");
        } else {
            throw new ObjetoNaoEncontradoException("Admin com o ID " + id + " não encontrado informe um id valido");
        }
    }

    public void removerFisicamente(int id) throws Exception {
        Optional<Admin> objetoOptional = adminRepository.findById(id);
        if (objetoOptional.isPresent()) {
            Admin admin = objetoOptional.get();

            adminRepository.delete(admin);

            AdminDTO adminDTO = objectMapper.convertValue(admin, AdminDTO.class);

            emailService.sendEmailAdmin(adminDTO, "Cadastro excluido, ","delete");
        } else {
            throw new ObjetoNaoEncontradoException("Admin com o ID " + id + " não encontrado informe um id valido");
        }
    }
}
