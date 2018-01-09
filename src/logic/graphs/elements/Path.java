/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.graphs.elements;

/**
 *
 * @author xabab
 */
public class Path extends Decor{
    Node _from = null;
    Node _to = null;
    int weight;

    public Path(Node from, Node to){
        _from = from;
        _to = to;
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