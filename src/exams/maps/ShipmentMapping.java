package exams.maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ShipmentMapping {

    static Shipment getShipmentFromRow(String line){
        String[] tokens = line.split(";");
        return new Shipment(
            Integer.parseInt(tokens[3]),
            Double.parseDouble(tokens[7]),
            Double.parseDouble(tokens[4]),
            LocalDate.parse(tokens[8], DateTimeFormatter.ofPattern("dd.MM.yyyy")),
            Integer.parseInt(tokens[6]),
            Double.parseDouble(tokens[5])
        );
    }

    public static String getWeightCategory(Shipment shipment){
        if (shipment.getWeight() < 200) return "Light Cargo";
        if (shipment.getWeight() <= 1000) return "Medium Cargo";
        return "Heavy Cargo";
    }

    public static String getDaysCategory(Shipment shipment){
        if (shipment.getDays() <= 2) return "Early delivery";
        if (shipment.getDays() <= 5) return "Normal delivery";
        return "Slow Delivery";
    }

    public static String getSeason(Shipment shipment){
        int yearsBefore = 2026 - shipment.getDate().getYear();
        if (yearsBefore <= 2) return "Current";
        if (yearsBefore <= 5) return "Recent";
        return "Historic";
    }

    public static String getFuelEfficiency(Shipment shipment){
        double efficiency = shipment.getFuel() / shipment.getDistance();

        if (efficiency <= 0.8) return "Efficient";
        if (efficiency <= 3.5) return "Normal";
        return "High Consumption";
    }

    public static String getPricePerKm(Shipment shipment){
        double efficiency = shipment.getPrice() / shipment.getDistance();

        if (efficiency <= 20) return "Cheap route";
        if (efficiency <= 50) return "Normal route";
        return "Expensive Route";
    }
    public static void main(String[] args) throws IOException {
        //nacist data:
        List<Shipment> shipments = Files.lines(Path.of("data/cargo_transport.csv"))
                .skip(1)
                .map(ShipmentMapping::getShipmentFromRow)
                .toList();

        //jednotlive ukoly:
        Map<String, List<Shipment>> byWeight = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::getWeightCategory));

        Map<String, List<Shipment>> byDays = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::getDaysCategory));

        Map<String, Double> bySeasonAvgWeight = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::getSeason, Collectors.averagingInt(Shipment::getWeight)));

        Map<String, Long> bySeasonAmount = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::getSeason, Collectors.counting()));

        Map<String, Long> byFuelEfficiency = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::getFuelEfficiency, Collectors.counting()));

        Map<String, Double> byPricePerKmAvg = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::getPricePerKm, Collectors.averagingDouble(Shipment::getPrice)));

        byWeight.forEach((key, value) -> System.out.println(key + ": " + value.size()));
        System.out.println("---------");
        byDays.forEach((key, value) -> System.out.println(key + ": " + value.size()));
        System.out.println("---------");
        bySeasonAvgWeight.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("---------");
        bySeasonAmount.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("----------");
        byFuelEfficiency.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("----------");
        byPricePerKmAvg.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
class Shipment {
    int weight;
    double fuel;
    double distance;
    LocalDate date;
    int days;
    double price;

    public Shipment(int weight, double fuel, double distance, LocalDate date, int days, double price) {
        this.weight = weight;
        this.fuel = fuel;
        this.distance = distance;
        this.date = date;
        this.days = days;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public double getFuel() {
        return fuel;
    }

    public double getDistance() {
        return distance;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDays() {
        return days;
    }

    public double getPrice() {
        return price;
    }
}
