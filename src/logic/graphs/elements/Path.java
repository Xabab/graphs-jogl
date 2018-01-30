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
public class Path extends Decor {
    private final Node _from;
    private final Node _to;
    private int weight;

    static public final Decor.COLOR defaultColor = COLOR.ORANGE;


    public Path(Node from, Node to){
        _from = from;
        _to = to;
        super.setColor(defaultColor);
    }

    public void setWeight(int w){
        weight = w;
    }

    public Node getFrom(){
        return _from;
    }

    public Node getTo(){
        return _to;
    }

    public int getWeight(){
        return weight;
    }


}
