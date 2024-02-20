package br.com.dbc.vemser.alfabetizai.dto.notificacao;

import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoContadorDTO {
  private TipoStatus tipoStatus;
  private Integer quantidade;
}
