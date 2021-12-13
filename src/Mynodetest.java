    import static org.junit.jupiter.api.Assertions.*;
    import org.junit.jupiter.api.Test;

public class Mynodetest {
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


    @Test
    void getTag() {
        assertEquals(tag,n1.getTag());

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
        n4.setTag(106);
        assertEquals(106,n4.getTag());
    }



}
