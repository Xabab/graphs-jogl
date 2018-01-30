/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import logic.graphs.BellmanFord;

import static logic.Mode.MODE.*;

public class Mode {
    public enum MODE {NODE_ADD, NODE_DELETE, PATH_ADD, PATH_ADD_SEC, SEL_STA,
        SEL_END, PROC, WEIGHT_ADD, DONE, FAIL, NEGATIVE_SEARCH, NEGATIVE, START_ONLY}

    private static MODE mode = MODE.NODE_ADD;

    public static void setMode(MODE m){
        if(mode == DONE || mode == FAIL ||  mode == NEGATIVE || mode == START_ONLY) BellmanFord.resetDecorAndColor();

        mode = m;
    }
                                                                                //TODO locks
    public static MODE getMode(){
        return mode;
    }
}
