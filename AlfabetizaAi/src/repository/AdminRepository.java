package repository;

import exceptions.BancoDeDadosException;
import models.Admin;

import java.sql.*;
import java.util.ArrayList;


public class AdminRepository implements Repositorio<Integer, Admin>{

    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_admin.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Integer getProximoIdUsuario(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_usuario.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }
    @Override
    public Admin adicionar(Admin admin) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoIdUsuario = this.getProximoId(con);
            Integer proximoIdAdmin = this.getProximoId(con);
            admin.setIdUsuario(proximoIdUsuario);
            admin.setIdAdmin(proximoIdAdmin);


            String sqlUsuario = "INSERT INTO USUARIO\n" +
                    "(ID_USUARIO, NOME, SOBRENOME, TELEFONE, EMAIL, DATA_NASCIMENTO, ATIVO, SEXO, SENHA, CPF)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sqlUsuario);

            stmt.setInt(1, admin.getIdUsuario());
            stmt.setString(2, admin.getNome());
            stmt.setString(3, admin.getSobrenome());
            stmt.setString(4, admin.getTelefone());
            stmt.setString(5, admin.getEmail());
            stmt.setDate(6, Date.valueOf(admin.getDataDeNascimento()));
            stmt.setString(7, admin.getAtivo());
            stmt.setString(8, admin.getSexo());
            stmt.setString(9, admin.getSenha());
            stmt.setString(10, admin.getCpf());

            int resUsuario = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res=" + resUsuario);

            String sqlAdmin = "INSERT INTO ADMIN\n" +
                    "(ID_ADMIN, ID_USUARIO, DESCRICAO)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmtAdmin = con.prepareStatement(sqlAdmin);

            stmtAdmin.setInt(1, admin.getIdAdmin());
            stmtAdmin.setInt(2, admin.getIdUsuario());
            stmtAdmin.setString(3, admin.getDescricao());

            int resAdmin = stmtAdmin.executeUpdate();
            System.out.println("adicionarAdmin.res=" + resAdmin);

            return admin;

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

            String sql = "DELETE FROM CONTATO WHERE ID_CONTATO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerContatoPorId.res=" + res);

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
    public boolean editar(Integer id, Contato contato) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE contato SET \n");
            Pessoa pessoa = contato.getPessoa();
            if (pessoa != null) {
                if (pessoa.getIdPessoa() != null) {
                    sql.append(" id_pessoa = ?,");
                }
            }
            if (contato.getTipoContato() != null) {
                sql.append(" tipo = ?,");
            }
            if (contato.getNumero() != null) {
                sql.append(" numero = ?,");
            }
            if (contato.getDescricao() != null) {
                sql.append(" descricao = ?,");
            }

            sql.deleteCharAt(sql.length() - 1); //remove o ultimo ','
            sql.append(" WHERE id_contato = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (pessoa != null) {
                if (pessoa.getIdPessoa() != null) {
                    stmt.setInt(index++, pessoa.getIdPessoa());
                }
            }
            if (contato.getTipoContato() != null) {
                stmt.setInt(index++, contato.getTipoContato().getTipo());
            }
            if (contato.getNumero() != null) {
                stmt.setString(index++, contato.getNumero());
            }
            if (contato.getDescricao() != null) {
                stmt.setString(index++, contato.getDescricao());
            }

            stmt.setInt(index++, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarContato.res=" + res);

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
    public List<Contato> listar() throws BancoDeDadosException {
        List<Contato> contatos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT C.*, " +
                    "            P.NOME AS NOME_PESSOA " +
                    "       FROM CONTATO C " +
                    "  LEFT JOIN PESSOA P ON (P.ID_PESSOA = C.ID_PESSOA) ";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Contato contato = getContatoFromResultSet(res);
                contatos.add(contato);
            }
            return contatos;
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

    public List<Contato> listarContatosPorPessoa(Integer idPessoa) throws BancoDeDadosException {
        List<Contato> contatos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();


            String sql = "SELECT C.*, " +
                    "            P.NOME AS NOME_PESSOA " +
                    "       FROM CONTATO C " +
                    " INNER JOIN PESSOA P ON (P.ID_PESSOA = C.ID_PESSOA) " +
                    "      WHERE C.ID_PESSOA = ? ";

            // Executa-se a consulta
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPessoa);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Contato contato = getContatoFromResultSet(res);
                contatos.add(contato);
            }
            return contatos;
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

    private Contato getContatoFromResultSet(ResultSet res) throws SQLException {
        Contato contato = new Contato();
        contato.setIdContato(res.getInt("id_Contato"));
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(res.getString("nome_pessoa"));
        pessoa.setIdPessoa(res.getInt("id_pessoa"));
        contato.setPessoa(pessoa);
        contato.setTipoContato(TipoContato.ofTipo(res.getInt("tipo")));
        contato.setNumero(res.getString("numero"));
        contato.setDescricao(res.getString("descricao"));
        return contato;
    }
}