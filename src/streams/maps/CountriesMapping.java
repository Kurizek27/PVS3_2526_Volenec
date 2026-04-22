package streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountriesMapping {
    public static void main(String[] args) {
        String dataPath = "data/countries.txt";

        try {
            List<Country> countries = Files.lines(Path.of(dataPath))
                    .map(line -> line.trim().split(";"))
                    .map(parts -> new Country(
                            parts[0],
                            parts[1],
                            Long.parseLong(parts[2]),
                            Double.parseDouble(parts[3])
                    ))
                    .toList();

            // Vypise vsechny zeme
            // countries.forEach(System.out::println);

            // počet zemí v kontinentu
            Map<String, Long> countByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent, Collectors.counting()));

            for (String continent : countByContinent.keySet()) {
                System.out.println(continent + " --- " + countByContinent.get(continent));
            }

            // vsechny staty v kontinentu
            Map<String, List<Country>> countriesByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent));

            // Africa:
            // --- Kongo
            // --- Egypt
            // Europe
            // --- Slovakia

            for (String continent : countByContinent.keySet()) {
                System.out.println(continent + ":");
                for (Country country : countriesByContinent.get(continent)){
                    System.out.println("--- " + country.getName());
                }
            }

            // populace na continent
            Map<String, Long> populationByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent, Collectors.summingLong(Country::getPopulation)));

            for (String continent : populationByContinent.keySet()) {
                System.out.println(continent + " --- " + populationByContinent.get(continent));
            }

            // průměrný věk dožití podle kontinentu
            Map<String, Double> avgLifeExpectancyByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent, Collectors.averagingDouble(Country::getLifeExpectancy)));

            for (String continent : avgLifeExpectancyByContinent.keySet()) {
                System.out.println(continent + " --- " + avgLifeExpectancyByContinent.get(continent));
            }

        } catch (IOException e) {
            System.err.println("Something went wrong :-C");
        }
    }
}

class Country {
    String name;
    String continent;
    long population;
    double lifeExpectancy;

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                ", lifeExpectancy=" + lifeExpectancy +
                '}';
    }

    public Country(String name, String continent, long population, double lifeExpectancy) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
}