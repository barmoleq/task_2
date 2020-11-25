package model;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Node[][] field;
    private int n, m;
    private List<Node> listOfNode = new ArrayList<Node>();

    public Field(int n, int m) {
        this.m = m;
        this.n = n;
    }


    public Field() {
        m = n = 10;
    }

    public int getHeight() {
        return n;
    }

    public int getWidth() {
        return m;
    }

    public void setField(Node[][] field) {
        this.field = field;
    }

    public void setListOfNode(List<Node> listOfNode) {
        this.listOfNode = listOfNode;
    }

    public List<Node> getListOfNode() {
        return listOfNode;
    }

    //проверка
    public Node getNode(int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return null;
        }
        return field[y][x];
    }

    public void removeNode(int index) {
        listOfNode.remove(index);

    }

    public void removeNode(Node node) {
        listOfNode.remove(node);
    }

}