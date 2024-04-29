package DAO;

import Model.Batalha;
import Model.Participante;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParticipanteDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertParticipante(Participante participante) {

        connectToDB();

        String sql = "INSERT INTO Participante (nome, idade, vulgo, estado, idParticipante) values(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,participante.getNome());
            pst.setString(2, participante.getIdade());
            pst.setString(3, participante.getVulgo());
            pst.setString(4, participante.getEstado());
            pst.setInt(5, participante.getParticipanteId());
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
    public boolean updateBatalhaNome(String nomeAntigo, String nomeNovo) {
        connectToDB();
        String sql = "UPDATE Alunos SET nomeNovo=? where nomeAntigo=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeAntigo);
            pst.setString(2, nomeNovo);
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
    public boolean deleteBatalha(String nome) {
        connectToDB();
        String sql = "DELETE FROM Batalha where nome=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
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

                Batalha batalhaAux = new Batalha(rs.getString("nome"), rs.getString("local"), rs.getString("diaDaSemana"));

                System.out.println("Nome da batalha: " + batalhaAux.getNome());
                System.out.println("Local: " + batalhaAux.getLocal());
                System.out.println("Dia da semana: " + batalhaAux.getDiaDaSemana());

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
}
