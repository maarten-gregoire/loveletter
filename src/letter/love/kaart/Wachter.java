package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

import static letter.love.kaart.KaartType.WACHTER;

public class Wachter extends Kaart {
    public Wachter() {
        super(WACHTER, "Wachter", 1, "Raad de hand van een speler", 5);
    }

    @Override
    void voerActieUit(VoerActieUit voerActieUit) {

    }
}
