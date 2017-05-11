package letter.love.speler;

import letter.love.kaart.Kaart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maarteng on 11/05/2017.
 */
public class Handkaarten {
    List<Kaart> kaarten;

    public Handkaarten() {
        this.kaarten = new ArrayList<>();
    }

    public void voegKaartToe(Kaart kaart) {
        if (kaarten.size() >= 2) {
            throw new IndexOutOfBoundsException("Een speler kan maximaal 2 handkaarten hebben");
        }
        kaarten.add(kaart);
    }
}
