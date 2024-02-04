package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.UsuarioDTO;
import br.com.dbc.vemser.alfabetizai.models.Usuario;
import br.com.dbc.vemser.alfabetizai.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

//    public List<Usuario> buscarUsuarios() {
//
////        public Page<PessoaDTO> findAll(Pageable pageable){
////            Page<PessoaDTO> pessoaDTOList = pessoaRepository.findAllPessoa(pageable)
////                    .map(this::convertToDTO);
////
////            return pessoaDTOList;
//   //     return usuarioRepository.findAllUsersWithPagination();
//        return null;
//    }

//    public Page<Usuario> getPorQualquerNomeJPQL(Integer paginaSolicitada, Integer tamanhoPagina, String nome) {
//       Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina);
//        return usuarioRepository.getPorQualquerNomeJPQL("%"+ nome + "%",pageable);
//     //  return usuarioRepository.findAll(pageable);
//    }

    public List<Usuario> listar(){
      return usuarioRepository.findAll();
    }

    public Page<UsuarioDTO> listar(Integer paginaSolicitada, Integer tamanhoPagina){
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoPagina);
        return usuarioRepository.procurarUsuariosDTO(pageable);
    }
}
