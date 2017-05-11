package letter.love.spel;

import letter.love.kaart.Kaart;
import letter.love.kaartenstapel.KaartSpel;
import letter.love.kaartenstapel.KaartenStapel;
import letter.love.speler.Speler;

import java.util.ArrayList;
import java.util.List;

import static letter.love.kaartenstapel.KaartSpel.eenKaartSpel;
import static letter.love.kaartenstapel.KaartenStapel.eenKaartenStapel;

public class Spel {
    private int totaalAantalSpelers;
    private int aantalComputers;

    private KaartenStapel kaartSpel;
    private KaartenStapel afneemStapel;
    private KaartenStapel aflegStapel;
    private KaartenStapel zichtbareOngebruikteKaarten;
    private Kaart onzichtbareOngebruikteKaart;

    private List<Speler> spelers;

    public Spel(int totaalAantalSpelers, int aantalComputers) {
        startNieuwSpel(totaalAantalSpelers, aantalComputers);
    }

    public void startNieuwSpel(int totaalAantalSpelers, int aantalComputers) {
        valideerTotaalAantalSpelers(totaalAantalSpelers);
        valideerAantalComputers(totaalAantalSpelers, aantalComputers);
        initialiseerAantallen(totaalAantalSpelers, aantalComputers);
        initialiseerSpelers();
        initialiseerKaartStapels();
    }

    private void initialiseerSpelers() {
        this.spelers = new ArrayList<>();
        for (int i = 0; i < totaalAantalSpelers; i++) {
            this.spelers.add(new Speler());
        }
    }

    private void initialiseerKaartStapels() {
        initialiseerKaartSpel();
        initialiseerOngebruikteKaarten();
        verdeelHandkaarten();
        initialiseerAfneemStapel();
        initialiseerAflegStapel();
    }

    private void verdeelHandkaarten() {
        spelers.forEach(speler -> speler.geefKaart(this.kaartSpel.neemBovensteKaart()));
    }

    private void initialiseerAflegStapel() {
        this.aflegStapel = eenKaartenStapel();
    }

    private void initialiseerAfneemStapel() {
        this.afneemStapel = eenKaartenStapel();
        this.afneemStapel.voegKaartenToe(kaartSpel.neemAlleKaarten());
    }

    private void initialiseerOngebruikteKaarten() {
        this.onzichtbareOngebruikteKaart = kaartSpel.neemBovensteKaart();
        this.zichtbareOngebruikteKaarten = eenKaartenStapel();
        if (this.totaalAantalSpelers == 2) {
            this.zichtbareOngebruikteKaarten.voegKaartenToe(this.kaartSpel.neemBovensteKaarten(3));
        }
    }

    private void initialiseerKaartSpel() {
        this.kaartSpel = eenKaartSpel();
        this.kaartSpel.schudKaarten();
    }

    private void initialiseerAantallen(int totaalAantalSpelers, int aantalComputers) {
        this.totaalAantalSpelers = totaalAantalSpelers;
        this.aantalComputers = aantalComputers;
    }

    private void valideerAantalComputers(int totaalAantalSpelers, int aantalComputers) {
        if (aantalComputers > totaalAantalSpelers) {
            throw new IllegalArgumentException("Er kunnen niet meer computers zijn dan er spelers in totaal zijn.");
        }
    }

    private void valideerTotaalAantalSpelers(int totaalAantalSpelers) {
        if (totaalAantalSpelers < 2 || totaalAantalSpelers > 4) {
            throw new IllegalArgumentException("Minimum 2, maximum5 spelers.");
        }
    }

}
