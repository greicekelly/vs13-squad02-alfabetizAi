package repository;

import exceptions.BancoDeDadosException;
import models.Admin;
import models.Aluno;
import models.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository implements Repositorio<Integer, Professor> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_professor.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Integer getProximoIdUsuario(Connection connection) throws SQLException {
        try {
            String sql = "SELECT seq_usuario.nextval as mysequence FROM DUAL";
            try (Statement stmt = connection.createStatement();
                 ResultSet res = stmt.executeQuery(sql)) {
                if (res.next()) {
                    return res.getInt("mysequence");
                }
                return null;
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Professor adicionar(Professor professor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoIdUsuario = this.getProximoId(con);
            Integer proximoIdProfessor = this.getProximoId(con);
            professor.setIdUsuario(proximoIdUsuario);
            professor.setIdProfessor(proximoIdProfessor);

            String sqlUsuario = "INSERT INTO USUARIO\n" +
                    "(ID_USUARIO, NOME, SOBRENOME, TELEFONE, EMAIL, DATA_NASCIMENTO, ATIVO, SEXO, SENHA, CPF)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sqlUsuario);

            stmt.setInt(1, professor.getIdUsuario());
            stmt.setString(2, professor.getNome());
            stmt.setString(3, professor.getSobrenome());
            stmt.setString(4, professor.getTelefone());
            stmt.setString(5, professor.getEmail());
            stmt.setDate(6, Date.valueOf(professor.getDataDeNascimento()));
            stmt.setString(7, "S");
            stmt.setString(8, professor.getSexo());
            stmt.setString(9, professor.getSenha());
            stmt.setString(10, professor.getCpf());

            int resUsuario = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res=" + resUsuario);

            String sqlProfessor = "INSERT INTO PROFESSOR\n" +
                    "(ID_PROFESSOR, ID_USUARIO, DESCRICAO)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmtAdmin = con.prepareStatement(sqlProfessor);

            stmtAdmin.setInt(1, professor.getIdProfessor());
            stmtAdmin.setInt(2, professor.getIdUsuario());
            stmtAdmin.setString(3, professor.getDescricao());

            int resProfessor = stmtAdmin.executeUpdate();
            System.out.println("adicionarProfessor.res=" + resProfessor);

            return professor;
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
    public boolean remover(Integer id, Professor professor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" ATIVO = ?,");
            sql.append(" WHERE id_pessoa = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, "N");
            stmt.setInt(2, id);

            int res = stmt.executeUpdate();
            System.out.println("editarUsuario.res=" + res);

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
    public boolean editar(Integer id, Professor professor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" NOME = ?,");
            sql.append(" SOBRENOME = ?,");
            sql.append(" TELEFONE = ?,");
            sql.append(" EMAIL = ?,");
            sql.append(" DATA_NASCIMENTO = ?,");
            sql.append(" SEXO = ?,");
            sql.append(" SENHA = ?,");
            sql.append(" CPF = ?,");
            sql.append(" WHERE id_pessoa = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(2, professor.getNome());
            stmt.setString(3, professor.getSobrenome());
            stmt.setString(4, professor.getTelefone());
            stmt.setString(5, professor.getEmail());
            stmt.setDate(6, Date.valueOf(professor.getDataDeNascimento()));
            stmt.setString(8, professor.getSexo());
            stmt.setString(9, professor.getSenha());
            stmt.setString(10, professor.getCpf());

            int res = stmt.executeUpdate();
            System.out.println("editarUsuario.res=" + res);

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
    public List<Professor> listar() throws BancoDeDadosException {
        List<Professor> professorBanco = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT U.*, A.DESCRICAO " +
                    "FROM USUARIO U " +
                    "INNER JOIN PROFESSOR A ON (A.ID_USUARIO = U.ID_USUARIO) ";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Professor professor = new Professor();
                professor.setIdUsuario(res.getInt("id_usuario"));
                professor.setNome(res.getString("nome"));
                professor.setSobrenome(res.getString("sobrenome"));
                professor.setTelefone(res.getString("telefone"));
                professor.setEmail(res.getString("email"));
                professor.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                professor.setSexo(res.getString("sexo"));
                professor.setSenha(res.getString("senha"));
                professor.setCpf(res.getString("cpf"));
                professorBanco.add(professor);
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
        return professorBanco;
    }
    public Professor buscarProfessorPorId(Integer idUsuasrio) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*, P.DESCRICAO " +
                    "FROM USUARIO U " +
                    "INNER JOIN PROFESSOR P ON (P.ID_USUARIO = U.ID_USUARIO) "+
                    "WHERE U.ID_USUARIO = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuasrio);

            ResultSet res = stmt.executeQuery();
            Professor professor = new Professor();
            while (res.next()) {

                professor.setIdUsuario(res.getInt("id_usuario"));
                professor.setNome(res.getString("nome"));
                professor.setSobrenome(res.getString("sobrenome"));
                professor.setTelefone(res.getString("telefone"));
                professor.setEmail(res.getString("email"));
                professor.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                professor.setSexo(res.getString("sexo"));
                professor.setSenha(res.getString("senha"));
                professor.setCpf(res.getString("cpf"));

            }
            System.out.println(professor);
            return professor;

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

    public Professor loginProfessor(String email, String senha) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM USUARIO U INNER JOIN PROFESSOR P ON (P.ID_USUARIO = U.ID_USUARIO) WHERE U.EMAIL = ? AND U.SENHA = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, senha);

                try (ResultSet res = stmt.executeQuery()) {
                    if (res.next()) {
                        Professor professor = new Professor();
                        professor.setIdUsuario(res.getInt("id_usuario"));
                        professor.setNome(res.getString("nome"));
                        professor.setSobrenome(res.getString("sobrenome"));
                        professor.setTelefone(res.getString("telefone"));
                        professor.setEmail(res.getString("email"));
                        professor.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                        professor.setSexo(res.getString("sexo"));
                        professor.setSenha(res.getString("senha"));
                        professor.setCpf(res.getString("cpf"));
                        professor.setIdProfessor(res.getInt("id_professor"));
                        professor.setDescricao(res.getString("descricao"));

                        return professor;
                    }
                }
            }

            return null;
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
}



