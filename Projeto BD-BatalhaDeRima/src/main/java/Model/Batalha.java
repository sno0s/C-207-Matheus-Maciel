package Model;

public class Batalha {
    private String nome;
    private String local;
    private String diaDaSemana;
    private int idBatalha;

    public Batalha(String nome, String local, String diaDaSemana, int idBatalha) {
        this.nome = nome;
        this.local = local;
        this.diaDaSemana = diaDaSemana;
        this.idBatalha = idBatalha;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public int getId() {
        return idBatalha;
    }
}
