package analyzer;

import ahp.AHP;
import reader.ReadJsonFromAllegroFile;

import static org.apache.commons.math3.util.Precision.round;

public class Analyzer {

    private ReadJsonFromAllegroFile reader;
    private AHP ahp;

    public Analyzer(ReadJsonFromAllegroFile reader, AHP ahp) {
        this.reader = reader;
        this.ahp = ahp;
    }

    //System.out.println(labels[k] + ": " + ahp.getWeights()[k] * 100);
    public double[][] boolListToDouble(Boolean[][] list) {
        double weights[] = ahp.getWeights();
        for (int i = 0; i<weights.length; i++) {
            weights[i] = round(weights[i]*100, 2);
        }
        double doubleList[][] = new double[list.length][list[0].length];
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[0].length; j++) {
                doubleList[i][j] = (list[i][j] ? weights[j] : 0);
            }
        }
        return doubleList;
    }

}
