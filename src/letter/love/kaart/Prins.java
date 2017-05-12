package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

import static letter.love.kaart.KaartType.PRINS;

public class Prins extends Kaart {
    public Prins() {
        super(PRINS, "Prins", 4, "Eén speler legt zijn hand af", 2);
    }

    @Override
    void voerActieUit(VoerActieUit voerActieUit) {

    }
}
