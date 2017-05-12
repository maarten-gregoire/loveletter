package letter.love;

import letter.love.spel.Spel;

public class CommandLineApp {

    Spel spel;

    public CommandLineApp() {
        spel = new Spel(4, 3);
        spel.speelBeurt();
    }
}
