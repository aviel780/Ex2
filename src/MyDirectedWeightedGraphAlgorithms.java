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
        if(src == dest){
            return 0;
        }
        List<NodeData> list = shortestPath(src,dest);
        if( list == null){
            return -1;
        }
        double pathSize = 0;
        Iterator <NodeData> itSrc= list.listIterator();
        Iterator <NodeData> itDest= list.listIterator();
        itDest.next();
        while(itDest.hasNext()){
            EdgeData e =graph.getEdge(itSrc.next().getKey(), itDest.next().getKey());
            pathSize += e.getWeight();
        }
        return pathSize;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List <NodeData> pathList = new ArrayList<>();
        int prev = Integer.MAX_VALUE;
        HashMap<Integer,Integer> changedAt = new HashMap<>();
        changedAt.put(src,null);
        HashMap<Integer,Double> shortestPath = new HashMap<>();
        Iterator <NodeData> iternode = graph.nodeIter();
        while(iternode.hasNext()){
            NodeData nod =iternode.next();
            if(nod.getKey() == src){
                shortestPath.put(src,0.0);
            }else{
                shortestPath.put(nod.getKey(),Double.MAX_VALUE);
            }
        }
        Iterator <EdgeData> iteredge = graph.edgeIter(src);
        while(iteredge.hasNext()){
            EdgeData e = iteredge.next();
            shortestPath.put(e.getDest(),e.getWeight());
            changedAt.put(e.getDest(),src);
        }
        graph.getNode(src).setTag(1);//visited
        while(true){
            double distshort = Double.MAX_VALUE;
            NodeData closesrechnode = null;
            Iterator <NodeData> itenode = graph.nodeIter();
            while(itenode.hasNext()){
                NodeData n = itenode.next();
                if(n.getTag() == 1){
                    continue;
                }
                double currDist = shortestPath.get(n.getKey());
                if(currDist == Double.MAX_VALUE){
                    continue;
                }
                if(currDist < distshort){
                    distshort = currDist;
                    closesrechnode = n;
                }
            }
            NodeData currNode =closesrechnode;
            if(currNode == null){
                return null;
            }
            if(currNode.getKey() == dest) {
                int temp = dest;
                pathList.add(0,graph.getNode(dest));
                while (true) {
                    prev = changedAt.get(temp);
                    if(prev == Integer.MAX_VALUE){
                        break;
                    }
                    pathList.add(0,graph.getNode(prev));
                    temp = prev;
                }
                pathList.add(0,graph.getNode(src));
                return pathList;
            }
            currNode.setTag(1);
            Iterator <EdgeData> itEdges = graph.edgeIter(currNode.getKey());
            while(itEdges.hasNext()){
                EdgeData e = itEdges.next();
                if(graph.getNode(e.getDest()).getTag() == 1){
                    continue;
                }
                if(shortestPath.get(currNode.getKey()) + e.getWeight() < shortestPath.get(e.getDest())){
                    shortestPath.put(e.getDest(), shortestPath.get(currNode.getKey())+e.getWeight());
                    changedAt.put(e.getDest(), currNode.getKey());
                }
            }
        }
    }



    @Override
    public NodeData center() {

        int size = this.graph.nodeSize();
        double min =  Double.MAX_VALUE;
        double [][] mat = new double[size][size];
        double maxrow = 0,maxmin=Double.MAX_VALUE;
        NodeData ans =null;
        for (int k=0 ; k <size ;k++)
        {
            for(int m=0;m<size;m++) {
                if(k==m)
                    mat[k][m]=Integer.MAX_VALUE;
                mat[k][m]=shortestPathDist(k,m);
            }
        }
        for (int i=0 ; i <size ;i++)
        {
            for(int j=0;j<size;j++) {
                if(i==j)
                    continue;
                if(maxrow<mat[i][j])
                    maxrow=mat[i][j];
            }
            if(maxmin>maxrow) {
                maxmin = maxrow;
                ans=this.graph.getNode(i);
            }
        }
        return ans;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        int size = cities.size();
        double [] [] mat = new double[size][size];

        //save all shortest paths between every two nodes in a matrix
        for(int i = 0 ; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j)
                    mat[i][i] = Double.MAX_VALUE;
                mat[i][j] = shortestPathDist(cities.get(i).getKey(), cities.get(j).getKey());
            }
        }
        double shortest= Double.MAX_VALUE, curr=0;
        int [] ans = new int[cities.size()];
        int [] Per = new int[cities.size()];
        ArrayList <int[]> permuted= new ArrayList<>();
        //init both arrays to be the indexes of the original list
        for(int i = 0 ;i < cities.size();i++ )
        {
            ans[i]=i;
            Per[i]=i;
        }
        //save all the permutaions of the array per into permuted list
        trnsf(permuted,Per,Per.length);
        //go over al the permuted arrays
        for(int i=0;i < permuted.size() ; i++)
        {
            Per=permuted.get(i);
            //compute the path dist between all of the nodes in the array
            for(int j= 0 ; j < cities.size()-1 ; j++){
                curr+=mat[Per[j]][Per[j+1]];
            }
            //check if the current array generated the shortest path
            if(curr<shortest){
                shortest=curr;
                ans=Per;
            }
        }
        List<NodeData> end = new ArrayList<>();
        //put all the elements from the ans array (and the nodes we need to go through between them) to the end list
        for (int i=0;i<ans.length-1;i++){
            end.addAll(shortestPath(ans[i],ans[i+1]));
        }
        return end;

    }
    private void trnsf(ArrayList<int[]> temp, int [] p, int e)
    {
        if(e==1)
            temp.add(p) ;
        else{
            e=e-1;
            trnsf(temp, p, e);

            for(int i=0 ; i< e;i++)
            {
                if(e%2==0)
                {
                    int Temp = p[i];
                    p[i] = p[e];
                    p[e] = Temp;
                }
                else
                {
                    int Temp = p[0];
                    p[0] = p[e];
                    p[e] = Temp;
                }
                trnsf(temp, p, e);
            }
        }
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