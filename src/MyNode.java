
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
            // replace the open non (delet the openers
            Nodes = Nodes.replace("{", "");
            // substring the node list
            Nodes = Nodes.substring(1, Nodes.length() - 2);
            // puse all the nodes to node list
            String[] Nodesstr = Nodes.split("}, ");
            // remove the index word in each of node
            Nodesstr[index] = Nodesstr[index].replace("pos=", "");
            // puse all the nodes to string list
            String[] tmp = Nodesstr[index].split(",");
            tmp[3] = tmp[3].replace(" id=", "");
            // cast the string to double number
            double idtemp = Double.parseDouble(tmp[3]);
            // casting it to int and dfine at to the if vairebal
            this.id = (int) idtemp;
            // close reader
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public MyNode(String n) { // builder by strings
        String[] nodest = n.split(", "); // puse the nodes to string list by ,
        nodest[0] = nodest[0].replace("pos=", ""); // delet the word pos for the 0 place
        String[] posst = nodest[0].split(",");// puse to string list the permetr
        MyGeo locaiton = new MyGeo(Double.parseDouble(posst[0]),Double.parseDouble(posst[1]),Double.parseDouble(posst[2]));
        this.location = locaiton; // set the location after dfine by the parmeter
        this.tag = -1;
        nodest[1] = nodest[1].replace("id=", "");// delet th id from place 1
        this.id = (int) Double.parseDouble(nodest[1]);// dfine the id
        this.weight = 0;
        this.tag= -1;
        this.info = "White";
    }
    public MyNode(int id, String pos){
        this.id = id;
        String[] cords = pos.split(",");
        MyGeo locaiton = new MyGeo(Double.parseDouble(cords[0]),Double.parseDouble(cords[1]),Double.parseDouble(cords[2]));
        this.location = locaiton;
        this.weight = 0;
        this.tag= -1;
        this.info = "White";
    }
    public MyNode(int id, String pos, double weight, int tag , String info){
        this.id = id;
        String[] cords = pos.split(",");
        MyGeo locaiton = new MyGeo(Double.parseDouble(cords[0]),Double.parseDouble(cords[1]),Double.parseDouble(cords[2]));
        this.location = locaiton;
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

