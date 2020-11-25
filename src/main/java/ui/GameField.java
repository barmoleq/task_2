package ui;

import game.*;
import model.*;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private Game game;
    private int player;
    private int h, w;
    private int cellSize;

    public GameField(Game game, int player, int cellSize) {
        this.game = game;
        this.player = player;
        this.cellSize = cellSize;
        h = (player == 0) ? game.getPlayer1().getField().getHeight() : game.getPlayer2().getField().getHeight();
        w = (player == 0) ? game.getPlayer1().getField().getWidth() : game.getPlayer2().getField().getWidth();
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // решетка
        for (int i = 0; i < h + 1; i++) {
            g.drawLine(0, i * cellSize, w * cellSize, i * cellSize);
        }
        for (int i = 0; i < w + 1; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, h * cellSize);
        }

        //элементы
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                drawCell(g, i, j);
            }
        }
    }

    private Color ColorOfCell(Status state) {
        switch (state) {
            case EMPTY:
            case MISS:
                return new Color(52, 153, 17);
            case SHEEPONE:
                return new Color(153, 147, 0);
            case SHEEPTWO:
                return new Color(13, 15, 153);
            case SHEEPBOSS:
                return new Color(153, 8, 125);
            case KILL:
                return new Color(150, 10, 15);

        }
        return Color.black;
    }


    private void drawCell(Graphics g, int i, int j) {
        Status state;
        if (player == 0) {
            state = game.getPlayer1().getField().getNode(i, j).getStatus();
        } else {
            state = game.getPlayer2().getField().getNode(i, j).getStatus();
        }
        g.setColor(ColorOfCell(state));
        g.fillRect(i * cellSize , j * cellSize , cellSize, cellSize);
        if (state == Status.MISS || state == Status.KILL) {
            drawX(g, i, j);
        }

    }

    private void drawX(Graphics g, int i, int j) {
        g.setColor(Color.black);
        int a = cellSize / 10;
        g.drawLine(i * cellSize + a, j * cellSize + a, (i + 1) * cellSize - a, (j + 1) * cellSize - a);
        g.drawLine(i * cellSize + a, (j + 1) * cellSize - a, (i + 1) * cellSize - a, j * cellSize + a);
    }
}
