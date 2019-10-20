import lombok.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Builder
public class JsonCreator {
    @Setter
    @NonNull
    String filename;
    final JSONArray array = new JSONArray();
    private static final String extension = ".json";

    void putArray(ArrayList<Offer> offers){
        JSONArray overview;
        for(Offer o : offers){
            JSONObject offer = new JSONObject();
            overview = new JSONArray();
            offer.put("name",o.getName());
            offer.put("location",o.getLocation());
            offer.put("price",o.getPrice());
            offer.put("picture",o.getPicture());
            overview.addAll(o.getOverview());
            offer.put("overview",overview);
            array.add(offer);
        }
    }

    void saveJSON(){
        try {
            Files.write(Paths.get(filename+extension),array.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
