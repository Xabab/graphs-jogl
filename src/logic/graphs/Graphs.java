/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.graphs;

import java.util.ArrayList;
import java.util.List;
import logic.Mode;
import logic.graphs.elements.Decor;
import logic.graphs.elements.Node;
import logic.graphs.elements.Path;

public class Graphs {

    public static class Field {
        private static final List<Node> nodes = new ArrayList<>();
        private static final List<Path> paths = new ArrayList<>();
        private static Node path_add_first_point_temp;
        private static Path weight_add_temp = null;
        static public final int DIST_BETW_NODES = Node.SIZE * 7;

        public static boolean nodeInRadiusB(int x, int y, int r) {
            return nodes.stream().anyMatch((node) ->                                                                        //suggested by IDE
                    (((node.getX() - x) * (node.getX() - x) +                                                               //x^2 + y^2 < r^2
                            (node.getY() - y) * (node.getY() - y))
                            < r * r));
        }

        public static Node nodeInRadiusN(int x, int y) {
            for (Node node : nodes) {
                if (((node.getX() - x) * (node.getX() - x) +
                        (node.getY() - y) * (node.getY() - y))
                        < Node.SIZE * Node.SIZE) {
                    return node;
                }
            }
            return null;
        }

        public static void addNode(int x, int y) {
            if (nodeInRadiusB(x, y, DIST_BETW_NODES)) return;

            Mode.setMode(Mode.MODE.PROC);
            nodes.add(new Node(x, y));
            nodes.get(nodes.size() - 1).setColor(Node.defaultColor);
            Mode.setMode(Mode.MODE.NODE_ADD);
        }

        private static void addPath(Node from, Node to) {
            paths.add(new Path(from, to));
            paths.get(paths.size() - 1).setColor(Path.defaultColor);
        }

        public static Node getNode(int i) {
            if (nodes.isEmpty()) return null;
            if (nodes.size() <= i) return null;
            return nodes.get(i);
        }

        public static Path getPath(int i) {
            if (paths.isEmpty()) return null;
            if (paths.size() <= i) return null;
            return paths.get(i);
        }

        public static int getNodeArrSize() {
            if (nodes.isEmpty()) return 0;
            return nodes.size();
        }

        public static int getPathArrSize() {
            if (paths.isEmpty()) return 0;
            return paths.size();
        }

        private static void deletePathsWithNode(Node node) {
            paths.removeIf(path -> (path.getFrom() == node) || (path.getTo() == node));
        }

        public static void deleteNode(Node node) {
            deletePathsWithNode(node);
            nodes.removeIf(temp -> node == temp);
        }

        public static void addPathFirstNode(Node node) {
            path_add_first_point_temp = node;

            if (path_add_first_point_temp != null) Mode.setMode(Mode.MODE.PATH_ADD_SEC);
        }

        public static void addPathSecondNode(Node node) {

            for (Path p : paths) {
                if (((p.getTo() == node) && (p.getFrom() == path_add_first_point_temp)) || (node == path_add_first_point_temp) || (node == null)) {
                    Mode.setMode(Mode.MODE.PATH_ADD);

                    path_add_first_point_temp = null;
                    return;
                }
            }

            addPath(path_add_first_point_temp, node);
            weight_add_temp = paths.get(paths.size() - 1);
            Mode.setMode(Mode.MODE.WEIGHT_ADD);
            path_add_first_point_temp = null;
        }

        public static void weightEnter(String weight) {
            weight_add_temp.setWeight(Integer.parseInt(weight));
            weight_add_temp = null;
            Mode.setMode(Mode.MODE.PATH_ADD);
        }

        public static Path getWeightPathTemp() {
            return weight_add_temp;
        }

        private static Path findPath(Node from, Node to){
            for(Path p: paths){
                if((p.getFrom() == from) && (p.getTo() == to)) return p;
            }
            return null;
        }
    }

    private static boolean success = false;
    private static boolean negativeCycle = false;
    private static Node start;
    private static Node finish;
    static private boolean fastForwardFlag = false;

    static private int outerIterationsCurrent = 0;
    static private int innerIterationsCurrent = 0;

    static public final int SMALL_TIME_STEP = 50;
    static public final int BIG_TIME_STEP = 1000;

    public static int getOuterIterationsCurrent() { return outerIterationsCurrent; }

    public static int getInnerIterationsCurrent() { return innerIterationsCurrent; }

    public static void fastForward(){fastForwardFlag = true;}

