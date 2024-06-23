package DAO;

import Model.Batalha;
import Model.Estrutura;
import Model.Vencedores;

import java.sql.SQLException;
import java.util.ArrayList;

public class VencedoresDAO extends ConnectionDAO{

    //ESTRUTURA EM PRODUÇÃO
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertVencedores(int idVencedor, int idEdicao, int idParticipante, String user, String senha) {

        connectLikeAdmin(user, senha);

        String sql = "INSERT INTO Vencedores (idVencedores, idEdicao, idParticipante) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idVencedor);
            pst.setInt(2, idEdicao);
            pst.setInt(3, idParticipante);
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

    public Vencedores selectVencedorByEdicao(int idEdicao, String user, String senha) {

        connectLikeAdmin(user, senha);

        String sql = "SELECT idParticipante FROM Vencedores where idEdicao=?";
        Vencedores vecendorAux = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idEdicao);
            rs = pst.executeQuery();

            if (rs.next()) {  // Move o cursor para a primeira linha válida
                vecendorAux = new Vencedores(
                        rs.getInt("idVencedores"),
                        rs.getInt("idEdicao"),
                        rs.getInt("idParticipante")
                );
            } else {
                System.out.println("Nenhum vencedor encontrado com este ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return vecendorAux;
    }

    public boolean updateVencedores(int idParticipante, int idedicao, String user, String senha) {
        connectLikeAdmin(user, senha);
        String sql = "update Vencedores SET idParticipante=? where idEdicao=?;";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idParticipante);
            pst.setInt(2, idedicao);

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