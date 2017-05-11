package letter.love;

public class Spel {
    private int totaalAantalSpelers;
    private int aantalComputers;

    KaartenStapel kaartSpel;
    KaartenStapel afneemStapel;
    KaartenStapel aflegStapel;
    KaartenStapel ongebruikteKaarten;

    public Spel (int totaalAantalSpelers, int aantalComputers) {
        if (totaalAantalSpelers < 2 || totaalAantalSpelers > 4) {
            throw new IllegalArgumentException("Minimum 2, maximum5 spelers.");
        }
        if (aantalComputers > totaalAantalSpelers) {
            throw new IllegalArgumentException("Er kunnen niet meer computers zijn dan er spelers in totaal zijn.");
        }
        this.totaalAantalSpelers = totaalAantalSpelers;
        this.aantalComputers = aantalComputers;
    }

}
