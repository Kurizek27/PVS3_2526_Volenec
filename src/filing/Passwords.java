package filing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Passwords {
    public static void main(String[] args) {
       String dirPath = "data/pins/";
       List<String> usernames;
       List<Integer> pins = new ArrayList<>();

       File directory = new File(dirPath);

       if(!directory.exists() || !directory.isDirectory()){
           System.out.println("Not a directory");
           return;
       }

       String usernamePath = dirPath + "usernames.txt";
       String pinsPath = dirPath + "AllPINs.txt";

        try {
            List<String> stringPins = Files.readAllLines(Path.of(pinsPath));
            usernames = Files.readAllLines(Path.of(usernamePath));
            for(String pin : stringPins){
                pins.add(Integer.parseInt(pin));
            }
            for (int i = 1; i < 6; i++) {
                String attemptDir = dirPath + "attempts_" + i + ".txt";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
