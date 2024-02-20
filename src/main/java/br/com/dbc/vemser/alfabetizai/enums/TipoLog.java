package br.com.dbc.vemser.alfabetizai.enums;

public enum TipoLog {
  PROFESSOR,
  ALUNO,
  ADMIN,
  RESPONSAVEL;

  public static TipoLog fromCargo(String cargo) {
    switch (cargo) {
      case "ROLE_PROFESSOR":
        return PROFESSOR;
      case "ROLE_RESPONSAVEL":
        return RESPONSAVEL;
      case "ROLE_ADMIN":
        return ADMIN;
      default:
        throw new IllegalArgumentException("Cargo inválido: " + cargo);
    }
  }

}
