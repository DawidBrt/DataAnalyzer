package analyzer;

import ahp.AHP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reader.ReadJsonFromAllegroFile;

public class Main {

    public static void main(String argv[]) {
        ReadJsonFromAllegroFile reader = new ReadJsonFromAllegroFile("cat256094.txt");

        JSONObject promoted = (JSONObject) (reader.getJsonObject()).get("items");

        JSONArray promoetedList = (JSONArray) promoted.get("promoted");
        System.out.println(promoetedList.size());

        System.out.println("\nPromoList names:");

//        for (int i = 0; i < promoetedList.size(); i++) {
        for (int i = 0; i < 2; i++) {
            JSONObject element = (JSONObject) promoetedList.get(i);
            System.out.println("Name: " + reader.name(element));
            System.out.println("Delivery: " + reader.isDeliveryFree(element));
            System.out.println("Auction: " + reader.isAuction(element));
            System.out.println("Units: " + reader.availableUnits(element));
            System.out.println();
        }

        int numberOfComparision = 3;
        String labels[] = {"Delivery ", "Auction  ", "Units    "};

        AHP ahp = new AHP(numberOfComparision);
        double compArray[] = ahp.getPairwiseComparisonArray();
        compArray[0] = 1.0 / 5.0;
        compArray[1] = 1.0 / 2.0;
        compArray[2] = 4.0;

        ahp.setPairwiseComparisonArray(compArray);
        System.out.println("Ranking");
        for (int k = 0; k < ahp.getWeights().length; k++) {
            System.out.println(labels[k] + ": " + ahp.getWeights()[k] * 100);
        }


    }
}
