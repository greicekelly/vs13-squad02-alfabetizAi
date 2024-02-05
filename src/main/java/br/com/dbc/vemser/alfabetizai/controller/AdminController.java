package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.controller.interfaces.IAdminController;
import br.com.dbc.vemser.alfabetizai.dto.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminController  implements IAdminController{

    private final AdminService adminService;


    @GetMapping
    public ResponseEntity<Page<AdminDTO>> listar(@PageableDefault(page = 0, size = 9, sort = "nome") Pageable pageable) throws Exception {
        log.info("Buscando lista Admin");
        Page<AdminDTO> listaAdmin = adminService.listar(pageable);
        log.info("Lista Admin Retornada");
        return new ResponseEntity<>(listaAdmin, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<AdminDTO> BuscarAdminPorId(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        log.info("Buscando Admin por ID");
        AdminDTO adminDTO = adminService.buscarAdminPorId(idUsuario);
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

    @PutMapping("/analisarmodulo/{idModulo}")
    public ResponseEntity<Void> aprovacaoModulo(@PathVariable("idModulo") Integer idModulo,
                                                  @Valid @RequestParam Integer idAdmin, String aprovacao) throws Exception {
        log.info("Atualizando modulo");
        adminService.modudoAnalisado(idModulo, aprovacao, idAdmin );
        log.info("Modulo atualizado");
        return ResponseEntity.ok().build();
    }

}
