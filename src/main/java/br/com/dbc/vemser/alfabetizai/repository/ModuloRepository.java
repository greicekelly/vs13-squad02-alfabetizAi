package br.com.dbc.vemser.alfabetizai.repository;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import lombok.extern.slf4j.Slf4j;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Repository
public class ModuloRepository implements Repositorio<Integer, Modulo>{

    private final ProfessorService professorService;
    private final ConexaoBancoDeDados conexaoBancoDeDados;

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_modulo.nextval mysequence from DUAL";

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
    public Modulo adicionar(Modulo modulo) throws Exception {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            modulo.setId(proximoId);

            String sql = "INSERT INTO MODULO\n" +
                    "(ID_MODULO,ID_PROFESSOR, TITULO, CONTEUDO, CLASSIFICACAO_MODULO, MODULO_APROVADO)\n" +
                    "VALUES(?,?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, modulo.getId());
            stmt.setInt(2, modulo.getIdProfessor());
            stmt.setString(3, modulo.getTitulo());
            stmt.setString(4, modulo.getConteudo());
            stmt.setInt(5, modulo.getClassificacao().ordinal()+1);
            stmt.setString(6, "N");

            Professor professor = new Professor();
            professor.setIdProfessor(modulo.getIdProfessor());
            modulo.setAutor(mapperProfessor(professor));
            modulo.setFoiAprovado("N");

            int res = stmt.executeUpdate();
            return modulo;
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

            if (RegistroInativo(con, id)) {
                throw new RegraDeNegocioException("Registro já inativo para o ID: " + id);
            }
            String sql = "UPDATE MODULO SET STATUS_MODULO = 1 WHERE id_modulo = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            if (res == 0) {
                throw new RegraDeNegocioException("Dados do Modulo Não Encontrado ou ja inativado para o  ID: " + id);
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
    @Override
    public Modulo editar(Integer id, Modulo modulo) throws Exception {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            String sql = "UPDATE MODULO SET " +
                    "id_professor = ?, " +
                    "titulo = ?, " +
                    "conteudo = ?, " +
                    "classificacao_modulo = ? " +
                    "WHERE id_modulo = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, modulo.getIdProfessor());
            stmt.setString(2, modulo.getTitulo());
            stmt.setString(3, modulo.getConteudo());
            stmt.setInt(4, modulo.getClassificacao().ordinal() + 1);
            stmt.setInt(5, id);

            if (stmt.executeUpdate() == 0) {
                throw new RegraDeNegocioException("Dados do Módulo Não Encontrados. ID: " + id);
            }
            Professor professor = new Professor();
            professor.setIdProfessor(modulo.getIdProfessor());
            modulo.setAutor(mapperProfessor(professor));

            modulo.setId(id);
            return modulo;
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
    public boolean editarAprovacaoPorAdmin(Integer idAdmin, Integer idModulo, String aprovacaoModulo) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MODULO SET ");
            sql.append(" id_admin = ?,");
            sql.append(" modulo_aprovado = ? ");
            sql.append(" WHERE id_modulo = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setInt(1, idAdmin);
            stmt.setString(2, aprovacaoModulo);
            stmt.setInt(3, idModulo);

            int res = stmt.executeUpdate();
            if (res == 0) {
                throw new RegraDeNegocioException("Dados do Admin Não Encontrado. ID: " + idAdmin);
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
    @Override
    public List<Modulo> listar() throws Exception {
        List<Modulo> modulos = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM MODULO";

            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Modulo modulo = new Modulo();
                Professor professor = new Professor();
                Admin admin = new Admin();

                modulo.setId(res.getInt("id_modulo"));
                modulo.setIdProfessor(res.getInt("id_professor"));

                professor.setIdProfessor(res.getInt("id_professor"));

                modulo.setAdminAprova(admin);
                admin.setIdUsuario(res.getInt("id_admin"));
                modulo.setTitulo(res.getString("titulo"));
                modulo.setConteudo(res.getString("conteudo"));
                modulo.setClassificacao(ClassificacaoModulo.trazEnumPeloOrdinal(res.getInt("classificacao_modulo")));
                modulo.setFoiAprovado(res.getString("modulo_aprovado"));
                modulo.setAutor(mapperProfessor(professor));
                modulos.add(modulo);
            }
        } catch (SQLException e) {
            log.info("Erro ao executar consulta SQL", e);
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

    public List<Modulo> listarPorIdProfessor(Integer idProfessor) throws BancoDeDadosException {
        List<Modulo> moduloPorProfessor = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            if (!existeProfessor(idProfessor, con)) {
                throw new RegraDeNegocioException("Professor com ID " + idProfessor + " não encontrado");
            }
            String sql = "SELECT M.ID_MODULO, P.ID_PROFESSOR, M.TITULO, M.CONTEUDO, M.CLASSIFICACAO_MODULO, M.MODULO_APROVADO " +
                    "FROM MODULO M " +
                    "JOIN PROFESSOR P ON " +
                    "M.ID_PROFESSOR = P.ID_PROFESSOR " +
                    "WHERE P.ID_PROFESSOR = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idProfessor);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Modulo moduloResponse = new Modulo();
                Professor professor = new Professor();
                moduloResponse.setId(res.getInt("ID_MODULO"));
                moduloResponse.setIdProfessor(res.getInt("ID_PROFESSOR"));

                professor.setIdProfessor(res.getInt("id_professor"));

                moduloResponse.setAutor(mapperProfessor(professor));

                moduloResponse.setTitulo(res.getString("TITULO"));
                moduloResponse.setConteudo(res.getString("CONTEUDO"));
                moduloResponse.setClassificacao(ClassificacaoModulo.trazEnumPeloOrdinal(res.getInt(5)));
                moduloResponse.setFoiAprovado(res.getString("modulo_aprovado"));
                moduloPorProfessor.add(moduloResponse);
            }
            if (moduloPorProfessor.isEmpty()) {
                throw new RegraDeNegocioException("Nenhum modulo " +
                        "encontrado para o professor com ID: " + idProfessor);
            }
            return moduloPorProfessor;
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
    public List<Modulo> listarSemAprovacao() throws BancoDeDadosException {
        List<Modulo> modulos = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM MODULO WHERE MODULO_APROVADO = 'N' OR MODULO_APROVADO = 'n'";
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Modulo modulo = new Modulo();
                modulo.setId(res.getInt("id_modulo"));
                modulo.setTitulo(res.getString("titulo"));
                modulo.setConteudo(res.getString("conteudo"));
                modulo.setClassificacao(ClassificacaoModulo.trazEnumPeloOrdinal(res.getInt("classificacao_modulo")));
                modulo.setFoiAprovado(res.getString("modulo_aprovado"));
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
    public List<Modulo> listarAprovados() throws BancoDeDadosException {
        List<Modulo> modulos = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM MODULO WHERE MODULO_APROVADO = 'S' OR MODULO_APROVADO = 's'";
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Modulo modulo = new Modulo();
                Professor professor = new Professor();
                Admin admin = new Admin();
                modulo.setId(res.getInt("id_modulo"));
                modulo.setAutor(professor);
                professor.setIdUsuario(res.getInt("id_professor"));
                modulo.setAdminAprova(admin);
                admin.setIdUsuario(res.getInt("id_admin"));
                modulo.setTitulo(res.getString("titulo"));
                modulo.setConteudo(res.getString("conteudo"));
                modulo.setClassificacao(ClassificacaoModulo.trazEnumPeloOrdinal(res.getInt("classificacao_modulo")));
                modulo.setFoiAprovado(res.getString("modulo_aprovado"));
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
    public List<Modulo> listarReprovados() throws BancoDeDadosException {
        List<Modulo> modulos = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM MODULO WHERE MODULO_APROVADO = 'R' OR MODULO_APROVADO = 'r'";
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Modulo modulo = new Modulo();
                Professor professor = new Professor();
                Admin admin = new Admin();
                modulo.setId(res.getInt("id_modulo"));
                modulo.setAutor(professor);
                professor.setIdUsuario(res.getInt("id_professor"));
                modulo.setAdminAprova(admin);
                admin.setIdUsuario(res.getInt("id_admin"));
                modulo.setTitulo(res.getString("titulo"));
                modulo.setConteudo(res.getString("conteudo"));
                modulo.setClassificacao(ClassificacaoModulo.trazEnumPeloOrdinal(res.getInt("classificacao_modulo")));
                modulo.setFoiAprovado(res.getString("modulo_aprovado"));
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
    public Modulo buscarModuloPorId(Integer idModulo) throws Exception {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM MODULO M " +
                    "WHERE M.ID_MODULO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idModulo);

            ResultSet res = stmt.executeQuery();
            if (!res.next()) {
                throw new RegraDeNegocioException("Dados do Módulo Não Encontrado para o ID: " + idModulo);
            }
            Modulo modulo = new Modulo();
            Professor professor = new Professor();

            modulo.setId(res.getInt("id_modulo"));
            modulo.setIdProfessor(res.getInt("id_professor"));

            professor.setIdProfessor(res.getInt("id_professor"));

            modulo.setAutor(mapperProfessor(professor));
            modulo.setTitulo(res.getString("titulo"));
            modulo.setConteudo(res.getString("conteudo"));
            modulo.setClassificacao(ClassificacaoModulo.ofTipo(res.getInt("classificacao_modulo")));
            modulo.setFoiAprovado(res.getString("modulo_aprovado"));

            return modulo;
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

    public List<Modulo> listarModulosConcluidos(int idAluno) throws Exception {
        List<Modulo> modulos = new ArrayList<>();

        Connection con = null;
        try {
            con = conexaoBancoDeDados.getConnection();

            String sql = "SELECT U.*" +
                    "FROM MODULO_ALUNO_DESAFIO U " +
                    "WHERE U.ID_ALUNO = ? AND U.MODULO_CONCLUIDO = 'S'";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idAluno);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Modulo modulo = buscarModuloPorId(res.getInt("id_modulo"));

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

    private boolean existeProfessor(Integer idProfessor, Connection connection) throws SQLException {
        String sql = "SELECT * FROM PROFESSOR WHERE ID_PROFESSOR  = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        }
    }
    private boolean RegistroInativo(Connection con, Integer id) throws SQLException {
        String checkSql = "SELECT * FROM MODULO WHERE id_modulo = ? AND STATUS_MODULO = 1";
        try (PreparedStatement checkStmt = con.prepareStatement(checkSql)) {
            checkStmt.setInt(1, id);
            try (ResultSet resultSet = checkStmt.executeQuery()) {
                return resultSet.next();

            }
        }
    }

    private Professor mapperProfessor(Professor professor) throws Exception {
        ProfessorDTO professorDTO = professorService.buscarProfessorPorId(professor.getIdProfessor());
        professor.setIdProfessor(professorDTO.getIdProfessor());
        professor.setNome(professorDTO.getNome());
        professor.setSobrenome(professorDTO.getSobrenome());
        professor.setEmail(professorDTO.getEmail());
        professor.setDescricao(professorDTO.getDescricao());
        professor.setTelefone(professorDTO.getTelefone());

        return professor;
    }
}
