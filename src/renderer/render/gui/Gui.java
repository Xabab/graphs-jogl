/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render.gui;

import logic.Mode;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import logic.graphs.Graphs;
import renderer.render.Shapes;
import renderer.render.Text;

public class Gui {
    private static GL2 gl = null;
    private Gui(){}

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    private static final Button []menu = {
            new Button(10, 10, 60, 20, .7f, .7f, 0f, "Add Node") {
                public void onClick() { Mode.setMode(Mode.MODE.NODE_ADD); }
            },
            new Button(10, 40, 60, 20, 0.8f, 0f, 0f, "Del Node") {
                public void onClick() { Mode.setMode(Mode.MODE.NODE_DELETE); }
            },
            new Button(10, 70, 60, 20, 0f, 0.2f, 0f, "Add Path") {
                public void onClick() { Mode.setMode(Mode.MODE.PATH_ADD); }
            },
            new Button(10, 110, 60, 20, 0f, .7f, 0f, "Sel Srt") {
                public void onClick() { Mode.setMode(Mode.MODE.SEL_STA); }
            },
            new Button(10, 140, 60, 20, .9f, 0f, 0f, "Sel End") {
                public void onClick() { Mode.setMode(Mode.MODE.SEL_END); }
            },
            new Button(10, 180, 60, 20, 0f, 0f, 0f, "Start") {
                public void onClick() { Graphs.findPath(); }
            }
    };

    public static void draw(){
        if (gl == null) return;
        for(Button b: menu){
            gl.glColor3f(b.get_cR(), b.get_cG(), b.get_cB());
            Shapes.drawBox(b.get_posX(), b.get_posY(), b.get_sizeX(), b.get_sizeY());
            Text.textButton(b.get_posX() + 3, b.get_posY() + 15, b.get_title(), 1, 1, 1);
        }
    }

    public static Button[] getMenu(){
        return menu;
    }
}
