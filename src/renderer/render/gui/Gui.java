/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render.gui;

import renderer.render.gui.buttons.ButtonNodeAdd;
import renderer.render.gui.buttons.ButtonSelectStart;
import renderer.render.gui.buttons.ButtonNodeDelete;
import renderer.render.gui.buttons.ButtonSelectEnd;
import renderer.render.gui.buttons.ButtonPathAdd;
import renderer.render.gui.buttons.Button;
import renderer.render.gui.buttons.ButtonStart;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import renderer.render.Shapes;
import renderer.render.Text;

public class Gui {

    private static Shapes sh;
    private static GL2 gl = null;
    private Gui(){}

    public static void init(GLAutoDrawable d){
        gl = d.getGL().getGL2();
    }

    static final Button []menu = {
        new ButtonNodeAdd       (10, 10, 60, 20, .7f, .7f, 0f, "Add Node"),
        new ButtonNodeDelete    (10, 40, 60, 20, 0.8f, 0f, 0f, "Del Node"),
        new ButtonPathAdd       (10, 70, 60, 20, 0f, 0.2f, 0f, "Add Path"),
        new ButtonSelectStart  (10, 110, 60, 20, 0f, .7f, 0f, "Sel Srt"),
        new ButtonSelectEnd    (10, 140, 60, 20, .9f, 0f, 0f, "Sel End"),
        new ButtonStart        (10, 180, 60, 20, 0f, 0f, 0f, "Start")
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
