package letter.love;

import com.sun.javafx.binding.StringFormatter;
import letter.love.kaart.Kaart;
import letter.love.spel.Spel;
import letter.love.speler.Speler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Scanner;

public class CommandLineApp {

    private Scanner scanner = new Scanner(System.in);


    CommandLineApp() {
        do {
            int aantalSpelers = vraagAantalSpelers();
            int aantalComputers = vraagAantalComputers(aantalSpelers);
            speelSpel(aantalSpelers, aantalComputers);
        } while (vraagNogEenSpel());
    }

    private int vraagAantalComputers(int aantalSpelers) {
        int aantalComputers;
        do {
            System.out.println(StringFormatter.format("Hoeveel van de spelers zijn computers? (maximum %s): ", aantalSpelers));
            aantalComputers = scanner.nextInt();
            if (aantalComputers > 4 || aantalComputers < 2 || aantalComputers > aantalSpelers) System.out.println("Minimum 2, maximum 4 spelers");
        } while (aantalComputers > 4 || aantalComputers < 2 || aantalComputers > aantalSpelers);
        return aantalComputers;
    }

    private int vraagAantalSpelers() {
        int aantal;
        do {
            System.out.println("Voor hoeveel spelers is dit spel? (min 2, max 4): ");
            aantal = scanner.nextInt();
            if (aantal > 4 || aantal < 2) System.out.println("Minimum 2, maximum 4 spelers");
        } while (aantal > 4 || aantal < 2);
        return aantal;
    }

    private boolean vraagNogEenSpel() {
        System.out.println("Wil je nog een spelletje spelen?: ");
        String nogEenSpel = scanner.next();
        return ("Y".equals(nogEenSpel) || "y".equals(nogEenSpel) || "j".equals(nogEenSpel) || "J".equals(nogEenSpel));
    }

    private void speelSpel(int aantalSpelers, int aantalComputers) {
        Spel spel = new Spel(aantalSpelers, aantalComputers);
        do {
            spel.startBeurt();
            Kaart teSpelenKaart = bepaalTeSpelenKaart();

        } while (!spel.isGameOver());
    }

    private Kaart bepaalTeSpelenKaart() {
        throw new NotImplementedException();
    }
}
