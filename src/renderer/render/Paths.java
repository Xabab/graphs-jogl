/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import logic.graphs.Graphs;


public class Paths {
    private static GL2 gl = null;

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    public static void drawDecor(){
        if(gl == null) return;
        //TODO

    }

    public static void drawPaths(){
        if(gl == null) return;

        for(int i = 0; i < Graphs.getPathArrSize(); i++){
            gl.glColor3f(0f, .5f, 0f);
            Shapes.drawLine(
                    Graphs.getPath(i).getFrom().getX(),
                    Graphs.getPath(i).getFrom().getY(),
                    Graphs.getPath(i).getTo().getX(),
                    Graphs.getPath(i).getTo().getY(),
                    3f //not working ffs
            );
        }

    }

    public static void drawWeights() {
        for(int i = 0; i < Graphs.getPathArrSize(); i++){
            if(Graphs.getPath(i) == Graphs.getWeightPathTemp()) continue;
            Text.textWeight(
                    (Graphs.getPath(i).getFrom().getX() + Graphs.getPath(i).getTo().getX())/2,
                    (Graphs.getPath(i).getFrom().getY() + Graphs.getPath(i).getTo().getY())/2,
                    Integer.toString(Graphs.getPath(i).getWeight()), 1, 1, 1);
        }
    }
}
