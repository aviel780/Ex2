import api.DirectedWeightedGraph;
import api.NodeData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


    class MyGraphAlgoTest {
        MyDirectedWeightedGraph g = new MyDirectedWeightedGraph("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G3.json");
        MyDirectedWeightedGraphAlgorithms g2 = new MyDirectedWeightedGraphAlgorithms(g);


        @Test
        void init() {
            g2.init(g);
            assertEquals(this.g, g2.getGraph());
        }

        @Test
        void getGraph() {
            DirectedWeightedGraph tmp = new MyDirectedWeightedGraph("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G3.json");
            tmp = g2.getGraph();
            assertEquals(tmp, g2.getGraph());
        }

        @Test
        void copy() {
            DirectedWeightedGraph tmp = g2.copy();
            assertEquals(tmp, g2.getGraph());

        }

        @Test
        void isConnected() {
            MyDirectedWeightedGraphAlgorithms Conntected = new MyDirectedWeightedGraphAlgorithms ();
            Conntected.load("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G2.json");
            assertTrue(Conntected.isConnected());

        }

        @Test
        void shortestPathDist() {
            MyDirectedWeightedGraph g = new MyDirectedWeightedGraph("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G3.json");
            MyDirectedWeightedGraphAlgorithms graph = new MyDirectedWeightedGraphAlgorithms(g);
            assertEquals(1.7420530403455134, this.g2.shortestPathDist(15, 40));
        }


        @Test
        void shortestPath() {
            // MyDirectedWeightedGraph g = new MyDirectedWeightedGraph("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G1.json");
            MyNode n1 = new MyNode(0,new MyGeo(3,3,0));
            MyNode n2 = new MyNode(1,new MyGeo(-4,-4,0));
            MyNode n3 = new MyNode(2,new MyGeo(5,-7.5,0));
            MyNode n4 = new MyNode(3,new MyGeo(-2.4,1.3,0));

            MyDirectedWeightedGraphAlgorithms gr= new MyDirectedWeightedGraphAlgorithms();
            DirectedWeightedGraph gr1=new MyDirectedWeightedGraph();
            gr1.addNode(n1);
            gr1.addNode(n2);
            gr1.addNode(n3);
            gr1.connect(0, 1,3);
            gr1.connect(1, 2, 4);
            gr.setG(gr1);
            List<NodeData> e = new ArrayList<>();
            e.add(n1);e.add(n2);e.add(n3);
            List<NodeData> ans = new ArrayList<>();
            ans = gr.shortestPath(0, 2);
            assertEquals(e, gr.shortestPath(0, 2));}

        @Test
        void center() {long start = new Date().getTime();
            MyDirectedWeightedGraphAlgorithms g = new MyDirectedWeightedGraphAlgorithms();
            g.load("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G1.json");
            DirectedWeightedGraph graph = g.getGraph();
            assertEquals(graph.getNode(8), g.center());

            g.load("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G2.json");
            graph = g.getGraph();
            assertEquals(graph.getNode(0), g.center());

            g.load("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G3.json");
            graph = g.getGraph();
            assertEquals(graph.getNode(40), g.center());

//            g.load("data/1000Nodes.json");
//            graph = g.getGraph();
//            assertEquals(graph.getNode(362), g.center());

//            long end = new Date().getTime();
//            double dt = (end - start) / 1000.0;
//            assertTrue(dt < 4);
//            System.out.println("center: " + dt);

        }



        @Test
        void tsp() {
            MyDirectedWeightedGraph g = new MyDirectedWeightedGraph("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G2.json");
            MyDirectedWeightedGraphAlgorithms graph = new MyDirectedWeightedGraphAlgorithms(g);
            List<NodeData> n = new ArrayList<>();
            List<NodeData> temp = new ArrayList<>();
            n.add(this.g.getNode(0));
            n.add(this.g.getNode(1));
            n.add(this.g.getNode(2));
            n.add(this.g.getNode(3));
            n.add(this.g.getNode(9));
            temp = graph.tsp(n);
            List<NodeData> ans = new ArrayList<>();
            ans.add(this.g.getNode(0));
            ans.add(this.g.getNode(1));
            ans.add(this.g.getNode(2));
            ans.add(this.g.getNode(3));
            ans.add(this.g.getNode(9));
            for (int i = 0; i < ans.size(); i++) {
                assertEquals(ans.get(i).getKey(), temp.get(i).getKey());
            }
        }

        @Test
        void save() {
            g2.save("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G7.json");
        }

        @Test
        void load() {
            MyDirectedWeightedGraphAlgorithms graph = new MyDirectedWeightedGraphAlgorithms(g);
            graph.load("C:\\Users\\aviel\\IdeaProjects\\Ex_2\\Ex2\\data\\G3.json");
            assertNull(this.g.getEdge(0, 100));
            assertEquals(0.4303080890843676, this.g.getEdge(21, 33).getWeight());
        }

    }

