package exams.serialization;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.List;
import java.text.NumberFormat;
import java.text.ParseException;

public class BookSerializer {
    public static void main(String[] args) {
        Locale currentLocale = Locale.getDefault();
        String path = "data/books.txt";
        File f = new File(path);
        try {
            List<String> books = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
            for(String b : books){
                String[] split = b.split(";");
                double price = NumberFormat.getInstance(currentLocale).parse(split[4]).doubleValue();
                Book book = new Book(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]), price);
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }
}
