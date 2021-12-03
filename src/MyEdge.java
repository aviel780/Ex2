import api.EdgeData;

public class MyEdge implements EdgeData {
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;

    public MyEdge(MyEdge edge){
        this.src= edge.getSrc();
        this.dest= edge.getDest();
        this.weight = edge.getWeight();
        this.info = edge.getInfo();
        this.tag =edge.getTag();
    }
    public MyEdge(int src, int dest, double wh, String in , int tag ){
        this.src= src;
        this.dest= dest;
        this.weight = wh;
        this.info = in;
        this.tag =tag;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    public void setSrc(int s ){
        this.src =s;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    public void setDest(int x){
        this.dest = x;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double x){
        this.weight=x;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
    this.info =s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
    this.tag= t;
    }
}
