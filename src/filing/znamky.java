package filing;

import java.io.*;
import java.util.Arrays;

public class znamky {
    public static void main(String[] args) {
        String input = "data/znamky.txt";
        String output = "data/znamky/";
        String fileFormat = ".txt";
        String iline;

        File adresar = new File("data/znamky");
        if (!adresar.exists()){
            adresar.mkdirs();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));


            bw.write("Prijmeni;prumer");
            bw.newLine();
            while ((iline = br.readLine()) != null) {
                String parts[] = iline.split(";");
                String jmeno = parts[0];

                double suma = 0;
                int pocetZnamek = parts.length - 1;
                for (int i = 1; i <= pocetZnamek ; i++) {
                    suma += Double.parseDouble(parts[i]);
                }
                double prumer = suma / pocetZnamek;
                System.out.println(jmeno +" "+ prumer);
                bw.write(jmeno +" "+prumer+"\n");
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}