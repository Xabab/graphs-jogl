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


public class Paths {
    private static GL2 gl = null;

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    public static void drawPaths(){
        if(gl == null) return;

        for(int i = 0; i < Graphs.Field.getPathArrSize(); i++){
            if(Graphs.Field.getPath(i).getDecor() == Decor.DEC_TYPE.GLOW) {
                applyDecorColor(Graphs.Field.getPath(i).getDecorColor());
                Shapes.drawLine(
                        Graphs.Field.getPath(i).getFrom().getX(),
                        Graphs.Field.getPath(i).getFrom().getY(),
                        Graphs.Field.getPath(i).getTo().getX(),
                        Graphs.Field.getPath(i).getTo().getY(),
                        Node.SIZE
                );
            }

            applyColor(Graphs.Field.getPath(i).getColor());

            Shapes.drawLine(
                    Graphs.Field.getPath(i).getFrom().getX(),
                    Graphs.Field.getPath(i).getFrom().getY(),
                    Graphs.Field.getPath(i).getTo().getX(),
                    Graphs.Field.getPath(i).getTo().getY(),
                    3f
            );

            /*
            double sin = abs((Graphs.getPath(i).getFrom().getX() - Graphs.getPath(i).getTo().getX()) /
                    (Graphs.getPath(i).getFrom().getY() - Graphs.getPath(i).getTo().getY()));
            double cos = abs((Graphs.getPath(i).getFrom().getY() - Graphs.getPath(i).getTo().getY()) /
                    (Graphs.getPath(i).getFrom().getX() - Graphs.getPath(i).getTo().getX()));
            Shapes.drawLine(

            )*/
        }

    }

    private static void applyDecorColor(Decor.COLOR col) {
        switch(col){
            case WHITE:
                gl.glColor3f(0.7f, 0.7f, 0.7f);
                break;
            case RED:
                gl.glColor3f(0.7f, 0.2f, 0.2f);
                break;
            case ORANGE:
                gl.glColor3f(0.7f, 0.7f, 0.2f);
                break;
            case GREEN:
                gl.glColor3f(0.2f, 0.7f, 0.2f);
                break;
            case YELLOW:
                gl.glColor3f(0.7f, 0.7f, 0.2f);
                break;
            case BLACK:
                gl.glColor3f(0f, 0f, 0f);
        }
    }

    private static void applyColor(Decor.COLOR col) {
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

    public static void drawWeights() {
        for(int i = 0; i < Graphs.Field.getPathArrSize(); i++){
            if(Graphs.Field.getPath(i) == Graphs.Field.getWeightPathTemp()) continue;
            Text.textWeight(
                    (Graphs.Field.getPath(i).getFrom().getX() + Graphs.Field.getPath(i).getTo().getX())/2,
                    (Graphs.Field.getPath(i).getFrom().getY() + Graphs.Field.getPath(i).getTo().getY())/2,
                    Integer.toString(Graphs.Field.getPath(i).getWeight()), 1, 1, 1);
        }
    }
}
