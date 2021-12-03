import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    private int MC = 0;
    private Map<Integer,MyNode> nodes;
    private Map<Integer,Map<Integer, MyEdge>> edges;
    private ArrayList<Integer> srclist;

    public MyDirectedWeightedGraph(){
        this.MC = 0;
        this.nodes= new HashMap<Integer,MyNode>();
        this.edges = new HashMap<Integer,Map<Integer,MyEdge>>();
    }
    public MyDirectedWeightedGraph(String json_file) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);
            String E = map.get("Edges").toString();
            E = E.replace("{", "");
            E = E.substring(1, E.length() - 2);
            String[] Edges = E.split("}, ");
            String N = map.get("Nodes").toString();
            N = N.replace("{", "");
            N = N.substring(1, N.length() - 2);
            String[] Nodes = N.split("}, ");

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.edegs = new HashMap<>();
        this.nodes = new HashMap<>();
        for (int i = 0; i < this.nodes.size(); i++) {
            this.nodes.put(i, new MyNode(json_file, i));
        }
        for (int i = 0; i < this.edgeSize(); i++) {
            MyEdge e = new MyEdge(json_file, i);
            MyEdge temp = new MyEdge(json_file, i);
            HashMap<Integer,MyEdge> tempedge = new HashMap<Integer,MyEdge>();
            tempedge.put(temp.getDest(),temp);
            this.edegs.put(e.getSrc(), tempedge);
            //int tmp = e.getSrc();
        }
        this.MC = 0;

    }
    public MyDirectedWeightedGraph(DirectedWeightedGraph graph) {
       //
    }


    // need to crate builders
    @Override
    public NodeData getNode(int key) {
        if (nodes.containsKey(key)){
        return nodes.get(key);}
        else{
            return null;
        }
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if(edges.containsKey(src)&& edges.containsValue(dest)){
            return edges.get(src).get(dest);}
        else{

            return null;

        }
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(), (MyNode) n);
        srclist.add(n.getKey());




    }

    public void addEdge(int src, EdgeData dest) {
        if (edges.containsKey(src)){
        edges.get(src).put(dest.getDest(),(MyEdge)dest);}
        else{
            HashMap<Integer,MyEdge> newedge = new HashMap<Integer,MyEdge>();
            newedge.put(dest.getDest(),(MyEdge)dest);
            edges.put(src,newedge);
        }
    }
    @Override
    public void connect(int src, int dest, double w) {
        if (!(edges.containsKey(src)&& edges.get(src).containsKey(dest))) {
            MyEdge newed = new MyEdge(src,dest,w,"",0);// in case we dont have info and tag
            edges.get(src).put(dest,newed);
        }
        // what happends if there is connected?
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return (Iterator<NodeData>) nodes.values();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return (Iterator<EdgeData>) edges.values();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {

        return (Iterator<EdgeData>) edges.get(node_id).values();
    }

    @Override
    public NodeData removeNode(int key) {// need to chak if the run time is ok.
        if(nodes.containsKey(key)){
            srclist.remove(key);
             nodes.remove(key);
             edges.remove(key);
             for (int i = 0; i<edges.size();i++){
                 if(edges.get(i).containsKey(key)){
                     edges.get(i).remove(key);
                 }
            }
             return nodes.remove(key);
        }
        else
            return null;
        }

    public ArrayList<Integer> collection(){
        return srclist;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (edges.containsKey(src)){
            if(edges.get(src).containsKey(dest)){
                return edges.get(src).remove(dest);
            }
            return null;
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return MC;
    }
}
