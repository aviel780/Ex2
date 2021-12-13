import api.GeoLocation;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import com.google.gson.Gson;

public class MyGeo implements GeoLocation {
     private double x;
    private double y;
    private double z;

    public MyGeo(double x , double y, double z ){
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    public MyGeo(String json_file, int index) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            // convert JSON file to map
            HashMap<?, ?> map = gson.fromJson(reader, HashMap.class);
            // put all the nodes to string
            String Nod = map.get("Nodes").toString();
            // delate the sayn {
            Nod = Nod.replace("{", "");
            //create substring
            Nod = Nod.substring(1, Nod.length() - 2);
            // puse all the nodes to string list by the sayn
            String[] Nodeslist = Nod.split("}, ");
            // delate the word pos form thr list
            Nodeslist[index] = Nodeslist[index].replace("pos=", "");
            // puse all the nodesto string list
            String[] tmplist = Nodeslist[index].split(",");
            this.x = Double.parseDouble(tmplist[0]);
            this.y = Double.parseDouble(tmplist[1]);
            this.z = Double.parseDouble(tmplist[2]);
            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public MyGeo(double x , double y ){// the parameter z equals to 0 in all cases
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

    public void setX(double a){
    this.x =a;
    }
    public void setY (double b){
    this.y=b;
    }


    @Override
    public double distance(GeoLocation g) {
        double distan=Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2);
        distan=Math.sqrt(distan);
        return distan;

    }
}
