package org.example;

import DAO.ConnectionDAO;
import Model.Batalha;
import DAO.BatalhaDAO;

public class Main {
    public static void main(String[] args) {
        Batalha batalha = new Batalha("Batalha da aldeia", "SP","sexta");
        BatalhaDAO b1 = new BatalhaDAO();


        //Inserindo a primeira batalha para fins de teste
        b1.insertBatalha(batalha);

        //selecionando todas as batalhas adicionadas
        b1.selectBatalha();

        //testando o comando delete
        b1.deleteBatalha("Batalha da aldeia");

        //selecionando todas dnv para ver se deu certo
        b1.selectBatalha();



    }
}
