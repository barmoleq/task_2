package game;

import model.Player;

public class Game {
    private Player player1;
    private Player player2;
    private boolean step = true;

    public boolean getStep() {
        return step;
    }

    public void setStep(boolean step) {
        this.step = step;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Game() {
        GameService service = new GameService();

        this.player1 = new Player(0);
        this.player2 = new Player(0);
        service.setField(player1);
        service.setField(player2);
        service.setSheep(player1.getField(), player1.getListOfSheep());
        service.setSheep(player2.getField(), player2.getListOfSheep());
    }
}
