package br.com.dbc.vemser.alfabetizai.dto.log;

import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogContadorDTO {
  private TipoLog tipoLog;
  private Integer quantidade;
}
