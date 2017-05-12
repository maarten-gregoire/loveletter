package letter.love.spel;

import letter.love.kaart.Kaart;
import letter.love.kaartenstapel.KaartenStapel;
import letter.love.speler.Speler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    private int actieveSpelerIndex;

    private Speler winnaar;

    private boolean spelBezig = false;

    public Spel(int totaalAantalSpelers, int aantalComputers) {
        startNieuwSpel(totaalAantalSpelers, aantalComputers);
    }

    public void startNieuwSpel(int totaalAantalSpelers, int aantalComputers) {
        valideerTotaalAantalSpelers(totaalAantalSpelers);
        valideerAantalComputers(totaalAantalSpelers, aantalComputers);
        initialiseerAantallen(totaalAantalSpelers, aantalComputers);
        initialiseerSpelers();
        initialiseerKaartStapels();
        this.actieveSpelerIndex = -1;
        spelBezig = true;

    }

    public void speelBeurt() {
        Speler speler = bepaalSpeler();

        //TODO: implement speel

        if(spelTenEinde()) {
            spelBezig = false;
            winnaar = bepaalWinnaar();
        }
    }

    private Speler bepaalSpeler() {
        actieveSpelerIndex++;
        if (actieveSpelerIndex > spelers.size()-1) {
            actieveSpelerIndex = 0;
        }
        return this.spelers.get(actieveSpelerIndex);
    }

    private Speler bepaalWinnaar() {
       if (eenLaatsteSpelerBlijftOver()) {
           return bepaalLaatsteSpeler();
       }

       List<Speler> spelersMetHoogsteKaart = bepaalSpelersMetHoogsteKaart();
        if (spelersMetHoogsteKaart.size() > 1) {
            return spelersMetHoogsteKaart.get(0);
        }
        return bepaalSpelerMetHoogsteWaardeGespeeldeKaarten(spelersMetHoogsteKaart);
    }

    private Speler bepaalSpelerMetHoogsteWaardeGespeeldeKaarten(List<Speler> spelers) {
        Comparator<Speler> comparator = Comparator.comparing(Speler::getTotaleWaardeGespeeldeKaarten);
        return spelers.stream().max(comparator).orElse(null);
    }

    private List<Speler> bepaalSpelersMetHoogsteKaart() {
        Kaart hoogsteKaart = bepaalHoogsteKaart();
        return spelers.stream().filter(speler -> speler.getHuidigeKaart().equals(hoogsteKaart)).collect(Collectors.toList());
    }

    private Kaart bepaalHoogsteKaart() {
        Comparator<Speler> comparator = Comparator.comparing(Speler::getKaartWaarde);
        return spelers.stream().max(comparator).orElse(null).getHuidigeKaart();
    }

    private Speler bepaalLaatsteSpeler() {
        return spelers.stream().filter(speler -> !speler.heeftKaarten()).collect(Collectors.toList()).get(0);
    }

    private Speler bepaalSpelerMetHoogsteKaart() {
        Comparator<Speler> comparator = Comparator.comparing(Speler::getKaartWaarde);
        return spelers.stream().max(comparator).orElse(null);
    }

    private boolean spelTenEinde() {
        return (eenLaatsteSpelerBlijftOver() || kaartenZijnOp());
    }

    private boolean eenLaatsteSpelerBlijftOver() {
        return spelers.stream().filter(speler -> !speler.heeftKaarten()).count() == 1;
    }

    private boolean kaartenZijnOp() {

        return afneemStapel.isLeeg();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spel spel = (Spel) o;

        if (totaalAantalSpelers != spel.totaalAantalSpelers) return false;
        if (aantalComputers != spel.aantalComputers) return false;
        if (actieveSpelerIndex != spel.actieveSpelerIndex) return false;
        if (spelBezig != spel.spelBezig) return false;
        if (kaartSpel != null ? !kaartSpel.equals(spel.kaartSpel) : spel.kaartSpel != null) return false;
        if (afneemStapel != null ? !afneemStapel.equals(spel.afneemStapel) : spel.afneemStapel != null) return false;
        if (aflegStapel != null ? !aflegStapel.equals(spel.aflegStapel) : spel.aflegStapel != null) return false;
        if (zichtbareOngebruikteKaarten != null ? !zichtbareOngebruikteKaarten.equals(spel.zichtbareOngebruikteKaarten) : spel.zichtbareOngebruikteKaarten != null)
            return false;
        if (onzichtbareOngebruikteKaart != null ? !onzichtbareOngebruikteKaart.equals(spel.onzichtbareOngebruikteKaart) : spel.onzichtbareOngebruikteKaart != null)
            return false;
        if (spelers != null ? !spelers.equals(spel.spelers) : spel.spelers != null) return false;
        return winnaar != null ? winnaar.equals(spel.winnaar) : spel.winnaar == null;
    }

    @Override
    public int hashCode() {
        int result = totaalAantalSpelers;
        result = 31 * result + aantalComputers;
        result = 31 * result + (kaartSpel != null ? kaartSpel.hashCode() : 0);
        result = 31 * result + (afneemStapel != null ? afneemStapel.hashCode() : 0);
        result = 31 * result + (aflegStapel != null ? aflegStapel.hashCode() : 0);
        result = 31 * result + (zichtbareOngebruikteKaarten != null ? zichtbareOngebruikteKaarten.hashCode() : 0);
        result = 31 * result + (onzichtbareOngebruikteKaart != null ? onzichtbareOngebruikteKaart.hashCode() : 0);
        result = 31 * result + (spelers != null ? spelers.hashCode() : 0);
        result = 31 * result + actieveSpelerIndex;
        result = 31 * result + (winnaar != null ? winnaar.hashCode() : 0);
        result = 31 * result + (spelBezig ? 1 : 0);
        return result;
    }
}
