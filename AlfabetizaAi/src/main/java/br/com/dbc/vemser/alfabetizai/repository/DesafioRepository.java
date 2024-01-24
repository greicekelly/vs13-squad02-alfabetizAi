package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.dto.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DesafioRepository implements Repositorio<Integer, Desafio>{

    @Override
    public Integer getProximoIdUsuario(Connection connection) throws BancoDeDadosException {
        return null;
    }
    @Override
    public List<Desafio> listar() throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM DESAFIO";

            ResultSet res = stmt.executeQuery(sql);
            List<Desafio> desafioLista = new ArrayList<>();

            while (res.next()) {
                desafioLista.add(mapperUsuario(res));
            }return desafioLista;
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

    public List<Desafio> listarModuloporId(int idModulo) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM DESAFIO WHERE id_modulo = ?";

            PreparedStatement stmt2 = con.prepareStatement(sql.toString());
            List<Desafio> desafioPorModulo = new ArrayList<>();

            stmt2.setInt(1, idModulo);

            ResultSet res = stmt2.executeQuery();

            while (res.next()) {
                desafioPorModulo.add(mapperUsuario(res));
                            }
            return desafioPorModulo;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    public Desafio adicionar(Desafio desafio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            desafio.setId(proximoId);

            String sql = "INSERT INTO DESAFIO\n" +
                    "(ID_DESAFIO, ID_MODULO, TITULO, CONTEUDO, TIPO_DESAFIO)\n" +
                    "VALUES(?, ?, ?, ?,?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, desafio.getId());
            stmt.setInt(2, desafio.getIdModulo());
            stmt.setString(3, desafio.getTitulo());
            stmt.setString(4, desafio.getConteudo());
            stmt.setInt(5, desafio.getTipoDesafio().ordinal()+1);

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
    public Desafio editar(Integer id, DesafioCreateDTO desafio) throws BancoDeDadosException {
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

            int res = stmt.executeUpdate();
            System.out.println("editarDesafio.res=" + res);

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
    public boolean remover(Integer id) throws BancoDeDadosException {
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
    private Desafio mapperUsuario(ResultSet res) throws SQLException {
        Desafio desafioResponse = new Desafio();
        desafioResponse.setId(res.getInt("id_desafio"));
        desafioResponse.setIdModulo(res.getInt("id_modulo"));
        desafioResponse.setTitulo(res.getString("titulo"));
        desafioResponse.setConteudo(res.getString("conteudo"));
        desafioResponse.setTipoDesafio(TipoDesafio.trazEnumPeloOrdinal(res.getInt("tipo_desafio")));
                return desafioResponse;
    }

}
