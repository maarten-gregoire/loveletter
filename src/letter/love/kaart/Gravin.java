package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

import static letter.love.kaart.KaartType.GRAVIN;

public class Gravin extends Kaart {
    public Gravin() {
        super(GRAVIN, "Gravin", 7, "Leg af als samen met koning of prins", 1);
    }

    @Override
    void voerActieUit(VoerActieUit voerActieUit) {


    }
}
