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
            pst.setInt(2, participante.getIdade());
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
    public boolean updateNomeParticipante(String nomeAntigo, String nomeNovo) {
        connectToDB();
        String sql = "UPDATE Participante SET nomeNovo=? where nomeAntigo=?";
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
    public boolean deleteParticipante(String nome) {
        connectToDB();
        String sql = "DELETE FROM Participante where nome=?";
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
    public ArrayList<Participante> selectParticipante() {
        ArrayList<Participante> participantes = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Participante";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n----->Listando todos os MC's De batalha cadastrados: ");

            while (rs.next()) {

                Participante participanteAux = new Participante(rs.getString("nome"), rs.getString("vulgo"), rs.getInt("idade"), rs.getString("estado"), rs.getInt("id"));

                System.out.println("Nome do mc: " + participanteAux.getNome());
                System.out.println("Vulgo: " + participanteAux.getVulgo());
                System.out.println("Idade: " + participanteAux.getIdade());
                System.out.println("Estado: " + participanteAux.getEstado());
                System.out.println("Id: " + participanteAux.getParticipanteId());
                participantes.add(participanteAux);
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
        return participantes;
    }
}
