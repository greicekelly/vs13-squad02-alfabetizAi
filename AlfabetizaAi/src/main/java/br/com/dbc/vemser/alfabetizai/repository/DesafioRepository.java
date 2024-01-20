package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesafioRepository implements Repositorio<Integer, Desafio>{

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_desafio.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Integer getProximoIdUsuario(Connection connection) throws BancoDeDadosException {
        return null;
    }

    @Override
    public Desafio adicionar(Desafio desafio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            desafio.setId(proximoId);

            String sql = "INSERT INTO DESAFIO\n" +
                    "(ID_MODULO, TITULO, CONTEUDO, TIPO_DESAFIO)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, desafio.getIdModulo());
            stmt.setString(2, desafio.getTitulo());
            stmt.setString(3, desafio.getConteudo());
            stmt.setInt(4, desafio.getTipoDesafio().ordinal()+1);

            int res = stmt.executeUpdate();
            System.out.println("adicionarDesafio.res=" + res);
            return desafio;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Integer id, Desafio desafio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM DESAFIO WHERE id_desafio = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerDesafioPorId.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean editar(Integer id, Desafio desafio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DESAFIO SET ");
            sql.append(" id_modulo = ?,");
            sql.append(" titulo = ?,");
            sql.append(" conteudo = ?,");
            sql.append(" tipo_desafio = ? ");
            sql.append(" WHERE id_desafio = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, desafio.getIdModulo());
            stmt.setString(2, desafio.getTitulo());
            stmt.setString(3, desafio.getConteudo());
            stmt.setInt(4, desafio.getTipoDesafio().ordinal()+1);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarDesafio.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Desafio> listar() throws BancoDeDadosException {
        List<Desafio> desafios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM DESAFIO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Desafio desafio = new Desafio();
                desafio.setId(res.getInt("id_desafio"));
                desafio.setIdModulo(res.getInt("id_modulo"));
                desafio.setTitulo(res.getString("titulo"));
                desafio.setConteudo(res.getString("conteudo"));
                desafio.setTipoDesafio(TipoDesafio.trazEnumPeloOrdinal(res.getInt("tipo_desafio")));
                desafios.add(desafio);
            }

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return desafios;
    }

    public List<Desafio> listarPorModulo(int idModulo) throws BancoDeDadosException {
        List<Desafio> desafios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM DESAFIO WHERE id_modulo = ?";

            PreparedStatement stmt2 = con.prepareStatement(sql.toString());

            stmt2.setInt(1, idModulo);

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Desafio desafio = new Desafio();
                desafio.setId(res.getInt("id_desafio"));
                desafio.setIdModulo(res.getInt("id_modulo"));
                desafio.setTitulo(res.getString("titulo"));
                desafio.setConteudo(res.getString("conteudo"));
                desafio.setTipoDesafio(TipoDesafio.trazEnumPeloOrdinal(res.getInt("tipo_desafio")));
                desafios.add(desafio);
            }

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return desafios;
    }

}
