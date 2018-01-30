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

import static java.lang.Math.*;


public class Paths {
    private static GL2 gl = null;

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    public static void drawPaths(){
        if(gl == null) return;

        int fromX;
        int fromY;
        int toX;
        int toY;


        for(int i = 0; i < BellmanFord.Graph.getPathArrSize(); i++){

            fromX = BellmanFord.Graph.getPath(i).getFrom().getX();
            fromY = BellmanFord.Graph.getPath(i).getFrom().getY();
            toX = BellmanFord.Graph.getPath(i).getTo().getX();
            toY = BellmanFord.Graph.getPath(i).getTo().getY();

            if(BellmanFord.Graph.getPath(i).getDecor() == Decor.DEC_TYPE.GLOW) {


                applyDecorColor(BellmanFord.Graph.getPath(i).getDecorColor());
                Shapes.drawLine(
                        fromX,
                        fromY,
                        toX,
                        toY,
                        Node.SIZE
                );
            }

            applyColor(BellmanFord.Graph.getPath(i).getColor());

            Shapes.drawLine(
                    fromX,
                    fromY,
                    toX,
                    toY,
                    3f
            );

            /*

            double direction;

            if((toY - fromY) == 0) {
                if ((toX - fromX) > 0) direction = 0;
                else direction = PI;

            }
            else direction = -Math.atan((toX - fromX) / (toY - fromY) ) + PI/2;



            Shapes.drawTriangle(
                    (int)(toX - Node.SIZE * cos(direction)),
                    (int)(toY - Node.SIZE * sin(direction)),
                    (int)(toX - 3*Node.SIZE * cos(direction + PI/8)),
                    (int)(toY - 3*Node.SIZE * sin(direction + PI/8)),
                    (int)(toX - 3*Node.SIZE * cos(direction - PI/8)),
                    (int)(toY - 3*Node.SIZE * sin(direction - PI/8))
            );

            */


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
        for(int i = 0; i < BellmanFord.Graph.getPathArrSize(); i++){
            if(BellmanFord.Graph.getPath(i) == BellmanFord.Graph.getWeightPathTemp()) continue;
            Text.textWeight(
                    (BellmanFord.Graph.getPath(i).getFrom().getX() + BellmanFord.Graph.getPath(i).getTo().getX())/2,
                    (BellmanFord.Graph.getPath(i).getFrom().getY() + BellmanFord.Graph.getPath(i).getTo().getY())/2,
                    Integer.toString(BellmanFord.Graph.getPath(i).getWeight()), 1, 1, 1);
        }
    }
}
