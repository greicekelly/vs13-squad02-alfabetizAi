package br.com.dbc.vemser.alfabetizai.models;

import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Document(collection = "log")
public class Log {
  @Id
  private String id;
  @Enumerated(EnumType.STRING)
  private TipoLog tipoLog;
  private String descricao;
  private String data;
}
