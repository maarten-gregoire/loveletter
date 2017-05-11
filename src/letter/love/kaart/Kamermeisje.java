package letter.love.kaart;

import static letter.love.kaart.KaartType.KAMERMEISJE;

public class Kamermeisje extends Kaart {
    public Kamermeisje() {
        super(KAMERMEISJE, "Kamermeisje", 4, "Bescherming tot je volgende beurt", 2);
    }

    @Override
    void voerActieUit() {

    }
}
