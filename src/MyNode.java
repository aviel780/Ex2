
import api.GeoLocation;
import api.NodeData;


public class MyNode implements NodeData{
    private int id;
    private double whight;
    private MyGeo location;
    private String info;
    private int tag;


    public MyNode(int id, String pos){
        this.id = id;
        String[] cords = pos.split(",");
        double x = Double.parseDouble(cords[0]);
        double y = Double.parseDouble(cords[1]);
        double high = Double.parseDouble(cords[2]);
        MyGeo locs = new MyGeo(x, y, high);
        this.location = locs;
        this.whight = 0;
        this.tag= 0;
        this.info = "";
    }
    public MyNode(int id, String pos, double w, int t , String i){
        this.id = id;
        String[] cords = pos.split(",");
        double x = Double.parseDouble(cords[0]);
        double y = Double.parseDouble(cords[1]);
        double high = Double.parseDouble(cords[2]);
        MyGeo locs = new MyGeo(x, y, high);
        this.location = locs;
        this.whight = w;
        this.tag= t;
        this.info = i;
    }
    public MyNode(MyNode node){
        this.id = node.id;
        this.location = node.location;
        this.whight = node.getWeight();
        this.info = node.getInfo();
        this.tag = node.getTag();
    }


    @Override
    public int getKey() {
        return  id;
    }

    @Override
    public GeoLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = new MyGeo(p.x(),p.y(),p.z());
    }

    @Override
    public double getWeight() {
        return this.whight;
    }

    @Override
    public void setWeight(double w) {
    this.whight =w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
    this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
    this.tag = t;
    }
}

