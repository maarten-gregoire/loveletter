package letter.love;

public class Spel {
    int totaalAantalSpelers;
    int aantalComputers;

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
