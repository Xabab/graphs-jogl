/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import input.KeyInput;
import logic.Mode;
import logic.graphs.Graphs;
import logic.graphs.elements.Path;
import renderer.Window;
import renderer.render.Cursor;
import renderer.render.Nodes;
import renderer.render.Paths;
import renderer.render.Shapes;
import renderer.render.Text;
import renderer.render.gui.Gui;

/**
 * @author xabab
 */
public class GameLoop {
    private static boolean running = false;

    public static void start() {


        Thread thread = new Thread(() -> {
            running = true;

            while (running) {
                Window.render();
            }
        });
        thread.setName("GameLoop");
        thread.start();
    }

    public static void loopIteration() {

        //draw effects for paths

        //draw effects for nodes

        //draw paths
        Paths.drawPaths();


        //draw nodes
        Nodes.drawNodes();

        //draw adding weight effect
        if (Mode.getMode() == Mode.MODE.WEIGHT_ADD) {
            Path p = Graphs.getWeightPathTemp();
            Shapes.drawCircle(
                    (p.getFrom().getX() + p.getTo().getX()) / 2,
                    (p.getFrom().getY() + p.getTo().getY()) / 2,
                    15, 16, 0.4f, 0.4f, 0.4f);

            Text.textWeight(
                    (p.getFrom().getX() + p.getTo().getX()) / 2,
                    (p.getFrom().getY() + p.getTo().getY()) / 2,
                    KeyInput.getWeightTemp() + "I", 1f, 1f, 1f);

        }


        //draw paths cost
        Paths.drawWeights();

        //draw path cost on nodes if algorithm going
        if(Mode.getMode() == Mode.MODE.DONE){
            Nodes.drawCosts();
        }


        ///////////////////////// DEBUG
        if(Mode.getMode() == Mode.MODE.DONE) {
            if (Graphs.getResult()) Shapes.drawBox(0, 400, 15, 15);
            else Shapes.drawCircle(0, 400, 15, 15);
        }
        /////////////////////////


        //draw cursor
        Cursor.draw();

        //draw gui
        Gui.draw();
    }

    public static void exit() {
        running = false;
    }
}
