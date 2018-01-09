/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render.gui.buttons;

/**
 *
 * @author xabab
 */
public class ButtonStart extends Button{

    public ButtonStart(int posX, int posY, int sizeX, int sizeY, float cR, float cG, float cB) {
        super(posX, posY, sizeX, sizeY, cR, cG, cB);
    }

    public ButtonStart(int posX, int posY, int sizeX, int sizeY, float cR, float cG, float cB, String title) {
        super(posX, posY, sizeX, sizeY, cR, cG, cB, title);
    }

    @Override
    public void onClick() {

    }

}
