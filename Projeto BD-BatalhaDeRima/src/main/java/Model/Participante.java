package Model;

public class Participante {
    //Atributos
    private int idParticipante;
    private String nome;
    private int idade;
    private String vulgo;
    private String estado;

    //Construtores
    public Participante(String nome, String vulgo, int idade, String estado, int idParticipante) {
        this.nome = nome;
        this.vulgo = vulgo;
        this.idade = idade;
        this.estado = estado;
        this.idParticipante = idParticipante;
    }
    public Participante() {
    }

    //Getters & Setters
    public String getNome() {
        return nome;
    }

    public String getVulgo() {
        return vulgo;
    }

    public int getIdade() {
        return idade;
    }

    public int getParticipanteId() {
        return idParticipante;
    }

    public String getEstado() {
        return estado;
    }
}
