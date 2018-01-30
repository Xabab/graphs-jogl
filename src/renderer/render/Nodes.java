/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import logic.graphs.Graphs;
import logic.graphs.elements.Decor;
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
        for(int i = 0; i < Graphs.Field.getNodeArrSize(); i++){
            if(Graphs.Field.getNode(i).getDecor() == Decor.DEC_TYPE.GLOW) {
                applyDecorColor(Graphs.Field.getNode(i).getDecorColor());
                Shapes.drawCircle(Graphs.Field.getNode(i).getX(), Graphs.Field.getNode(i).getY(), (int) (Node.SIZE * 1.3), 16);
            }

            if(Graphs.getStart() == Graphs.Field.getNode(i)) Graphs.Field.getNode(i).setColor(Decor.COLOR.GREEN);
            if(Graphs.getFinish() == Graphs.Field.getNode(i)) Graphs.Field.getNode(i).setColor(Decor.COLOR.RED);

            applyColor(Graphs.Field.getNode(i).getColor());
            //if(Graphs.Field.getNode(i) == null) continue;                                                                     //hack
            Shapes.drawCircle(Graphs.Field.getNode(i).getX(), Graphs.Field.getNode(i).getY(), Node.SIZE, 16);
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
        for (int i = 0; i < Graphs.Field.getNodeArrSize(); i++) {
            n = Graphs.Field.getNode(i);
            if(n.getCost() == 1000000) {
                Text.textWeight(n.getX(), n.getY(), "inf", 0, 0, 0);
            }
            else {
                Text.textWeight(n.getX(), n.getY(), Integer.toString(n.getCost()), 0, 0, 0);
            }
        }
    }
}
