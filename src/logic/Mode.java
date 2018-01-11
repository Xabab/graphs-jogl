/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


public class Mode {
    public enum MODE {NODE_ADD, NODE_DELETE, PATH_ADD, PATH_ADD_SEC, SEL_STA,
        SEL_END, PROC, WEIGHT_ADD, DONE}

    private static MODE mode = MODE.NODE_ADD;

    public static void setMode(MODE m){
        mode = m;
    }
                                                                                //TODO locks
    public static MODE getMode(){
        return mode;
    }
}
