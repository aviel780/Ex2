
import api.GeoLocation;
import api.NodeData;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


public class MyNode implements NodeData{
    private int id;
    private double weight = Integer.MAX_VALUE;
    private GeoLocation location;
    private String info = "White";
    private int tag = -1;

    public MyNode(String json_file, int index) {
        try {
            // set the location by the permeters
            this.location = new MyGeo(json_file, index);
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            // convert JSON file to map
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);
            // create list of nodes by the word nodes form the map
            String Nodes = map.get("Nodes").toString();
            // replace
            Nodes = Nodes.replace("{", "");
            Nodes = Nodes.substring(1, Nodes.length() - 2);
            String[] Nodesstr = Nodes.split("}, ");
            Nodesstr[index] = Nodesstr[index].replace("pos=", "");
            String[] tmp = Nodesstr[index].split(",");
            tmp[3] = tmp[3].replace(" id=", "");
            double tmpID = Double.parseDouble(tmp[3]);
            this.id = (int) tmpID;
            // close reader
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public MyNode(String n) {
        String[] node = n.split(", ");
        node[0] = node[0].replace("pos=", "");
        String[] pos = node[0].split(",");
        double x = Double.parseDouble(pos[0]);
        double y = Double.parseDouble(pos[1]);
        double z = Double.parseDouble(pos[2]);
        MyGeo l = new MyGeo(x, y, z);
        this.location = l;
        this.tag = -1;
        node[1] = node[1].replace("id=", "");
        this.id = (int) Double.parseDouble(node[1]);
    }
    public MyNode(int id, String pos){
        this.id = id;
        String[] cords = pos.split(",");
        double x = Double.parseDouble(cords[0]);
        double y = Double.parseDouble(cords[1]);
        double high = Double.parseDouble(cords[2]);
        MyGeo locs = new MyGeo(x, y, high);
        this.location = locs;
        this.weight = 0;
        this.tag= -1;
        this.info = "White";
    }
    public MyNode(int id, String pos, double weight, int tag , String info){
        this.id = id;
        String[] cords = pos.split(",");
        double x = Double.parseDouble(cords[0]);
        double y = Double.parseDouble(cords[1]);
        double high = Double.parseDouble(cords[2]);
        MyGeo locs = new MyGeo(x, y, high);
        this.location = locs;
        this.weight = weight;
        this.tag= tag;
        this.info = info;
    }
    public MyNode(int id, MyGeo pos, double weight, int tag , String info){
        this.id = id;
        this.location = pos;
        this.weight = weight;
        this.tag= tag;
        this.info = info;
    }
    public MyNode(int id, MyGeo pos){
        this.id = id;
        this.location = pos;
        this.weight = 0;
        this.tag= -1;
        this.info = "White";
    }
    public MyNode(MyGeo pos){
        this.id = 0;
        this.location = pos;
        this.weight = 0;
        this.tag= -1;
        this.info = "White";
    }

    public MyNode(MyNode node){
        this.id = node.id;
        this.location = node.location;
        this.weight = node.getWeight();
        this.info = node.getInfo();
        this.tag = node.getTag();
    }

    public MyNode() {
        this.id = 0;
        this.location = new MyGeo(0,0,0);
        this.weight = 0;
        this.info = "White";
        this.tag = -1;
    }

    public MyNode(int i) {
        this.id =i;
        this.location = new MyGeo(0,0,0);
        this.weight = 0;
        this.info = "White";
        this.tag = -1;
    }


    @Override
    public int getKey() {
        return  this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {

        this.location = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
    this.weight =w;
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

