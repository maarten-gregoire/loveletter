package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

import static letter.love.kaart.KaartType.KONING;

public class Koning extends Kaart {
    public Koning() {
        super(KONING, "Koning", 5, "Ruil handen", 1);
    }


    @Override
    void voerActieUit(VoerActieUit voerActieUit) {

    }
}
