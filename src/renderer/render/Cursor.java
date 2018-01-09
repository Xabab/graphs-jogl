/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import input.MouseInfo;
import logic.Mode;
import static logic.Mode.MODE.*;
import logic.graphs.Graphs;

/**
 *
 * @author xabab
 */
public class Cursor {

    static GL2 gl = null;                                                       //and word of the day is "static"

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    public static void draw(){
                if(gl == null || MouseInfo.getX() < 90) return;
        switch(Mode.getMode()){ //NODE_ADD, NODE_DELETE, PATH_ADD, PATH_ADD_SEC, SEL_STA, SEL_END, ALG

            case NODE_ADD:
                if(Graphs.nodeInRadiusB(MouseInfo.getX(), MouseInfo.getY(), Graphs.DIST_BETW_NODES))
                        gl.glColor3f(0.7f, 0.7f, 0.7f);                         //gray
                else gl.glColor3f(1f, 1f, 0.4f);                                //yellow-ish
                Shapes.drawCircle(MouseInfo.getX() - 10, MouseInfo.getY() - 10, 5, 8);
                break;

            case NODE_DELETE:
                gl.glColor3f(1f, 0f, 0f);
                Shapes.drawLine(MouseInfo.getX() - 15, MouseInfo.getY() - 5,
                               MouseInfo.getX() -5,    MouseInfo.getY() - 15, 5f);
                Shapes.drawLine(MouseInfo.getX() - 15, MouseInfo.getY() - 15,
                               MouseInfo.getX() - 5,   MouseInfo.getY() - 5, 5f);
                break;

            case PATH_ADD:
                gl.glColor3f(0f, 0.7f, 0f);
                Shapes.drawLine(MouseInfo.getX() - 15, MouseInfo.getY() - 5,
                               MouseInfo.getX() -5,    MouseInfo.getY() - 15, 5f);
                break;
            case PATH_ADD_SEC:
                gl.glColor3f(0.3f, 1f, 0.3f);
                Shapes.drawLine(MouseInfo.getX() - 15, MouseInfo.getY() - 5,
                               MouseInfo.getX() -5,    MouseInfo.getY() - 15, 5f);
                break;
            case WEIGHT_ADD:
                gl.glColor3f(1f, 0, 0);
                Shapes.drawLine(MouseInfo.getX() - 15, MouseInfo.getY() - 5,
                               MouseInfo.getX() -5,    MouseInfo.getY() - 15, 5f);
                break;
            case SEL_STA:
                break;
            case SEL_END:
                break;
            case PROC:
                break;
        }
    }
}
