package letter.love.kaart;

public enum KaartType {
    WACHTER(1, 5),
    PRIESTER(2, 2),
    BARON(3, 2),
    KAMERMEISJE(4, 2),
    PRINS(5, 2),
    KONING(6, 1),
    GRAVIN(7, 1),
    PRINSES(8, 1);

    int waarde;
    int aantalKaartenInHetSpel;

    KaartType(int waarde, int aantalKaartenInHetSpel) {
        this.waarde = waarde;
        this.aantalKaartenInHetSpel = aantalKaartenInHetSpel;
    }
}
