package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.log.LogContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.log.LogCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.log.LogDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Log;
import br.com.dbc.vemser.alfabetizai.repository.ILogRepository;
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
public class LogService {
  private final ILogRepository logRepository;
  private final ObjectMapper objectMapper;

  private String NOT_FOUND_MESSAGE = "Id da pessoa não encontrado";

  public List<LogDTO> listAllLogs() {
    return logRepository.findAll().stream().map(log -> objectMapper.convertValue(log, LogDTO.class)).collect(Collectors.toList());
  }

  public Page<LogDTO> listAllLogsPageable(Pageable pageable) {
    Page<LogDTO> all = logRepository.findAll(pageable).map(log -> objectMapper.convertValue(log, LogDTO.class));
    return all;
  }

  public List<LogDTO> listAllLogsByTipoLog(TipoLog tipoLog) {
    return logRepository.findAllByTipoLog(tipoLog).stream().map(log -> objectMapper.convertValue(log, LogDTO.class)).collect(Collectors.toList());
  }

  public List<LogContadorDTO> groupByTipoLogAndCount() {
    return logRepository.groupByTipoLogAndCount().stream().map(log -> {
      return new LogContadorDTO(log.getTipoLog(), log.getQuantidade());
    }).collect(Collectors.toList());
  }

  public List<LogContadorDTO> groupByDateAndCountTipoLog(String date) {
    String dateRegex = ".*" + date + ".*";
    return logRepository.findAllByDateAndCountTipoLog(dateRegex).stream().map(log -> {
      return new LogContadorDTO(log.getTipoLog(), log.getQuantidade());
    }).collect(Collectors.toList());
  }

  public List<LogDTO> listAllByData(String date) throws Exception {
    LocalDate dataAtual = LocalDate.now();
    LocalDate dataProcurada = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

    if(dataProcurada.isAfter(dataAtual)) {
      throw new Exception("Não há logs para datas futuras!");
    }

    return logRepository.findAllByDataContains(date).stream().map(log -> objectMapper.convertValue(log, LogDTO.class)).collect(Collectors.toList());
  }

  public LogDTO listById(String id) throws RegraDeNegocioException {
    return objectMapper.convertValue(retornarPeloId(id), LogDTO.class);
  }

  public Integer countLogsByDate(String date) {
    return logRepository.countAllByDataContains(date);
  }

  public Log retornarPeloId(String id) throws RegraDeNegocioException {
    return logRepository.findById(id)
        .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
  }

  public List<LogDTO> findAllAfterDate(String data) {
    return logRepository.findAllAfterDate(data).stream().map(obj -> objectMapper.convertValue(obj, LogDTO.class)).collect(Collectors.toList());
  }

  public void registerLog(LogCreateDTO logCreateDTO){
    Log log = objectMapper.convertValue(logCreateDTO, Log.class);
    logRepository.save(log);
  }

}
