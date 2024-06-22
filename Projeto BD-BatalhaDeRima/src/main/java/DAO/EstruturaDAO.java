package DAO;

import Model.Batalha;
import Model.Estrutura;

import java.sql.SQLException;
import java.util.ArrayList;

public class EstruturaDAO extends ConnectionDAO{

    //ESTRUTURA FINALIZADA
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertEstrutura(Estrutura estrutura, String user, String senha) {

        connectLikeAdmin(user, senha);

        String sql = "INSERT INTO Estrutura (Coberto, Microfone, idBatalha) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, estrutura.getCoberto());
            pst.setInt(2,estrutura.getMicrofone());
            pst.setInt(3, estrutura.getIdBatalha());
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

    //UPDATE
    public boolean updateEstrutura(boolean temMicrofone, boolean temCobertura, int id) {
        connectToDB();
        String sql = "UPDATE Estrutura SET Microfone=?, Coberto=? where idBatalha=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setBoolean(1, temMicrofone);
            pst.setBoolean(2, temCobertura);
            pst.setInt(3, id);
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

    //DELETE
    public boolean deleteEstrutura(int idBatalha, String user, String senha) {
        connectLikeAdmin(user, senha);
        String sql = "DELETE FROM Estrutura where idBatalha=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idBatalha);
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