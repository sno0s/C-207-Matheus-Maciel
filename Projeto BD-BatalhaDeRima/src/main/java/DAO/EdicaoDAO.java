package DAO;

import Model.Batalha;
import Model.Edicao;
import Model.Participante;
import Model.Vencedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EdicaoDAO extends ConnectionDAO {

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //EDICAODAO TUDO CERTO
    //INSERT
    public boolean insertEdicao(Edicao edicao) {

        connectToDB();

        String sql = "INSERT INTO Edicao (idEdicao, numEdicao, data, idBatalha) values(?,?,?,?)";
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
    public boolean deleteEdicao(int numEdicao, int idBatalha) {
        connectToDB();
        String sql = "DELETE FROM Edicao where numEdicao=? and idBatalha=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, numEdicao);
            pst.setInt(2, idBatalha);
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

    public ArrayList<Edicao> selectVencedoresTodasEdicoes() {
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
                "    Participante ON Vencedores.idParticipante = Participante.idParticipante ";

        try {
            pst = con.prepareStatement(sql);
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

    public int selectEdicaoPorNomeENumero(String nomeBatalha, int numEdicao, String user, String senha) {
        int idedicao=01;
        try {

            connectLikeAdmin(user, senha);

            String sql = "SELECT " +
                    "    Batalha.nome AS Nome_Batalha, " +
                    "    Edicao.numEdicao AS Numero_Edicao, " +
                    "    Edicao.data AS Data_Edicao, " +
                    "    Participante.vulgo AS Nome_Vencedor, " +
                    "    Edicao.idEdicao AS id_edicao " +
                    "FROM " +
                    "    Edicao " +
                    "INNER JOIN " +
                    "    Batalha ON Edicao.idBatalha = Batalha.idBatalha " +
                    "INNER JOIN " +
                    "    Vencedores ON Edicao.idEdicao = Vencedores.idEdicao " +
                    "INNER JOIN " +
                    "    Participante ON Vencedores.idParticipante = Participante.idParticipante " +
                    "WHERE " +
                    "    Batalha.nome = ? AND " +
                    "    Edicao.numEdicao = ?";

            pst = con.prepareStatement(sql);
            pst.setString(1, nomeBatalha);
            pst.setInt(2, numEdicao);
            rs = pst.executeQuery();

            boolean encontrouResultado = false;

            while (rs.next()) {
                encontrouResultado = true;

                String nomeBatalhaResult = rs.getString("Nome_Batalha");
                int numeroEdicao = rs.getInt("Numero_Edicao");
                String dataEdicao = rs.getString("Data_Edicao");
                String nomeVencedor = rs.getString("Nome_Vencedor");
                idedicao = rs.getInt("id_edicao");

                System.out.println("\n----->Informações antigas da Edição: ");
                System.out.println("Nome da Batalha: " + nomeBatalhaResult);
                System.out.println("Número antigo da Edição: " + numeroEdicao);
                System.out.println("Data antiga da Edição: " + dataEdicao);
                System.out.println("Nome antigo do Vencedor: " + nomeVencedor);
                System.out.println("ID da edição: " + idedicao);
            }

            if (!encontrouResultado) {
                System.out.println("Nenhum resultado encontrado para a batalha '" + nomeBatalha + "' e número de edição '" + numEdicao + "'.");
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
        return idedicao;
    }

    public boolean updateEdicao(int numnovo, String datanova, int idBatalha, String user, String senha) {
        connectLikeAdmin(user, senha);
        String sql = "UPDATE Edicao SET numEdicao=?, data=? where idBatalha=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, numnovo);
            pst.setString(2, datanova);
            pst.setInt(3, idBatalha);
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