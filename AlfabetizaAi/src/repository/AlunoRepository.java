package repository;

import exceptions.BancoDeDadosException;
import models.Aluno;

import java.sql.*;
import java.util.List;

public class AlunoRepository implements Repositorio<Integer, Aluno> {

    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_aluno.nextval as mysequence FROM DUAL";
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
    public Aluno adicionar(Aluno aluno) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            aluno.setIdUsuario(proximoId);

            String sql = "INSERT INTO ALUNO\n" +
                         "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con. prepareStatement(sql);

            stmt.setInt(1, aluno.getIdUsuario());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getSobrenome());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.setDate(6, Date.valueOf(aluno.getDataDeNascimento()));
            stmt.setString(7, "S");
            stmt.setString(8, aluno.getSexo());
            stmt.setString(9, aluno.getSenha());
            stmt.setString(10, aluno.getCpf());

            int res = stmt.executeUpdate();
            System.out.println("adicionarAluno=" + res);
            return aluno;

        } catch (SQLException e){
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
