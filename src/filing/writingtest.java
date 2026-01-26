package filing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class writingtest {
    public static void main(String[] args) {
        String filename = "coords.txt";
        Random rnd = new Random();
        FileWriter fw;

        {
            try {
                fw = new FileWriter(filename);
                for (int i = 0; i < 10000; i++) {
                    fw.write(String.valueOf(rnd.nextInt(-10000,10001)));
                    fw.write(" , ");
                    fw.write(String.valueOf(rnd.nextInt(-10000,10001)));
                    fw.write("\n");
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                for (int i = 0; i < 10000; i++) {
                    bw.write(rnd.nextInt(-10000, 10001) + "," + rnd.nextInt(-10000, 10001));
                    bw.newLine();
                }
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}