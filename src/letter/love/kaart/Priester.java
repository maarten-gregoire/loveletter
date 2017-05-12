package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

import static letter.love.kaart.KaartType.PRIESTER;

public class Priester extends Kaart {
    public Priester() {
        super(PRIESTER, "Priester", 2, "Bekijk een hand", 2);
    }


    @Override
    void voerActieUit(VoerActieUit voerActieUit) {

    }
}
