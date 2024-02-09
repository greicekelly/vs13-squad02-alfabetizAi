package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.ObjetoNaoEncontradoException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.repository.IAdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdminService {
    private final IAdminRepository adminRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final ModuloService moduloService;
    private final PasswordEncoder passwordEncoder;


    public AdminDTO criar(AdminCreateDTO adminCreateDTO) throws Exception {
        Admin adminEntity = objectMapper.convertValue(adminCreateDTO, Admin.class);

        adminPorCpfEmail(adminCreateDTO.getCpf(), adminCreateDTO.getEmail());

        adminEntity.setAtivo("S");
        String senha = passwordEncoder.encode(adminEntity.getPassword());
        adminEntity.setSenha(senha);
        adminEntity = adminRepository.save(adminEntity);

        AdminDTO adminDTO = objectMapper.convertValue(adminEntity, AdminDTO.class);

        emailService.sendEmailAdmin(adminDTO, "Cadastro efetuado, ", "create");

        return adminDTO;
    }

    private Admin adminPorCpfEmail(String cpf, String email) throws Exception {
        Admin admin = adminRepository.findAllByCpfOrEmail(cpf, email);
        if (admin != null) {
            throw new RegraDeNegocioException("Cpf ou Email já estão em uso.");
        } else {
           return admin;
        }
    }

    public Page<AdminDTO> listar(Pageable pageable) throws RegraDeNegocioException {
        Page<Admin> admins = adminRepository.findAll(pageable);

        return admins.map(admin -> objectMapper.convertValue(admin, AdminDTO.class));
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
        Optional<Admin> admin = adminRepository.findById(id);

        if (admin.isPresent()) {

            Admin adminAtualizacao = objectMapper.convertValue(adminCreateDTO, Admin.class);
            adminAtualizacao.setIdUsuario(admin.get().getIdUsuario());
            adminAtualizacao.setAtivo("S");

            adminAtualizacao = adminRepository.save(adminAtualizacao);

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

    public ModuloDTO modudoAnalisado (Integer idModulo, String analise, Integer idAdmin) throws Exception {

        ModuloDTO moduloDTO = moduloService.listarPorIdModulo(idModulo);
        AdminModuloDTO adminModuloDTO = objectMapper.convertValue(buscarAdminPorId(idAdmin), AdminModuloDTO.class);

        moduloDTO.setAdmin(adminModuloDTO);
        moduloDTO.setFoiAprovado(analise);

        moduloDTO = moduloService.save(moduloDTO);

        return moduloDTO;
    }

//    public Optional<Admin> loginAdmin(String email, String senha) {
//        return adminRepository.findByEmailAndSenha(email, senha);
//    }
}
