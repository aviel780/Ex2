import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import com.google.gson.Gson;
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
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }
    public static MyDirectedWeightedGraph readGRaphFromJson(String json_file) {
        MyDirectedWeightedGraph ans = new MyDirectedWeightedGraph();
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
            int sizeOfNodes = Nodes.length;
            int sizeOfEdges = Edges.length;

            for (int i = 0; i < sizeOfNodes; i++) {
                String temp = Nodes[i];
                MyNode tempnode= new MyNode(i, temp);
                ans.addNode(tempnode);
            }
            for (int i = 0; i < sizeOfEdges; i++) {
                MyEdge e = new MyEdge(Edges[i]);
                HashMap<Integer, EdgeData> newedge = new HashMap<Integer,EdgeData>();
                ans.addEdge(e.getDest(),e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }
}
