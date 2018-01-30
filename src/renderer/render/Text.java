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
    private static final TextRenderer button = new TextRenderer(new Font("SansSerif", Font.PLAIN, 10));
    private static final TextRenderer weight = new TextRenderer(new Font("SansSerif", Font.BOLD, 15));



    public static void textButton(int x, int y, String text, float cR, float cG, float cB){
        button.setColor(cR, cG, cB, 1f);
        button.beginRendering(Window.X, Window.Y);
        button.draw(text, x, Window.Y - y);
        button.endRendering();
        button.flush();
    }

    public static void textWeight(int x, int y, String text, float cR, float cG, float cB){
        weight.setColor(cR, cG, cB, 1f);
        weight.beginRendering(Window.X, Window.Y);
        weight.draw(text, x - 5, Window.Y - y - 5);
        weight.endRendering();
        weight.flush();
    }
}
