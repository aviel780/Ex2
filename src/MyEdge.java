import api.EdgeData;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class MyEdge implements EdgeData {
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag =0;

    public MyEdge(String json_file, int index) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            // convert JSON file to map
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);
            //create list of edges by the word nodes form the map
            String Edge = map.get("Edges").toString();
            // replace the open to non (delet the openers)
            Edge = Edge.replace("{", "");
            // substring the edge list
            Edge = Edge.substring(1, Edge.length() - 2);
            // puse all the nodes to edge list of string
            String[] Edgeslist = Edge.split("}, ");
            // puse all the nodes to edge list of string
            String[] temp = Edgeslist[index].split(",");
            //remove the word src from the place of the srcses
            temp[0] = temp[0].replace("src=", "");
            //remove the word w from the place of the whightes
            temp[1] = temp[1].replace(" w=", "");
            //remove the word dest from the place of the dests
            temp[2] = temp[2].replace(" dest=", "");
            //close the reader
            reader.close();
            // prase the string number double
            double tempSrc = Double.parseDouble(temp[0]);
            // prase the string number double
            double tempDest = Double.parseDouble(temp[2]);
            //cast the double number and dfine it to the src vairebal
            this.src = (int) tempSrc;
            this.weight = Double.parseDouble(temp[1]);
            //cast the double number and dfine it to the dest vairebal
            this.dest = (int) tempDest;
            this.tag = 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.info = "";
    }

    public MyEdge(MyEdge edge){
        this.src= edge.getSrc();
        this.dest= edge.getDest();
        this.weight = edge.getWeight();
        this.info = edge.getInfo();
        this.tag =edge.getTag();
    }
    public MyEdge(String e) {
        String[] ed = e.split(", ");
        ed[0] = ed[0].replace("src=", "");
        ed[1] = ed[1].replace("w=", "");
        ed[2] = ed[2].replace("dest=", "");
        this.src = (int) Double.parseDouble(ed[0]);
        this.weight = Double.parseDouble(ed[1]);
        this.dest = (int) Double.parseDouble(ed[2]);
        this.tag = 0;
        this.info = "";
    }
    public MyEdge(int src, int dest, double wh, String in , int tag ){
        this.src= src;
        this.dest= dest;
        this.weight = wh;
        this.info = in;
        this.tag =tag;
    }

    public MyEdge() {
        this.src=0;
        this.tag = 0;
        this.info="";
        this.dest = 0;
        this.weight = 0;

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
    public void setwhiget(double x){
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
