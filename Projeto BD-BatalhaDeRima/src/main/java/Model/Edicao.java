package Model;

public class Edicao {
    //Atributos
    private int idEdicao;
    private int numEdicao;
    private String data;
    private String Batalha_nome;
    //Construtores
    public Edicao(int idEdicao, int numEdicao, String data, String batalha_nome) {
        this.idEdicao = idEdicao;
        this.numEdicao = numEdicao;
        this.data = data;
        Batalha_nome = batalha_nome;
    }
    public Edicao() {
    }

    //Getters & Setters (depois apagar os que não estão sendo usados)
    public int getIdEdicao() {
        return idEdicao;
    }

    public void setIdEdicao(int idEdicao) {
        this.idEdicao = idEdicao;
    }

    public int getNumEdicao() {
        return numEdicao;
    }

    public void setNumEdicao(int numEdicao) {
        this.numEdicao = numEdicao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBatalha_nome() {
        return Batalha_nome;
    }

    public void setBatalha_nome(String batalha_nome) {
        Batalha_nome = batalha_nome;
    }
}
