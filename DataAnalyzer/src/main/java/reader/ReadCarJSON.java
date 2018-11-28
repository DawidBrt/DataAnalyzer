package reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadCarJSON {

    private JSONArray jsonArray;

    private List<String> country;
    private List<String> city;
    private List<String> make;
    private List<String> model;
    private List<String> color;
    private int minYear;
    private int maxYear;
    private int minCost;
    private int maxCost;
    private int minHP;
    private int maxHP;


    public ReadCarJSON() {
    }

    public ReadCarJSON(String filePath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            this.jsonArray = (JSONArray) obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void smt() {
        JSONObject obj = (JSONObject) jsonArray.get(0);
        System.out.println(obj.toString());
        System.out.println(obj.get("Year").toString());
    }

    private boolean inList(List<String> list, String string) {
        return list.contains(string);
    }

    private boolean inInt(int number, int min, int max) {
        return (number >= min && number <= max);
    }

    public boolean[] getListOfTrues(int number) {
        JSONObject obj = (JSONObject) jsonArray.get(number);
        boolean list[] = new boolean[8];
        list[0] = inList(getCountry(), obj.get("Country").toString());
        list[1] = inList(getCity(), obj.get("City").toString());
        list[2] = inList(getMake(), obj.get("Make").toString());
        list[3] = inList(getModel(), obj.get("Model").toString());
        list[4] = inList(getColor(), obj.get("Color").toString());
        list[5] = inInt(Integer.parseInt(obj.get("Year").toString()), minYear, maxYear);
        list[6] = inInt(Integer.parseInt(obj.get("Cost").toString()), minCost, maxCost);
        list[7] = inInt(Integer.parseInt(obj.get("HorsePower").toString()), minHP, maxHP);
        return list;
    }

    public boolean[][] getMatrixOfTrues() {

        int size = jsonArray.size();
        boolean matrix[][] = new boolean[size][8];
        for (int i = 0; i < size; i++) {
            matrix[i] = getListOfTrues(i);
        }
        return matrix;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    public List<String> getMake() {
        return make;
    }

    public void setMake(List<String> make) {
        this.make = make;
    }

    public List<String> getModel() {
        return model;
    }

    public void setModel(List<String> model) {
        this.model = model;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public int getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    public int getMinHP() {
        return minHP;
    }

    public void setMinHP(int minHP) {
        this.minHP = minHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setMinMaxHP(int minHP, int maxHP){
        this.minHP = minHP;
        this.maxHP = maxHP;
    }

    public void setMinMaxCost(int minCost, int maxCost){
        this.minCost = minCost;
        this.maxCost = maxCost;
    }

    public void setMinMaxYear(int minYear, int maxYear){
        this.minYear = minYear;
        this.maxYear = maxYear;
    }
}
