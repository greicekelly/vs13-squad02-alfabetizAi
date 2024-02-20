package br.com.dbc.vemser.alfabetizai.dto.notificacao;

import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoCreateDTO {
  @Enumerated(EnumType.STRING)
  private TipoStatus tipoStatus;
  private String descricao;
  private String aluno;
  private String responsavel;
  private String contato;
  private String data;
}
