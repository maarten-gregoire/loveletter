package letter.love.speler;

import letter.love.kaart.Kaart;

public class Speler {

    Handkaarten handkaarten;

    public void geefKaart(Kaart kaart) {
        this.handkaarten.voegKaartToe(kaart);
    }
}
