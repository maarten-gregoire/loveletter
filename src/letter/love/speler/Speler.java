package letter.love.speler;

import letter.love.kaart.Kaart;
import letter.love.kaartenstapel.KaartenStapel;

import java.util.ArrayList;

public class Speler {

    private Handkaarten handkaarten;
    private KaartenStapel aflegStapel;
    private String naam;

    public Speler() {
        this.handkaarten = new Handkaarten();
    }

    public void geefKaart(Kaart kaart) {
        this.handkaarten.voegKaartToe(kaart);
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public boolean heeftKaarten() {
        return this.handkaarten.bevatKaarten();
    }

    public int getKaartWaarde() {
        return handkaarten.getKaartWaarde();
    }

    public void speelKaart(Kaart kaart) {
        aflegStapel.voegKaartenToe(handkaarten.speelKaart(kaart));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speler speler = (Speler) o;

        if (handkaarten != null ? !handkaarten.equals(speler.handkaarten) : speler.handkaarten != null) return false;
        if (aflegStapel != null ? !aflegStapel.equals(speler.aflegStapel) : speler.aflegStapel != null) return false;
        return naam != null ? naam.equals(speler.naam) : speler.naam == null;

    }

    @Override
    public int hashCode() {
        int result = handkaarten != null ? handkaarten.hashCode() : 0;
        result = 31 * result + (aflegStapel != null ? aflegStapel.hashCode() : 0);
        result = 31 * result + (naam != null ? naam.hashCode() : 0);
        return result;
    }
}
