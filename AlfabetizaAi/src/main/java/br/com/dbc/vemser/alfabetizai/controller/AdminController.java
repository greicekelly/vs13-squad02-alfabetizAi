package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.dto.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;


    @GetMapping
    public ResponseEntity<List<AdminDTO>> listar() throws Exception {
        log.info("Buscando lista Admin");
        List<AdminDTO> listaAdmin = adminService.listar();
        log.info("Lista Admin Retornada");
        return new ResponseEntity<>(listaAdmin, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<AdminDTO> BuscarAdminPorId(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        log.info("Buscando Admin por ID");
        AdminDTO adminDTO = adminService.BuscarAdminPorId(idUsuario);
        log.info("Admin por ID Retornado");
        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdminDTO> criar(@Valid @RequestBody AdminCreateDTO adminCreateDTO) throws Exception {
        log.info("Criando admin");
        AdminDTO adminDTO = adminService.criar(adminCreateDTO);
        log.info("Admin criado");
        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<AdminDTO> atualizar(@PathVariable("idUsuario") Integer id,
                                                @Valid @RequestBody AdminCreateDTO adminCreateDTO) throws Exception {
        log.info("Atualizando admin");
        AdminDTO adminAtualizado = adminService.atualizar(id, adminCreateDTO);
        log.info("Admin atualizado");
        return new ResponseEntity<>(adminAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletar(@PathVariable("idUsuario") Integer id) throws Exception {
        log.info("Deletando admin");
        adminService.remover(id);
        log.info("Aluno admin");
        return ResponseEntity.ok().build();
    }

}
