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


}