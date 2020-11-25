
import game.*;
import ui.*;

public class Main {

    public static void main(String[] args) {
        GameService service = new GameService();
        Game game = new Game();
        GameFrame gf = new GameFrame(game, service);
        gf.setVisible(true);
    }

}