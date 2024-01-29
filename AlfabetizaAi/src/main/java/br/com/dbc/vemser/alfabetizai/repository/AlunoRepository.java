package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.dto.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.*;
import br.com.dbc.vemser.alfabetizai.services.DesafioService;
import br.com.dbc.vemser.alfabetizai.services.ModuloService;
import br.com.dbc.vemser.alfabetizai.services.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class AlunoRepository implements Repositorio<Integer, Aluno> {

    private final ModuloService moduloService;

    private final DesafioService desafioService;

    private final ProfessorService professorService;

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_aluno.nextval as mysequence FROM DUAL";
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
    public Integer getProximoIdUsuario(Connection connection) throws BancoDeDadosException {
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

    public Integer getProximoIdAlternativas(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT SEQ_DESAFIO_ALTERNATIVA.nextval as mysequence FROM DUAL";
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
    public Aluno adicionar(Aluno aluno) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            Integer proximoIdUsuario = this.getProximoIdUsuario(con);
            Integer proximoIdAluno = this.getProximoId(con);
            aluno.setIdUsuario(proximoIdUsuario);
            aluno.setIdAluno(proximoIdAluno);

            String sqlUsuario = "INSERT INTO USUARIO (ID_USUARIO, NOME, SOBRENOME, TELEFONE, EMAIL, DATA_NASCIMENTO, ATIVO, SEXO, SENHA, CPF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmtUsuario = con.prepareStatement(sqlUsuario)) {
                stmtUsuario.setInt(1, aluno.getIdUsuario());
                stmtUsuario.setString(2, aluno.getNome());
                stmtUsuario.setString(3, aluno.getSobrenome());
                stmtUsuario.setString(4, aluno.getTelefone());
                stmtUsuario.setString(5, aluno.getEmail());
                stmtUsuario.setDate(6, Date.valueOf(aluno.getDataDeNascimento()));
                stmtUsuario.setString(7, "S");
                stmtUsuario.setString(8, aluno.getSexo());
                stmtUsuario.setString(9, aluno.getSenha());
                stmtUsuario.setString(10, aluno.getCpf());

                int resUsuario = stmtUsuario.executeUpdate();
                System.out.println("adicionarUsuario.res=" + resUsuario);
            }

            String sqlAluno = "INSERT INTO ALUNO (ID_ALUNO, ID_USUARIO, NOME_ALUNO, SOBRENOME_ALUNO, DATA_NASCIMENTO_ALUNO, SEXO_ALUNO, PONTUACAO) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmtAluno = con.prepareStatement(sqlAluno)) {
                stmtAluno.setInt(1, aluno.getIdAluno());
                stmtAluno.setInt(2, aluno.getIdUsuario());
                stmtAluno.setString(3, aluno.getNomeAluno());
                stmtAluno.setString(4, aluno.getSobrenomeAluno());
                stmtAluno.setDate(5, Date.valueOf(aluno.getDataDeNascimento()));
                stmtAluno.setString(6, aluno.getSexoAluno());
                stmtAluno.setInt(7, 0);


                int resAluno = stmtAluno.executeUpdate();
            }

            return aluno;

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

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "UPDATE USUARIO SET ATIVO = ? WHERE ID_USUARIO = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, "N");
                stmt.setInt(2, id);

                int res = stmt.executeUpdate();

                return res > 0;
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
    }

    @Override
    public Aluno editar(Integer id, Aluno aluno) throws BancoDeDadosException {
        Aluno alunoBanco = buscarAlunoPorId(id);
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" NOME = ?,");
            sql.append(" SOBRENOME = ?,");
            sql.append(" TELEFONE = ?,");
            sql.append(" EMAIL = ?,");
            sql.append(" DATA_NASCIMENTO = ?,");
            sql.append(" ATIVO = 'S',");
            sql.append(" SEXO = ?,");
            sql.append(" SENHA = ?,");
            sql.append(" CPF = ?");
            sql.append(" WHERE ID_USUARIO = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSobrenome());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getEmail());
            stmt.setDate(5, Date.valueOf(aluno.getDataDeNascimento()));
            stmt.setString(6, aluno.getSexo());
            stmt.setString(7, aluno.getSenha());
            stmt.setString(8, aluno.getCpf());
            stmt.setInt(9, id);

            int res = stmt.executeUpdate();

            StringBuilder sqlAluno = new StringBuilder();
            sqlAluno.append("UPDATE ALUNO SET");
            sqlAluno.append(" NOME_ALUNO = ?,");
            sqlAluno.append(" SOBRENOME_ALUNO = ?,");
            sqlAluno.append(" DATA_NASCIMENTO_ALUNO = ?,");
            sqlAluno.append(" SEXO_ALUNO = ?");
            sqlAluno.append(" WHERE ID_ALUNO = ? ");

            PreparedStatement stmtAluno = con.prepareStatement(sqlAluno.toString());

            stmtAluno.setString(1, aluno.getNomeAluno());
            stmtAluno.setString(2, aluno.getSobrenomeAluno());
            stmtAluno.setDate(3, Date.valueOf(aluno.getDataNascimentoAluno()));
            stmtAluno.setString(4, aluno.getSexoAluno());
            stmtAluno.setInt(5, alunoBanco.getIdAluno());

            stmtAluno.executeUpdate();

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
        aluno.setIdUsuario(alunoBanco.getIdUsuario());
        aluno.setIdAluno(alunoBanco.getIdAluno());
        return aluno;
    }

    @Override
    public List<Aluno> listar() throws BancoDeDadosException {
        List<Aluno> adminBanco = new ArrayList<>();
        Connection con = null;


        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT U.*, A.* FROM USUARIO U INNER JOIN ALUNO A ON (A.ID_USUARIO = U.ID_USUARIO)";
            try (ResultSet res = stmt.executeQuery(sql)) {
                while (res.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setIdUsuario(res.getInt("id_usuario"));
                    aluno.setNome(res.getString("nome"));
                    aluno.setSobrenome(res.getString("sobrenome"));
                    aluno.setTelefone(res.getString("telefone"));
                    aluno.setEmail(res.getString("email"));
                    aluno.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                    aluno.setAtivo(res.getString("ativo"));
                    aluno.setSexo(res.getString("sexo"));
                    aluno.setSenha(res.getString("senha"));
                    aluno.setCpf(res.getString("cpf"));
                    aluno.setPontuacao(res.getInt("pontuacao"));
                    aluno.setIdAluno(res.getInt("id_aluno"));
                    aluno.setNomeAluno(res.getString("nome_aluno"));
                    aluno.setSobrenomeAluno(res.getString("sobrenome_aluno"));
                    aluno.setDataNascimentoAluno(res.getDate("data_nascimento_aluno").toLocalDate());
                    aluno.setSexoAluno(res.getString("sexo_aluno"));
                    adminBanco.add(aluno);
                }
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


    public Aluno buscarAlunoPorId(Integer idUsuario) throws BancoDeDadosException {
        Aluno aluno = new Aluno();
        Connection con = null;

        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*, A.* " +
                    "FROM USUARIO U " +
                    "INNER JOIN ALUNO A ON (A.ID_USUARIO = U.ID_USUARIO) " +
                    "WHERE U.ID_USUARIO = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            ResultSet res = stmt.executeQuery();
            while (res.next()) {

                aluno.setIdUsuario(res.getInt("id_usuario"));
                aluno.setNome(res.getString("nome"));
                aluno.setSobrenome(res.getString("sobrenome"));
                aluno.setTelefone(res.getString("telefone"));
                aluno.setEmail(res.getString("email"));
                aluno.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                aluno.setAtivo(res.getString("ativo"));
                aluno.setSexo(res.getString("sexo"));
                aluno.setSenha(res.getString("senha"));
                aluno.setCpf(res.getString("cpf"));
                aluno.setPontuacao(res.getInt("pontuacao"));
                aluno.setIdAluno(res.getInt("id_aluno"));
                aluno.setNomeAluno(res.getString("nome_aluno"));
                aluno.setSobrenomeAluno(res.getString("sobrenome_aluno"));
                aluno.setDataNascimentoAluno(res.getDate("data_nascimento_aluno").toLocalDate());
                aluno.setSexoAluno(res.getString("sexo_aluno"));
            }
        }catch (SQLException e) {
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
        return aluno;
    }

    public Aluno loginAluno(String email, String senha) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM USUARIO U INNER JOIN ALUNO A ON (A.ID_USUARIO = U.ID_USUARIO) WHERE U.EMAIL = ? AND U.SENHA = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, senha);

                try (ResultSet res = stmt.executeQuery()) {
                    if (res.next()) {
                        Aluno aluno = new Aluno();
                        aluno.setIdUsuario(res.getInt("id_usuario"));
                        aluno.setNome(res.getString("nome"));
                        aluno.setSobrenome(res.getString("sobrenome"));
                        aluno.setTelefone(res.getString("telefone"));
                        aluno.setEmail(res.getString("email"));
                        aluno.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                        aluno.setSexo(res.getString("sexo"));
                        aluno.setSenha(res.getString("senha"));
                        aluno.setCpf(res.getString("cpf"));
                        aluno.setNomeAluno(res.getString("nome_aluno"));
                        aluno.setSobrenomeAluno(res.getString("sobrenome_aluno"));
                        aluno.setSexoAluno(res.getString("sexo_aluno"));
                        aluno.setIdAluno(res.getInt("id_aluno"));

                        return aluno;
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

