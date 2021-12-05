import api.EdgeData;
import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class MyEdge implements EdgeData {
    private int src;
    private int dest;
    private double whight;
    private String info;
    private int tag;

    public MyEdge(String json_file, int index) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);
            String E = map.get("Edges").toString();
            E = E.replace("{", "");
            E = E.substring(1, E.length() - 2);
            String[] Edges = E.split("}, ");
            String[] tmp = Edges[index].split(",");
            tmp[0] = tmp[0].replace("src=", "");
            tmp[1] = tmp[1].replace(" w=", "");
            tmp[2] = tmp[2].replace(" dest=", "");
            reader.close();
            double tmpSrc = Double.parseDouble(tmp[0]);
            double tmpDest = Double.parseDouble(tmp[2]);

            this.src = (int) tmpSrc;
            this.whight = Double.parseDouble(tmp[1]);
            this.dest = (int) tmpDest;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.info = "";
    }
    public MyEdge(MyEdge edge){
        this.src= edge.getSrc();
        this.dest= edge.getDest();
        this.whight = edge.getWeight();
        this.info = edge.getInfo();
        this.tag =edge.getTag();
    }
    public MyEdge(String e) {
        String[] ed = e.split(", ");
        ed[0] = ed[0].replace("src=", "");
        ed[1] = ed[1].replace("w=", "");
        ed[2] = ed[2].replace("dest=", "");
        this.src = (int) Double.parseDouble(ed[0]);
        this.whight = Double.parseDouble(ed[1]);
        this.dest = (int) Double.parseDouble(ed[2]);
        this.tag = 0;
        this.info = "";
    }
    public MyEdge(int src, int dest, double wh, String in , int tag ){
        this.src= src;
        this.dest= dest;
        this.whight = wh;
        this.info = in;
        this.tag =tag;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    public void setsrc(int s ){
        this.src =s;
    }

    @Override
    public int getDest() {
        return this.dest;
    }
    public void setSrc(int x){
        this.src = x;
    }

    @Override
    public double getWeight() {
        return this.whight;
    }
    public void sedwhiget(double x){
        this.whight=x;
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
