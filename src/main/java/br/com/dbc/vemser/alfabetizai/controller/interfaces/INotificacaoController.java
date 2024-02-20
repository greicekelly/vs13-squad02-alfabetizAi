package br.com.dbc.vemser.alfabetizai.controller.interfaces;

import br.com.dbc.vemser.alfabetizai.dto.admin.AdminCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.admin.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoContadorDTO;
import br.com.dbc.vemser.alfabetizai.dto.notificacao.NotificacaoDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Notificação", description = "Endpoint de Notificação")
public interface INotificacaoController {

    @Operation(summary = "Listar notificações", description = "Lista de todas as notificações do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de notificações"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<NotificacaoDTO> list();

    @Operation(summary = "Página notificações", description = "Paginação de todas as notificações do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a página de notificações"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/pageable")
    public Page<NotificacaoDTO> listPageable(@PageableDefault(size = 10, page = 0, sort = {"data"}) Pageable pageable);

    @Operation(summary = "Notificação por ID", description = "Retorna a notificação pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a notificação pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-id")
    public NotificacaoDTO listById(String id) throws RegraDeNegocioException;

    @Operation(summary = "Notificação por tipo de Status", description = "Retorna as notificações pelo tipo de status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna as notificações pelo tipo de status"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-tipostatus")
    public List<NotificacaoDTO> listByTipoStatus(TipoStatus tipoStatus);

    @Operation(summary = "Quantidade de Notificação por tipo de Status", description = "Retorna a quantidade de notificações pelo tipo de status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a quantidade de notificações pelo tipo de status"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/group-by-tipostatus-and-count")
    public List<NotificacaoContadorDTO> groupByTipoStatusAndCount();

    @Operation(summary = "Notificação pela data e tipo de Status", description = "Retorna as notificações por data e tipo de status")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna as notificações por data e tipo de status"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/group-by-date-and-count-tipostatus")
    public List<NotificacaoContadorDTO> groupByDateAndCountTipoStatus(String date);

    @Operation(summary = "Notificação por data", description = "Retorna as notificações por data")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna as notificações por data"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/find-all-by-date")
    public List<NotificacaoDTO> listByDate(String date) throws Exception;

    @Operation(summary = "Quantidade de notificação por data", description = "Retorna a quantidade de notificações por data")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a quantidade de notificações por data"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/count-all-by-date")
    public Integer countNotificacoesByDate(String date);

    @Operation(summary = "Quantidade de notificação do dia atual", description = "Retorna a quantidade de notificações do dia")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a quantidade de notificações do dia"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/count-all-of-today")
    public Integer countLogsOfToday();

    @Operation(summary = "Notificação com data posterior", description = "Retorna todas as notificações posterior a data informada.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna todas as notificações posterior a data informada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/return-all-after-date")
    public List<NotificacaoDTO> returnAllAfterDate(String data);

}
