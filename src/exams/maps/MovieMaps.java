package exams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MovieMaps {

    public static void printDirectorMinutes(List<Movie> movies, String name) {
        // TODO
        // Metoda vypíše celkovou délku všech filmů od režiséra jménem "name".
        // Délka filmu je součet minut všech jeho scén.
    }

    public static void main(String[] args) throws IOException {
        // Dodělejte třídu Movie a Scene (gettery, settery, constructor, toString + co uznáte za vhodné)
        // Načtěte soubory movies.csv a scenes.csv

        List<Movie> movies = Files.lines(Paths.get("data/movies.csv"))
                .skip(1)
                .map(line -> line.split(",", 3))
                .map(parsed -> new Movie(
                        Integer.parseInt(parsed[0]),
                        parsed[1],
                        parsed[2]
                )).toList();

        List<Scene> scenes = Files.lines(Paths.get("data/scenes.csv"))
                .skip(1)
                .map(line -> line.split(",", 4))
                .map(split -> new Scene(
                        Integer.parseInt(split[1]),
                        split[2],
                        Integer.parseInt(split[3])
                )).toList();

        // TODO: Propojte scény s filmy podle movieId.
        // Implementujte metodu printDirectorMinutes.
        // Vypište, kolik filmů natočil každý režisér.
        // Vypište top 5 režisérů dle celkové délky jejich filmů v minutách.
    }
}

class Movie {
    private int movieId;
    private String title;
    private String director;
    private List<Scene> scenes = new ArrayList<>();

    public int getTotalMinutes() {
        // TODO
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }

    public Movie(int movieId, String title, String director) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.scenes = new ArrayList<>();
    }
}

class Scene {
    private int movieId;
    private String title;
    private int durationMinutes;

    public Scene(int movieId, String title, int durationMinutes) {
        this.movieId = movieId;
        this.title = title;
        this.durationMinutes = durationMinutes;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
