package analyzer;

import java.io.PrintWriter;
import java.util.Random;

public class TruthTable {

    private boolean[][] table;

    //optional value, need to Wcnf
    private int[] weight;

    public TruthTable(int row, int col) {
        this.table = generateTruthTable(row, col);
    }

    public TruthTable(boolean[][] table) {
        this.table = table;
    }

    public int[] getWeight() {
        return weight;
    }

    public void setWeight(int[] weight) {
        this.weight = weight;
    }

    public void toMaxSatCnfFormat(String filePath) throws Exception {
        int row = table.length;
        if (row < 1) {
            throw new Exception("Bad data");
        }
        int col = table[0].length;
        PrintWriter writer = new PrintWriter(filePath);
        writer.println("p cnf " + col + " " + row);
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; ) {
                if (table[i][j]) {
                    writer.print(++j + " ");
                } else {
                    writer.print("-" + ++j + " ");
                }
            }
            writer.println("0");
        }
        writer.close();
    }

    public void toMaxSatWcnfFormat(String filePath) throws Exception {
        if (weight == null || weight.length < 1) {
            throw new Exception("No weight");
        }
        int row = table.length;
        if (row < 1) {
            throw new Exception("Bad data");
        }
        int col = table[0].length;
        int rowPlusWeights = row + weight.length;
        int sum = 0;
        PrintWriter writer = new PrintWriter(filePath);
        writer.println("p wcnf " + col + " " + rowPlusWeights);
        for (int i = 0; i < weight.length; ) {
            sum += weight[i];
            writer.println(weight[i] + " " + ++i + " 0");
        }
        for (int i = 0; i < table.length; i++) {
            writer.print(sum + " ");
            for (int j = 0; j < table[0].length; ) {
                if (table[i][j]) {
                    writer.print(++j + " ");
                } else {
                    writer.print("-" + ++j + " ");
                }
            }
            writer.println("0");
        }
        writer.close();
    }

    public boolean[][] generateTruthTable(int row, int col) {
        boolean[][] table = new boolean[row][col];
        Random rand = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                table[i][j] = rand.nextBoolean();
            }
        }
        return table;
    }

}
