package streams.maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenreMapping {

    public static void main(String[] args) throws IOException {
       List<Movie> movies = Files.lines(Paths.get("data/movieList.txt"))
                .map(line -> line.split(";"))
                .map(tokens -> new Movie(tokens[0], Integer.parseInt(tokens[1]), tokens[2], Double.parseDouble(tokens[3])))
                .toList();

        System.out.println(movies);

        //mapa zanr-vsechny filmy zanru
        HashMap<String, List<Movie>> genreMap = new HashMap<>();

        for (Movie movie : movies){
            //zanr uz v mape je
            if (genreMap.containsKey(movie.getGenre())){
               //zanr uz tam je, jen k nemu pridam dalsi film
                genreMap.get(movie.getGenre()).add(movie);
            }
            //zanr v mape jeste neni
            else {
                genreMap.put(movie.getGenre(), new ArrayList<>());
                genreMap.get(movie.getGenre()).add(movie);
            }
        }

        //vypis output v podobe
        // Zanr
        // --film1
        // --film2...
        for (String genre : genreMap.keySet()){
            System.out.println(genre);
            for (Movie m : genreMap.get(genre)){
                System.out.println("-- " + m.getName());
            }
        }

        //ziskani ratingu dle zanru
        //zanr: rating
        for (String genre : genreMap.keySet()){
            double avgRating = genreMap.get(genre).stream()
                    .mapToDouble(Movie::getRating)
                    .average()
                    .orElse(0);
            System.out.println(genre + ": " + avgRating);
        }

        //tohle udela to same :)
        //ekvivalent pro sql GROUP BY()
        Map<String, List<Movie>> alsoGenreMap = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre));

        //Mapa Zanr:Rating
        Map<String, Double> avgMap = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getRating)));
        System.out.println(avgMap);

        //Mapa Zanr:PocetFilmu
        Map<String, Long> countMap = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));
        System.out.println(countMap);
    }
}

class Movie {
    String name;
    int year;
    String genre;
    double rating;

    public Movie(String name, int year, String genre, double rating) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name + "(" + genre + ")" + " [" + year + "]";
    }
}
