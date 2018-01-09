/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import logic.Mode;
import static logic.Mode.MODE.*;
import logic.graphs.Graphs;
import renderer.render.gui.Gui;
import renderer.render.gui.buttons.Button;

/**
 *
 * @author xabab
 */
public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if( MouseInfo.getX() < 90){                                             //if not adding path at the moment
            if (Mode.getMode() != PATH_ADD_SEC && Mode.getMode() != WEIGHT_ADD){
                for(Button b: Gui.getMenu()){
                    b.checkForClick();
                }
            }
        }
        else{
            //switch: mode
            //  case mode: do things
            switch(Mode.getMode()){ //NODE_ADD, NODE_DELETE, PATH_ADD, PATH_ADD_SEC, SEL_STA, SEL_END, ALG
                case NODE_ADD:
                    Graphs.addNode(e.getX(), e.getY());
                    break;
                case NODE_DELETE:
                    Graphs.deleteNode(Graphs.nodeInRadiusN(e.getX(), e.getY(), Graphs.DIST_BETW_NODES));
                    break;
                case PATH_ADD:
                    Graphs.addPathFirstNode(Graphs.nodeInRadiusN(e.getX(), e.getY(), Graphs.DIST_BETW_NODES));
                    break;
                case PATH_ADD_SEC:
                    Graphs.addPathSecondNode(Graphs.nodeInRadiusN(e.getX(), e.getY(), Graphs.DIST_BETW_NODES));
                    break;
        }
}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseInfo.setX(e.getX());
        MouseInfo.setY(e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseEvent e) {
    }
}
