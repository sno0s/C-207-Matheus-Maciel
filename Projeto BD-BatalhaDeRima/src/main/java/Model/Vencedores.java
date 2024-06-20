package Model;

public class Vencedores {
    private int idVencedores;
    private int idEdicao;
    private int idParticipante;

    //Construtores
    public Vencedores(int idVencedores, int idEdicao, int idParticipante) {
        this.idVencedores = idVencedores;
        this.idEdicao = idEdicao;
        this.idParticipante = idParticipante;
    }
    public Vencedores() {
    }

    //Getters & Setters
    public int getIdVencedores() {
        return idVencedores;
    }

    public void setIdVencedores(int idVencedores) {
        this.idVencedores = idVencedores;
    }

    public int getIdEdicao() {
        return idEdicao;
    }

    public void setIdEdicao(int idEdicao) {
        this.idEdicao = idEdicao;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }
}
