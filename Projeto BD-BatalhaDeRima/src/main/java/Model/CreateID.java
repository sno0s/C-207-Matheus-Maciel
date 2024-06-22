package Model;
import java.util.concurrent.ThreadLocalRandom;
public class CreateID {

    public static int CreateBatalhaId(){
        int min = 00001;
        int max = 99999;
        int id = ThreadLocalRandom.current().nextInt(min, max + 1);
        return id;
    }

    public static int CreateEdicaoId(){
        int min = 000001;
        int max = 999999;
        int id = ThreadLocalRandom.current().nextInt(min, max + 1);
        return id;
    }
    public static int CreateParticipanteId(){
        int min = 000001;
        int max = 999999;
        int id = ThreadLocalRandom.current().nextInt(min, max + 1);
        return id;
    }
    public static int CreateVencedorId(){
        int min = 0000001;
        int max = 9999999;
        int id = ThreadLocalRandom.current().nextInt(min, max + 1);
        return id;
    }
}
