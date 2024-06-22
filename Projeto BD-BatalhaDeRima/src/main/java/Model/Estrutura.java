package Model;

public class Estrutura {
    private int coberto;
    private int microfone;
    private int idBatalha;

    public Estrutura(int coberto, int microfone, int idBatalha) {
        this.coberto = coberto;
        this.microfone = microfone;
        this.idBatalha = idBatalha;
    }

    public Estrutura() {
    }

    public int getCoberto() {
        return coberto;
    }

    public void setCoberto(int coberto) {
        this.coberto = coberto;
    }

    public int getMicrofone() {
        return microfone;
    }

    public void setMicrofone(int microfone) {
        this.microfone = microfone;
    }

    public int getIdBatalha() {
        return idBatalha;
    }

    public void setIdBatalha(int idBatalha) {
        this.idBatalha = idBatalha;
    }
}
