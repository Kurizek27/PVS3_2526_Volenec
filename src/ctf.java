import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class ctf {
    public static void main(String[] args) {
        String line;
        File adresar = new File("data/ctf/final");

        for (File currentsoubor : adresar.listFiles() ) {

            try {
                if(currentsoubor.isDirectory()){
                    return;
                }
                BufferedReader br = new BufferedReader(new FileReader(currentsoubor));
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    String normal = parts[0];
                    int charcount = Integer.parseInt(parts[1]);
                    String reversed = parts[2];
                    String verifyreversed = new StringBuilder(reversed).reverse().toString();
                    if (normal.length() != charcount || !normal.equals(verifyreversed)) {
                        System.out.println("CTF: "+ line +" File: "+currentsoubor.getName());
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
