package Model;

public class Batalha {
    private String nome;
    private String local;
    private String estado;
    private String diaDaSemana;
    private int idBatalha;


    public Batalha(String nome, String local, String estado, String diaDaSemana, int idBatalha) {
        this.nome = nome;
        this.local = local;
        this.estado = estado;
        this.diaDaSemana = diaDaSemana;
        this.idBatalha = idBatalha;
    }

    public Batalha() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
