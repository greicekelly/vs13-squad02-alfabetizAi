package br.com.dbc.vemser.alfabetizai.controller;


import br.com.dbc.vemser.alfabetizai.controller.interfaces.ILogController;
import br.com.dbc.vemser.alfabetizai.dto.log.LogContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.log.LogDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.services.LogService;
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
@RequestMapping("/log")
public class LogController implements ILogController {
  private final LogService logService;

  @GetMapping()
  public List<LogDTO> list() {
    return logService.listAllLogs();
  }

  @GetMapping("/pageable")
  public Page<LogDTO> listPageable(@PageableDefault(size = 10, page = 0, sort = {"data"}) Pageable pageable) {
    return logService.listAllLogsPageable(pageable);
  }

  @GetMapping("/by-id")
  public LogDTO listById(String id) throws RegraDeNegocioException {
    return logService.listById(id);
  }

  @GetMapping("/by-tipolog")
  public List<LogDTO> listByTipoLog(TipoLog tipoLog) {
    return logService.listAllLogsByTipoLog(tipoLog);
  }

  @GetMapping("/group-by-tipolog-and-count")
  public List<LogContadorDTO> groupByTipoLogAndCount() {
    return logService.groupByTipoLogAndCount();
  }

  @GetMapping("/group-by-date-and-count-tipolog")
  public List<LogContadorDTO> groupByDateAndCountTipoLog(String date) {
    return logService.groupByDateAndCountTipoLog(date);
  }

  @GetMapping("/find-all-by-date")
  public List<LogDTO> listByDate(String date) throws Exception {
    return logService.listAllByData(date);
  }

  @GetMapping("/count-all-by-date")
  public Integer countLogsByDate(String date) {
    return logService.countLogsByDate(date);
  }

  @GetMapping("/count-all-of-today")
  public Integer countLogsOfToday() {
    String currentDate = LocalDate.now().toString();
    return logService.countLogsByDate(currentDate);
  }

  @GetMapping("/return-all-after-date")
  public List<LogDTO> returnAllAfterDate(String data) {
    return logService.findAllAfterDate(data);
  }
}
