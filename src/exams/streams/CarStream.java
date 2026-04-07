package exams.streams;

import java.util.List;

/**
 * 1. Načtěte soubor data/cars.csv
 *    - přeskočte 1. řádek souboru
 *    - všechny záznamy uložte do List<Car> cars
 * 2. Implementujte metodu, která vrátí průměrnou cenu za Porsche
 * 3. Implementujte metodu, která vrátí všechny auta pod cenu "price"
 * 4. Implementujte metodu, která vypíše základní statistiky o autech na základě "performance"
 */
public class CarStream {
    public static void main(String[] args) {
        String path = "data/cars.csv";
        List<Car> cars = loadCars(path);

        double avgPrice = getAveragePrice(cars, "Porsche");
        System.out.println(avgPrice); // zhruba 14 000

        List<Car> budgetCars = filterByPrice(cars, 8_000);
        budgetCars.forEach(System.out::println); // 8 aut

        printStatistics(cars);
    }


    /**
     * Vypíše základní statistiky o autech s pomocí summaryStatistics dle hodnoty "performance"
     * @param cars seznam aut
     */
    private static void printStatistics(List<Car> cars) {

    }

    /**
     * Vráti nový vyfiltrovaný seznam aut
     * @param cars Seznam aut
     * @param price Maximální cena (včetně) kterou mohou mít auta v novém seznamu
     * @return Nový redukovaný seznam
     */
    private static List<Car> filterByPrice(List<Car> cars, int price) {
        return null;
    }

    /**
     * Vrátí nový seznam aut od jedné firmy
     * @param cars
     * @param brand Jméno firmy, která auto vyrábí (například Škoda)
     * @return
     */
    private static double getAveragePrice(List<Car> cars, String brand) {
        return 0;
    }

    /**
     * Načte všechny řádky (až na 1.) a vrátí jako seznam aut
     * @param path cesta k souboru
     * @return Seznam aut
     */
    public static List<Car> loadCars(String path){
        return null;
    }
}

class Car{
    private String companyName;
    private String carName;
    private int horsePower;
    private int totalSpeed;
    private double performance;
    private int priceUsd;

    public Car(String companyName, String carName, int horsePower, int totalSpeed, double performance, int priceUsd) {
        this.companyName = companyName;
        this.carName = carName;
        this.horsePower = horsePower;
        this.totalSpeed = totalSpeed;
        this.performance = performance;
        this.priceUsd = priceUsd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getTotalSpeed() {
        return totalSpeed;
    }

    public void setTotalSpeed(int totalSpeed) {
        this.totalSpeed = totalSpeed;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public int getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(int priceUsd) {
        this.priceUsd = priceUsd;
    }

    @Override
    public String toString() {
        return String.format("Company: %s | Name: %s | HorsePower: %d | Total speed: %dkm/h | 0 - 100 km/h: %.2f | Price: $%d", companyName, carName, horsePower, totalSpeed, performance, priceUsd);
    }
}