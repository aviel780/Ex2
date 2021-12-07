import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyGeoTest {

    double x= 0.222;
    double y=2200.0;
    double z= 3.222;
    double u= 0.0001;
    double t=11111.2;

    MyGeo g1 = new MyGeo(x,y,z);
    MyGeo g2 = new MyGeo(u,t,z);

    @Test
    void x() {
        assertEquals(0.222,g1.x());
    }

    @Test
    void y() {
        assertEquals(y,g1.y());
    }

    @Test
    void z() {
        assertEquals(0.0,g2.z());
    }

    @Test
    void distance() {
        double ans =0;
        ans = Math.sqrt(Math.pow(g1.x()-g2.x(),2) + Math.pow(g1.y()-g2.y(), 2));
        assertEquals(ans ,g2.distance(g1));
    }

    @Test
    void setY() {
        g1.setY(8.9);
        assertEquals(8.9,g1.y());
    }

    @Test
    void setX() {
        g1.setX(0.0);
        assertEquals(0.0,g1.x());
    }

}