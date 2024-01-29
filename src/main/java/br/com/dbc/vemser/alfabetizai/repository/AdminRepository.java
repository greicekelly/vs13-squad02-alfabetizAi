package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class AdminRepository implements Repositorio<Integer, Admin>{

    private final ConexaoBancoDeDados conexaoBancoDeDados;

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
            con = conexaoBancoDeDados.getConnection();

            Integer proximoIdUsuario = this.getProximoIdUsuario(con);
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
            stmt.setString(7, "S");
            stmt.setString(8, admin.getSexo());
            stmt.setString(9, admin.getSenha());
            stmt.setString(10, admin.getCpf());

            int resUsuario = stmt.executeUpdate();
            log.info("adicionarUsuario.res=" + resUsuario);

            String sqlAdmin = "INSERT INTO ADMIN\n" +
                    "(ID_ADMIN, ID_USUARIO, DESCRICAO)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmtAdmin = con.prepareStatement(sqlAdmin);

            stmtAdmin.setInt(1, admin.getIdAdmin());
            stmtAdmin.setInt(2, admin.getIdUsuario());
            stmtAdmin.setString(3, admin.getDescricao());

            int resAdmin = stmtAdmin.executeUpdate();
            log.info("adicionarAdmin.res=" + resAdmin);

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
            con = conexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET");
            sql.append(" ATIVO = ?");
            sql.append(" WHERE id_usuario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, "N");
            stmt.setInt(2, id);

            int res = stmt.executeUpdate();
            log.info("editarUsuario.res=" + res);

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
    public Admin editar(Integer id, Admin admin) throws BancoDeDadosException {
        Admin adminBanco = buscarAdminPorId(id);
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET");
            sql.append(" NOME = ?,");
            sql.append(" SOBRENOME = ?,");
            sql.append(" TELEFONE = ?,");
            sql.append(" EMAIL = ?,");
            sql.append(" DATA_NASCIMENTO = ?,");
            sql.append(" SEXO = ?,");
            sql.append(" SENHA = ?,");
            sql.append(" CPF = ?");
            sql.append(" WHERE id_usuario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, admin.getNome());
            stmt.setString(2, admin.getSobrenome());
            stmt.setString(3, admin.getTelefone());
            stmt.setString(4, admin.getEmail());
            stmt.setDate(5, Date.valueOf(admin.getDataDeNascimento()));
            stmt.setString(6, admin.getSexo());
            stmt.setString(7, admin.getSenha());
            stmt.setString(8, admin.getCpf());
            stmt.setInt(9, id);

            int res = stmt.executeUpdate();

            if(admin.getDescricao() != null){
                StringBuilder sqlAdmin = new StringBuilder();
                sqlAdmin.append("UPDATE ADMIN SET");
                sqlAdmin.append(" DESCRICAO = ?");
                sqlAdmin.append(" WHERE id_admin = ?");

                PreparedStatement stmtAdmin = con.prepareStatement(sqlAdmin.toString());

                stmtAdmin.setString(1, admin.getDescricao());
                stmtAdmin.setInt(2, adminBanco.getIdAdmin());


                stmtAdmin.executeUpdate();
            }

            log.info("editarUsuario.res=" + res);

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
        admin.setIdUsuario(adminBanco.getIdUsuario());
        admin.setIdAdmin(adminBanco.getIdAdmin());
        return admin;
    }

    public List<Admin> listar() throws BancoDeDadosException {
        List<Admin> adminBanco = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT U.*, A.* " +
                    "FROM USUARIO U " +
                    "INNER JOIN ADMIN A ON (A.ID_USUARIO = U.ID_USUARIO) ";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Admin admin = new Admin();
                admin.setIdUsuario(res.getInt("id_usuario"));
                admin.setIdAdmin(res.getInt("id_admin"));
                admin.setNome(res.getString("nome"));
                admin.setSobrenome(res.getString("sobrenome"));
                admin.setTelefone(res.getString("telefone"));
                admin.setEmail(res.getString("email"));
                admin.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                admin.setAtivo(res.getString("ativo"));
                admin.setSexo(res.getString("sexo"));
                admin.setSenha(res.getString("senha"));
                admin.setCpf(res.getString("cpf"));
                admin.setDescricao(res.getString("descricao"));
                adminBanco.add(admin);
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
        return adminBanco;
    }

    public Admin buscarAdminPorId(Integer idUsuario) throws BancoDeDadosException {
        Admin admin = new Admin();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*, A.* " +
                    "FROM USUARIO U " +
                    "INNER JOIN ADMIN A ON (A.ID_USUARIO = U.ID_USUARIO) "+
                    "WHERE U.ID_USUARIO = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {

                admin.setIdUsuario(res.getInt("id_usuario"));
                admin.setIdAdmin(res.getInt("id_admin"));
                admin.setNome(res.getString("nome"));
                admin.setSobrenome(res.getString("sobrenome"));
                admin.setTelefone(res.getString("telefone"));
                admin.setEmail(res.getString("email"));
                admin.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                admin.setAtivo(res.getString("ativo"));
                admin.setSexo(res.getString("sexo"));
                admin.setSenha(res.getString("senha"));
                admin.setCpf(res.getString("cpf"));
                admin.setDescricao(res.getString("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return admin;
    }

    public Admin LoginAdmin(String email, String senha) throws BancoDeDadosException {
        List<Admin> adminBanco = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = "SELECT U.*, A.* " +
                    "FROM USUARIO U " +
                    "INNER JOIN ADMIN A ON (A.ID_USUARIO = U.ID_USUARIO) "+
                    "WHERE U.EMAIL = ? "+
                    "AND U.SENHA = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet res = stmt.executeQuery();

            Admin admin = new Admin();

            while (res.next()) {
                    admin.setIdUsuario(res.getInt("id_usuario"));
                    admin.setNome(res.getString("nome"));
                    admin.setSobrenome(res.getString("sobrenome"));
                    admin.setTelefone(res.getString("telefone"));
                    admin.setEmail(res.getString("email"));
                    admin.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                    admin.setSexo(res.getString("sexo"));
                    admin.setSenha(res.getString("senha"));
                    admin.setCpf(res.getString("cpf"));
                    admin.setIdAdmin(res.getInt("id_admin"));
                    admin.setDescricao(res.getString("descricao"));
                    adminBanco.add(admin);
            }
            if(admin.getIdUsuario() == null) {
                return null;
            } else {
                return admin;
            }


        } catch (SQLException e) {
            e.printStackTrace();
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
}