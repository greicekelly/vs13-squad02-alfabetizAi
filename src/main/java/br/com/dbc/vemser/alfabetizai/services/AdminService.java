package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final ModuloService moduloService;

    public List<AdminDTO> listar() throws Exception {
        List<Admin> listaAdminBanco = adminRepository.listar();

        return listaAdminBanco.stream()
                .map(admin -> objectMapper.convertValue(admin, AdminDTO.class))
                .collect(Collectors.toList());

    }

    public AdminDTO buscarAdminPorId(Integer idUsuario) throws Exception{
        Admin admin = adminRepository.buscarAdminPorId(idUsuario);

        return objectMapper.convertValue(admin, AdminDTO.class);
    }

    public AdminDTO criar(AdminCreateDTO adminCreateDTO) throws Exception {
        Admin adminEntity = objectMapper.convertValue(adminCreateDTO, Admin.class);
        adminEntity = adminRepository.adicionar(adminEntity);

        AdminDTO adminDTO = objectMapper.convertValue(adminEntity, AdminDTO.class);

        emailService.sendEmailAdmin(adminDTO, "Cadastro efetuado, ", "create");

        return adminDTO;
    }

    public AdminDTO atualizar(Integer id, AdminCreateDTO adminCreateDTO) throws Exception {
        Admin adminEntity = objectMapper.convertValue(adminCreateDTO, Admin.class);
        adminEntity = adminRepository.editar(id, adminEntity);

        AdminDTO adminDTO = objectMapper.convertValue(adminEntity, AdminDTO.class);

        emailService.sendEmailAdmin(adminDTO, "Cadastro atualizado, ","update");
        return adminDTO;
    }

    public void remover(Integer id) throws Exception {
        boolean conseguiuRemover = adminRepository.remover(id);
        AdminDTO adminDTO = buscarAdminPorId(id);
        emailService.sendEmailAdmin(adminDTO, "Cadastro excluido, ","delete");
    }

 public void aprovacaoModulo(Integer idModulo, Integer idAdmin, String aprovacao){
        moduloService.editarAprovacaoPorAdmin(idAdmin, idModulo, aprovacao);
 }
}
