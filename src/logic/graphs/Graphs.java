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


    private static final List<Node> nodes = new ArrayList<>();
    private static final List<Path> paths = new ArrayList<>();

    private static Node path_add_first_point_temp;
    private static Path weight_add_temp = null;

    static public final int DIST_BETW_NODES = Node.SIZE * 7;

    private static boolean success = false;
    private static Node start;
    private static Node finish;


    public static boolean nodeInRadiusB(int x, int y, int r){
        return nodes.stream().anyMatch((node) ->                                //suggested by IDE
              (((node.getX() - x)*(node.getX() - x) +                           //x^2 + y^2 < r^2
                (node.getY() - y)*(node.getY() - y))
                < r*r));
    }

//    public static Path pathInArea(int x, int y, int area){
//        //TODO
//        return null;
//    }

    public static Node nodeInRadiusN(int x, int y){
        for (Node node : nodes) {
            if(((node.getX() - x)*(node.getX() - x) +
                    (node.getY() - y)*(node.getY() - y))
                    < Node.SIZE*Node.SIZE){
                return node;
            }
        }
        return null;
    }

    public static void addNode(int x, int y){
        if (nodeInRadiusB(x, y, DIST_BETW_NODES)) return;

        Mode.setMode(Mode.MODE.PROC);
        nodes.add(new Node(x, y));
        Mode.setMode(Mode.MODE.NODE_ADD);
    }

    private static void addPath(Node from, Node to){
        paths.add(new Path(from, to));
    }

    public static Node getNode(int i){
        if(nodes.isEmpty()) return null;
        if(nodes.size() <= i) return null;
        return nodes.get(i);
    }

    public static Path getPath(int i){
        if(paths.isEmpty()) return null;
        if(paths.size() <= i) return null;
        return paths.get(i);
    }

    public static int getNodeArrSize(){
        if(nodes.isEmpty()) return 0;
        return nodes.size();
    }

    public static int getPathArrSize(){
        if(paths.isEmpty()) return 0;
        return paths.size();
    }

    private static void deletePathsWithNode(Node node){
        paths.removeIf(path -> (path.getFrom() == node) || (path.getTo() == node));
    }


    public static void deleteNode(Node node) {
        deletePathsWithNode(node);
        nodes.removeIf(temp -> node == temp);
    }

    public static void addPathFirstNode(Node node){
        path_add_first_point_temp = node;

        if(path_add_first_point_temp != null) Mode.setMode(Mode.MODE.PATH_ADD_SEC);
    }

    public static void addPathSecondNode(Node node){

        for(Path p: paths){
            if(((p.getTo() == node) && (p.getFrom() == path_add_first_point_temp)) || (node == path_add_first_point_temp) || (node == null)){
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

    public static void findPath(){
        System.out.println("Processing");
        if((start != null) && (finish != null)){
            Mode.setMode(Mode.MODE.PROC);
            for (Node n : nodes) {
                n.setCost(1000000);
            }
            success = true;
            start.setCost(0);

            boolean relaxed;
            for (int iteration = 0; iteration < nodes.size(); iteration++) {
                for (Path p : paths) {
                    relaxed = relax(p);
                    if(relaxed)p.getTo().setParent(p.getFrom());
                    if ((iteration == (nodes.size() - 1)) && relaxed) success = false;
                }
            }
            if (finish.getParent() == null) success = false;
            Mode.setMode(Mode.MODE.DONE);
            System.out.println("Done");
            if (!success){
                for (Path p: paths){
                    p.setColor(Decor.COLOR.GREEN);
                }
            }
            else{
                Node tmp = finish;
                while(tmp.getParent() != null){
                    findPath(tmp.getParent(), tmp).setColor(Decor.COLOR.RED);
                    tmp = tmp.getParent();
                }
            }
        }
        else Mode.setMode(Mode.MODE.PATH_ADD);
    }

    private static Path findPath(Node from, Node to){
        for(Path p: paths){
            if((p.getFrom() == from) && (p.getTo() == to)) return p;
        }
        return null;
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
        start = n;
    }
    public static void setFinish(Node n){
        finish = n;
    }


    public static Node getStart(){
        return start;
    }
    public static Node getFinish(){
        return finish;
    }
}
