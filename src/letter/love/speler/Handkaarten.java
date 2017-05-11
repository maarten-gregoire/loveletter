package letter.love.speler;

import letter.love.kaart.Kaart;

import java.util.ArrayList;
import java.util.List;

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

    public boolean bevatKaarten() {
        return !kaarten.isEmpty();
    }

    public int getKaartWaarde() {
        return kaarten.get(0).getWaarde();
    }

    public Kaart speelKaart(Kaart kaart) {
        kaarten.remove(kaart);
        return kaart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Handkaarten that = (Handkaarten) o;

        return kaarten != null ? kaarten.equals(that.kaarten) : that.kaarten == null;

    }

    @Override
    public int hashCode() {
        return kaarten != null ? kaarten.hashCode() : 0;
    }
}
