package kubashitmyshit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class bangerchooser {
    public static void main(String[] args) {
        String path = "data/tracks.txt";


        try (Stream<String> lines = Files.lines(Paths.get(path))) {lines.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    class Music {
        String name;
        int year;
        long rating;
        int length;

        @Override
        public String toString() {
            return "Music{" +
                    "name='" + name + '\'' +
                    ", year=" + year +
                    ", rating=" + rating +
                    ", length=" + length +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public long getRating() {
            return rating;
        }

        public void setRating(long rating) {
            this.rating = rating;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public Music(String name, int year, long rating, int length) {
            this.name = name;
            this.year = year;
            this.rating = rating;
            this.length = length;
        }
    }
}
