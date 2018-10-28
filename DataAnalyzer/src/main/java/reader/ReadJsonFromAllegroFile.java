package reader;

import org.json.simple.JSONArray;
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
        return delivery.get("availableForFree").equals("true");
    }

    public Long availableUnits(JSONObject obj) {
        JSONObject stock = (JSONObject) obj.get("stock");
        return (Long) stock.get("available");
    }

    public String name(JSONObject obj) {
        return (String) obj.get("name");
    }

    public static void main(String[] args) {
//        ReadJsonFromAllegroFile reader = new ReadJsonFromAllegroFile("cat256094.txt");
//
//        JSONObject promoted = (JSONObject) ((JSONObject) reader.getJsonObject()).get("items");
//
//        JSONArray promoetedList = (JSONArray) promoted.get("promoted");
//        System.out.println(promoetedList.size());
//
//        System.out.println("\nPromoList names:");
//
//        for (int i = 0; i < promoetedList.size(); i++) {
//            JSONObject element = (JSONObject) promoetedList.get(i);
//            System.out.println("Name: " + reader.name(element));
//            System.out.println("Delivery: " + reader.isDeliveryFree(element));
//            System.out.println("Auction: " + reader.isAuction(element));
//            System.out.println("Units: " + reader.availableUnits(element));
//            System.out.println();
//        }

    }

}
