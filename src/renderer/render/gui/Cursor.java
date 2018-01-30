/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render.gui;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import input.MouseInfo;
import logic.Mode;
import logic.graphs.Graphs;
import logic.graphs.elements.Node;
import renderer.render.Nodes;
import renderer.render.Shapes;
import renderer.render.Text;

/**
 *
 * @author xabab
 */
public class Cursor {

    private static GL2 gl = null;                                                                                       //and word of the day is "static"

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    public static void draw(){
                if((gl == null) || (MouseInfo.getX() < 90)) return;                                                     //TODO remove magic constant
        switch(Mode.getMode()){ //NODE_ADD, NODE_DELETE, PATH_ADD, PATH_ADD_SEC, SEL_STA, SEL_END, ALG

            case NODE_ADD:
                if(Graphs.Field.nodeInRadiusB(MouseInfo.getX(), MouseInfo.getY(), Graphs.Field.DIST_BETW_NODES))
                        gl.glColor3f(0.7f, 0.7f, 0.7f);                                                      //gray
                else Nodes.applyColor(Node.defaultColor);
                Shapes.drawCircle(MouseInfo.getX() - 10, MouseInfo.getY() - 10, 5, 8);
                break;

            case NODE_DELETE:
                Text.textWeight(MouseInfo.getX() - 12, MouseInfo.getY() - 10, "X", 1, 0, 0);
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
                Text.textWeight(MouseInfo.getX() - 12, MouseInfo.getY() - 10, "W", 1, 1, 1);
                break;
            case SEL_STA:
                gl.glColor3f(0, 1, 0);
                Shapes.drawCircle(MouseInfo.getX() - 10, MouseInfo.getY() - 10, 5, 8);
                break;
            case SEL_END:
                gl.glColor3f(1, 0, 0);
                Shapes.drawCircle(MouseInfo.getX() - 10, MouseInfo.getY() - 10, 5, 8);
                break;
            case PROC:

                break;
        }
    }
}
