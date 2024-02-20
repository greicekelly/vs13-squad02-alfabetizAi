package br.com.dbc.vemser.alfabetizai.dto.log;
import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogCreateDTO {
  @Enumerated(EnumType.STRING)
  private TipoLog tipoLog;
  private String descricao;
  private String data;
}
