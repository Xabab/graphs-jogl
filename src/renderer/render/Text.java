/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;
import renderer.Window;

/**
 *
 * @author xabab
 */
public class Text {
    static TextRenderer button = new TextRenderer(new Font("SansSerif", Font.PLAIN, 10));
    static TextRenderer weihgt = new TextRenderer(new Font("SansSerif", Font.CENTER_BASELINE, 15));



    public static void textButton(int x, int y, String text, float cR, float cG, float cB){
        button.setColor(cR, cG, cB, 1f);
        button.beginRendering(Window.X, Window.Y);
        button.draw(text, x, Window.Y - y);
        button.endRendering();
    }

    public static void textWeight(int x, int y, String text, float cR, float cG, float cB){
        weihgt.setColor(cR, cG, cB, 1f);
        weihgt.beginRendering(Window.X, Window.Y);
        weihgt.draw(text, x - 5, Window.Y - y - 5);
        weihgt.endRendering();
    }
}
