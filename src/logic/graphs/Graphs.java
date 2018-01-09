/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import logic.Mode;
import logic.graphs.elements.Node;
import logic.graphs.elements.Path;

public class Graphs {


    static final List<Node> nodes = new ArrayList<Node>();
    static final List<Path> paths = new ArrayList<Path>();

    static Node path_add_first_point_temp;
    static Path weight_add_temp = null;

    static public final int DIST_BETW_NODES = Node.SIZE * 7;


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

    public static Node nodeInRadiusN(int x, int y, int r){
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

    public static void addPath(Node from, Node to){
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
        for (Iterator<Path> pitr = paths.iterator(); pitr.hasNext(); ) {
            Path path = pitr.next();
            if(path.getFrom()== node || path.getTo() == node){
                pitr.remove();
            }
        }
    }


    public static void deleteNode(Node node) {
        deletePathsWithNode(node);
        for (Iterator<Node> nitr = nodes.iterator(); nitr.hasNext(); ) {
            Node temp = nitr.next();
            if(node == temp){
                nitr.remove();
            }
        }
    }

    public static void addPathFirstNode(Node node){
        path_add_first_point_temp = node;

        if(path_add_first_point_temp != null) Mode.setMode(Mode.MODE.PATH_ADD_SEC);
    }

    public static void addPathSecondNode(Node node){

        for(Path p: paths){
            if((p.getFrom() == node || p.getTo() == node) &&
                    (p.getFrom() == path_add_first_point_temp || p.getTo() == path_add_first_point_temp)){
                Mode.setMode(Mode.MODE.PATH_ADD);
                path_add_first_point_temp = null;
                return;
            }
        }

        addPath(path_add_first_point_temp, node);
        weight_add_temp = paths.get(paths.size() - 1);
        Mode.setMode(Mode.MODE.WEIGHT_ADD);
    }

    public static void weightEnter(String weight) {
        weight_add_temp.setWeight(Integer.parseInt(weight));
        weight_add_temp = null;
        Mode.setMode(Mode.MODE.PATH_ADD);
    }

    public static Path getWeightPathTemp() {
        return weight_add_temp;
    }

}
