import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.google.gson.Gson;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    private int MC = 0;
    private Map<Integer,NodeData> nodes;
    private Map<Integer,Map<Integer, EdgeData>> edges;
    private int sizeOfNodes,sizeOfEdges;
   private Iterator<EdgeData> edgeItr;
    private Iterator<NodeData> nodeItr;

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
            this.sizeOfNodes = Nodes.length;
            this.sizeOfEdges = Edges.length;

            this.edges = new HashMap<>();
            this.nodes = new HashMap<>();
            for (int i = 0; i < this.sizeOfNodes; i++) {
                this.nodes.put(i, new MyNode(Nodes[i]));
            }
            for (int i = 0; i < this.sizeOfEdges; i++) {
                MyEdge e = new MyEdge(Edges[i]);
                HashMap<Integer,EdgeData> newedge = new HashMap<Integer,EdgeData>();
                newedge.put(e.getDest(),e);
                this.edges.put(e.getSrc(), newedge);
            }
            this.MC = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MyDirectedWeightedGraph(){
        this.MC = 0;
        this.nodes= new HashMap<Integer,NodeData>();
        this.edges = new HashMap<Integer,Map<Integer,EdgeData>>();
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
        if(edges.containsKey(src)&& edges.get(src).containsKey(dest)){
            return edges.get(src).get(dest);}
        else{
            return null;
        }
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(), (MyNode) n);
    }

    public void addEdge(int src, EdgeData dest) {
        if (edges.containsKey(src)){
        edges.get(src).put(dest.getDest(),(MyEdge)dest);}
        else{
            HashMap<Integer,EdgeData> newedge = new HashMap<Integer,EdgeData>();
            newedge.put(dest.getDest(),(MyEdge)dest);
            edges.put(src,newedge);
        }
    }
    @Override
    public void connect(int src, int dest, double w) {
        if (!(edges.containsKey(src)&& edges.get(src).containsKey(dest))) {
            MyEdge newed = new MyEdge(src,dest,w,"",0); // in case we dont have info and tag
           addEdge(src,newed);

        }// what if connected
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        this.nodeItr = nodes.values().iterator();
        return this.nodeItr;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {

        HashMap<Integer,EdgeData> hased = new HashMap<Integer,EdgeData>();
        while (edges.values().iterator().hasNext()){
            hased.put(0, (EdgeData) edges.values());
            }
        this.edgeItr = hased.values().iterator();
        return  this.edgeItr;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {// not completed

        return (Iterator<EdgeData>) edges.get(node_id).values();
    }

    @Override
    public NodeData removeNode(int key) {// need to chak if the run time is ok.
        if(nodes.containsKey(key)){
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

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (this.edges.containsKey(src)){
            if(this.edges.get(src).containsKey(dest)){
                EdgeData ans = this.edges.get(src).remove(dest);
                 if(this.edges.get(src).size()==0){
                     edges.remove(src);
                 }
                 return ans;
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
