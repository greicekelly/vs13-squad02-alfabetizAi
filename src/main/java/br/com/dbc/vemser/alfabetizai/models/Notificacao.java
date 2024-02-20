package br.com.dbc.vemser.alfabetizai.models;
import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Document(collection = "notificacao")
public class Notificacao {
  @Id
  private String id;
  @Enumerated(EnumType.STRING)
  private TipoStatus tipoStatus;
  private String descricao;
  private String aluno;
  private String responsavel;
  private String contato;
  private String data;
}
