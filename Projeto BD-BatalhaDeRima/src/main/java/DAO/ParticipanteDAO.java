package DAO;

import Model.Batalha;
import Model.Participante;
import Model.Vencedores;

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
    public boolean updateNomeParticipante(String nome, int idade, String vulgo, String estado, int idParticipante, String user, String senha) {
        connectLikeAdmin(user, senha);

        String sql = "update Participante SET nome=?, idade=?, vulgo=?, estado=?  where idParticipante=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, idade);
            pst.setString(3, vulgo);
            pst.setString(4, estado);
            pst.setInt(5, idParticipante);
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
    public boolean deleteParticipante(int idParticipante, String user, String senha) {
        connectLikeAdmin(user, senha);
        boolean sucesso = false;

        try {
            // Desabilitar auto-commit para iniciar uma transação
            con.setAutoCommit(false);

            // Primeira instrução DELETE para a tabela Edicao_has_Participante
            String sqlEdicaoHasParticipante = "DELETE FROM Edicao_has_Participante WHERE idParticipante = ?";
            pst = con.prepareStatement(sqlEdicaoHasParticipante);
            pst.setInt(1, idParticipante);
            pst.executeUpdate();
            pst.close();

            // Segunda instrução DELETE para a tabela Vencedores
            String sqlVencedores = "DELETE FROM Vencedores WHERE idParticipante = ?";
            pst = con.prepareStatement(sqlVencedores);
            pst.setInt(1, idParticipante);
            pst.executeUpdate();
            pst.close();

            // Terceira instrução DELETE para a tabela Participante
            String sqlParticipante = "DELETE FROM Participante WHERE idParticipante = ?";
            pst = con.prepareStatement(sqlParticipante);
            pst.setInt(1, idParticipante);
            pst.executeUpdate();
            pst.close();

            // Commit da transação se todas as operações forem bem-sucedidas
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
    public ArrayList<Participante> selectParticipante() {
        ArrayList<Participante> participantes = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Participante";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("\n----->Listando todos os MC's De batalha cadastrados: ");

            while (rs.next()) {

                Participante participanteAux = new Participante(rs.getString("nome"), rs.getString("vulgo"), rs.getInt("idade"), rs.getString("estado"), rs.getInt("idParticipante"));

                System.out.println("Nome do mc: " + participanteAux.getNome());
                System.out.println("Vulgo: " + participanteAux.getVulgo());
                System.out.println("Idade: " + participanteAux.getIdade());
                System.out.println("Estado: " + participanteAux.getEstado());
                System.out.println("Id: " + participanteAux.getParticipanteId() + "\n");
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

    public Participante selectParticipanteByID(int idParticipante, String user, String senha) {

        connectLikeAdmin(user, senha);

        String sql = "SELECT nome FROM Participantes where idParticipante=?";
        Participante participanteaux = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idParticipante);
            rs = pst.executeQuery();

            if (rs.next()) {  // Move o cursor para a primeira linha válida
                participanteaux = new Participante(
                        rs.getString("nome"),
                        rs.getString("vulgo"),
                        rs.getInt("idade"),
                        rs.getString("estado"),
                        rs.getInt("idParticipante")
                );
            } else {
                System.out.println("Nenhum Participante encontrado com este ID.");
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
        return participanteaux;
    }

    public Participante selectParticipanteToUpdate(int idParticipante, String user, String senha) {

        connectLikeAdmin(user, senha);

        String sql = "SELECT * FROM Participante where idParticipante=?";
        Participante participanteaux = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idParticipante);
            rs = pst.executeQuery();

            if (rs.next()) {  // Move o cursor para a primeira linha válida
                participanteaux = new Participante(
                        rs.getString("nome"),
                        rs.getString("vulgo"),
                        rs.getInt("idade"),
                        rs.getString("estado"),
                        rs.getInt("idParticipante")
                );
                System.out.println("----->Informações antigas do participante: ");
                System.out.println("Nome antigo: " + participanteaux.getNome());
                System.out.println("Vulgo antigo: " + participanteaux.getVulgo());
                System.out.println("Idade antiga: " + participanteaux.getIdade());
                System.out.println("Estado antigo: " + participanteaux.getEstado());
            } else {
                System.out.println("Nenhum Participante encontrado com este ID.");
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
        return participanteaux;
    }
}
