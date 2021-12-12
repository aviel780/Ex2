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

    public MyDirectedWeightedGraphAlgorithms(MyDirectedWeightedGraph g) {
        this.graph = g;
    }

    public MyDirectedWeightedGraphAlgorithms() {

    }


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
        return new MyDirectedWeightedGraph(this.graph);
    }

    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() == 0) {
            return true;
        }
        for (Iterator<NodeData> itN = graph.nodeIter(); itN.hasNext(); ) {
            NodeData n = itN.next();
            boolean temp = this.BFS(n);
            resetTag();
            if (!temp) {
                return false;
            }
        }
        return true;
    }
    private boolean BFS(NodeData n) {
        Queue<NodeData> q = new LinkedList<>();
        n.setTag(0);
        int count = 1;
        q.add(n);
        while (!q.isEmpty()) {
            NodeData temp = q.poll();
            for (Iterator<EdgeData> itN = graph.edgeIter(temp.getKey()); itN.hasNext(); ) {
                EdgeData next = itN.next();
                NodeData dest = this.graph.getNode(next.getDest());
                if (dest.getTag() == -1) {
                    dest.setTag(0);
                    q.add(dest);
                    count++;
                }
            }
        }
        return (count == this.graph.nodeSize());
    }

    private void resetTag() {
        for (Iterator<NodeData> itN = graph.nodeIter(); itN.hasNext(); ) {
            NodeData n = itN.next();
            n.setTag(-1);
        }
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        resetInfo(); resetTag(); resetWeight();
        double dist = Dijkstra(this.graph.getNode(src), this.graph.getNode(dest));
        resetInfo(); resetTag(); resetWeight();
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return dist;
    }
    public void setG(DirectedWeightedGraph x){
        this.graph=x;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> al = new LinkedList<>();
        if (shortestPathDist(src, dest) == -1) {
            return null;
        }
        if (src == dest) {
            al.add(this.graph.getNode(dest));
            return al;
        }
        resetInfo(); resetTag(); resetWeight();
        Dijkstra(this.graph.getNode(src), this.graph.getNode(dest));
        NodeData Nsrc = this.graph.getNode(src);
        NodeData Ndest = this.graph.getNode(dest);
        List <NodeData> reverseAL = new LinkedList<>();
        NodeData temp = Ndest;
        while ( temp.getKey()!=0) {
            //temp.getTag() != 0 &&
            reverseAL.add(temp);
            temp = this.graph.getNode(temp.getTag());
        }
        NodeData[] arr = reverseAL.toArray(NodeData[]::new);
        al.add(Nsrc);
        for (int i = reverseAL.size()- 1; i >= 0; i--) {
            al.add(arr[i]);
        }
        resetInfo(); resetTag(); resetWeight();
        return al;
    }
    private double Dijkstra(NodeData src, NodeData dest) {
        double shortest = Integer.MAX_VALUE;
        PriorityQueue<NodeData> pq = new PriorityQueue<>(this.graph.nodeSize(), new Comparator<NodeData>() {
            @Override
            public int compare(NodeData o1, NodeData o2) {
                return Double.compare(o1.getWeight(),o2.getWeight());
            }
        });
        src.setWeight(0.0);
        pq.add(src);
        while (!pq.isEmpty()) {
            NodeData temp = pq.poll();
            if(temp.getInfo()=="White"){
                temp.setInfo("Black");
                if (temp.getKey() == dest.getKey()) {
                    return temp.getWeight();
                }
            for (Iterator<EdgeData> it = this.graph.edgeIter(temp.getKey()); it.hasNext(); ) {
                EdgeData e = it.next();
                NodeData n = this.graph.getNode(e.getDest());
                if (n.getInfo() == "White") {
                    if (n.getWeight() > temp.getWeight() + e.getWeight()) {
                        n.setWeight(Math.min(n.getWeight(), temp.getWeight() + e.getWeight()));
                        n.setTag(temp.getKey());
                    }
                    pq.add(n);
                }
            }
            temp.setInfo("Black");

        }}
        return shortest;
    }
    private void resetInfo() {
        for (Iterator<NodeData> itN = graph.nodeIter(); itN.hasNext();) {
            NodeData n = itN.next();
            n.setInfo("White");
        }}
    private void resetWeight() {
        for (Iterator<NodeData> itN = graph.nodeIter(); itN.hasNext(); ) {
            NodeData n = itN.next();
            n.setWeight(Integer.MAX_VALUE);
        }
    }

    @Override
    public NodeData center() {
        MyNode center = null;
        MyNode magic = new MyNode(-1);
        double distAns = Double.MAX_VALUE;
        for (Iterator<NodeData> itN = graph.nodeIter(); itN.hasNext(); ) {
            NodeData n = itN.next();
            resetInfo();resetWeight();resetTag();
            Dijkstra(n, magic);
            double distTemp = Double.MIN_VALUE;
            for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
                NodeData k = it.next();
                if (k.getWeight() > distTemp)
                    distTemp = k.getWeight();
            }
            if (distTemp < distAns) {
                distAns = distTemp;
                center = (MyNode) n;
            }
        }
        return center;
    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        LinkedList<NodeData> ret = new LinkedList<>(); //list to be returned
        if (cities.isEmpty()) return ret;
        NodeData currNode = cities.get(0); //current node we are working on
        ret.add(currNode);
        HashSet<NodeData> visitedCities = new HashSet<>();
        while (!cities.isEmpty()) { //while there are still unvisited cities
            visitedCities.add(currNode);
            double minDistance = Double.MAX_VALUE;
            NodeData nextNode = currNode;
            cities.remove(currNode);
            List<NodeData> path = new LinkedList<>();

            for (NodeData node : cities) { //For each unvisited node out of the cities, calculate the one which is closest, save its path
                if (!visitedCities.contains(node)) {
                    double currDistance = this.shortestPathDist(currNode.getKey(), node.getKey());
                    if (currDistance < minDistance) {
                        minDistance = currDistance;
                        nextNode = node;
                        path = this.shortestPath(currNode.getKey(), node.getKey());
                    }
                }
            }
            currNode = nextNode;
            for (NodeData node : path) { //The closest node's path (out of all cities) is appended to the list which is to be returned
                if (node != path.get(0)) {
                    ret.addLast(node);
                    visitedCities.add(node);
                    cities.remove(node); //if exists
                }
            }
        }
        return ret;
    }

    @Override
    public boolean save(String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            gson.toJson(graph, new FileWriter("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G7.json"));
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