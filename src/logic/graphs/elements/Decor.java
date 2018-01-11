
package logic.graphs.elements;

import static logic.graphs.elements.Decor.COLOR.WHITE;
import static logic.graphs.elements.Decor.DEC_TYPE.NONE;                                 //?


public class Decor {
    public enum DEC_TYPE {NONE, GLOW}
    public enum COLOR {WHITE, RED, ORANGE, YELLOW, GREEN}

    protected DEC_TYPE dec = NONE;
    protected COLOR col = WHITE;

    public DEC_TYPE getDecor(){
        return dec;
    }

    public void setDecor(DEC_TYPE decor){
        dec = decor;
    }

    public COLOR getColor(){
        return col;
    }
    public void setColor(COLOR color){
        col = color;
    }

    public static void drawNodeDecor(Node n){
        //TODO
    }

    public static void drawPathDecor(Path p){
        //TODO
    }

}
