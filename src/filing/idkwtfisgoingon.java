package filing;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class idkwtfisgoingon {
    public static void main(String[] args) {
        String path = "data/textInput.txt";
        File f = new File(path);
        int charcounter = 0;
        try {
            FileReader fr = new FileReader(f);
            int input;
            while((input = fr.read()) != -1){
               charcounter++;
            }
            fr.close();
        } catch (IOException exception) {
            System.out.println("File not found");
        }
        System.out.println("Počet znaků: " + charcounter);

        int linecounter = 0;
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));    
            for(String l : lines){
                linecounter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Počet řádek: " + linecounter);
    }
}
