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


    @Test
    void getTag() {
        assertEquals(t,n1.getTag());

    }
    @Test
    void getLocation() {
        assertEquals(g1,n1.getLocation());
    }
    @Test
    void setLocation() {
      n1.setLocation(g3);

      assertEquals(g3,n1.getLocation());
    }
    @Test
    void getInfo() {
        assertEquals("",n3.getInfo());
    }
    @Test
    void setInfo() {
        n3.setInfo("the info is chingd");
        assertEquals("the info is chingd",n3.getInfo());
    }
    @Test
    void setTag() {
        n4.setTag(99);
        assertEquals(99,n4.getTag());
    }



}
