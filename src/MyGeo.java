import api.GeoLocation;

public class MyGeo implements GeoLocation {
     private double x;
    private double y;
    private double z;

    public MyGeo(double x , double y, double z ){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public MyGeo(double x , double y ){// the permetr z is 0 in all the cases
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }
    public void setx(double a){
    this.x =a;
    }
    public void sety (double b){
    this.y=b;
    }
    public void setz(double c){
    this.z = c;
    }

    @Override
    public double distance(GeoLocation g) {
        double dist=Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2);
        dist=Math.sqrt(dist);
        return dist;

    }
}
