package Model;

public class Edicao_has_Participante {
    private int idEdicao;
    private int idParticipante;

    //Construtores
    public Edicao_has_Participante(int idEdicao, int idParticipante) {
        this.idEdicao = idEdicao;
        this.idParticipante = idParticipante;
    }
    public Edicao_has_Participante() {
    }

    //Getters & Setters
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
