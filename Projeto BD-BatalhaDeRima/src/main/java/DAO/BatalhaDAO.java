package DAO;

import Model.Batalha;

import java.sql.SQLException;
import java.util.ArrayList;

public class BatalhaDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertBatalha(Batalha batalha) {

        connectToDB();

        String sql = "INSERT INTO Batalha (nome, local, diaDaSemana) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(2,batalha.getLocal());
            pst.setString(1, batalha.getNome());
            pst.setString(3, batalha.getDiaDaSemana());
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
    public boolean updateAlunoNome(int matricula, String nome) {
        connectToDB();
        String sql = "UPDATE Alunos SET nome=? where matricula=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, matricula);
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

