package letter.love.kaartenstapel;

import letter.love.kaart.Kaart;

import java.util.*;

public class KaartenStapel {
    private List<Kaart> kaarten = new ArrayList<>();

    public void schudKaarten() {
        long seed = System.nanoTime();
        Collections.shuffle(this.kaarten, new Random(seed));
    }

    public static KaartenStapel eenKaartenStapel() {
        return new KaartenStapel();
    }

    public void voegKaartenToe(Kaart ...kaarten) {
        this.kaarten.addAll(Arrays.asList(kaarten));
    }

    public void voegKaartenToe(List<Kaart> kaarten) {
        this.kaarten.addAll(kaarten);
    }

    public Kaart neemBovensteKaart() {
        Kaart genomenKaart = this.kaarten.get(kaarten.size()-1);
        this.kaarten.remove(genomenKaart);
        return genomenKaart;
    }

    public List<Kaart> neemBovensteKaarten(int aantal) {
        int aantalKaarten = this.kaarten.size();
        List<Kaart> genomenKaarten = this.kaarten.subList(aantalKaarten-aantal, aantalKaarten-1);
        this.kaarten.removeAll(genomenKaarten);
        return genomenKaarten;
    }

    public List<Kaart> neemAlleKaarten() {
        kaarten = this.kaarten;
        this.kaarten.clear();
        return kaarten;
    }

    public boolean isLeeg() {
        return kaarten.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KaartenStapel that = (KaartenStapel) o;

        return kaarten != null ? kaarten.equals(that.kaarten) : that.kaarten == null;

    }

    @Override
    public int hashCode() {
        return kaarten != null ? kaarten.hashCode() : 0;
    }
}
