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
            String N = map.get("Nodes").toString();
            N = N.replace("{", "");
            N = N.substring(1, N.length() - 2);
            String[] Nodes = N.split("}, ");
            Nodes[index] = Nodes[index].replace("pos=", "");
            String[] tmp = Nodes[index].split(",");
            this.x = Double.parseDouble(tmp[0]);
            this.y = Double.parseDouble(tmp[1]);
            this.z = Double.parseDouble(tmp[2]);
            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
