package model;

public class Node {
    private int x;
    private int y;
    private Status status;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        status = Status.EMPTY;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Status getStatus (){
        return status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
