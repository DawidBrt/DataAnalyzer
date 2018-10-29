package reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadJsonFromAllegroFile {

    private JSONObject jsonObject;

    public ReadJsonFromAllegroFile() {
    }

    public ReadJsonFromAllegroFile(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            this.jsonObject = (JSONObject) obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public boolean isAuction(JSONObject obj) {
        JSONObject sellingMode = (JSONObject) obj.get("sellingMode");
        return sellingMode.get("format").equals("AUCTION");
    }

    public boolean isDeliveryFree(JSONObject obj) {
        JSONObject delivery = (JSONObject) obj.get("delivery");
        return (Boolean) delivery.get("availableForFree");
    }

    public Long availableUnits(JSONObject obj) {
        JSONObject stock = (JSONObject) obj.get("stock");
        return (Long) stock.get("available");
    }

    public String name(JSONObject obj) {
        return (String) obj.get("name");
    }

    public Boolean[] getListOfTrues(JSONObject obj, boolean delivery, boolean auction, Long minUnits, Long maxUnits) {
        //list args: 0 delivery, 1 auction, 2 units
        Boolean list[] = new Boolean[3];
        list[0] = (isDeliveryFree(obj) == delivery);
        list[1] = (isAuction(obj) == auction);
        if (maxUnits.equals((long) -1)) {
            list[2] = (availableUnits(obj) >= minUnits);
        } else {
            list[2] = (availableUnits(obj) >= minUnits && availableUnits(obj) <= maxUnits);
        }
        return list;
    }

}
