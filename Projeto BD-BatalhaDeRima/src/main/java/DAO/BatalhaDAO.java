package DAO;

import Model.Batalha;

import java.sql.SQLException;
import java.util.ArrayList;

public class BatalhaDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    //BATALHADAO TUDO CERTO

    //INSERT
    public boolean insertBatalha(Batalha batalha, String user, String senha) {

        connectLikeAdmin(user, senha);

        String sql = "INSERT INTO Batalha (nome, local, estado, diaDaSemana, idBatalha) values(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, batalha.getNome());
            pst.setString(2,batalha.getLocal());
            pst.setString(3, batalha.getEstado());
            pst.setString(4, batalha.getDiaDaSemana());
            pst.setInt(5, batalha.getId());
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
    public boolean updateBatalhaNome(String novoNome, String novoLocal, String novoEstado, String novoDiaDaSemana, int idBatalha, String user, String senha) {
        connectLikeAdmin(user, senha);
        String sql = "UPDATE Batalha SET nome=?, local=?, estado=?, diaDaSemana=? where idBatalha=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
            pst.setString(2, novoLocal);
            pst.setString(3, novoEstado);
            pst.setString(4, novoDiaDaSemana);
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
    public boolean deleteBatalha(int id, String user, String senha) {
        connectLikeAdmin(user, senha);
        boolean sucesso = false;

        try {
            // Desabilitar auto-commit para iniciar uma transação
            con.setAutoCommit(false);

            // Primeira instrução DELETE para a tabela Estrutura
            String sqlEstrutura = "DELETE FROM Estrutura WHERE idBatalha = ?";
            pst = con.prepareStatement(sqlEstrutura);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();

            // Segunda instrução DELETE para a tabela Batalha
            String sqlBatalha = "DELETE FROM Batalha WHERE idBatalha = ?";
            pst = con.prepareStatement(sqlBatalha);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();

            // Commit da transação se ambas as operações forem bem-sucedidas
            con.commit();
            sucesso = true;
        } catch (SQLException ex) {
            // Rollback em caso de erro
            try {
                con.rollback();
                System.out.println("Erro = " + ex.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Erro no rollback: " + rollbackEx.getMessage());
            }
            sucesso = false;
        } finally {
            // Restaurar o auto-commit e fechar os recursos
            try {
                con.setAutoCommit(true);
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //SELECT
    public ArrayList<Batalha> selectBatalha() {
        ArrayList<Batalha> batalha = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Batalha";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n----->Batalhas de rima listadas: ");

            while (rs.next()) {

                Batalha batalhaAux = new Batalha(rs.getString("nome"), rs.getString("local"), rs.getString("Estado"), rs.getString("diaDaSemana"), rs.getInt("idBatalha"));

                System.out.println("Nome da batalha: " + batalhaAux.getNome());
                System.out.println("Local: " + batalhaAux.getLocal());
                System.out.println("Estado: " + batalhaAux.getEstado());
                System.out.println("Dia da semana: " + batalhaAux.getDiaDaSemana());
                System.out.println("Id: " + batalhaAux.getId());

                batalha.add(batalhaAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return batalha;
    }

    public ArrayList<Batalha> selectBatalhaPorEstado(String estado1) {
        ArrayList<Batalha> batalhas = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Batalha WHERE estado=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, estado1);
            rs = pst.executeQuery();

            System.out.println("\n----->Batalhas de rima listadas: ");

            while (rs.next()) {
                Batalha batalhaAux = new Batalha(
                        rs.getString("nome"),
                        rs.getString("local"),
                        rs.getString("estado"),  // Corrigido para estado em minúsculas
                        rs.getString("diaDaSemana"),
                        rs.getInt("idBatalha")
                );

                System.out.println("Nome da batalha: " + batalhaAux.getNome());
                System.out.println("Local: " + batalhaAux.getLocal());
                System.out.println("Estado: " + batalhaAux.getEstado());
                System.out.println("Dia da semana: " + batalhaAux.getDiaDaSemana());
                System.out.println("Id: " + batalhaAux.getId());

                batalhas.add(batalhaAux);
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
        return batalhas;
    }
}

