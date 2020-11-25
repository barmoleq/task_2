package model;

import java.util.List;

public class Sheep {
    private List<Node> nodeList;
    private int lvl;

    public Sheep(List<Node> nodeList, int lvl) {
        this.nodeList = nodeList;
        this.lvl = lvl;
    }

    public int score(){
        if (lvl == 0) {
            return 10;
        }if(lvl == 1){
            return 30;
        }if(lvl == 2){
            return 50;
        } else return -1;
    }

    public boolean isAlive(){
        for (Node c : nodeList) {
            if (c.getStatus() == Status.SHEEPONE || c.getStatus() == Status.SHEEPTWO) {
                return true;
            }
        }
        return false;
    }

    public boolean isAliveBoss(){
        for(Node c : nodeList){
            if(c.getStatus() == Status.SHEEPBOSS){
                return true;
            }
        }
        return false;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }
}

