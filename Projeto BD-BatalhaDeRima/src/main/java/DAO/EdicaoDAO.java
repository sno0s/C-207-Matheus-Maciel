package DAO;

import Model.Batalha;
import Model.Edicao;
import Model.Participante;
import Model.Vencedores;

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
            pst.setInt(4, edicao.getIdBatalha());
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

    public ArrayList<Edicao> selectEdicaoVencedores(String nomeBatalha) {
        ArrayList<Edicao> edicoes = new ArrayList<>();
        connectToDB();
        String sql = "SELECT " +
                "    Edicao.idEdicao AS idEdicao, " +
                "    Edicao.numEdicao AS numEdicao, " +
                "    Edicao.data AS data, " +
                "    Edicao.idBatalha AS idBatalha, " +
                "    Batalha.nome AS Batalha, " +
                "    Participante.vulgo AS Vencedor " +
                "FROM " +
                "    Edicao " +
                "INNER JOIN " +
                "    Batalha ON Edicao.idBatalha = Batalha.idBatalha " +
                "INNER JOIN " +
                "    Vencedores ON Edicao.idEdicao = Vencedores.idEdicao " +
                "INNER JOIN " +
                "    Participante ON Vencedores.idParticipante = Participante.idParticipante " +
                "WHERE " +
                "    Batalha.nome = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeBatalha);
            rs = pst.executeQuery();

            while (rs.next()) {
                Edicao edicaoAux = new Edicao(
                        rs.getInt("idEdicao"),
                        rs.getInt("numEdicao"),
                        rs.getString("data"),
                        rs.getInt("idBatalha")
                );

                String batalhaNome = rs.getString("Batalha");
                String vencedorNome = rs.getString("Vencedor");

                System.out.println("\nEdição: " + edicaoAux.getNumEdicao());
                System.out.println("Data: " + edicaoAux.getData());
                System.out.println("Batalha: " + batalhaNome);
                System.out.println("Vencedor: " + vencedorNome);

                edicoes.add(edicaoAux);
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
        return edicoes;
    }

}