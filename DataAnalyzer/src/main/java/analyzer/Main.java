package analyzer;

import ahp.AHP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import reader.ReadCarJSON;
import reader.ReadJsonFromAllegroFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    void allegro() throws FileNotFoundException {
        ReadJsonFromAllegroFile reader = new ReadJsonFromAllegroFile("MaxSats/Examples/cat256094.txt");

        JSONObject promoted = (JSONObject) (reader.getJsonObject()).get("items");

        JSONArray promoetedList = (JSONArray) promoted.get("promoted");
/*        System.out.println(promoetedList.size());

        System.out.println("\nPromoList names:");

        for (int i = 0; i < 2; i++) {
            JSONObject element = (JSONObject) promoetedList.get(i);
            System.out.println("Name: " + reader.name(element));
            System.out.println("Delivery: " + reader.isDeliveryFree(element));
            System.out.println("Auction: " + reader.isAuction(element));
            System.out.println("Units: " + reader.availableUnits(element));
            System.out.println();
        }
*/
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

        boolean table[][] = new boolean[promoetedList.size()][3];
        for (int i = 0; i < promoetedList.size(); i++) {
            JSONObject element = (JSONObject) promoetedList.get(i);
            table[i] = reader.getListOfTrues(element, true, false, (long) 0, (long) 10);
        }

        TruthTable matrix = new TruthTable(table);
        double[] weightDouble = ahp.getWeights();
        int[] weight = new int[weightDouble.length];
        for (int i = 0; i < weightDouble.length; i++) {
            weight[i] = (int) (weightDouble[i] * 100 + 0.5d);
        }
        matrix.setWeight(weight);
        String file = "MaxSats/Examples/testCat";
        try {
            matrix.toMaxSatWcnfFormat(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MaxSatScriptRunner runScript = new MaxSatScriptRunner();
        runScript.setDatabaseFile(file);
        runScript.setSolution("MaxSats/Solution/testCatSol.txt");
        runScript.runScript();
    }

    void car() throws FileNotFoundException {
        ReadCarJSON reader = new ReadCarJSON("MaxSats/Examples/CarSum.json");

        List<String> country = new ArrayList<>();
        country.add("Poland");
        country.add("Germany");
        country.add("Czech Republic");
        country.add("Russia");
        country.add("Ukraine");
        reader.setCountry(country);

        List<String> city = new ArrayList<>();
        city.add("Nový Hrozenkov");
        city.add("Cisiec");
        city.add("Chełmiec");
        city.add("Kraków");
        reader.setCity(city);

        List<String> make = new ArrayList<>();
        make.add("Honda");
        make.add("Suzuki");
        make.add("Nissan");
        make.add("Toyota");
        make.add("Hyundai");
        make.add("Acura");
        make.add("Mitsubishi");
        make.add("Mazda");
        reader.setMake(make);

        List<String> model = new ArrayList<>();
        model.add("FJ Cruiser");
        model.add("Civic");
        model.add("CR-V");
        model.add("626");
        model.add("Mazda5");
        model.add("Mazda6");
        model.add("Mazda3");
        model.add("Montero");
        model.add("Pajero");
        reader.setModel(model);

        List<String> color = new ArrayList<>();
        color.add("Indigo");
        color.add("Blue");
        color.add("Turquoise");
        reader.setColor(color);

        reader.setMinMaxYear(2005, 2018);
        reader.setMinMaxCost(200, 400);
        reader.setMinMaxHP(100, 200);

        System.out.println("\nPromoList names:");

        String labels[] = {"Country ", "City ", "Make ", "Model ", "Color ", "Year ", "Cost ", "HorsePower "};
        int numberOfComparision = labels.length;

        AHP ahp = new AHP(numberOfComparision);
        double compArray[] = ahp.getPairwiseComparisonArray();
        Random rand = new Random();
        for (int i = 0; i < compArray.length; i++) {
            compArray[i] = (double) (rand.nextInt(9) + 1) / (rand.nextInt(9) + 1);
        }

        ahp.setPairwiseComparisonArray(compArray);
        System.out.println("Ranking");
        for (int k = 0; k < ahp.getWeights().length; k++) {
            System.out.println(labels[k] + ": " + ahp.getWeights()[k] * 100);
        }

        boolean table[][] = reader.getMatrixOfTrues();

        TruthTable matrix = new TruthTable(table);
        double[] weightDouble = ahp.getWeights();
        int[] weight = new int[weightDouble.length];
        for (int i = 0; i < weightDouble.length; i++) {
            weight[i] = (int) (weightDouble[i] * 100 + 0.5d);
        }
        matrix.setWeight(weight);
        String file = "MaxSats/Examples/test";
        try {
            matrix.toMaxSatWcnfFormat(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MaxSatScriptRunner runScript = new MaxSatScriptRunner();
        runScript.setDatabaseFile(file);
        runScript.setSolution("MaxSats/Solution/testSol.txt");
        runScript.runScript();
    }

    void random(int row, int col) throws FileNotFoundException {

        AHP ahp = new AHP(col);
        double compArray[] = ahp.getPairwiseComparisonArray();
        Random rand = new Random();
        for (int i = 0; i < compArray.length; i++) {
            compArray[i] = (double) (rand.nextInt(9) + 1) / (rand.nextInt(9) + 1);
        }

        ahp.setPairwiseComparisonArray(compArray);
//        System.out.println("Ranking");
//        for (int k = 0; k < ahp.getWeights().length; k++) {
//            System.out.println("Row " + k + ": " + ahp.getWeights()[k] * 100);
//        }

        TruthTable matrix = new TruthTable(row, col);
        double[] weightDouble = ahp.getWeights();
        int[] weight = new int[weightDouble.length];

        int specialWeigh = col/10;

        for (int i = 0; i < weightDouble.length; i++) {
            weight[i] = (int) (weightDouble[i] * 100 * specialWeigh + 0.5d);
        }
        matrix.setWeight(weight);
        String file = "MaxSats/Examples/testRandom";
        try {
            matrix.toMaxSatWcnfFormat(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MaxSatScriptRunner runScript = new MaxSatScriptRunner();
        runScript.setDatabaseFile(file);
        runScript.setSolution("MaxSats/Solution/testRandSol.txt");

        runScript.runScript();
    }

    public static void main(String argv[]) throws FileNotFoundException {
//        new Main().allegro();
//        new Main().car();
        int row = 100000;
        int col = 15;
        new Main().random(row, col);
    }
}
