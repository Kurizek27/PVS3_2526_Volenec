package filing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class slepestrevo {
    public static void main(String[] args) {
        Random r = new Random();
        String path = "added.txt";
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            for (int i = 0; i < 20; i++) {
                pw.println(r.nextInt(100, 10000000));
            }
            pw.close();

            PrintWriter another = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            another.println("test");
            another.close();
        } catch (IOException exception){
            System.out.println("skill issue" + exception.getMessage());
        }
    }
}
