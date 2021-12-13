import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeTest {


    MyGeo g1=new MyGeo(0.0,0.0,0.0);
    MyGeo g2=new MyGeo(-3,5.1,0.0);
    MyGeo g3=new MyGeo(0.8,-6.1,0.0);
    MyGeo g4=new MyGeo(30.2,0.0,0.0);

    String str="";
    int tag=0;

    MyNode n1=new MyNode(0,g1,2.5,tag,str);
    MyNode n2=new MyNode(1,g2,3.98,tag,str);
    MyNode n3=new MyNode(2,g3,8.1,tag,str);
    MyNode n4=new MyNode(3,g4,2.654,5,str);

    MyEdge e1=new MyEdge(n1.getKey(),n2.getKey(),1.25684,"",0);
    MyEdge e2=new MyEdge(6,9,1.3,str,0);
    MyEdge e3=new MyEdge(1,1000,6.22,str,0);
    MyEdge e4=new MyEdge(3,50,100,str,1);
    MyEdge e5=new MyEdge(3,2,2,str,0);


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
        assertEquals(1.3,e2.getWeight());
    }

    @Test
    void getInfo() {
        assertEquals("",e3.getInfo());
    }

    @Test
    void setInfo() {
        e3.setInfo("aviel and chen");
        assertEquals("aviel and chen",e3.getInfo());
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