import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;


public class MyDirectedWeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;


    public void Mygraph() {
        this.graph = new MyDirectedWeightedGraph();
    }

    @Override
    public void init(DirectedWeightedGraph g) {

        this.graph = g;
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

    @Override
    public boolean isConnected() {
        DirectedWeightedGraph tmp = this.copy();
        Iterator<EdgeData> it = tmp.edgeIter();
        boolean[] vis1 = new boolean[tmp.edgeSize()];
        boolean[] vis2 = new boolean[tmp.edgeSize()];
        Vector<Integer>[] gr1 = new Vector[tmp.edgeSize()];
        Vector<Integer>[] gr2 = new Vector[tmp.edgeSize()];
        for (int i = 0; i < tmp.edgeSize(); i++){
            gr1[i] = new Vector<>();
            gr2[i] = new Vector<>();}
        while(it.hasNext()){ // we dont have the first element
                int dest = it.next().getDest();
                int src = it.next().getSrc();
            gr1[src].add(dest);
            gr2[dest].add(src);
        }
        Arrays.fill(vis1, false);
        dfs1(vis1,gr1,1);

        // Call for reverse direction
        Arrays.fill(vis2, false);
        dfs2(vis2,gr2,1);

        for (int i = 1; i <= tmp.edgeSize(); i++)
        {
            if (!vis1[i] && !vis2[i])// if there is nodes who not visited
                return false;
        }

            return true; // if all the nodes are visited
    }

    static void dfs1(boolean[] vis1,Vector<Integer>[] gr1,int x)// normal grahp
    {
        vis1[x] = true;
        for (int i : gr1[x])
            if (!vis1[i])
                dfs1(vis1,gr1,i);
    }
    static void dfs2(boolean[] vis2,Vector<Integer>[] gr2,int x)// transpoze graph
    {
        vis2[x] = true;
        for (int i : gr2[x])
            if (!vis2[i])
                dfs2(vis2,gr2,i);
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        List<Integer> prev = new ArrayList<>();
        List<Double> dist = new ArrayList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Iterator<NodeData> it = this.graph.nodeIter();
        NodeData n = new MyNode();

        while (it.hasNext()) {
            n = it.next();
            if (n.getKey() != src)
                dist.add(n.getKey(), Double.MAX_VALUE);
            else {
                dist.add(n.getKey(), 0.0);
            }
            prev.add(n.getKey(), null);
            q.add(n.getKey());
        }
        if (!dist.contains(src)|| !dist.contains(dest)){// the src or the dest is not in the graph
            return -1;
        }
        int u = src;
        while (q.isEmpty()== false) {
            u = dist(dist, q, u);
            q.remove(u);
            Iterator<Integer> iter = q.iterator();
            double alt = 0;
            int node = 0;
            while (iter.hasNext()) {
                node = iter.next();
                if (this.graph.getEdge(u, node) != null && u != src && node != 0) {
                    alt = dist.get(u) + this.graph.getEdge(u, node).getWeight();
                    if (alt < dist.get(node)) {
                        dist.remove(node);
                        dist.add(node, alt);
                        prev.remove(node);
                        prev.add(node, u);
                    }
                }
            }
        }
        return dist.get(dest);
    }
    private int dist(List<Double> dist, PriorityQueue<Integer> q, int src) {
        double min = Double.MAX_VALUE;
        int min_index = -1;
        Iterator<NodeData> it = this.graph.nodeIter();
        NodeData n = new MyNode();

        while (it.hasNext()) {
            n = it.next();
            double tmp = 0;
            if (this.graph.getEdge(src, n.getKey()) != null) {
                min_index = q.peek();
                tmp = this.graph.getEdge(src, n.getKey()).getWeight() + dist.get(src);
                if (tmp < dist.get(n.getKey())) {
                    dist.remove(n.getKey());
                    dist.add(n.getKey(), tmp);
                }
                if (this.graph.getEdge(src, n.getKey()).getWeight() <= min && q.contains(n.getKey()) && dist.get(n.getKey()) != 0) {
                    min = this.graph.getEdge(src, n.getKey()).getWeight();
                    min_index = n.getKey();
                }
            }
        }
        return min_index;
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
            gson.toJson(graph, new FileWriter(""));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean load(String fileName) {
        try {
            this.graph = new MyDirectedWeightedGraph(fileName);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}