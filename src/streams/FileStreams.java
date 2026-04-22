package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class FileStreams {
    public static void main(String[] args) {
        String path = "data/Movies.txt";

        try {
            List<Movie> movies = Files.lines(Path.of(path))
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(parts -> new Movie(
                            parts[0],
                            Integer.parseInt(parts[1]),
                            Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]))
                    )
                    .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                    .toList();

            movies.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Something went wrong with reading a file");;
        }
    }
}