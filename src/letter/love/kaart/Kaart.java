package letter.love.kaart;

public abstract class Kaart {
    int id;
    final KaartType type;
    final String naam;
    final int waarde;

    Kaart(KaartType type, String naam, int waarde) {
        this.type = type;
        this.naam = naam;
        this.waarde = waarde;
    }
}
