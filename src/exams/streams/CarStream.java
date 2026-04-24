package exams.streams;

import java.util.List;

/**
 * 1. Načtěte soubor data/cars.csv
 *    - přeskočte 1. řádek souboru
 *    - všechny záznamy uložte do List<Car> cars
 * 2. Implementujte všechny metody
 */
public class CarStream {
    public static void main(String[] args) {
        String path = "data/cars.csv";
        List<Car> cars = loadCars(path);

        // Vypište průměrnou maximální rychlost aut značky Porsche

        // Vypište všechny auta levnější než 8000 USD

        // Vypište statistiky o autech na základě jejich "Performance"

        // Vypište všechny auta značky Bugatti nad 420 HP

        // Vypište, kolik procent aut je značky Nissan
    }

    /**
     * Vypíše základní statistiky o autech pomocí {@code summaryStatistics}
     * nad hodnotou {@code performance}.
     *
     * @param cars seznam aut
     */
    private static void printStatistics(List<Car> cars) {

    }

    /**
     * Vrátí nový vyfiltrovaný seznam aut.
     *
     * @param cars seznam aut
     * @param price maximální cena (včetně), kterou mohou mít auta v novém seznamu
     * @return nový vyfiltrovaný seznam aut
     */
    private static List<Car> filterByPrice(List<Car> cars, int price) {
        return null;
    }

    /**
     * Vrátí průměrnou maximální rychlost aut od značky {@code brand}.
     *
     * @param cars vstupní seznam aut
     * @param brand název značky auta (například Škoda)
     * @return průměrná maximální rychlost aut dané značky
     */
    private static double getAverageTopSpeed(List<Car> cars, String brand) {
        return 0;
    }

    /**
     * Načte všechny řádky souboru kromě prvního a vrátí je jako seznam aut.
     *
     * @param path cesta k souboru
     * @return seznam aut
     */
    public static List<Car> loadCars(String path){
        return null;
    }

    /**
     * Vrátí seznam aut dané značky, jejichž výkon je větší než zadaná mez.
     *
     * @param cars vstupní seznam aut
     * @param brand název značky auta
     * @param threshold minimální výkon, který musí auto překročit
     * @return seznam aut dané značky s výkonem větším než {@code threshold}
     */
    public static List<Car> getCarsAbovePowerByBrand(List<Car> cars, String brand, int threshold) {

    }

    /**
     * Vrátí procentuální zastoupení aut dané značky v seznamu.
     * Výsledek je vrácen v procentech, například {@code 24.5}, nikoli {@code 0.245}.
     *
     * @param cars vstupní seznam aut
     * @param brand název značky auta
     * @return procentuální zastoupení aut dané značky v seznamu
     */
    public static double getBrandPercentage(List<Car> cars, String brand) {

    }
}

class Car{
    // TODO: Implement
}