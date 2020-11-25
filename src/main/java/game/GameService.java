package game;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    public void setField(Player player) {
        Field field = player.getField();
        int w = field.getWidth();
        int h = field.getHeight();

        Node[][] node = new Node[h][w];
        List<Node> list = new ArrayList<Node>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                node[i][j] = new Node(j, i);
                list.add(node[i][j]);
            }
        }
        field.setField(node);
        field.setListOfNode(list);
    }

    public void setSheep(Field field, List<Sheep> listOfSheep) {
        List<Node> listOfRandom = new ArrayList<Node>(field.getListOfNode());
        int level = 0;
        for (int i = 0; i < 3; i++) {//ответает за уровень овцы
            for (int j = 0; j < 9 - level*4; j++) {//отвечает за количество овец
                List<Node> listOfNode = getListOfSheepNode(listOfRandom);
                if (listOfNode != null) {
                    listOfSheep.add(new Sheep(listOfNode, level));
                    for (Node c : listOfNode) {
                        if(level == 0){
                            c.setStatus(Status.SHEEPONE);}
                        if(level == 1){
                            {c.setStatus(Status.SHEEPTWO);}
                        }if(level == 2) {
                            c.setStatus(Status.SHEEPBOSS);
                        }
                    }
                    removeNode(field, listOfRandom, listOfNode);
                }
            }
            level++;
        }
    }

    private List<Node> getListOfSheepNode(List<Node> listOfRandom) {
        List<Node> listOfNodeX = new ArrayList<Node>();
        Node node;
        if (listOfRandom.size() > 0) {
            node = listOfRandom.get(random(0, listOfRandom.size()));
        } else {
            return null;
        }
        listOfNodeX.add(node);
        return listOfNodeX;
    }

    //удаляем клетки, уже занятые овцами
    private void removeNode(Field field, List<Node> listOfRandom, List<Node> listOfNode) {
        for (Node c : listOfNode) {
            int x = c.getX();
            int y = c.getY();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Node node = field.getNode(x + i, y + j);
                    listOfRandom.remove(node);
                }
            }
        }
    }

    public boolean alive(Player player) {
        for (Sheep sheep : player.getListOfSheep()) {
            if (sheep.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public boolean aliveBoss(Player player) {
        for (Sheep sheep : player.getListOfSheep()) {
            if (sheep.isAliveBoss()) {
                return true;
            }
        }
        return false;
    }

    //удаляем клетки, которые можно не проверять
    private void killAround(Player player, Sheep sheep) {
        for (Node c : sheep.getNodeList()) {
            int x = c.getX();
            int y = c.getY();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Node node = player.getField().getNode(x + i, y + j);
                    if (player.getField().getListOfNode().contains(node)) {
                        player.getField().removeNode(node);
                        if (node.getStatus() == Status.EMPTY) {
                            node.setStatus(Status.MISS);
                        }
                    }
                }
            }
        }
    }

    private boolean shot(Player player) {
        Node node = player.getField().getListOfNode().remove(random(0, player.getField().getListOfNode().size()));
        if (node.getStatus() == Status.SHEEPONE || node.getStatus() == Status.SHEEPTWO || node.getStatus() == Status.SHEEPBOSS) {
            node.setStatus(Status.KILL);
            for (Sheep s : player.getListOfSheep()) {
                if (s.getNodeList().contains(node)) {
                    if (!s.isAlive()) {
                        killAround(player, s);
                        player.setScore(s.score());
                    }
                }
            }
            return true;
        } else if (node.getStatus() == Status.EMPTY) {
            node.setStatus(Status.MISS);
        }
        return false;
    }

    public boolean gameShot(Game game) {
        if (alive(game.getPlayer1()) && alive(game.getPlayer2())) {
            if (game.getStep()) {
                if (!shot(game.getPlayer2())) {
                    game.setStep(false);
                }
            } else {
                if (!shot(game.getPlayer1())) {
                    game.setStep(true);
                }
            }
        }
        return game.getStep();
    }


    private int random(int a, int b) {
        return (int) (Math.random() * (b - a)) + a;
    }

}
