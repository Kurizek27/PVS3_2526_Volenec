package serial;

import java.io.*;
import java.util.Arrays;

public class work {
    public static void main(String[] args) {
        String filepath = "data/countries/parsedcountries.txt";
        for (int i = 1; i < 4; i++) {
            String csvpath = "data/countries/country" + i + ".csv";
            try {
                BufferedReader br = new BufferedReader(new FileReader(csvpath));
                String line;

                while((line = Arrays.toString(br.readLine().split(","))) != null){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
                    bw.write(line);
                    bw.close();
                }
                br.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream());
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        for (int i = 1; i < 4; i++) {
            String serialpath = "data/countries/country" + i + ".ser";

        }
    }
}

class SerialisedPoint implements Serializable {
    @Serial
    private static final long serialVersionUid = 1;
    int x;
    int y;

    public SerialisedPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}