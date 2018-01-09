/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render.gui.buttons;

import logic.Mode;
import static logic.Mode.MODE.NODE_ADD;

/**
 *
 * @author xabab
 */
public class ButtonNodeAdd  extends Button{
    public ButtonNodeAdd(int sizeX, int sizeY, int posX, int posY, float cR, float cG, float cB) {
        super(sizeX, sizeY, posX, posY, cR, cG, cB);
    }

    public ButtonNodeAdd(int sizeX, int sizeY, int posX, int posY, float cR, float cG, float cB, String title) {
        super(sizeX, sizeY, posX, posY, cR, cG, cB, title);
    }

    @Override
    public void onClick() {
        Mode.setMode(NODE_ADD);
    }

}
