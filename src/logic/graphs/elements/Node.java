/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.graphs.elements;

import renderer.render.Decor;

/**
 *
 * @author xabab
 */
public class Node extends Decor {
    public static final int SIZE = 15;

    private Node parent;
    private final int _x;
    private final int _y;
    public static int INF = 1000000;
    private int cost = INF;

    static public final Decor.COLOR defaultColor = COLOR.YELLOW;

    public Node(int x, int y){
        super.setColor(defaultColor);
        _x = x;
        _y = y;
    }

    public int getX(){
        return _x;
    }

    public int getY(){
        return _y;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Node getParent(){
        return parent;
    }
    public void setParent(Node p){
        parent = p;
    }
}
