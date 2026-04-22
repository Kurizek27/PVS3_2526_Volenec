package exams.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class TracksStreaming {

    public static void main(String[] args) {
        String data = "data/tracks.txt";

        try {
            List<Track> tracks = Files.lines(Path.of(data))
                    .map(line -> line.split(";"))
                    .map(tokens -> new Track(
                            tokens[0],
                            Integer.parseInt(tokens[3]),
                            Integer.parseInt(tokens[1]),
                            Double.parseDouble(tokens[2])))
                    .filter(track -> track.getRating() >= 8.5)
                    .sorted(Comparator.comparingDouble(Track::getRating).reversed())
                    .limit(10)
                    .toList();

            tracks.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


class Track {
    String nazev;
    int lenght;
    int year;
    double rating;

    public Track(String nazev, int lenght, int year, double rating) {
        this.nazev = nazev;
        this.lenght = lenght;
        this.year = year;
        this.rating = rating;
    }

    public String getNazev() {
        return nazev;
    }

    public int getLenght() {
        return lenght;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return nazev + " - " + lenght / 60 + ":" + lenght % 60 + " " + getRating();
    }
}
