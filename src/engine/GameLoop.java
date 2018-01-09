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
//                GLContext.makeCurrent();

            while (running) {
                Window.render();
            }
        });
        thread.setName("Gameloop");
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
                    30, 16, 0.4f, 0.4f, 0.4f);
            if (!KeyInput.getWeightTemp().isEmpty()) {
                Text.textWeight(
                        (p.getFrom().getX() + p.getTo().getX()) / 2,
                        (p.getFrom().getY() + p.getTo().getY()) / 2,
                        KeyInput.getWeightTemp(), 1f, 1f, 1f);
            }
        }


        //draw paths cost
        Paths.drawWeights();

        //draw path cost on nodes if algoritm going

        //draw cursor
        Cursor.draw();

        //draw gui
        Gui.draw();
    }

    public static void exit() {
        running = false;
    }
}
