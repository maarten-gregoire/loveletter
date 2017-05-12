package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

public class Prinses extends Kaart {
    public Prinses() {
        super(KaartType.PRINSES, "Prinses", 8, "Verlies als je haar aflegt", 1);
    }

    @Override
    void voerActieUit(VoerActieUit voerActieUit) {

    }
}
