/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import logic.graphs.BellmanFord;
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

    public static void drawNodes(){
        if(gl == null) return;
        for(int i = 0; i < BellmanFord.Graph.getNodeArrSize(); i++){
            if(BellmanFord.Graph.getNode(i).getDecor() == Decor.DEC_TYPE.GLOW) {
                applyDecorColor(BellmanFord.Graph.getNode(i).getDecorColor());
                Shapes.drawCircle(BellmanFord.Graph.getNode(i).getX(), BellmanFord.Graph.getNode(i).getY(), (int) (Node.SIZE * 1.3), 16);
            }

            if(BellmanFord.getStart() == BellmanFord.Graph.getNode(i)) BellmanFord.Graph.getNode(i).setColor(Decor.COLOR.GREEN);
            if(BellmanFord.getFinish() == BellmanFord.Graph.getNode(i)) BellmanFord.Graph.getNode(i).setColor(Decor.COLOR.RED);

            applyColor(BellmanFord.Graph.getNode(i).getColor());
            //if(BellmanFord.Graph.getNode(i) == null) continue;                                                                     //hack
            Shapes.drawCircle(BellmanFord.Graph.getNode(i).getX(), BellmanFord.Graph.getNode(i).getY(), Node.SIZE, 16);
        }


    }

    private static void applyDecorColor(Decor.COLOR col) {
        switch(col){
            case WHITE:
                gl.glColor3f(1f, 1f, 1f);
                break;
            case RED:
                gl.glColor3f(1f, 0.2f, 0.2f);
                break;
            case ORANGE:
                gl.glColor3f(1f, 0.7f, 0.2f);
                break;
            case GREEN:
                gl.glColor3f(0.2f, 1f, 0.2f);
                break;
            case YELLOW:
                gl.glColor3f(1f, 1f, 0.2f);
                break;
            case BLACK:
                gl.glColor3f(0f, 0f, 0f);
        }
    }

    public static void applyColor(Decor.COLOR col) {
        switch(col){
            case WHITE:
                gl.glColor3f(1f, 1f, 1f);
                break;
            case RED:
                gl.glColor3f(1f, 0f, 0f);
                break;
            case ORANGE:
                gl.glColor3f(1f, 0.5f, 0f);
                break;
            case GREEN:
                gl.glColor3f(0f, 1f, 0f);
                break;
            case YELLOW:
                gl.glColor3f(1f, 1f, 0f);
                break;
            case BLACK:
                gl.glColor3f(0f, 0f, 0f);
        }
    }

    public static void drawCosts() {
        if (gl == null) return;
        Node n;
        for (int i = 0; i < BellmanFord.Graph.getNodeArrSize(); i++) {
            n = BellmanFord.Graph.getNode(i);
            if(n.getCost() > 900000) {
                Text.textWeight(n.getX(), n.getY(), "inf", 0, 0, 0);
            }
            else {
                Text.textWeight(n.getX(), n.getY(), Integer.toString(n.getCost()), 0, 0, 0);
            }
        }
    }
}
