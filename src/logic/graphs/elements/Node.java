/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.graphs.elements;

import static logic.graphs.elements.Decor.COLOR.ORANGE;

/**
 *
 * @author xabab
 */
public class Node extends Decor{

    public static final int SIZE = 15;

    int _x;
    int _y;

    public Node(int x, int y){
        super.col= ORANGE;
        _x = x;
        _y = y;
    }

    public int getX(){
        return _x;
    }

    public int getY(){
        return _y;
    }

}
