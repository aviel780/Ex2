import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    private int MC = 0;
    private Map<Integer, NodeData> nodes;
    private Map<Integer, EdgeData> edges;

    public MyDirectedWeightedGraph(DirectedWeightedGraph g){
        nodes = new HashMap<>();
        edges = new HashMap<>();
        Iterator<NodeData> Node = g.nodeIter();
        while(Node.hasNext()) {
            NodeData currNode = Node.next();
            nodes.put(currNode.getKey(), new MyNode((MyNode) currNode));
        }
        Iterator<EdgeData> edge = g.edgeIter();
        while(edge.hasNext()) {
            EdgeData curredge = edge.next();
            MyEdge newEdge = new MyEdge((MyEdge) curredge);


        }


    }
    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
         return null;
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(),n);

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return nodes.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
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
