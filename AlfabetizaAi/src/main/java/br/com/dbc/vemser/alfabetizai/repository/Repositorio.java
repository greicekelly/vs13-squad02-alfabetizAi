package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<CHAVE, OBJETO> {
    Integer getProximoId(Connection connection) throws SQLException;

    Integer getProximoIdUsuario(Connection connection) throws SQLException;

    OBJETO adicionar(OBJETO object) throws BancoDeDadosException;

    boolean remover(CHAVE id) throws BancoDeDadosException;

    OBJETO editar(CHAVE id, OBJETO objeto) throws BancoDeDadosException;

    List<OBJETO> listar() throws BancoDeDadosException;
}