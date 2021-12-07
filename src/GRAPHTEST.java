import org.junit.jupiter.api.Test;

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
    MyEdge e5=new MyEdge(n3.getKey(),n4.getKey(),2.3,"",0);

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

//    @Test
//    void connect() {
//         gr1.connect(2,3,2.3);
//        assertEquals(e5,gr1.getEdge(2,3));
//    }

    @Test
    void getEdge() {
        MyDirectedWeightedGraph gr2=new MyDirectedWeightedGraph();
        MyEdge e1=new MyEdge(n1.getKey(),n2.getKey(),1.25684,"",0);
        MyEdge e2=new MyEdge(n2.getKey(),n1.getKey(),0.65166,"",0);
        gr2.addEdge(n1.getKey(),e1);



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
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}