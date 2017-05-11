package letter.love;

import java.util.*;

public class KaartenStapel {
    private List<Kaart> kaarten = new ArrayList<>();

    public void schudKaarten() {
        long seed = System.nanoTime();
        Collections.shuffle(this.kaarten, new Random(seed));
    }

    public void voegKaartenToe(Kaart ...kaarten) {
        this.kaarten.addAll(Arrays.asList(kaarten));
    }

    public Kaart neemBovensteKaart() {
        Kaart genomenKaart = this.kaarten.get(kaarten.size()-1);
        this.kaarten.remove(genomenKaart);
        return genomenKaart;
    }
}
