package ui;

import game.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private Game game;
    private GameService gameService;
    private Button buttonShot;
    private Button buttonGame;
    private JLabel jl;
    private JLabel jl1;
    private JLabel jl2;
    private int nodeSize = 40;

    public GameFrame(Game game, GameService service) {
        this.game = game;
        this.gameService = service;
        setup();
    }

    private void setup() {
        this.setTitle("Волки и Овцы");
        jl = new JLabel("Ход Овец");
        jl1 = new JLabel("Счет овец: " + game.getPlayer2().getScore());
        jl2 = new JLabel("Счет волков: " + game.getPlayer1().getScore());
        buttonShot = new Button("Поиск");
        buttonGame = new Button("Перезапуск");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.setResizable(false);

        GameField gameField1 = new GameField(this.game, 0, nodeSize);
        int f1W = gameField1.getW() * nodeSize + 1;
        int f1H = gameField1.getH() * nodeSize + 1;
        gameField1.setBounds(20, 50, f1W, f1H);
        this.add(gameField1);

        GameField gameField2 = new GameField(this.game, 1, nodeSize);
        int f2W = gameField2.getW() * nodeSize + 1;
        int f2H = gameField2.getH() * nodeSize + 1;
        gameField2.setBounds(f1W + 40, 50, f2W, f2H);
        this.add(gameField2);

        this.setBounds(200, 100, f1W + f2W + 70, Math.max(f1H, f2H) + 100);
        panel.add(jl1);
        panel.add(buttonShot);
        panel.add(buttonGame);
        buttonShot.setBounds(20, 200, 70, 20);
        buttonGame.setBounds(20, 200, 70, 20);
        panel.add(jl2);
        panel.add(jl);
        this.add(panel);


        buttonShot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameService.gameShot(game);
                if (game.getStep()) {
                    jl.setText("Ход Овец");
                    jl1.setText("Счет овец: " + game.getPlayer2().getScore());
                    jl2.setText("Счет волков: " + game.getPlayer1().getScore());
                } else {
                    jl.setText("Ход Волков");
                    jl1.setText("Счет овец: " + game.getPlayer2().getScore());
                    jl2.setText("Счет волков: " + game.getPlayer1().getScore());
                }
                if (!gameService.alive(game.getPlayer1()) ||
                        !gameService.alive(game.getPlayer2()) ||
                        !gameService.aliveBoss(game.getPlayer1()) ||
                        !gameService.aliveBoss(game.getPlayer2())) {
                    buttonShot.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame f=new JFrame("Конец игры");
                            final JLabel label = new JLabel("КОНЕЦ");
                            label.setBounds(180,180, 200,50);
                            f.add(label);
                            f.setSize(400,400);
                            f.setLocationRelativeTo(null);
                            f.setLayout(null);
                            f.setVisible(true);
                        }
                    });
                    if (game.getPlayer1().getScore() > game.getPlayer2().getScore()) {
                        jl.setText("Волки выиграли");
                    }if (game.getPlayer1().getScore() < game.getPlayer2().getScore()) {
                        jl.setText("Овцы выиграли");
                    } else {
                        jl.setText("Ничья");
                    }
                }
                repaint();
            }
        });

        buttonGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                game = new Game();
                newGame();
            }
        });

    }

    private void newGame() {
//        this = new GameFrame(new Game(), gameService);
        GameFrame gf = new GameFrame(new Game(), gameService);
        gf.setVisible(true);
        dispose();
//        setup();
    }
}