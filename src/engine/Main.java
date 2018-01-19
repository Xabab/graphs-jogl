package engine;

import renderer.Window;

/**
 *
 * @author xabab
 */

/*
Author's note:
    ABANDON HOPE ALL YE WHO ENTER HERE
    Happy refactoring -and/or- *(strikethrough)* AND debugging
 */

public class Main {
    public static void main(String []args){
        Window.init();
        GameLoop.start();
    }
}
