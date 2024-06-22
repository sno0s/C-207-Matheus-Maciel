package DAO;

import Model.Edicao;
import Model.Participante;
import Model.Vencedores;

import java.sql.SQLException;

public class Edicao_has_ParticipanteDAO extends ConnectionDAO {

    boolean sucesso = false; //Para saber se funcionou
    public boolean insertParticipanteOnEdicao(int idParticipante, int idEdicao, String user, String senha) {

        connectLikeAdmin(user, senha);

        String sql = "INSERT INTO Edicao_has_Participante (idEdicao, idParticipante) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idEdicao);
            pst.setInt(2,idParticipante);
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
}
