package org.example;

import DAO.ConnectionDAO;
import DAO.EdicaoDAO;
import DAO.ParticipanteDAO;
import Model.Batalha;
import DAO.BatalhaDAO;
import Model.Edicao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Iniciando variáveis
        EdicaoDAO e = new EdicaoDAO();
        ParticipanteDAO p = new ParticipanteDAO();
        BatalhaDAO b = new BatalhaDAO();
        boolean running = true;
        int choice=0;
        Scanner entrada = new Scanner(System.in);

    while(running){
            do{
                System.out.println("(1)Usuário\n(2)Admin\n(3)Sair");
                choice = entrada.nextInt();
                if(choice != 1 && choice!=2 && choice != 3)
                    System.out.println("Entrada incorreta, tente novamente.");
            }while(choice != 1 && choice!=2&& choice != 3);
            switch (choice){
                case 1:
                    boolean rodando = true;
                    //Região de interação do usuário com o banco de dados
                    int choice_user = 0;
                    while(rodando){
                        do{
                            System.out.println("\n(1)Ver todas as batalhas de rima;\n(2)Ver todos os vencedores de uma batalha;\n(3)Ver todos os MC's cadastrados;\n(4)Voltar ao menu principal.");
                            choice_user = entrada.nextInt();
                            if(choice_user != 1 && choice_user!=2 && choice_user!=3 && choice_user!=4)
                                System.out.println("Entrada incorreta, tente novamente.");
                        }while(choice_user != 1 && choice_user!=2 && choice_user!=3 && choice_user!=4);

                        switch (choice_user){
                            case 1:
                                //Case para ver todas as batalhas de rima
                                do{
                                    System.out.println("Deseja filtar por estado?\n(1)Sim (2)Não");
                                    choice_user = entrada.nextInt();
                                    if(choice_user != 1 && choice_user!=2)
                                        System.out.println("Entrada incorreta, tente novamente.");
                                }while(choice_user != 1 && choice_user!=2);
                                switch (choice_user){
                                    case 1:
                                        System.out.print("Digite o estado que deseja procurar (Letras maísculas, ex 'SP'): ");
                                        entrada.nextLine();
                                        String estado = entrada.nextLine();
                                        b.selectBatalhaPorEstado(estado);
                                        break;
                                    case 2:
                                        b.selectBatalha();
                                        break;
                                }
                                break;
                            case 2:
                                //Vendo todos os vencedores de batalha de rima
                                do{
                                    System.out.println("Deseja filtar por batalha?\n(1)Sim (2)Não");
                                    choice_user = entrada.nextInt();
                                    if(choice_user != 1 && choice_user!=2)
                                        System.out.println("Entrada incorreta, tente novamente.");
                                }while(choice_user != 1 && choice_user!=2);
                                switch (choice_user){
                                    case 1:
                                        System.out.print("Digite o nome da batalha: ");
                                        entrada.nextLine();
                                        String batalhanome = entrada.nextLine();
                                        e.selectEdicaoVencedores(batalhanome);
                                        break;
                                    case 2:
                                        p.selectParticipante();
                                        break;
                                }
                                break;
                            case 3:
                                break;
                            case 4:
                                rodando = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    //Região de interação do admin com o banco de dados
                    entrada.nextLine();
                    System.out.print("\nUsuário: ");
                    String user = entrada.nextLine();
                    System.out.print("Senha: ");
                    String senha = entrada.nextLine();
                    break;
                case 3:
                    running = false;
                    break;
            }
    }
    }
}
