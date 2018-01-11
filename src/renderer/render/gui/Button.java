/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer.render.gui;

import input.MouseInfo;

/**
 *
 * @author xabab
 */
public abstract class Button{
    private float _cR;
    private float _cG;
    private float _cB;

    private int _sizeX;
    private int _sizeY;

    private int _posX;
    private int _posY;

    private String _title;

    public Button(int posX, int posY, int sizeX, int sizeY, float cR ,float cG, float cB, String title){
    _cR = cR;
    _cG = cG;
    _cB = cB;
    _sizeX = sizeX;
    _sizeY = sizeY;
    _posX = posX;
    _posY = posY;
    _title = title;

    }

    public void checkForClick(){
        if((MouseInfo.getX() > _posX) && (MouseInfo.getX() < (_posX + _sizeX)) &&
           (MouseInfo.getY() > _posY) && (MouseInfo.getY() < (_posY + _sizeY))
                ){
            this.onClick();
        }
    }



    public abstract void onClick();

    public float get_cR(){
        return _cR;
    }
    public float get_cG(){
        return _cG;
    }
    public float get_cB(){
        return _cB;
    }
    public int get_sizeX(){
        return _sizeX;
    }
    public int get_sizeY(){
        return _sizeY;
    }
    public int get_posX(){
        return _posX;
    }
    public int get_posY(){
        return _posY;
    }
    public String get_title(){
        return _title;
    }


}