    public static void findPath(){
        if((start != null)){
            Mode.setMode(Mode.MODE.PROC);

            Thread thready = new Thread(() -> {
                for (Node n : Field.nodes) {
                    n.setCost(1000000);
                }
                success = true;
                start.setCost(0);

                boolean relaxed;

                synchronized (Thread.class) {fastForwardFlag = false;}
                synchronized (Thread.class) {outerIterationsCurrent = 0;}
                synchronized (Thread.class) {innerIterationsCurrent = 0;}

                System.out.println("relaxing");

                for (int iteration = 0; iteration < Field.nodes.size(); iteration++) {
                    synchronized (Thread.class) {outerIterationsCurrent++;}
                    synchronized (Thread.class) {innerIterationsCurrent = 0;}
                    for (Path p : Field.paths) {
                        synchronized (Thread.class) {innerIterationsCurrent++;}

                        System.out.println("iteration");

                        synchronized (Thread.class) {resetDecorAndColor();}

                        p.setDecor(Decor.DEC_TYPE.GLOW);
                        p.setDecorColor(Decor.COLOR.GREEN);

                        p.getFrom().setDecor(Decor.DEC_TYPE.GLOW);
                        p.getFrom().setDecorColor(Decor.COLOR.RED);

                        p.getTo().setDecor(Decor.DEC_TYPE.GLOW);
                        p.getTo().setDecorColor(Decor.COLOR.GREEN);

                        relaxed = relax(p);
                        if(relaxed) p.getTo().setParent(p.getFrom());
                        if ((iteration == (Field.nodes.size() - 1)) && relaxed) negativeCycle = true;

                        try {
                            //System.out.println(Thread.currentThread().toString());
                            Thread.sleep(fastForwardFlag? SMALL_TIME_STEP : BIG_TIME_STEP);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("done");

                synchronized (Thread.class) {resetDecorAndColor();}

                if(finish != null) {
                    if (finish.getParent() == null) {
                        success = false;
                    }
                }

                if (!success || negativeCycle){
                    for (Path p: Field.paths){
                        p.setColor(Path.defaultColor);
                    }
                    if (negativeCycle) {
                        Mode.setMode(Mode.MODE.NEGATIVE_SEARCH);

                        synchronized (Thread.class) {fastForwardFlag = false;}

                        System.out.println("negative");

                        for(Node n: Field.nodes) {
                            n.setCost(0);
                            n.setParent(null);

                        }

                        synchronized (Thread.class) {outerIterationsCurrent = 0;}
                        synchronized (Thread.class) {innerIterationsCurrent = 0;}

                        Node cycleCheck = null;

                        Label: for (int iteration = 0; iteration < Field.nodes.size(); iteration++) {
                            synchronized (Thread.class) {outerIterationsCurrent++;}
                            synchronized (Thread.class) {innerIterationsCurrent = 0;}
                            for (Path p : Field.paths) {
                                synchronized (Thread.class) {innerIterationsCurrent++;}

                                System.out.println("negative iteration");

                                synchronized (Thread.class) {resetDecorAndColor();}

                                p.setDecor(Decor.DEC_TYPE.GLOW);
                                p.setDecorColor(Decor.COLOR.BLACK);

                                p.getFrom().setDecor(Decor.DEC_TYPE.GLOW);
                                p.getFrom().setDecorColor(Decor.COLOR.RED);

                                p.getTo().setDecor(Decor.DEC_TYPE.GLOW);
                                p.getTo().setDecorColor(Decor.COLOR.BLACK);

                                relaxed = relax(p);
                                if(relaxed) p.getTo().setParent(p.getFrom());

                                try {
                                    //System.out.println(Thread.currentThread().toString());
                                    Thread.sleep(fastForwardFlag? SMALL_TIME_STEP : BIG_TIME_STEP);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                cycleCheck = Field.nodes.get(iteration);
                                while(cycleCheck.getParent() != null){
                                    cycleCheck = cycleCheck.getParent();
                                    if (cycleCheck == Field.nodes.get(iteration)) break Label;
                                }
                                cycleCheck = null;
                            }
                        }

                        synchronized (Thread.class) {resetDecorAndColor();}

                        Node temp = cycleCheck.getParent();
                        if(temp != null){
                            do {
                                Field.findPath(temp.getParent(), temp).setColor(Decor.COLOR.BLACK);
                                Field.findPath(temp.getParent(), temp).setDecorColor(Decor.COLOR.BLACK);
                                Field.findPath(temp.getParent(), temp).setDecor(Decor.DEC_TYPE.GLOW);
                                temp = temp.getParent();
                            } while (temp != cycleCheck.getParent());
                        }

                        System.out.println("negative done");
                    }
                }
                else{
                    if(finish != null) {
                        Node tmp = finish;
                        while (tmp.getParent() != null) {
                            Field.findPath(tmp.getParent(), tmp).setColor(Decor.COLOR.GREEN);
                            Field.findPath(tmp.getParent(), tmp).setDecorColor(Decor.COLOR.GREEN);
                            Field.findPath(tmp.getParent(), tmp).setDecor(Decor.DEC_TYPE.GLOW);
                            tmp = tmp.getParent();
                        }
                    }
                }

                if (negativeCycle) Mode.setMode(Mode.MODE.NEGATIVE);
                else if (!success) Mode.setMode(Mode.MODE.FAIL);
                else Mode.setMode(Mode.MODE.DONE);
            });

            thready.setName("Algorythm");

            thready.start();
        }
        else Mode.setMode(Mode.MODE.PATH_ADD);
    }

    public static void resetDecorAndColor(){
        for (Path p : Field.paths) {
            p.setDecor(Decor.DEC_TYPE.NONE);
            p.setColor(Path.defaultColor);
        }
        for(Node n: Field.nodes){
            n.setDecor(Decor.DEC_TYPE.NONE);
            n.setColor(Node.defaultColor);
        }
    }

    private static boolean relax(Path p) {
        if ((p.getFrom().getCost() + p.getWeight()) < p.getTo().getCost()) {
            p.getTo().setCost(p.getFrom().getCost() + p.getWeight());
            return true;
        }
        return false;
    }

    public static boolean getResult(){
        return success;
    }



    public static void setStart(Node n){
        if(start == n) start = null;
        else start = n;
    }
    public static void setFinish(Node n){
        if(finish == n) finish = null;
        else finish = n;
    }





    public static Node getStart(){
        return start;
    }
    public static Node getFinish(){
        return finish;
    }


}
