package letter.love.kaartenstapel;

import letter.love.kaart.*;
import letter.love.kaartenstapel.KaartenStapel;

import java.util.Arrays;

public class KaartSpel extends KaartenStapel {
    public KaartSpel() {
        voegKaartenToe(
                new Wachter(),
                new Wachter(),
                new Wachter(),
                new Wachter(),
                new Wachter(),
                new Baron(),
                new Baron(),
                new Priester(),
                new Priester(),
                new Kamermeisje(),
                new Kamermeisje(),
                new Prins(),
                new Prins(),
                new Koning(),
                new Gravin(),
                new Prinses()
        );
        schudKaarten();
    }

    public static KaartSpel eenKaartSpel() {
        return new KaartSpel();
    }

}
