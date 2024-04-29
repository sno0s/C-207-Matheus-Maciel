package Model;

public class Participante {
    private String nome;
    private String vulgo;
    private String idade;
    private String estado;
    private int participanteId;
    private static int id;


    public Participante(String nome, String vulgo, String idade, String estado) {
        this.nome = nome;
        this.vulgo = vulgo;
        this.idade = idade;
        this.estado = estado;
        id+=1;
        this.participanteId = this.id;
    }

    public String getNome() {
        return nome;
    }

    public String getVulgo() {
        return vulgo;
    }

    public String getIdade() {
        return idade;
    }

    public int getParticipanteId() {
        return participanteId;
    }

    public String getEstado() {
        return estado;
    }
}
