package br.com.dbc.vemser.alfabetizai.enums;

public enum TipoStatus {
  INICIADO,
  EM_ANDAMENTO,
  CONCLUIDO;

  public static TipoStatus fromNotificacao(String status) {
    switch (status) {
      case "INICIADO":
        return INICIADO;
      case "CONCLUIDO":
        return CONCLUIDO;
      case "EM_ANDAMENTO":
        return EM_ANDAMENTO;
      default:
        throw new IllegalArgumentException("Status inválido: " + status);
    }
  }

}
