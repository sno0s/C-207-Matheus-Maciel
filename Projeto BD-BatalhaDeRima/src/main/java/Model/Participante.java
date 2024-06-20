package Model;

public class Participante {
    //Atributos
    private static int idParticipante;
    private String nome;
    private int idade;
    private String vulgo;
    private String estado;

    //Construtores
    public Participante(String nome, String vulgo, int idade, String estado) {
        this.nome = nome;
        this.vulgo = vulgo;
        this.idade = idade;
        this.estado = estado;
        idParticipante+=1;
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
