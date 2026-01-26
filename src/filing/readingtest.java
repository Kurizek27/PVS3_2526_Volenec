package filing;

import fileworks.DataImport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readingtest {
    public static void main(String[] args) {
        String path = "data/books.txt";
//        DataImport di = new DataImport(path);
//        while(di.hasNext()){
//            System.out.print(di.readChar());
//        }
//        di.finishImport();

        try {
            File f = new File(path);
            FileReader fr = new FileReader(f);
            while(fr.read() != 1){
                char znak = (char) fr.read();
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("U stupid ts file does not exist");
        } catch (IOException e) {
            System.out.println("u fucking retarded idk what went wrong");
        }


    }
}
