import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Mynodetest {
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
        gr1.addNode(n3);
        assertEquals(n3,gr1.getNode(2));

    }

    @Test
    void getTag() {
        gr1.addNode(n1);

    }
    @Test
    void getLocation() {
        gr1.connect(0,1,3.524);
        gr1.connect(1,0,1.2);
    }
    @Test
    void setLocation() {
        gr1.connect(0,1,3.524);
        gr1.connect(1,0,1.2);
    }
    @Test
    void getInfo() {
        gr1.connect(0,1,3.524);
        gr1.connect(1,0,1.2);
    }
    @Test
    void setInfo() {
        gr1.connect(0,1,3.524);
        gr1.connect(1,0,1.2);
    }
    @Test
    void setTag() {
        gr1.connect(0,1,3.524);
        gr1.connect(1,0,1.2);
    }



}
