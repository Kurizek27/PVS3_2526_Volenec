package streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SeriesMapping {
    public static void main(String[] args) throws IOException {
        // Mapa<Series, List<Episode>>
        List<Series> series = Files.lines(Path.of("data/series.csv"))
                .skip(1)
                .map(line -> line.split(";"))
                .map(tokens -> new Series(tokens[0], tokens[1]))
                .toList();

        List<Episode> episodes = Files.lines(Path.of("data/episodes.csv"))
                .skip(1)
                .map(line -> line.split(";"))
                .map(tokens -> new Episode(
                        Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[1]),
                        Double.parseDouble(tokens[2]),
                        tokens[3]
                ))
                .toList();


        Map<Series, List<Episode>> seriesMap = episodes.stream()
                .collect(Collectors.groupingBy(
                        episode -> series.stream()
                                .filter(s -> s.getCode().equals(episode.getCode()))
                                .findAny()
                                .orElseThrow()
                ));

        for(Series s : seriesMap.keySet()){
            System.out.println(s.getTitle() + " " + s.getCode());
            seriesMap.get(s).forEach(episode -> System.out.println("\t" + episode));
        }

        // Vypsat průměrné hodnocení na seriál
        //
        // " Chernobyl - 9.2 "
        //
        // Bonus - vypsat hodnocení na jednu sezonu
    }

}

class Episode {
    int season;
    int epNum;
    double rating;
    String code;

    @Override
    public String toString() {
        return "Episode{" +
                "season=" + season +
                ", epNum=" + epNum +
                ", rating=" + rating +
                ", code='" + code + '\'' +
                '}';
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpNum() {
        return epNum;
    }

    public void setEpNum(int epNum) {
        this.epNum = epNum;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Episode(int season, int epNum, double rating, String code) {
        this.season = season;
        this.epNum = epNum;
        this.rating = rating;
        this.code = code;
    }
}

class Series {
    String code;
    String title;
    List<Episode> episodes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return Objects.equals(code, series.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Series(String code, String title) {
        this.code = code;
        this.title = title;
    }
}