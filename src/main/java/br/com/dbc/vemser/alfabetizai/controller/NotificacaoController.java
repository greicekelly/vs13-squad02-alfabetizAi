package br.com.dbc.vemser.alfabetizai.controller;


import br.com.dbc.vemser.alfabetizai.dto.log.LogContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.log.LogDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.LogService;
import br.com.dbc.vemser.alfabetizai.services.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificacao")
public class NotificacaoController {
  private final NotificacaoService notificacaoService;

  @GetMapping()
  public List<NotificacaoDTO> list() {
    return notificacaoService.listAllNotificacoes();
  }

  @GetMapping("/pageable")
  public Page<NotificacaoDTO> listPageable(@PageableDefault(size = 10, page = 0, sort = {"data"}) Pageable pageable) {
    return notificacaoService.listAllLogsPageable(pageable);
  }

  @GetMapping("/by-id")
  public NotificacaoDTO listById(String id) throws RegraDeNegocioException {
    return notificacaoService.listById(id);
  }

  @GetMapping("/by-tipostatus")
  public List<NotificacaoDTO> listByTipoStatus(TipoStatus tipoStatus) {
    return notificacaoService.listAllNotificacoesByTipoStatus(tipoStatus);
  }

  @GetMapping("/group-by-tipostatus-and-count")
  public List<NotificacaoContadorDTO> groupByTipoStatusAndCount() {
    return notificacaoService.groupByTipoStatusAndCount();
  }

  @GetMapping("/group-by-date-and-count-tipostatus")
  public List<NotificacaoContadorDTO> groupByDateAndCountTipoStatus(String date) {
    return notificacaoService.groupByDateAndCountTipoStatus(date);
  }

  @GetMapping("/find-all-by-date")
  public List<NotificacaoDTO> listByDate(String date) throws Exception {
    return notificacaoService.listAllByData(date);
  }

  @GetMapping("/count-all-by-date")
  public Integer countNotificacoesByDate(String date) {
    return notificacaoService.countLogsByDate(date);
  }

  @GetMapping("/count-all-of-today")
  public Integer countLogsOfToday() {
    String currentDate = LocalDate.now().toString();
    return notificacaoService.countLogsByDate(currentDate);
  }

  @GetMapping("/return-all-after-date")
  public List<NotificacaoDTO> returnAllAfterDate(String data) {
    return notificacaoService.findAllAfterDate(data);
  }
}
