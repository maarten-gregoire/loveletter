package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

import static letter.love.kaart.KaartType.BARON;

public class Baron extends Kaart {
    public Baron() {
        super(BARON, "Baron", 3, "Vergelijk handen, lagere hand is afgewezen", 2);
    }

    @Override
    void voerActieUit(VoerActieUit voerActieUit) {

    }
}
