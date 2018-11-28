package analyzer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MaxSatScriptRunner {
    private String maxSatScript = "MaxSats/run.sh";
    private String databaseFile = "MaxSats/Examples/test";
    private String solution = "MaxSats/Solution/ans.txt";
    private String maxSatProgram = "./MaxSats/open-wbo/open-wbo_static";

    private String result;

    public MaxSatScriptRunner() throws FileNotFoundException {
//        PrintWriter writer = new PrintWriter(solution);
//        writer.print("Brak wyniku.");
//        writer.close();
    }

    public void setDatabaseFile(String path) {
        this.databaseFile = path;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getResult() {
        return this.result;
    }

    public void runScript() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            System.out.println("Start running maxSat at: " + sdf.format(cal.getTime()) );
            Process p = new ProcessBuilder(maxSatScript, maxSatProgram, databaseFile, solution).start();
            while (p.isAlive()) ;
            cal = Calendar.getInstance();
            System.out.println("End work at: " + sdf.format(cal.getTime()) );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
