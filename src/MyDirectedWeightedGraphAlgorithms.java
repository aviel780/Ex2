import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.List;



public class MyDirectedWeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;


    public void Mygraph(){
        this.graph = new MyDirectedWeightedGraph();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
    this.graph=g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {

        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph newgrhp = new MyDirectedWeightedGraph(graph);
        return newgrhp;
    }

    private static void DFS(MyDirectedWeightedGraph graph,int v,boolean[] visited){
        visited[v]=true;

        for (int i =0; i<graph.collection().size();i++){

        }
    }


    @Override
    public boolean isConnected() {

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            gson.toJson(this.graph, new FileWriter("src/Ex2/data/file.json"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;    }

    @Override
    public boolean load(String fileName) {

        try {
            this.graph = new MyDirectedWeightedGraph(fileName);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    }
}
