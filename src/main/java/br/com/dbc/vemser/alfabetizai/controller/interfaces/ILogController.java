package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.log.LogContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.log.LogDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Log", description = "Endpoint de Log")
public interface ILogController {

    @Operation(summary = "Listar logs", description = "Lista de todos os logs do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping()
    public List<LogDTO> list();

    @Operation(summary = "Página logs", description = "Paginação de todas os logs do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a página de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/pageable")
    public Page<LogDTO> listPageable(@PageableDefault(size = 10, page = 0, sort = {"data"}) Pageable pageable);

    @Operation(summary = "Log por ID", description = "Retorna o log pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o log pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-id")
    public LogDTO listById(String id) throws RegraDeNegocioException;

    @Operation(summary = "Log por tipo", description = "Retorna os logs pelo tipo de log")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os logs pelo tipo de log"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-tipolog")
    public List<LogDTO> listByTipoLog(TipoLog tipoLog);

    @Operation(summary = "Quantidade de logs por tipo de log", description = "Retorna a quantidade de logs pelo tipo de log")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a quantidade de logs pelo tipo de log"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/group-by-tipolog-and-count")
    public List<LogContadorDTO> groupByTipoLogAndCount();

    @Operation(summary = "Log pela data e tipo de log", description = "Retorna os logs por data e tipo de log")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os logs por data e tipo de log"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/group-by-date-and-count-tipolog")
    public List<LogContadorDTO> groupByDateAndCountTipoLog(String date);

    @Operation(summary = "Log por data", description = "Retorna os log por data")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna os logs por data"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/find-all-by-date")
    public List<LogDTO> listByDate(String date) throws Exception;

    @Operation(summary = "Quantidade de log por data", description = "Retorna a quantidade de logs por data")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a quantidade de logs por data"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/count-all-by-date")
    public Integer countLogsByDate(String date);

    @Operation(summary = "Quantidade de log do dia atual", description = "Retorna a quantidade de logs do dia")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a quantidade de logs do dia"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/count-all-of-today")
    public Integer countLogsOfToday();

    @Operation(summary = "Log com data posterior", description = "Retorna todos os logs posterior a data informada.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna todas as notificações posterior a data informada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/return-all-after-date")
    public List<LogDTO> returnAllAfterDate(String data);

}
