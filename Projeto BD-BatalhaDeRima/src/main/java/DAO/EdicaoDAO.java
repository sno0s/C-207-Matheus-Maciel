package DAO;

import Model.Edicao;

import java.sql.SQLException;
import java.util.ArrayList;

public class EdicaoDAO extends ConnectionDAO {

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertEdicao(Edicao edicao) {

        connectToDB();

        String sql = "INSERT INTO Edicao (idEdicao, numEdicao, data, Batalha_nome) values(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, edicao.getIdEdicao());
            pst.setInt(2, edicao.getNumEdicao());
            pst.setString(3, edicao.getData());
            pst.setString(4, edicao.getBatalha_nome());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteEdicao(int numEdicao) {
        connectToDB();
        String sql = "DELETE FROM Edicao where numEdicao=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, numEdicao);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }
}