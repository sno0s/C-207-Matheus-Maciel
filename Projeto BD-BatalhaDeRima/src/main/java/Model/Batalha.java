package Model;

public class Batalha {
    private String nome;
    private String local;
    private String diaDaSemana;
    private static int idBatalha;

    public Batalha(String nome, String local, String diaDaSemana) {
        this.nome = nome;
        this.local = local;
        this.diaDaSemana = diaDaSemana;
        idBatalha+=1;
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

    public static int getId() {
        return idBatalha;
    }
}
