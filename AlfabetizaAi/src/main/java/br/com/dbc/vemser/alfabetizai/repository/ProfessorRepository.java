package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Professor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
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
        try (Connection con = ConexaoBancoDeDados.getConnection()) {
            con.setAutoCommit(false);

            Integer proximoIdUsuario = getProximoIdUsuario(con);
            Integer proximoIdProfessor = getProximoId(con);

            professor.setIdUsuario(proximoIdUsuario);
            professor.setIdProfessor(proximoIdProfessor);

            adicionarUsuario(con, professor);
            adicionarProfessor(con, professor);

            con.commit();
            return professor;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar professor: " + e.getMessage(), e);

        }
    }
    private void adicionarUsuario(Connection con, Professor professor) throws SQLException {
        String sqlUsuario = "INSERT INTO USUARIO\n" +
                "(ID_USUARIO, NOME, SOBRENOME, TELEFONE, EMAIL, DATA_NASCIMENTO, ATIVO, SEXO, SENHA, CPF)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

        try (PreparedStatement stmt = con.prepareStatement(sqlUsuario)) {
            stmt.setInt(1, professor.getIdUsuario());
            stmt.setString(2, professor.getNome());
            stmt.setString(3, professor.getSobrenome());
            stmt.setString(4, professor.getTelefone());
            stmt.setString(5, professor.getEmail());

            LocalDate dataNascimento = professor.getDataDeNascimento();
            if (dataNascimento != null) {
                stmt.setDate(6, Date.valueOf(dataNascimento));
            } else {
                // Se a data de nascimento for nula, ajuste os índices para corresponder
                throw new SQLException("Data de nascimento não pode ser nula");
            }

            stmt.setString(7, "S");
            stmt.setString(8, professor.getSexo());
            stmt.setString(9, professor.getSenha());
            stmt.setString(10, professor.getCpf());

            int resUsuario = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res=" + resUsuario);
        }
    }


    private void adicionarProfessor(Connection con, Professor professor) throws SQLException {
        String sqlProfessor = "INSERT INTO PROFESSOR\n" +
                "(ID_PROFESSOR, ID_USUARIO, DESCRICAO)\n" +
                "VALUES(?, ?, ?)\n";

        try (PreparedStatement stmtProfessor = con.prepareStatement(sqlProfessor)) {
            stmtProfessor.setInt(1, professor.getIdProfessor());
            stmtProfessor.setInt(2, professor.getIdUsuario());
            stmtProfessor.setString(3, professor.getDescricao());

            int resProfessor = stmtProfessor.executeUpdate();
            System.out.println("adicionarProfessor.res=" + resProfessor);
        }
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" ATIVO = ? ");
            sql.append(" WHERE ID_USUARIO = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, "N");
            stmt.setInt(2, id);

            int res = stmt.executeUpdate();
            System.out.println("removerProfessor.res=" + res);

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
    public Professor editar(Integer id, Professor professor) throws BancoDeDadosException {
        Professor professorBanco = buscarProfessorPorId(id);
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

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

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getSobrenome());
            stmt.setString(3, professor.getTelefone());
            stmt.setString(4, professor.getEmail());
            stmt.setDate(5, Date.valueOf(professor.getDataDeNascimento()));
            stmt.setString(6, professor.getSexo());
            stmt.setString(7, professor.getSenha());
            stmt.setString(8, professor.getCpf());
            stmt.setInt(9, id);

            int res = stmt.executeUpdate();

            if(professor.getDescricao() != null){
                StringBuilder sqlProfessor = new StringBuilder();
                sqlProfessor.append("UPDATE PROFESSOR SET");
                sqlProfessor.append(" DESCRICAO = ?");
                sqlProfessor.append(" WHERE id_professor = ?");

                PreparedStatement stmtProfessor = con.prepareStatement(sqlProfessor.toString());

                stmtProfessor.setString(1, professor.getDescricao());
                stmtProfessor.setInt(2, professorBanco.getIdProfessor());


                stmtProfessor.executeUpdate();
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
        professor.setIdUsuario(professorBanco.getIdUsuario());
        professor.setIdProfessor(professorBanco.getIdProfessor());
        return professor;
    }

    @Override
    public List<Professor> listar() throws BancoDeDadosException {
        List<Professor> professorBanco = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT U.*, A.* " +
                    "FROM USUARIO U " +
                    "INNER JOIN PROFESSOR A ON (A.ID_USUARIO = U.ID_USUARIO) ";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Professor professor = new Professor();
                professor.setIdUsuario(res.getInt("id_usuario"));
                professor.setIdProfessor(res.getInt("id_professor"));
                professor.setNome(res.getString("nome"));
                professor.setSobrenome(res.getString("sobrenome"));
                professor.setTelefone(res.getString("telefone"));
                professor.setEmail(res.getString("email"));
                professor.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                professor.setSexo(res.getString("sexo"));
                professor.setSenha(res.getString("senha"));
                professor.setCpf(res.getString("cpf"));
                professor.setDescricao(res.getString("descricao"));

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

    public Professor buscarProfessorPorIdUsuario(Integer idUsuario) throws BancoDeDadosException {
        Professor professor = new Professor();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*, A.* " +
                    "FROM USUARIO U " +
                    "INNER JOIN PROFESSOR A ON (A.ID_USUARIO = U.ID_USUARIO) "+
                    "WHERE U.ID_USUARIO = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {

                professor.setIdUsuario(res.getInt("id_usuario"));
                professor.setIdProfessor(res.getInt("id_professor"));
                professor.setNome(res.getString("nome"));
                professor.setSobrenome(res.getString("sobrenome"));
                professor.setTelefone(res.getString("telefone"));
                professor.setEmail(res.getString("email"));
                professor.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                professor.setAtivo(res.getString("ativo"));
                professor.setSexo(res.getString("sexo"));
                professor.setSenha(res.getString("senha"));
                professor.setCpf(res.getString("cpf"));
                professor.setDescricao(res.getString("descricao"));
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
        return professor;
    }

    public Professor buscarProfessorPorId(Integer id) throws BancoDeDadosException {
        Professor professor = new Professor();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*, A.* " +
                    "FROM USUARIO U " +
                    "INNER JOIN PROFESSOR A ON (A.ID_USUARIO = U.ID_USUARIO) "+
                    "WHERE A.ID_PROFESSOR = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {

                professor.setIdUsuario(res.getInt("id_usuario"));
                professor.setIdProfessor(res.getInt("id_professor"));
                professor.setNome(res.getString("nome"));
                professor.setSobrenome(res.getString("sobrenome"));
                professor.setTelefone(res.getString("telefone"));
                professor.setEmail(res.getString("email"));
                professor.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                professor.setAtivo(res.getString("ativo"));
                professor.setSexo(res.getString("sexo"));
                professor.setSenha(res.getString("senha"));
                professor.setCpf(res.getString("cpf"));
                professor.setDescricao(res.getString("descricao"));
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
        return professor;
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