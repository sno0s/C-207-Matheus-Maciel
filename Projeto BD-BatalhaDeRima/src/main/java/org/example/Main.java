package org.example;

import DAO.*;
import Exceptions.UserNotFoundException;
import Model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Iniciando variáveis
        CreateID criarid = new CreateID();
        Edicao_has_ParticipanteDAO ehp = new Edicao_has_ParticipanteDAO();
        VencedoresDAO v = new VencedoresDAO();
        EstruturaDAO es = new EstruturaDAO();
        UsersDAO u = new UsersDAO();
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
                                        e.selectVencedoresTodasEdicoes();
                                        break;
                                }
                                break;
                            case 3:
                                p.selectParticipante();
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
                    try{
                        u.loginUser(user, senha);
                        System.out.println("Login Completo!");
                        System.out.println("Selecione a função:\n(1)Adicionar\n(2)Deletar\n(3)Editar");
                        int choice_adm = entrada.nextInt();

                        switch (choice_adm){
                            case 1:
                                //Adicionar
                                do{
                                    System.out.println("O que deseja adicionar?:\n(1)Batalha\n(2)Edição\n(3)Participante");
                                    choice_adm = entrada.nextInt();
                                    if(choice_adm != 1 && choice_adm!=2 && choice_adm!=3)
                                        System.out.println("Entrada incorreta, tente novamente.");
                                }while(choice_adm != 1 && choice_adm!=2 && choice_adm!=3);

                                switch (choice_adm){
                                    case 1:
                                        int cobertura = 0;
                                        int microfone = 0;
                                        System.out.print("Nome da batalha: ");
                                        entrada.nextLine();
                                        String nome = entrada.nextLine();
                                        System.out.print("Local da batalha: ");
                                        String local = entrada.nextLine();
                                        System.out.print("Estado da batalha: ");
                                        String estado = entrada.nextLine();
                                        System.out.print("Dia da semana: ");
                                        String diadasemana = entrada.nextLine();
                                        //Criando o id da batalha automaticamente
                                        int id = criarid.CreateBatalhaId();
                                        do{
                                            System.out.println("A Batalha tem cobertura? (contra chuva) (1)Sim (2)Não");
                                            choice_adm = entrada.nextInt();
                                            if(choice_adm != 1 && choice_adm!=2)
                                                System.out.println("Entrada incorreta, tente novamente.");
                                        }while(choice_adm != 1 && choice_adm!=2);
                                        if(choice_adm == 1)
                                            cobertura = 1;

                                        do{
                                            System.out.println("A batalha fornece microfone? (1)Sim (2)Não");
                                            choice_adm = entrada.nextInt();
                                            if(choice_adm != 1 && choice_adm!=2)
                                                System.out.println("Entrada incorreta, tente novamente.");
                                        }while(choice_adm != 1 && choice_adm!=2);
                                        if(choice_adm == 1)
                                            microfone = 1;
                                        //Criando a batalha e a estrutura com as informaões fornecidas
                                        Batalha baux = new Batalha(nome, local, estado, diadasemana, id);
                                        b.insertBatalha(baux, user, senha);
                                        Estrutura estaux = new Estrutura(cobertura, microfone, id);
                                        es.insertEstrutura(estaux, user, senha);
                                        break;
                                    case 2:
                                        System.out.println("Id da Batalha: ");
                                        int idBatlha = entrada.nextInt();
                                        //Criando o ID da edição automaticamente
                                        int idEdicao = criarid.CreateEdicaoId();
                                        System.out.println("Data: ");
                                        String data = entrada.nextLine();
                                        System.out.println("Número da edição: ");
                                        int numEdicao = entrada.nextInt();
                                        Edicao editAux = new Edicao(idEdicao, numEdicao, data, idBatlha);
                                        e.insertEdicao(editAux);

                                        //Inserindo participantes em uma edição (total 16) identificados por Id
                                        System.out.println("Adicione os participantes dessa Batalha (ID): ");
                                        for (int i = 0; i < 16; i++) {
                                            System.out.print("Id do participante "+ i + ": ");
                                            int idParticipante = entrada.nextInt();
                                            ehp.insertParticipanteOnEdicao(idEdicao, idParticipante, user, senha);
                                        }

                                        System.out.print("Id do participante vencedor: ");
                                        int idParticipantevencedor = entrada.nextInt();
                                        int idVencedores = criarid.CreateVencedorId();
                                        v.insertVencedores(idVencedores, idEdicao, idParticipantevencedor, user, senha);
                                        System.out.println("Edição criada com sucesso!");
                                        break;
                                    case 3:
                                        //Criando um novo participante
                                        System.out.print("Nome: ");
                                        entrada.nextLine();
                                        String nomeP = entrada.nextLine();
                                        System.out.print("Vulgo: ");
                                        String vulgo = entrada.nextLine();
                                        System.out.print("Idade: ");
                                        int idade = entrada.nextInt();
                                        System.out.print("Estado: ");
                                        entrada.nextLine();
                                        String estadoP = entrada.nextLine();

                                        Participante paux = new Participante(nomeP, vulgo, idade, estadoP, criarid.CreateParticipanteId());
                                        p.insertParticipante(paux);
                                        break;
                                }
                                break;
                            case 2:
                                //Deletar
                                do{
                                    System.out.println("O que deseja deletar?:\n(1)Batalha\n(2)Edição\n(3)Participante");
                                    choice_adm = entrada.nextInt();
                                    if(choice_adm != 1 && choice_adm!=2 && choice_adm!=3)
                                        System.out.println("Entrada incorreta, tente novamente.");
                                }while(choice_adm != 1 && choice_adm!=2 && choice_adm!=3);
                                switch (choice_adm){
                                    case 1:
                                        System.out.print("Qual o ID da Batalha que deseja deletar?: ");
                                        int idBatalha = entrada.nextInt();
                                        b.deleteBatalha(idBatalha, user, senha);
                                        System.out.println("Batalha deletada com sucesso!");
                                        break;
                                    case 2:
                                        System.out.print("Digite o número da edição: ");
                                        int numEdicao = entrada.nextInt();
                                        System.out.println("Id da batalha: ");
                                        int idbatalha = entrada.nextInt();
                                        e.deleteEdicao(numEdicao, idbatalha);
                                        System.out.println("Edição deletada com sucesso!");
                                        break;
                                    case 3:
                                        System.out.println("Atenção, ao realizar esta ação, irá retirar o participante de todas as\nbatalhas que o mesmo ja participou.");
                                        System.out.print("Digite o ID do participante: ");
                                        int pid = entrada.nextInt();
                                        p.deleteParticipante(pid, user, senha);
                                        System.out.println("Participante deletado com sucesso!");
                                        break;
                                }
                                break;
                            case 3:
                                //Editar
                                System.out.println("O que deseja editar?:\n(1)Batalha\n(2)Edição");
                                break;

                        }
                    }catch (UserNotFoundException e1){
                        System.out.println(e1.getMessage());
                    }

                    break;
                case 3:
                    running = false;
                    break;
            }
    }
    }
}
