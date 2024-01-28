package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.dto.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.services.DesafioService;
import br.com.dbc.vemser.alfabetizai.services.ModuloService;
import br.com.dbc.vemser.alfabetizai.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AlunoRepository implements Repositorio<Integer, Aluno> {

    private final ModuloService moduloService;

    private final DesafioService desafioService;

    private final ProfessorService professorService;

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
            con = ConexaoBancoDeDados.getConnection();

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

            String sqlAluno = "INSERT INTO ALUNO (ID_ALUNO, ID_USUARIO, NOME_ALUNO, SOBRENOME_ALUNO, DATA_NASCIMENTO_ALUNO, SEXO_ALUNO) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmtAluno = con.prepareStatement(sqlAluno)) {
                stmtAluno.setInt(1, aluno.getIdAluno());
                stmtAluno.setInt(2, aluno.getIdUsuario());
                stmtAluno.setString(3, aluno.getNomeAluno());
                stmtAluno.setString(4, aluno.getSobrenomeAluno());
                stmtAluno.setDate(5, Date.valueOf(aluno.getDataDeNascimento()));
                stmtAluno.setString(6, aluno.getSexoAluno());

                int resAluno = stmtAluno.executeUpdate();
                System.out.println("adicionarAluno.res=" + resAluno);
            }

            return aluno;

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

            String sql = "UPDATE USUARIO SET ATIVO = ? WHERE ID_USUARIO = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, "N");
                stmt.setInt(2, id);

                int res = stmt.executeUpdate();
                System.out.println("editarUsuario.res=" + res);

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
            sql.append(" CPF = ? ");
            sql.append(" PONTUACAO = ? ");
            sql.append(" WHERE ID_ALUNO = ? ");

            try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {
                stmt.setString(1, aluno.getNome());
                stmt.setString(2, aluno.getSobrenome());
                stmt.setString(3, aluno.getTelefone());
                stmt.setString(4, aluno.getEmail());
                stmt.setDate(5, Date.valueOf(aluno.getDataDeNascimento()));
                stmt.setString(6, aluno.getSexo());
                stmt.setString(7, aluno.getSenha());
                stmt.setString(8, aluno.getCpf());
                stmt.setInt(9, aluno.getPontuacao());
                stmt.setInt(10, id);

                int res = stmt.executeUpdate();
                System.out.println("editarAluno.res=" + res);

                return aluno;
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
    public List<Aluno> listar() throws BancoDeDadosException {
        List<Aluno> adminBanco = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
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
                    aluno.setSexo(res.getString("sexo"));
                    aluno.setSenha(res.getString("senha"));
                    aluno.setCpf(res.getString("cpf"));
                    aluno.setPontuacao(res.getInt("pontuacao"));
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
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*, A.* FROM USUARIO U INNER JOIN ALUNO A ON (A.ID_USUARIO = U.ID_USUARIO) WHERE U.ID_USUARIO = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);

                try (ResultSet res = stmt.executeQuery()) {
                    while (res.next()) {

                        aluno.setIdUsuario(res.getInt("id_usuario"));
                        aluno.setNome(res.getString("nome"));
                        aluno.setSobrenome(res.getString("sobrenome"));
                        aluno.setTelefone(res.getString("telefone"));
                        aluno.setEmail(res.getString("email"));
                        aluno.setDataDeNascimento(res.getDate("data_nascimento").toLocalDate());
                        aluno.setSexo(res.getString("sexo"));
                        aluno.setSenha(res.getString("senha"));
                        aluno.setCpf(res.getString("cpf"));
                        aluno.setPontuacao(res.getInt("pontuacao"));
                    }
                }
            }

            return aluno;

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

    public Aluno loginAluno(String email, String senha) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

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

    public List<Modulo> listarModulosConcluidos(int idAluno) throws Exception {
        List<Modulo> modulos = new ArrayList<>();

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*" +
                    "FROM MODULO_ALUNO_DESAFIO U " +
                    "WHERE U.ID_ALUNO = ? AND U.MODULO_CONCLUIDO = 'S'";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idAluno);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Modulo modulo = new Modulo();
                Professor professor = new Professor();
                modulo.setId(res.getInt("id_modulo"));
                ModuloDTO moduloDTO = moduloService.buscarModuloPorId(modulo.getId());
                ProfessorDTO professorDTO = professorService.buscarProfessorPorId(moduloDTO.getAutor().getIdProfessor());
                professor.setIdProfessor(professorDTO.getIdProfessor());
                professor.setNome(professorDTO.getNome());
                professor.setSobrenome(professorDTO.getSobrenome());
                professor.setEmail(professorDTO.getEmail());
                professor.setDescricao(professorDTO.getDescricao());
                professor.setTelefone(professorDTO.getTelefone());
                modulo.setAutor(professor);
                modulo.setTitulo(moduloDTO.getTitulo());
                modulo.setConteudo(moduloDTO.getConteudo());
                modulo.setClassificacao(moduloDTO.getClassificacao());
                modulo.setFoiAprovado(moduloDTO.getFoiAprovado());
                modulos.add(modulo);
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
        return modulos;
    }

    public List<Desafio> listardesafiosConcluidos(Integer idAluno) throws Exception {
        List<Desafio> desafios = new ArrayList<>();

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*" +
                    "FROM MODULO_ALUNO_DESAFIO U " +
                    "WHERE U.ID_ALUNO = ? AND U.DESAFIO_CONCLUIDO = 'S'";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idAluno);

            ResultSet res = stmt.executeQuery();

                while (res.next()) {
                    Desafio desafio = new Desafio();
                    desafio.setId(res.getInt("id_desafio"));
                    DesafioDTO desafioDTO = desafioService.buscarDesafioPorId(desafio.getId());
                    desafio.setTitulo(desafioDTO.getTitulo());
                    desafio.setConteudo(desafioDTO.getConteudo());
                    desafio.setTipoDesafio(desafioDTO.getTipoDesafio());
                    desafio.setIdModulo(desafioDTO.getIdModulo());

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

