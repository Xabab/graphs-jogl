/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;


import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import engine.GameLoop;
import logic.Mode;
import logic.graphs.BellmanFord;
import renderer.render.Shapes;

/**
 *
 * @author xabab
 */
public class KeyInput implements KeyListener{

    private static boolean _pressed;
    private static String weight_temp = "";
    private static final String ALLOWED_TEXT_INPUT = "-1234567890";

    @Override
    public void keyPressed(KeyEvent e) {
        Shapes.drawCircle(100, 100, 5, 8, 1, 1, 1);
        System.out.println(e.getKeyCode());
        System.out.println(e.getKeyChar());
        if((Mode.getMode() == Mode.MODE.WEIGHT_ADD) && !_pressed){
            switch (e.getKeyCode()) {
                case 13: //if ENTER
                    weightEnter();
                    break;
                case 8:  //if BACKSPACE
                    if(!weight_temp.isEmpty()) weight_temp = weight_temp.substring(0, weight_temp.length() - 1);
                    break;
                case 27:
                    GameLoop.exit();
                    break;
                default:
                    weightKeyPress(e.getKeyChar());
                    break;
            }
            _pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Shapes.drawCircle(100, 100, 5, 8, 1, 0, 0);
        _pressed = false;
    }


    private static void weightKeyPress(char keyChar) {
        if(ALLOWED_TEXT_INPUT.contains(Character.toString(keyChar))){
            if(!weight_temp.isEmpty() && (keyChar == '-')) return;
            weight_temp = weight_temp.concat(Character.toString(keyChar));
        }
    }

    private static void weightEnter() {
        if(!weight_temp.isEmpty() && !weight_temp.equals("-")){
            System.out.println(Integer.parseInt(weight_temp));
            BellmanFord.Graph.weightEnter(weight_temp);
            weight_temp = "";
        }
    }

    public static String getWeightTemp(){
        return weight_temp;
    }
}
