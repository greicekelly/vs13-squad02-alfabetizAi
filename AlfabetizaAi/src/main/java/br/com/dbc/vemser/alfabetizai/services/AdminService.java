package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;

    public List<AdminDTO> listar() throws Exception {
        List<Admin> listaAdminBanco = adminRepository.listar();

        return listaAdminBanco.stream()
                .map(admin -> objectMapper.convertValue(admin, AdminDTO.class))
                .collect(Collectors.toList());

    }

    public AdminDTO BuscarAdminPorId(Integer idUsuario) throws Exception{
        Admin admin = adminRepository.BuscarAdminPorId(idUsuario);

        return objectMapper.convertValue(admin, AdminDTO.class);
    }

    public AdminDTO criar(AdminCreateDTO adminCreateDTO) throws Exception {
        Admin adminEntity = objectMapper.convertValue(adminCreateDTO, Admin.class);
        adminEntity = adminRepository.adicionar(adminEntity);

        return objectMapper.convertValue(adminEntity, AdminDTO.class);
    }

    public AdminDTO atualizar(Integer id, AdminCreateDTO adminCreateDTO) throws Exception {
        Admin adminEntity = objectMapper.convertValue(adminCreateDTO, Admin.class);
        adminEntity = adminRepository.editar(id, adminEntity);

        return objectMapper.convertValue(adminEntity, AdminDTO.class);
    }

    public void remover(Integer id) throws Exception {
        boolean conseguiuRemover = adminRepository.remover(id);
    }

    public Admin loginAdmin(String email, String senha) {
        try {
            return adminRepository.LoginAdmin(email, senha);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }
}
