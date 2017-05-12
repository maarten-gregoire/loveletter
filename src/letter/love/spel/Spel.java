package letter.love.spel;

import letter.love.kaart.Kaart;
import letter.love.kaartenstapel.KaartenStapel;
import letter.love.speler.Computer;
import letter.love.speler.Mens;
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
    private int aantalMensen;

    private KaartenStapel kaartSpel;
    private KaartenStapel afneemStapel;
    private KaartenStapel aflegStapel;
    private KaartenStapel zichtbareOngebruikteKaarten;
    private Kaart onzichtbareOngebruikteKaart;

    private List<Speler> spelers;
    private int actieveSpelerIndex;
    private Speler actieveSpeler;

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

    public void startBeurt() {
        actieveSpeler = bepaalSpeler();
        geefKaartVanAfneemStapelAanActieveSpeler();
        //TODO: implement speel


    }

    public Speler getActieveSpeler() {
        return actieveSpeler;
    }

    public boolean isGameOver() {
        if(spelTenEinde()) {
            spelBezig = false;
            winnaar = bepaalWinnaar();
        }
        return spelBezig;
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
        for (int i = 0; i < aantalComputers; i++) {
            this.spelers.add(new Computer());
        }
        for (int i = aantalComputers; i < totaalAantalSpelers; i++) {
            this.spelers.add(new Mens());
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
        this.aantalMensen = totaalAantalSpelers - aantalComputers;
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

    public void geefKaartVanAfneemStapelAanActieveSpeler() {
        spelers.get(actieveSpelerIndex).geefKaart(afneemStapel.neemBovensteKaart());
    }
}
