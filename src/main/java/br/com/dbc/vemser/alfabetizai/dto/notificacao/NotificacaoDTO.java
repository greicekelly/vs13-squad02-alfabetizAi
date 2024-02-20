package br.com.dbc.vemser.alfabetizai.dto.notificacao;


import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoDTO {

  private String id;
  private TipoStatus tipoStatus;
  private String descricao;
  private String aluno;
  private String responsavel;
  private String contato;
  private String data;
}
