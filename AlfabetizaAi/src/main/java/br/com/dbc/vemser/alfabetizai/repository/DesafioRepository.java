package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
@Slf4j
@AllArgsConstructor
public class DesafioRepository implements Repositorio<Integer, Desafio> {

    private final ConexaoBancoDeDados conexaoBancoDeDados;
   @Override
    public List<Desafio> listar() throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM DESAFIO";

            ResultSet res = stmt.executeQuery(sql);
            List<Desafio> desafioLista = new ArrayList<>();
            while (res.next()) {
                Desafio desafio = mapperUsuario(res);

                sql = "SELECT * FROM DESAFIO_ALTERNATIVAS WHERE id_desafio = ?";
                PreparedStatement stmtAlternativas = con.prepareStatement(sql);
                stmtAlternativas.setInt(1, desafio.getId());

                ResultSet resAlternativas = stmtAlternativas.executeQuery();

                while (resAlternativas.next()) {
                    desafioLista.add(mapperDesafioAlternativas(resAlternativas, desafio));
                }
            }
            return desafioLista;
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
            con = conexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM DESAFIO WHERE id_modulo = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idModulo);

            ResultSet res = stmt.executeQuery();

            List<Desafio> desafioPorModulo = new ArrayList<>();

            while (res.next()) {
                Desafio desafio = mapperUsuario(res);

                sql = "SELECT * FROM DESAFIO_ALTERNATIVAS WHERE id_desafio = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, desafio.getId());

                ResultSet resAlternativas = stmt.executeQuery();

                while (resAlternativas.next()) {
                    desafioPorModulo.add(mapperDesafioAlternativas(resAlternativas, desafio));
                }

                if (desafioPorModulo.isEmpty()) {
                    throw new RegraDeNegocioException("Nenhum desafio encontrado para o módulo com ID: " + idModulo);
                }
                }return desafioPorModulo;
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
            }}
    }
    @Override
    public Desafio adicionar(Desafio desafio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Integer proximoId = this.getProximoId(con);
            Integer proximoIdAlternativas = this.getProximoIdAlternativas(con);
            desafio.setId(proximoId);
            String sql = "INSERT INTO DESAFIO" +
                    "(ID_DESAFIO, " +
                    "ID_MODULO, " +
                    "TITULO, " +
                    "CONTEUDO, " +
                    "INSTRUCAO, " +
                    "PONTOS, " +
                    "TIPO_DESAFIO)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, desafio.getId());
            stmt.setInt(2, desafio.getIdModulo());
            stmt.setString(3, desafio.getTitulo());
            stmt.setString(4, desafio.getConteudo());
            stmt.setString(5,desafio.getInstrucao());
            stmt.setInt(6, desafio.getPontos());
            stmt.setInt(7, desafio.getTipoDesafio().ordinal() + 1);

            int res = stmt.executeUpdate();
            log.info("adicionarDesafio.res=" + res);

            sql = "INSERT INTO DESAFIO_ALTERNATIVAS" +
                    "(ID_DESAFIO_ALTERNATIVAS, " +
                    "ID_DESAFIO, " +
                    "A, " +
                    "B, " +
                    "C, " +
                    "D, " +
                    "E, " +
                    "CORRETA)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, proximoIdAlternativas);
            stmt.setInt(2, desafio.getId());
            stmt.setString(3, desafio.getAlternativas().get(0));
            stmt.setString(4, desafio.getAlternativas().get(1));
            stmt.setString(5, desafio.getAlternativas().get(2));
            stmt.setString(6, desafio.getAlternativas().get(3));
            stmt.setString(7, desafio.getAlternativas().get(4));
            stmt.setString(8, desafio.getAlternativaCorreta());

            res = stmt.executeUpdate();

            log.info("adicionarDesafioAlternativas.res=" + res);

            return desafio;
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
    public Desafio editar(Integer id, Desafio desafio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE DESAFIO SET ");
            sql.append("id_modulo = ?, ");
            sql.append("titulo = ?, ");
            sql.append("conteudo = ?, ");
            sql.append("tipo_desafio = ? ");
            sql.append("WHERE id_desafio = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setInt(1, desafio.getIdModulo());
            stmt.setString(2, desafio.getTitulo());
            stmt.setString(3, desafio.getConteudo());
            stmt.setInt(4, desafio.getTipoDesafio().ordinal() + 1);
            stmt.setInt(5, id);

            if (stmt.executeUpdate() == 0) throw new RegraDeNegocioException("Dados do Usuário Não Encontrado. ID: ");
            desafio.setId(id);
            return desafio;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause() != null ? e.getCause() : e);
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
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = "DELETE FROM DESAFIO WHERE id_desafio = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int res = stmt.executeUpdate();
            if (res == 0) {
                throw new RegraDeNegocioException("Dados do Usuário Não Encontrado. ID: " + id);
            }return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (RegraDeNegocioException e) {
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

    public Desafio buscarDesafioPorId(int idDesafio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = "SELECT * FROM DESAFIO WHERE id_desafio = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idDesafio);

            ResultSet res = stmt.executeQuery();

            Desafio desafio = new Desafio();

            while (res.next()) {
                desafio = mapperUsuario(res);
            }

            return desafio;

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
            }}
    }
    @Override
    public Integer getProximoIdUsuario(Connection connection) throws BancoDeDadosException {
        return null;
    }
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_desafio.nextval mysequence from DUAL";
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            return res.getInt("mysequence");
        }return null;
    }

    public Integer getProximoIdAlternativas(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_DESAFIO_ALTERNATIVA.nextval mysequence from DUAL";
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            return res.getInt("mysequence");
        }return null;
    }
    private Desafio mapperUsuario(ResultSet res) throws SQLException {
        Desafio desafioResponse = new Desafio();
        desafioResponse.setId(res.getInt("id_desafio"));
        desafioResponse.setIdModulo(res.getInt("id_modulo"));
        desafioResponse.setTitulo(res.getString("titulo"));
        desafioResponse.setConteudo(res.getString("conteudo"));
        desafioResponse.setInstrucao(res.getString("instrucao"));
        desafioResponse.setPontos(res.getInt("pontos"));
        desafioResponse.setTipoDesafio(TipoDesafio.trazEnumPeloOrdinal(res.getInt("tipo_desafio")));
        return desafioResponse;
    }

    private Desafio mapperDesafioAlternativas(ResultSet res , Desafio desafio) throws SQLException {
       List<String> alternativas = new ArrayList<>();
        alternativas.add(res.getString("A"));
        alternativas.add(res.getString("B"));
        alternativas.add(res.getString("C"));
        alternativas.add(res.getString("D"));
        alternativas.add(res.getString("E"));
        desafio.setAlternativaCorreta(res.getString("correta"));
        desafio.setAlternativas(alternativas);

        return desafio;
    }
}

