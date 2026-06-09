package streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightMapping {

    public static Flight parseLine(String[] tokens) {
        if (tokens.length != 7) {
            throw new IllegalArgumentException("Nesprávný počet řetězců - očekáváno 7");
        }

        //

        return new Flight(tokens[0],
                tokens[1],
                tokens[2],
                Integer.parseInt(tokens[3]),
                Integer.parseInt(tokens[4]),
                Integer.parseInt(tokens[5]),
                LocalDate.parse(tokens[6], DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    public static Integer compensationByCategory(Flight flight) {
        int perPassanger = 0;

        if (flight.getDelayMinutes() == 0) return 0;
        else if (flight.getDelayMinutes() <= 15) perPassanger = 2;
        else if (flight.getDelayMinutes() <= 60) perPassanger = 10;
        else perPassanger = 100;

        return perPassanger * flight.getPassengers();
    }

    public static String delayCategory(Flight flight) {
        if (flight.getDelayMinutes() == 0) return "OK";
        if (flight.getDelayMinutes() <= 15) return "Small";
        if (flight.getDelayMinutes() <= 60) return "Big";
        return "Critical";
    }

    public static String getQ(Flight flight) {
        int monthValue = flight.getDate().getMonth().getValue();

        if (monthValue <= 3) return "Q1";
        if (monthValue <= 6) return "Q2";
        if (monthValue <= 9) return "Q3";
        return "Q4";
    }

    public static void main(String[] args) {
        String dataPath = "data/airport_traffic.csv";
        try {
            List<Flight> flights = Files.lines(Path.of(dataPath))
                    .skip(1)
                    .map(line -> line.trim().split(";"))
                    .map(FlightMapping::parseLine)
                    .toList();

            // Delay
            // == 0 -> OK
            // <= 15 -> Small
            // <= 60 -> Big
            // > 60 -> Critical
            Map<String, List<Flight>> byDelay = flights.stream()
                    .collect(Collectors.groupingBy(
                            FlightMapping::delayCategory
                    ));

            byDelay.forEach((category, fs) -> {
                System.out.println("------------------");
                System.out.println(category);
                System.out.println("------------------");
                fs.forEach(System.out::println);
            });

            Map<String, Long> byDelayCount = flights.stream()
                    .collect(Collectors.groupingBy(
                            FlightMapping::delayCategory,
                            Collectors.counting()
                    ));


            // procentuálně
            byDelayCount.forEach((category, count) -> {
                System.out.println(category + " --> " + (100.0 * count / flights.size()) + " %");
            });

            // Kompenzovat pasazeri

            Map<String, Integer> compensationMap = flights.stream()
                    .collect(Collectors.groupingBy(
                            FlightMapping::delayCategory,
                            Collectors.summingInt(FlightMapping::compensationByCategory)
                    ));

            compensationMap.forEach((category, toPay) -> System.out.println(category + " ---> " + toPay + "$."));

            // Podle čtvrtletí
            Map<String, Long> byQ = flights.stream()
                    .collect(Collectors.groupingBy(
                            FlightMapping::getQ     // Q1, Q2, Q3, Q4
                            , Collectors.counting() // Počet letů
                    ));

            byQ.forEach((q, c) -> System.out.printf("%s --> %d\n", q, c));

        } catch (IOException e) {
            System.err.println("Something wen wrong with file" + e);
        }
    }
}

class Flight {
    String planeId;
    String airport;
    String flightType;
    int passengers;
    int delayMinutes;
    int flightDuration;
    LocalDate date;

    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(int delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Flight(String planeId, String airport, String flightType, int passengers, int delayMinutes, int flightDuration, LocalDate date) {
        this.planeId = planeId;
        this.airport = airport;
        this.flightType = flightType;
        this.passengers = passengers;
        this.delayMinutes = delayMinutes;
        this.flightDuration = flightDuration;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "planeId='" + planeId + '\'' +
                ", airport='" + airport + '\'' +
                ", flightType='" + flightType + '\'' +
                ", passengers=" + passengers +
                ", flightDuration=" + flightDuration +
                ", date=" + date +
                '}';
    }
}