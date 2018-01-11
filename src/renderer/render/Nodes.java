/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import logic.graphs.Graphs;
import logic.graphs.elements.Node;

/**
 *
 * @author xabab
 */
public class Nodes {
    private static GL2 gl = null;

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    public static void drawDecor(){
        if(gl == null) return;
        //TODO

    }

    public static void drawNodes(){
        if(gl == null) return;

        for(int i = 0; i < Graphs.getNodeArrSize(); i++){
            switch(Graphs.getNode(i).getColor()){ //WHITE, RED, ORANGE, GREEN
                case WHITE:
                    gl.glColor3f(1f, 1f, 1f);
                    break;
                case RED:
                    gl.glColor3f(1f, 0f, 0f);
                    break;
                case ORANGE:
                    gl.glColor3f(1f, 1f, 0.4f);
                    break;
                case GREEN:
                    gl.glColor3f(0f, 1f, 0f);
                    break;
            }
            if(Graphs.getStart() == Graphs.getNode(i)) gl.glColor3f(0f, 1f, 0f);
            if(Graphs.getFinish() == Graphs.getNode(i)) gl.glColor3f(1f, 0f, 0f);
            if(Graphs.getNode(i) == null) continue;                                                                     //hack
            Shapes.drawCircle(Graphs.getNode(i).getX(), Graphs.getNode(i).getY(), Node.SIZE, 16);
        }


    }
    public static void drawCosts() {
        if (gl == null) return;
        Node n;
        for (int i = 0; i < Graphs.getNodeArrSize(); i++) {
            n = Graphs.getNode(i);
            if(n.getCost() == 1000000) {
                Text.textWeight(n.getX(), n.getY(), "inf", 0, 0, 0);
            }
            else {
                Text.textWeight(n.getX(), n.getY(), Integer.toString(n.getCost()), 0, 0, 0);
            }
        }
    }
}
