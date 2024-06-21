package Model;

public class Edicao {
    //Atributos
    private int idEdicao;
    private int numEdicao;
    private String data;
    private int idBatalha;
    //Construtores
    public Edicao(int idEdicao, int numEdicao, String data, int idBatalha) {
        this.idEdicao = idEdicao;
        this.numEdicao = numEdicao;
        this.data = data;
        this.idBatalha = idBatalha;
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

    public int getIdBatalha() {
        return idBatalha;
    }

    public void setIdBatalha(int idBatalha) {
        this.idBatalha = idBatalha;
    }
}
