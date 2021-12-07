import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeTest {


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
    MyEdge e2=new MyEdge(0,9,0.5,s,0);
    MyEdge e3=new MyEdge(1,455,26,"",0);
    MyEdge e4=new MyEdge(3,203,23.2,s,1);
    MyEdge e5=new MyEdge(3,2,2,s,0);


    @Test
    void getDest1() {
        assertEquals(n2.getKey(),e1.getDest());
    }

    @Test
    void getSrc1() {
        assertEquals(n1.getKey(),e1.getSrc());
    }

    @Test
    void getSrc() {
        assertEquals(n4.getKey(),e4.getSrc());
    }

    @Test
    void getDest() {
        assertEquals(n3.getKey(),e5.getDest());
    }

    @Test
    void getWeight() {
        assertEquals(0.5,e2.getWeight());
    }

    @Test
    void getInfo() {
        assertEquals("",e3.getInfo());
    }

    @Test
    void setInfo() {
        e3.setInfo("hi");
        assertEquals("hi",e3.getInfo());
    }

    @Test
    void getTag() {
        assertEquals(0,e5.getTag());
    }

    @Test
    void setTag() {
        e5.setTag(5);
        assertEquals(5,e5.getTag());
    }
}