/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import renderer.Window;

/**
 *
 * @author xabab
 */
public class Main {
    public static void main(String []args){
        Window.init();
        GameLoop.start();
    }
}
