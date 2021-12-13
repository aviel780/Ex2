import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {

    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph graph;
        try {
            graph = readGRaphFromJson(json_file);
        } catch (Exception e) {
            e.printStackTrace();
            graph = new MyDirectedWeightedGraph();
        }
        return graph;


    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        // ****** Add your code here ******
         DirectedWeightedGraph graph = getGrapg(json_file);
                DirectedWeightedGraphAlgorithms graphAlgo = new MyDirectedWeightedGraphAlgorithms();
                graphAlgo.init(graph);
                return graphAlgo;
        // ********************************

    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file); //if we use the extends way it will work. (this is boaz's line)

    }

    public static MyDirectedWeightedGraph readGRaphFromJson(String json_file) {
        MyDirectedWeightedGraph ans = new MyDirectedWeightedGraph();
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);
            String edg = map.get("Edges").toString();
            edg = edg.replace("{", "");
            edg = edg.substring(1, edg.length() - 2);
            String[] Edgeslist = edg.split("}, ");
            String Nod = map.get("Nodes").toString();
            Nod = Nod.replace("{", "");
            Nod = Nod.substring(1, Nod.length() - 2);
            String[] Nodes = Nod.split("}, ");
            int sizeOfNodes = Nodes.length;
            int sizeOfEdges = Edgeslist.length;

            for (int i = 0; i < sizeOfNodes; i++) {
                String temp = Nodes[i];
                MyNode tempnode= new MyNode(i, temp);
                ans.addNode(tempnode);
            }
            for (int i = 0; i < sizeOfEdges; i++) {
                MyEdge e = new MyEdge(Edgeslist[i]);
                HashMap<Integer, EdgeData> newedge = new HashMap<Integer,EdgeData>();
                ans.addEdge(e.getDest(),e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }
}
