package Model;

public class Edicao {
    private Batalha batalha;
    private Participante participante;
    private String nomeBatalha;
    private int idParticipante;
    private int idVencedores;
    private static int numEdicao;

    public Edicao(Batalha batalha, Participante participante) {
        this.batalha = batalha;
        this.participante = participante;
        this.nomeBatalha = batalha.getNome();
        this.idParticipante = participante.getParticipanteId();


        numEdicao+=1;
    }
}
