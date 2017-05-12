package letter.love.kaart;

import letter.love.voeractieuit.VoerActieUit;

public abstract class Kaart {
    int id;
    final KaartType type;
    final String naam;
    final int waarde;
    final String actieBeschrijving;
    final int aantalInSpel;

    Kaart(KaartType type, String naam, int waarde, String actieBeschrijving, int aantalInSpel) {
        this.type = type;
        this.naam = naam;
        this.waarde = waarde;
        this.actieBeschrijving = actieBeschrijving;
        this.aantalInSpel = aantalInSpel;
    }

    abstract void voerActieUit(VoerActieUit voerActieUit);

    public int getWaarde() {
        return waarde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kaart kaart = (Kaart) o;

        if (id != kaart.id) return false;
        if (waarde != kaart.waarde) return false;
        if (aantalInSpel != kaart.aantalInSpel) return false;
        if (type != kaart.type) return false;
        if (naam != null ? !naam.equals(kaart.naam) : kaart.naam != null) return false;
        return actieBeschrijving != null ? actieBeschrijving.equals(kaart.actieBeschrijving) : kaart.actieBeschrijving == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (naam != null ? naam.hashCode() : 0);
        result = 31 * result + waarde;
        result = 31 * result + (actieBeschrijving != null ? actieBeschrijving.hashCode() : 0);
        result = 31 * result + aantalInSpel;
        return result;
    }
}
