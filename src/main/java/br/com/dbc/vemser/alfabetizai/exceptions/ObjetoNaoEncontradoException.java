package br.com.dbc.vemser.alfabetizai.exceptions;

import java.sql.SQLException;

public class ObjetoNaoEncontradoException extends SQLException {
    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
