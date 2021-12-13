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
    private ArrayList<EdgeData> edgelist;

    public MyDirectedWeightedGraph(String json_file) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);
            String Eges = map.get("Edges").toString();
            Eges = Eges.replace("{", "");
            Eges = Eges.substring(1, Eges.length() - 2);
            String[] Edges = Eges.split("}, ");
            String Node = map.get("Nodes").toString();
            Node = Node.replace("{", "");
            Node = Node.substring(1, Node.length() - 2);
            String[] Nodes = Node.split("}, ");
            System.out.println(Edges.length);
            this.sizeOfNodes = Nodes.length;
            this.sizeOfEdges = Edges.length;

            this.edges = new HashMap<Integer,Map<Integer,EdgeData>>();
            this.nodes = new HashMap<Integer,NodeData>();
            this.edgelist = new ArrayList<EdgeData>();

            for (int i = 0; i < this.sizeOfNodes; i++) {
                this.nodes.put(i, new MyNode(Nodes[i]));
            }
            for (int i = 0; i < this.sizeOfEdges; i++) {
                MyEdge e = new MyEdge(Edges[i]);
                addEdge(e.getSrc(),e);

            }
            this.MC = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MyDirectedWeightedGraph(){
        this.edgelist = new ArrayList<EdgeData>();
        this.nodes= new HashMap<Integer,NodeData>();
     this.edges = new HashMap<Integer,Map<Integer,EdgeData>>();
       this.MC = 0;
       this.sizeOfNodes = 0;
       this.sizeOfEdges = 0;

    }

    public MyDirectedWeightedGraph(DirectedWeightedGraph graph) {
        this.MC = 0;
        this.nodes = new HashMap<Integer, NodeData>();
        this.edges = new HashMap<Integer, Map<Integer, EdgeData>>();
        this.edgelist = new ArrayList<EdgeData>();
        this.MC = graph.getMC();
        this.sizeOfEdges = graph.edgeSize();
        this.sizeOfNodes = graph.nodeSize();
        this.edgeItr = graph.edgeIter();
        this.nodeItr = graph.nodeIter();
        for (int i = 0; i < this.sizeOfNodes; i++) {
            NodeData temp = graph.getNode(i);
            nodes.put(temp.getKey(), temp);
        }
        while(edgeItr.hasNext()){
            EdgeData temp = edgeItr.next();
            HashMap<Integer,EdgeData> tempedge = new HashMap<Integer,EdgeData>();
            edges.put(temp.getSrc(),tempedge );
        }
    }

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
//        nodes.put(n.getKey(), (MyNode) n);
//        nodeItr= nodes.values().iterator();
        if (nodes.containsKey(n.getKey())) {
            return;
        }
        nodes.put(n.getKey(), (MyNode)n);
        edges.put(n.getKey(), new HashMap<Integer,EdgeData>());
        MC++;

    }


    public void addEdge(int src, EdgeData dest) {
        if (edges.containsKey(src)){
            edges.get(src).put(dest.getDest(),(MyEdge)dest);
            edgelist.add((MyEdge)dest);
        }
        else{
            HashMap<Integer,EdgeData> newedge = new HashMap<Integer,EdgeData>();
            newedge.put(dest.getDest(),(MyEdge)dest);
            edges.put(src,newedge);
            edgelist.add((MyEdge)dest);
        }
    }
    @Override
    public void connect(int src, int dest, double w) {
        if (!(edges.containsKey(src)&& edges.get(src).containsKey(dest))) {
            MyEdge newed = new MyEdge(src,dest,w,"",0); // in case we dont have info and tag
           addEdge(src,newed);

        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        this.nodeItr = nodes.values().iterator();
        return this.nodeItr;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        this.edgeItr = edgelist.iterator();
        return this.edgeItr;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        ArrayList<EdgeData> templist = new ArrayList<EdgeData>();
        for (int i = 0 ; i < edgelist.size();i++){
            EdgeData temp = edgelist.get(i);
            if(temp.getSrc() == node_id){
                templist.add(temp);
            }

        }
        return  templist.iterator();
    }

    @Override
    public NodeData removeNode(int key) {
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
    public ArrayList<EdgeData> getedgelist() {
        return this.edgelist;
    }

}
