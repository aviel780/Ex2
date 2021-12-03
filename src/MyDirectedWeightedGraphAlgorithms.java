import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

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
    private static void DFS(MyDirectedWeightedGraph graph,int v,boolean[] visited){
        visited[v]=true;

        for (int i =0; i<graph.collection().size();i++){

        }
    }
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph newGraph = new MyDirectedWeightedGraph(graph);
        return newGraph;
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
        try {
            FileOutputStream file_out = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file_out);
            out.writeObject(this.graph);
            out.close();
            file_out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String fileName) {
        try {
            FileInputStream file_in = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file_in);
            this.graph = (DirectedWeightedGraph) in.readObject();
            file_in.close();
            in.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
