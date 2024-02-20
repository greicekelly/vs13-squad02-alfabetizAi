package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Notificacao;
import br.com.dbc.vemser.alfabetizai.repository.INotificacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
  private final INotificacaoRepository notificacaoRepository;
  private final ObjectMapper objectMapper;

  private String NOT_FOUND_MESSAGE = "Id da pessoa não encontrado";

  public List<NotificacaoDTO> listAllNotificacoes() {
    return notificacaoRepository.findAll().stream().map(notificacao -> objectMapper.convertValue(notificacao, NotificacaoDTO.class)).collect(Collectors.toList());
  }

  public Page<NotificacaoDTO> listAllLogsPageable(Pageable pageable) {
    Page<NotificacaoDTO> all = notificacaoRepository.findAll(pageable).map(notificacao -> objectMapper.convertValue(notificacao, NotificacaoDTO.class));
    return all;
  }

  public List<NotificacaoDTO> listAllNotificacoesByTipoStatus(TipoStatus tipoStatus) {
    return notificacaoRepository.findAllByTipoStatus(tipoStatus).stream().map(notificacao -> objectMapper.convertValue(notificacao, NotificacaoDTO.class)).collect(Collectors.toList());
  }

  public List<NotificacaoContadorDTO> groupByTipoStatusAndCount() {
    return notificacaoRepository.groupByTipoStatusAndCount().stream().map(notificacao -> {
      return new NotificacaoContadorDTO(notificacao.getTipoStatus(), notificacao.getQuantidade());
    }).collect(Collectors.toList());
  }

  public List<NotificacaoContadorDTO> groupByDateAndCountTipoStatus(String date) {
    String dateRegex = ".*" + date + ".*";
    return notificacaoRepository.findAllByDateAndCountTipoStatus(dateRegex).stream().map(notificacao -> {
      return new NotificacaoContadorDTO(notificacao.getTipoStatus(), notificacao.getQuantidade());
    }).collect(Collectors.toList());
  }

  public List<NotificacaoDTO> listAllByData(String date) throws Exception {
    LocalDate dataAtual = LocalDate.now();
    LocalDate dataProcurada = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

    if(dataProcurada.isAfter(dataAtual)) {
      throw new Exception("Não há notificações para datas futuras!");
    }

    return notificacaoRepository.findAllByDataContains(date).stream().map(notificacao -> objectMapper.convertValue(notificacao, NotificacaoDTO.class)).collect(Collectors.toList());
  }

  public NotificacaoDTO listById(String id) throws RegraDeNegocioException {
    return objectMapper.convertValue(retornarPeloId(id), NotificacaoDTO.class);
  }

  public Integer countLogsByDate(String date) {
    return notificacaoRepository.countAllByDataContains(date);
  }

  public Notificacao retornarPeloId(String id) throws RegraDeNegocioException {
    return notificacaoRepository.findById(id)
        .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
  }

  public List<NotificacaoDTO> findAllAfterDate(String data) {
    return notificacaoRepository.findAllAfterDate(data).stream().map(obj -> objectMapper.convertValue(obj, NotificacaoDTO.class)).collect(Collectors.toList());
  }

  public void registerEvent(NotificacaoCreateDTO notificacaoCreateDTO){
    Notificacao notificacao = objectMapper.convertValue(notificacaoCreateDTO, Notificacao.class);
    notificacaoRepository.save(notificacao);
  }

}
