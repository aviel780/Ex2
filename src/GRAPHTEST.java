import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GRAPHTEST {

    MyGeo g1=new MyGeo(0.0,0.0,0.0);
    MyGeo g2=new MyGeo(-1.5,3.6,0.0);
    MyGeo g3=new MyGeo(2.55,-6.5,0.0);
    MyGeo g4=new MyGeo(12.61,0.0,0.0);

    String s="";
    int t=0;

    MyNode n1=new MyNode(0,g1,2.5,t,s);
    MyNode n2=new MyNode(1,g2,3.98,t,s);
    MyNode n3=new MyNode(2,g3,8.1,t,s);
    MyNode n4=new MyNode(3,g4,2.654,5,s);

    MyEdge e1=new MyEdge(n1.getKey(),n2.getKey(),1.25684,"",0);
    MyEdge e2=new MyEdge(n2.getKey(),n1.getKey(),0.65166,"",0);
    MyEdge e3=new MyEdge(n2.getKey(),n3.getKey(),2.15189,"",0);
    MyEdge e4=new MyEdge(n3.getKey(),n4.getKey(),3.65166,"",0);
    MyEdge e5=new MyEdge(2,3,2.3,"",0);

    MyDirectedWeightedGraph gr1=new MyDirectedWeightedGraph();


    @Test
    void addNode() {
        gr1.addNode(n1);
        gr1.addNode(n2);
         assertEquals(n1,gr1.getNode(0));
         assertEquals(n2,gr1.getNode(1));
    }

    @Test
    void getNode() {
        gr1.addNode(n2);
        assertEquals(n2,gr1.getNode(1));

    }

    @Test
    void connect() {
         gr1.connect(2,3,2.3);
        assertEquals(e5.getSrc(),gr1.getEdge(2,3).getSrc());
        assertEquals(e5.getDest(),gr1.getEdge(2,3).getDest());
    }

    @Test
    void getEdge() {
        MyDirectedWeightedGraph gr2=new MyDirectedWeightedGraph();
        MyEdge e1=new MyEdge(0,1,1.25684,"",0);
        MyEdge e2=new MyEdge(n2.getKey(),n1.getKey(),0.65166,"",0);
        gr2.addEdge(0,e1);
        gr2.addEdge(0,e2);
        gr2.addEdge(0,e3);


        assertEquals(e1,gr2.getEdge(0,1));

    }
    @Test
    void addEdge() {
        MyDirectedWeightedGraph gr2=new MyDirectedWeightedGraph();
        MyEdge e1=new MyEdge(0,1,1.25684,"",0);
        MyEdge e2=new MyEdge(n2.getKey(),n1.getKey(),0.65166,"",0);
        gr2.addEdge(0,e1);
        assertEquals(e1,gr2.getEdge(0,1));
    }

    @Test
    void nodeIter() {
        gr1.addNode(n1);
        gr1.addNode(n2);
        gr1.addNode(n3);
        gr1.addNode(n4);
        Iterator<NodeData> temp = gr1.nodeIter();
        NodeData tempp=temp.next();
        assertEquals(n1, tempp);
    }

    @Test
    void edgeIter() {
        MyDirectedWeightedGraph gr2=new MyDirectedWeightedGraph();
        MyEdge e1=new MyEdge(0,1,1.25684,"",0);
        MyEdge e2=new MyEdge(1,2,2.84,"",0);
        MyEdge e3=new MyEdge(2,3,8.456,"",0);
        MyEdge e4=new MyEdge(3,4,6.2498,"",0);
        gr2.addEdge(0,e1);
        gr2.addEdge(1,e2);
        gr2.addEdge(2,e3);
        gr2.addEdge(3,e4);
        gr2.addNode(n1);
        gr2.addNode(n2);
        gr2.addNode(n3);
        gr2.addNode(n4);
        Iterator<EdgeData> temp = gr2.edgeIter();
        EdgeData tempp=temp.next();
        assertEquals(e1, tempp);
    }

    @Test
    void testEdgeIter() {
        MyDirectedWeightedGraph gr2=new MyDirectedWeightedGraph();
        MyEdge e1=new MyEdge(0,1,1.25684,"",0);
        MyEdge e2=new MyEdge(1,2,2.84,"",0);
        MyEdge e3=new MyEdge(2,3,8.456,"",0);
        MyEdge e4=new MyEdge(3,4,6.2498,"",0);
        gr2.addEdge(0,e1);
        gr2.addEdge(1,e2);
        gr2.addEdge(2,e3);
        gr2.addEdge(3,e4);
        gr2.addNode(n1);
        gr2.addNode(n2);
        gr2.addNode(n3);
        gr2.addNode(n4);
        Iterator<EdgeData> temp = gr2.edgeIter(0);
        EdgeData tempp=temp.next();
        assertEquals(e1, tempp);
    }

    @Test
    void removeNode() {
        gr1.addNode(n1);
        gr1.addNode(n2);
        gr1.addNode(n3);
        gr1.addNode(n4);
        gr1.removeNode(n1.getKey());
        gr1.removeNode(n2.getKey());
        assertEquals(2,gr1.nodeSize());
    }

    @Test
    void removeEdge() {
        MyDirectedWeightedGraph gr2=new MyDirectedWeightedGraph();
        MyEdge e1=new MyEdge(0,1,1.25684,"",0);
        MyEdge e2=new MyEdge(1,2,2.84,"",0);
        MyEdge e3=new MyEdge(2,3,8.456,"",0);
        MyEdge e4=new MyEdge(3,4,6.2498,"",0);
        gr2.addEdge(0,e1);
        gr2.addEdge(1,e2);
        gr2.addEdge(2,e3);
        gr2.addEdge(3,e4);
        gr2.removeEdge(0,1);
        gr2.removeEdge(1,2);

        assertEquals(2,gr2.edgeSize());

    }

    @Test
    void nodeSize() {
        gr1.addNode(n1);
        gr1.addNode(n2);
        gr1.addNode(n3);
        gr1.addNode(n4);
        assertEquals(4,gr1.nodeSize());
    }

    @Test
    void edgeSize() {
        MyDirectedWeightedGraph gr2=new MyDirectedWeightedGraph();
        MyEdge e1=new MyEdge(0,1,1.25684,"",0);
        MyEdge e2=new MyEdge(1,2,2.84,"",0);
        MyEdge e3=new MyEdge(2,3,8.456,"",0);
        MyEdge e4=new MyEdge(3,4,6.2498,"",0);
        gr2.addEdge(0,e1);
        gr2.addEdge(1,e2);
        gr2.addEdge(2,e3);
        gr2.addEdge(3,e4);
        assertEquals(4,gr2.edgeSize());

    }

    @Test
    void getMC() {

    }
}