package br.com.dbc.vemser.alfabetizai.repository;

import br.com.dbc.vemser.alfabetizai.enums.ClassificacaoModulo;
import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Admin;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.models.Professor;
import br.com.dbc.vemser.alfabetizai.services.AdminService;
import br.com.dbc.vemser.alfabetizai.services.ProfessorService;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ModuloRepository implements Repositorio<Integer, Modulo>{

    private final ProfessorService professorService;
    private final AdminService adminService;


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
    public Modulo adicionar(Modulo modulo) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            modulo.setId(proximoId);

            String sql = "INSERT INTO MODULO\n" +
                    "(ID_PROFESSOR, TITULO, CONTEUDO, CLASSIFICACAO_MODULO, MODULO_APROVADO)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, modulo.getAutor().getIdProfessor());
            stmt.setString(2, modulo.getTitulo());
            stmt.setString(3, modulo.getConteudo());
            stmt.setInt(4, modulo.getClassificacao().ordinal()+1);
            stmt.setString(5, "N");

            int res = stmt.executeUpdate();
            System.out.println("adicionarModulo.res=" + res);
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
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM MODULO WHERE id_modulo = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerModuloPorId.res=" + res);

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
    public Modulo editar(Integer id, Modulo modulo) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MODULO SET ");
            sql.append(" id_professor = ?,");
            sql.append(" id_admin = ?,");
            sql.append(" titulo = ?,");
            sql.append(" conteudo = ?,");
            sql.append(" classificacao_modulo = ? ");
            sql.append(" modulo_aprovado = ? ");
            sql.append(" WHERE id_modulo = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, modulo.getAutor().getIdProfessor());
            stmt.setInt(2, modulo.getAdminAprova().getIdAdmin());
            stmt.setString(3, modulo.getTitulo());
            stmt.setString(4, modulo.getConteudo());
            stmt.setInt(5, modulo.getClassificacao().ordinal()+1);
            stmt.setString(6, String.valueOf(modulo.isFoiAprovado()));

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarModulo.res=" + res);

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
    public boolean editarAprovacaoPorAdmin(Integer idAdmin, Integer idModulo, String aprovacaoModulo) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

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
            System.out.println("editarModulo.res=" + res);

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
    public List<Modulo> listar() throws BancoDeDadosException {
        List<Modulo> modulos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM MODULO";

            // Executa-se a consulta
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
                modulo.setFoiAprovado((res.getString("modulo_aprovado").charAt(0)));
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

    public List<Modulo> listarSemAprovacao() throws BancoDeDadosException {
        List<Modulo> modulos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM MODULO WHERE MODULO_APROVADO = 'N' OR MODULO_APROVADO = 'n'";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Modulo modulo = new Modulo();
                modulo.setId(res.getInt("id_modulo"));
                modulo.setTitulo(res.getString("titulo"));
                modulo.setConteudo(res.getString("conteudo"));
                modulo.setClassificacao(ClassificacaoModulo.trazEnumPeloOrdinal(res.getInt("classificacao_modulo")));
                modulo.setFoiAprovado((res.getString("modulo_aprovado").charAt(0)));
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
            con = ConexaoBancoDeDados.getConnection();
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
                modulo.setFoiAprovado((res.getString("modulo_aprovado").charAt(0)));
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
            con = ConexaoBancoDeDados.getConnection();
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
                modulo.setFoiAprovado((res.getString("modulo_aprovado").charAt(0)));
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
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM MODULO M " +
                    "WHERE M.ID_MODULO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idModulo);

            ResultSet res = stmt.executeQuery();
            Modulo modulo = new Modulo();
            while (res.next()) {

                modulo.setId(res.getInt("id_modulo"));
                //modulo.setAdminAprova(adminService.BuscarAdminPorId(res.getInt("id_admin")));
//                modulo.setAutor(professorService.buscarProfessorPorId(res.getInt("id_professor")));
                modulo.setTitulo(res.getString("titulo"));
                modulo.setConteudo(res.getString("conteudo"));
                modulo.setClassificacao(ClassificacaoModulo.ofTipo(res.getInt("classificacao_modulo")));
                modulo.setFoiAprovado(res.getString("modulo_aprovado").charAt(0));

            }
            System.out.println(modulo);
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
}