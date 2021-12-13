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
        DirectedWeightedGraph n = new MyDirectedWeightedGraph();
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
        for (Iterator<NodeData> iternod = graph.nodeIter(); iternod.hasNext(); ) {
            NodeData nd = iternod.next();
            boolean temp = this.BFS(nd);
            resetTag();
            if (!temp) {
                return false;
            }
        }
        return true;
    }
    private boolean BFS(NodeData n) {
        Queue<NodeData> que = new LinkedList<>();
        n.setTag(0);
        int ans = 1;
        que.add(n);
        while (!que.isEmpty()) {
            NodeData temp = que.poll();
            for (Iterator<EdgeData> iternod = graph.edgeIter(temp.getKey()); iternod.hasNext(); ) {
                EdgeData ne = iternod.next();
                NodeData de = this.graph.getNode(ne.getDest());
                if (de.getTag() == -1) {
                    de.setTag(0);
                    que.add(de);
                    ans++;
                }
            }
        }
        if(ans == this.graph.nodeSize()){
            return true;
        }

        return false;
    }

    private void resetTag() {
        for (Iterator<NodeData> iternod = graph.nodeIter(); iternod.hasNext(); ) {
            NodeData next = iternod.next();
            next.setTag(-1);
        }
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        resetInfo(); resetTag(); resetWeight();
        double distan = Dijkstra(this.graph.getNode(src), this.graph.getNode(dest));
        resetInfo(); resetTag(); resetWeight();
        if (distan == Integer.MAX_VALUE) {
            return -1;
        }
        return distan;
    }
    public void setG(DirectedWeightedGraph x){
        this.graph=x;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> ans = new LinkedList<>();
        if (shortestPathDist(src, dest) == -1) {
            return null;
        }
        if (src == dest) {
            ans.add(this.graph.getNode(dest));
            return ans;
        }
        resetInfo(); resetTag(); resetWeight();
        Dijkstra(this.graph.getNode(src), this.graph.getNode(dest));
        NodeData nodsrc = this.graph.getNode(src);
        NodeData nodest = this.graph.getNode(dest);
        List <NodeData> rev = new LinkedList<>();
        NodeData temp = nodest;
        while ( temp.getKey()!=0) {
            rev.add(temp);
            temp = this.graph.getNode(temp.getTag());
        }
        NodeData[] arr = rev.toArray(NodeData[]::new);
        ans.add(nodsrc);
        for (int i = rev.size()- 1; i >= 0; i--) {
            ans.add(arr[i]);
        }
        resetInfo(); resetTag(); resetWeight();
        return ans;
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
            NodeData tempp = pq.poll();
            if(tempp.getInfo()=="White"){
                tempp.setInfo("Black");
                if (tempp.getKey() == dest.getKey()) {
                    return tempp.getWeight();
                }
            for (Iterator<EdgeData> iteredge = this.graph.edgeIter(tempp.getKey()); iteredge.hasNext(); ) {
                EdgeData e = iteredge.next();
                NodeData n = this.graph.getNode(e.getDest());
                if (n.getInfo() == "White") {
                    if (n.getWeight() > tempp.getWeight() + e.getWeight()) {
                        n.setWeight(Math.min(n.getWeight(), tempp.getWeight() + e.getWeight()));
                        n.setTag(tempp.getKey());
                    }
                    pq.add(n);
                }
            }
                tempp.setInfo("Black");

        }}
        return shortest;
    }
    private void resetInfo() {
        for (Iterator<NodeData> iternod = graph.nodeIter(); iternod.hasNext();) {
            NodeData nextnode = iternod.next();
            nextnode.setInfo("White");
        }}
    private void resetWeight() {
        for (Iterator<NodeData> iternod = graph.nodeIter(); iternod.hasNext(); ) {
            NodeData nextnode = iternod.next();
            nextnode.setWeight(Integer.MAX_VALUE);
        }
    }

    @Override
    public NodeData center() {
        MyNode center = null;
        MyNode emptylop = new MyNode(-1);
        double dista = Double.MAX_VALUE;
        for (Iterator<NodeData> iternod = graph.nodeIter(); iternod.hasNext(); ) {
            NodeData nod = iternod.next();
            resetInfo();resetWeight();resetTag();
            Dijkstra(nod, emptylop);
            double distt = Double.MIN_VALUE;
            for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
                NodeData k = it.next();
                if (k.getWeight() > distt)
                    distt = k.getWeight();
            }
            if (distt < dista) {
                dista = distt;
                center = (MyNode) nod;
            }
        }
        return center;
    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> ans = new ArrayList<>();
        Queue<NodeData> citiesset =  new LinkedList<NodeData>();
//        List<NodeData> citiesset = new ArrayList<>();
        double mindist;
        double tempdist;

        for (NodeData city:cities){
            if(!citiesset.contains(city)){
                citiesset.add(city);
            }
        }
        NodeData currnode = citiesset.poll();
        ans.add(currnode);
        NodeData nodetoadd = null;
        while(citiesset.size()>0){
            mindist = Double.MAX_VALUE;
            for (NodeData n : citiesset){
                tempdist = shortestPathDist(currnode.getKey(),n.getKey());
                if(tempdist<mindist){
                    mindist=tempdist;
                    nodetoadd=n;
                }
            }
            ans.add(nodetoadd);
            citiesset.remove(nodetoadd);
            currnode=nodetoadd;
        }
        return ans;
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