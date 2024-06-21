package Model;

public class Estrutura {
    private boolean coberto;
    private boolean microfone;
    private int idBatalha;

    public Estrutura(boolean coberto, boolean microfone, int idBatalha) {
        this.coberto = coberto;
        this.microfone = microfone;
        this.idBatalha = idBatalha;
    }

    public Estrutura() {
    }

    public boolean isCoberto() {
        return coberto;
    }

    public void setCoberto(boolean coberto) {
        this.coberto = coberto;
    }

    public boolean isMicrofone() {
        return microfone;
    }

    public void setMicrofone(boolean microfone) {
        this.microfone = microfone;
    }

    public int getIdBatalha() {
        return idBatalha;
    }

    public void setIdBatalha(int idBatalha) {
        this.idBatalha = idBatalha;
    }
}
